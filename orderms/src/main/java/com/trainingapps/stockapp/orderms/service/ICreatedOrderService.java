package com.trainingapps.stockapp.orderms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.trainingapps.stockapp.orderms.dto.CreateOrderRequest;
import com.trainingapps.stockapp.orderms.dto.ItemOrderRequest;
import com.trainingapps.stockapp.orderms.dto.OrderDetails;
import com.trainingapps.stockapp.orderms.dto.ShortOrderDetails;
import com.trainingapps.stockapp.orderms.entities.CreatedOrder;

@Validated
public interface ICreatedOrderService {
	
	OrderDetails createOrder(@NotNull @Valid Set<ItemOrderRequest> requestedItems);
	
	CreatedOrder findByOrderId(Long orderId);
	
	OrderDetails findOrderDetailsByOrderId(@NotNull @Min(1) Long orderId);
	
	List<OrderDetails> findAllOrders(@NotNull String startDate,@NotNull String endDate);

	Set<Long> findAllOrderIds(@NotNull String start,@NotNull String end);

	ShortOrderDetails findShortOrderDetailsByOrderId(@NotNull @Min(1) Long orderId);
	
}
