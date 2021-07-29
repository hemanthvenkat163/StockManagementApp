package com.trainingapps.stockapp.orderms.ui;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.orderms.dto.ItemOrderRequest;
import com.trainingapps.stockapp.orderms.dto.OrderDetails;
import com.trainingapps.stockapp.orderms.service.ICreatedOrderService;
import com.trainingapps.stockapp.orderms.util.DateConverter;

@Component
public class ManualTesting {

	private static final Logger Log = LoggerFactory.getLogger(ManualTesting.class);

	/*@Autowired
	private ICreatedOrderService service;

	@Autowired
	private DateConverter dateConverter;

	@PostConstruct
	public void runTest() {
		create();
		orderDetailsById(1);
		LocalDate startDate = LocalDate.of(2021, 07, 24);
		LocalDate endDate = LocalDate.of(2021, 07, 26);
		fetchAllOrders(startDate, endDate);
	}

	private void fetchAllOrders(LocalDate startDate, LocalDate endDate) {
		String start = dateConverter.toText(startDate);
		String end = dateConverter.toText(endDate);
		List<OrderDetails> allOrders = service.findAllOrders(start, end);
		Log.info(allOrders.toString());

	}

	private void orderDetailsById(long orderId) {

		OrderDetails detailsById = service.findOrderDetailsByOrderId(1l);
		Log.info(detailsById.toString());

	}

	private void create() {
		Set<ItemOrderRequest> items = new HashSet<>();
		ItemOrderRequest item1 = new ItemOrderRequest();
		item1.setStockId(100);
		item1.setUnits(10);
		ItemOrderRequest item2 = new ItemOrderRequest();
		item2.setStockId(101);
		item2.setUnits(5);
		items.add(item1);
		items.add(item2);
		OrderDetails details = service.createOrder(items);
		Log.info("order1 : " + details.toString());
	}*/
}
