package com.trainingapps.stockapp.stockms.manualtesting;
/*
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.stockms.dto.AddFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.CreateStockRequest;
import com.trainingapps.stockapp.stockms.dto.UpdateFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.UpdateQuantity;
import com.trainingapps.stockapp.stockms.service.FutureAvailableStockServiceImpl;
import com.trainingapps.stockapp.stockms.service.StockServiceImpl;

@Component
public class ManualTesting {

	private static final Logger Log = LoggerFactory.getLogger(ManualTesting.class);
	
	@Autowired
	private StockServiceImpl service;
	
	@Autowired
	private FutureAvailableStockServiceImpl futureService;
	/**
	 * 
	 * Testing for add Stock method
	 * 
	 */
/*	@PostConstruct
	public void addtest()
	{
		Log.info("add method testing started...");
		CreateStockRequest stockRequest = new CreateStockRequest();
		stockRequest.setStockname("Dell");
		stockRequest.setUnits(10);
		stockRequest.setPrice(10000);
		Log.info("New Stock Request created...");
		Log.info(service.add(stockRequest).toString());
		Log.info("add method testing ended...");

	}
	*/
	/**
	 * 
	 * Testing for increaseQuantity method
	 * 
	 */
/*	@PostConstruct
	public void increaseQuantity()
	{
		Log.info("increaseQuantity method testing started...");
		UpdateQuantity updateRequest = new UpdateQuantity();
		updateRequest.setStockName("Dell");
		updateRequest.setUnits(25);
		Log.info(service.increaseQuantity(updateRequest).toString());
		Log.info("increaseQuantity testing method ended...");
	}
	
	/**
	 * 
	 * Testing for decreaseQuantity method
	 * 
	 */
/*	@PostConstruct
	public void decreaseQuantity()
	{
		Log.info("decreaseQuantity method testing started...");
		UpdateQuantity updateRequest = new UpdateQuantity();
		updateRequest.setStockName("Dell");
		updateRequest.setUnits(5);
		Log.info(service.decreaseQuantity(updateRequest).toString());
		Log.info("decreaseQuantity testing method ended...");
	}
	
	/**
	 * 
	 * Testing for add method in futureAvailableServiceImpl
	 * 
	 */
/*	@PostConstruct
	public void addFutureStock()
	{
		Log.info("add method for FutureStockAvailable testing started...");
		AddFutureAvailableRequest futureRequest = new AddFutureAvailableRequest();
		futureRequest.setAvailableDate("23-03-2000");
		futureRequest.setStockName("Dell");
		Log.info(futureService.add(futureRequest).toString());
		Log.info("add method for FutureStockAvailable testing ended...");
	}
	*/
	/**
	 * 
	 * Testing update method in FutureAvailableStockServiceIMPl
	 * 
	 */
/*	@PostConstruct
	public void updateFutureStock()
	{
		Log.info("update method for FutureStockAvailable testing started...");
		UpdateFutureAvailableRequest updateRequest = new UpdateFutureAvailableRequest();
		updateRequest.setAvailableDate("24-03-2000");
		updateRequest.setStockName("Dell");
		Log.info(futureService.update(updateRequest).toString());
		Log.info("update method for FutureStockAvailable testing ended...");
	}
}
*/