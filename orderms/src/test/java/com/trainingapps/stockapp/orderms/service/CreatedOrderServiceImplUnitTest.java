package com.trainingapps.stockapp.orderms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainingapps.stockapp.orderms.dao.ICreatedOrderRepository;
import com.trainingapps.stockapp.orderms.dto.ItemOrderRequest;
import com.trainingapps.stockapp.orderms.dto.OrderDetails;
import com.trainingapps.stockapp.orderms.entities.CreatedOrder;
import com.trainingapps.stockapp.orderms.entities.OrderStockItem;
import com.trainingapps.stockapp.orderms.exceptions.OrderNotFoundException;
import com.trainingapps.stockapp.orderms.util.DateConverter;
import com.trainingapps.stockapp.orderms.util.OrderUtil;

@ExtendWith(MockitoExtension.class)
class CreatedOrderServiceImplUnitTest {

	@Mock
	private ICreatedOrderRepository orderRepo;

	@Mock
	private DateConverter dateConverter;
	
	@Mock
	private OrderUtil orderUtil;

	@InjectMocks
	@Spy
	private CreatedOrderServiceImpl service;

	/**
	 * Order is present
	 */
	@Test
	public void testFindById_1() {
		long orderId = 1;
		CreatedOrder createdOrder = mock(CreatedOrder.class);
		Optional<CreatedOrder> optional = Optional.of(createdOrder);
		when(orderRepo.findById(orderId)).thenReturn(optional);
		CreatedOrder result = service.findByOrderId(orderId);
		assertEquals(createdOrder, result);
		verify(orderRepo).findById(orderId);
	}

	/**
	 * if Order id is Not Found
	 */
	@Test
	public void testFindById_2() {
		long orderId = -10;
		Executable executable = () -> {
			Optional<CreatedOrder> optional = Optional.empty();
			when(orderRepo.findById(orderId)).thenReturn(optional);
			service.findByOrderId(orderId);
		};
		assertThrows(OrderNotFoundException.class, executable);
	}
	

	@Test
	public void testFindOrderDetailsById_1() {
		long id = 1;
		CreatedOrder createdOrder = new CreatedOrder();
		createdOrder.setOrderId(id);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderId(id);
		doReturn(createdOrder).when(service).findByOrderId(id);
		when(orderUtil.toDetails(createdOrder)).thenReturn(orderDetails);
		OrderDetails result = service.findOrderDetailsByOrderId(id);
		assertEquals(orderDetails, result);
		verify(service).findByOrderId(id);
		verify(orderUtil).toDetails(createdOrder);
	}

	@Test
	public void testAddOrder() {
		
		OrderDetails orderDetails = mock(OrderDetails.class);
		Set<ItemOrderRequest> itemRequests = mock(Set.class);
		CreatedOrder createdOrder = mock(CreatedOrder.class);
		Map<Long, Double> map = mock(Map.class);
		List<OrderStockItem> stockItems = mock(List.class);
		LocalDate now = LocalDate.now();
		double totalOrderPrice=1000;
		doReturn(totalOrderPrice).when(service).calculateTotalOrderPrice(stockItems);
		doReturn(createdOrder).when(service).newCreatedOrder();	
		doReturn(now).when(service).currentDate();
		doReturn(stockItems).when(service).toOrderStockItems(itemRequests, map, createdOrder);
		when(orderUtil.fetchPricesFromStock(itemRequests)).thenReturn(map);
		when(orderRepo.save(createdOrder)).thenReturn(createdOrder);
		when(orderUtil.toDetails(createdOrder)).thenReturn(orderDetails);
		OrderDetails result = service.createOrder(itemRequests);
		assertEquals(orderDetails,result);
		verify(orderRepo).save(createdOrder);
		verify(orderUtil).toDetails(createdOrder);

	}

	@Test
	public void testCalculateTotalOrderPrice_1(){
		Collection<OrderStockItem>items=new ArrayList<>();
		OrderStockItem item1=new OrderStockItem();
		item1.setRequestedUnits(3);
		item1.setProvidedUnits(2);
		item1.setPricePerUnit(5);
		OrderStockItem item2=new OrderStockItem();
		item2.setRequestedUnits(4);
		item2.setProvidedUnits(2);
		item2.setPricePerUnit(10);
		items.add(item1);
		items.add(item2);
		double result=service.calculateTotalOrderPrice(items);
	    assertEquals(30,result);
	}
	
	@Test
	public void testfindAllOrdersInPeriod_1()
	{
		List<CreatedOrder> createdOrders = new ArrayList<>();
		String startDate = "26-07-2021";
		String endDate = "28-07-2021";
		LocalDate start = dateConverter.toDate(startDate);
		LocalDate end = dateConverter.toDate(endDate);
		//doThrow(InvalidPeriodException.class).when(service).validatePeriod(start, end);
		doNothing().when(service).validatePeriod(startDate, endDate);
		when(orderRepo.findInPeriod(start,end)).thenReturn(createdOrders);
		List<OrderDetails> orderDetails = new ArrayList<>();
		when(orderUtil.toOrderDetailsList(createdOrders)).thenReturn(orderDetails);
		List<OrderDetails> result = service.findAllOrders(startDate,endDate);
		
		assertEquals(orderDetails,result);
		verify(orderRepo).findInPeriod(start,end);
		verify(orderUtil).toOrderDetailsList(createdOrders);
		
	}

}
