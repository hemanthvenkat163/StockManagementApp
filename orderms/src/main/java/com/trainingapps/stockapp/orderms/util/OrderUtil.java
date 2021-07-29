package com.trainingapps.stockapp.orderms.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.trainingapps.stockapp.orderms.dao.IOrderStockItemRepository;
import com.trainingapps.stockapp.orderms.dto.ItemOrderRequest;
import com.trainingapps.stockapp.orderms.dto.OrderDetails;
import com.trainingapps.stockapp.orderms.dto.OrderItemDetails;
import com.trainingapps.stockapp.orderms.dto.ShortOrderDetails;
import com.trainingapps.stockapp.orderms.dto.StockDetails;
import com.trainingapps.stockapp.orderms.entities.CreatedOrder;
import com.trainingapps.stockapp.orderms.entities.OrderStockItem;

@Component
public class OrderUtil {

	@Autowired
	private DateConverter dateConverter;

	@Autowired
	private IOrderStockItemRepository stockItemRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${stock.baseurl}")
	private String stockBaseUrl;

	/**
	 * In integration, number of available units will be fetched from Stock Module
	 * 
	 * @param stockId
	 * @return
	 */
	public int fetchAvailableItems(Long stockId) {
		
		StockDetails details = fetchStockDetails(stockId);
		int availableUnits = details.getUnits();
		return availableUnits;
	}

	
	public Map<Long, Double> fetchPrices(Collection<Long> stockIds) {
		System.out.println("Inside fetchPrices stockIds: "+stockIds);
		Map<Long, Double> map = new HashMap<>();
		
		for (Long stockId : stockIds) {
			StockDetails details= fetchStockDetails(stockId);
			map.put(stockId, details.getPrice());
		}
		return map;
	}

	/**
	 * Fetching prices for set of stocks
	 * 
	 * @param itemInfo
	 * @return
	 */
	public Map<Long, Double> fetchPricesFromStock(Collection<ItemOrderRequest> itemInfo) {
		Set<Long> stockIds = new HashSet<>();
		for (ItemOrderRequest item : itemInfo) {
			stockIds.add(item.getStockId());
		}
		Map<Long, Double> map = fetchPrices(stockIds);
		return map;

	}

	public OrderDetails toDetails(CreatedOrder order) {
		Collection<OrderStockItem> stockItems = stockItemRepo.findByOrder(order);
		OrderDetails desired = new OrderDetails();
		desired.setOrderId(order.getOrderId());
		desired.setOrderPrice(order.getOrderPrice());
		desired.setOrderDate(dateConverter.toText(order.getOrderDate()));
		Set<OrderItemDetails> responseItems = new HashSet<>();
		
		responseItems = stockItems.stream().map(item -> toOrderItemDetails(item)).collect(Collectors.toSet());
		
//		for (OrderStockItem stockItem : stockItems) {
//			OrderItemDetails details = toOrderItemDetails(stockItem);
//			responseItems.add(details);
//		}
		desired.setItems(responseItems);
		return desired;
	}

	public OrderItemDetails toOrderItemDetails(OrderStockItem stockItem) {
		OrderItemDetails response = new OrderItemDetails();
		response.setStockId(stockItem.getStockId());
		response.setProvidedUnits(stockItem.getProvidedUnits());
		double totalProvidedPrice = stockItem.getProvidedUnits() * stockItem.getPricePerUnit();
		response.setTotalProvidedPrice(totalProvidedPrice);
		int unavailable = stockItem.getRequestedUnits() - stockItem.getProvidedUnits();
		response.setUnAvailableUnits(unavailable);
		return response;
	}

	public List<OrderDetails> toOrderDetailsList(Collection<CreatedOrder> orders) {
//		List<OrderDetails> desired = new ArrayList<>();
//		for (CreatedOrder order : orders) {
//			OrderDetails details = toDetails(order);
//			desired.add(details);
//		}
//		return desired;
		return orders.stream().map((n) -> toDetails(n)).collect(Collectors.toList());
	}
	
	public ShortOrderDetails toShortOrderDetails(CreatedOrder order) {
		ShortOrderDetails desired = new ShortOrderDetails();
		desired.setOrderId(order.getOrderId());
		desired.setOrderPrice(order.getOrderPrice());
		desired.setOrderDate(dateConverter.toText(order.getOrderDate()));
		return desired;
	}
	
	public List<ShortOrderDetails> toShortOrderDetailsList(Collection<CreatedOrder> orders) {
		return orders.stream().map((n) -> toShortOrderDetails(n)).collect(Collectors.toList());
	}

	/**
	 * for available items
	 * @param orderId
	 * @return
	 */
	public StockDetails fetchStockDetails(Long stockId){
        String url=stockBaseUrl+"/stocks/bystockid/"+stockId;
        StockDetails details=restTemplate.getForObject(url,StockDetails.class);
        return details;
   } 
	
	
}
