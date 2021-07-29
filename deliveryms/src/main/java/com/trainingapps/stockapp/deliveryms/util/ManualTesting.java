package com.trainingapps.stockapp.deliveryms.util;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.deliveryms.dto.AddDeliveryRequest;
import com.trainingapps.stockapp.deliveryms.dto.ChangeDeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dto.DeliveryDetails;
import com.trainingapps.stockapp.deliveryms.entities.Delivery;

import com.trainingapps.stockapp.deliveryms.service.IDeliveryService;
/*
@Component
public class ManualTesting {
	private static final Logger Log= LoggerFactory.getLogger(ManualTesting.class);
	@Autowired
	private IDeliveryService ds;
	
	@PostConstruct
	public void run() {
		add();
		//change();
	}

	public void add() {
		AddDeliveryRequest request=new AddDeliveryRequest();
		request.setOrderId(103L);
		DeliveryDetails result=ds.add(request);
		Log.debug(result.toString());
	}
	
//	public void change() {
//		ChangeDeliveryStatus request=new ChangeDeliveryStatus();
//		request.setDeliveryId(1L);
//		request.setDeliveryStatus("dispatched");
//		DeliveryDetails result=ds.changeDeliveryStatus(request);
//		Log.debug(result.toString());
//	}
}*/

