package com.trainingapps.stockapp.orderms.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.orderms.dao.ICreatedOrderRepository;
import com.trainingapps.stockapp.orderms.dao.IOrderStockItemRepository;
import com.trainingapps.stockapp.orderms.dto.ItemOrderRequest;
import com.trainingapps.stockapp.orderms.dto.OrderDetails;
import com.trainingapps.stockapp.orderms.dto.ShortOrderDetails;
import com.trainingapps.stockapp.orderms.entities.CreatedOrder;
import com.trainingapps.stockapp.orderms.entities.OrderStockItem;
import com.trainingapps.stockapp.orderms.exceptions.InvalidPeriodException;
import com.trainingapps.stockapp.orderms.exceptions.OrderNotFoundException;
import com.trainingapps.stockapp.orderms.util.DateConverter;
import com.trainingapps.stockapp.orderms.util.OrderUtil;

/**
 * Service implementation of CreatedOrder
 *
 * @author sutukuru
 */
@Component
public class CreatedOrderServiceImpl implements ICreatedOrderService {

    @Autowired
    private IOrderStockItemRepository itemRepo;

    @Autowired
    private ICreatedOrderRepository createOrderRepo;

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private DateConverter dateConverter;

    /**
     * Adding Order details
     */
    @Override
    public OrderDetails createOrder(Set<ItemOrderRequest> requestedItems) {
        CreatedOrder createdOrder = newCreatedOrder();
        Map<Long, Double> prices = orderUtil.fetchPricesFromStock(requestedItems);
        LocalDate orderDate = currentDate();
        createdOrder.setOrderDate(orderDate);
        List<OrderStockItem> stockItems = toOrderStockItems(requestedItems, prices, createdOrder);
        createdOrder.setItems(stockItems);
        double totalOrderPrice = calculateTotalOrderPrice(stockItems);
        createdOrder.setOrderPrice(totalOrderPrice);
        createdOrder = createOrderRepo.save(createdOrder);
        OrderDetails details = orderUtil.toDetails(createdOrder);
        return details;
    }

    /**
     * calculates total order prices from collection of items
     *
     * @param stockItems
     * @return total order price
     */
    public double calculateTotalOrderPrice(Collection<OrderStockItem> stockItems) {
        Optional<Double> totalOrderPrice = stockItems.stream()
                .map(item -> item.getProvidedUnits() * item.getPricePerUnit())
                .reduce((totalItem1, totalItem2) -> totalItem1 + totalItem2);
        if (!totalOrderPrice.isPresent()) {
            return 0;
        }
        return totalOrderPrice.get();
    }

    public List<OrderStockItem> toOrderStockItems(Set<ItemOrderRequest> requestedItems, Map<Long, Double> prices,
                                                  CreatedOrder createdOrder) {
        List<OrderStockItem> desired = requestedItems.stream().map(item -> {
            OrderStockItem orderItem = new OrderStockItem();
            orderItem.setStockId(item.getStockId());
            double stockPrice = prices.get(item.getStockId());
            orderItem.setPricePerUnit(stockPrice);
            orderItem.setRequestedUnits(item.getUnits());
            orderItem.setOrder(createdOrder);
            int available = orderUtil.fetchAvailableItems(item.getStockId());
            int providedUnits;
            if (available < item.getUnits()) {
                providedUnits = available;
                
            } else {
                providedUnits = item.getUnits();
            }

            orderItem.setProvidedUnits(providedUnits);
            return orderItem;
        }).collect(Collectors.toList());
        return desired;
    }

    /**
     * Finding the Created Order by order id
     *
     * @param orderId
     * @return
     */
    public CreatedOrder findByOrderId(Long orderId) {
        Optional<CreatedOrder> optional = createOrderRepo.findById(orderId);
        if (!optional.isPresent()) {
            throw new OrderNotFoundException("Order not found : " + orderId);
        }
        return optional.get();
    }

    /**
     * Finding Order Details based on order id
     */
    @Override
    public OrderDetails findOrderDetailsByOrderId(Long orderId) {
        CreatedOrder order = findByOrderId(orderId);
        OrderDetails desired = orderUtil.toDetails(order);
        return desired;
    }

    /**
     * List of Order details in between start-date and end-date
     */
    @Override
    public List<OrderDetails> findAllOrders(String start, String end) {
        validatePeriod(start, end);
        LocalDate startDate = dateConverter.toDate(start);
        LocalDate endDate = dateConverter.toDate(end);
        List<CreatedOrder> createdOrders = createOrderRepo.findInPeriod(startDate, endDate);
        List<OrderDetails> desired = orderUtil.toOrderDetailsList(createdOrders);
        return desired;
    }
    
    public CreatedOrder newCreatedOrder() {
        return new CreatedOrder();
    }

    public LocalDate currentDate() {
        return LocalDate.now();
    }

    /**
     * Finding the short order details
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<Long> findAllOrderIds(String start, String end) {
        validatePeriod(start, end);
        LocalDate startDate = dateConverter.toDate(start);
        LocalDate endDate = dateConverter.toDate(end);
        List<CreatedOrder> createdOrders = createOrderRepo.findInPeriod(startDate, endDate);
        List<ShortOrderDetails> desired = orderUtil.toShortOrderDetailsList(createdOrders); 
        return desired.stream().map( n -> n.getOrderId()).collect(Collectors.toSet());
    }
    
    @Override
    public ShortOrderDetails findShortOrderDetailsByOrderId(Long orderId) {
        CreatedOrder order = findByOrderId(orderId);
        ShortOrderDetails desired = orderUtil.toShortOrderDetails(order);
        return desired;
    }
    
    
    /**
     * Validating start-date and end-date
     *
     * @param startDate
     * @param endDate
     */
    void validatePeriod(String startDateText, String endDateText) {
        LocalDate startDate = dateConverter.toDate(startDateText);
        LocalDate endDate = dateConverter.toDate(endDateText);
        if (endDate.isBefore(startDate)) {
            throw new InvalidPeriodException("End date is less than Start Date");
        }
    }
}
