package com.trainingapps.stockapp.orderms.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trainingapps.stockapp.orderms.dto.ItemOrderRequest;
import com.trainingapps.stockapp.orderms.dto.OrderDetails;
import com.trainingapps.stockapp.orderms.dto.ShortOrderDetails;
import com.trainingapps.stockapp.orderms.service.ICreatedOrderService;
import com.trainingapps.stockapp.orderms.util.DateConverter;

@RequestMapping("/orderms")
@RestController
public class OrdermsController {

	@Autowired
	private ICreatedOrderService service;
	
	@Autowired
	private DateConverter dateConverter;
	

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public OrderDetails addOrder(@RequestBody Set<ItemOrderRequest> requestedItems) {
		OrderDetails response = service.createOrder(requestedItems);
		return response;
	}

	/**
	 * Fetching order details by orderId
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/byid/{orderId}")
	public OrderDetails fetchOrderById(@PathVariable("orderId") Long id) {
		OrderDetails response = service.findOrderDetailsByOrderId(id);
		return response;
	}
	
	
	@GetMapping("/all/orders/{startDate}/{endDate}")
    public List<OrderDetails> fetchAll(@PathVariable String startDate,@PathVariable String endDate) {
        List<OrderDetails> response = service.findAllOrders(startDate, endDate);
        return response;
    }
	
	@GetMapping("/byorderid/{orderId}")
	public ShortOrderDetails fetchShortOrderById(@PathVariable("orderId") Long id) {
		ShortOrderDetails response = service.findShortOrderDetailsByOrderId(id);
		return response;
	}
	
	@GetMapping("/all/orderids/{startDate}/{endDate}")
    public Set<Long> fetchAllOrders(@PathVariable String startDate,@PathVariable String endDate) {
        Set<Long> response = service.findAllOrderIds(startDate, endDate);
        return response;
    }
}
