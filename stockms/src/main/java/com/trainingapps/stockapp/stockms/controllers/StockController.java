package com.trainingapps.stockapp.stockms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainingapps.stockapp.stockms.dto.AddFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.CreateStockRequest;
import com.trainingapps.stockapp.stockms.dto.FutureAvailableStockDetails;
import com.trainingapps.stockapp.stockms.dto.StockDetails;
import com.trainingapps.stockapp.stockms.dto.UpdateFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.UpdateQuantity;
import com.trainingapps.stockapp.stockms.exceptions.InvalidStockId;
import com.trainingapps.stockapp.stockms.service.FutureAvailableStockServiceImpl;
import com.trainingapps.stockapp.stockms.service.StockServiceImpl;

@RequestMapping("/stocks")
@RestController
public class StockController {
	
	@Autowired
	private StockServiceImpl service;
	
	@Autowired
	private FutureAvailableStockServiceImpl futureService;
	
	@PostMapping("/add")
	public StockDetails createStock(@RequestBody CreateStockRequest request)
	{
		return service.add(request);
	}
	
	@PutMapping("/increaseQuantity")
	public StockDetails increaseQuantity(@RequestBody UpdateQuantity quantity ) 
	{
		return service.increaseQuantity(quantity);
	}
	
	@PutMapping("/decreaseQuantity")
	public StockDetails decreaseQuantity(@RequestBody UpdateQuantity quantity) 
	{
		return service.decreaseQuantity(quantity);
	}
	
	@PostMapping("/addFutureAvailableStock")
	public FutureAvailableStockDetails add(@RequestBody AddFutureAvailableRequest request)
	{
		return futureService.add(request);
	}
	
	@PutMapping("/updateFutureAvailableStock")
	public FutureAvailableStockDetails update(@RequestBody UpdateFutureAvailableRequest request) throws InvalidStockId
	{
		return futureService.update(request);
	}
	
	@GetMapping("/bystockid/{stockid}")
	public StockDetails findStockDetailsById(@PathVariable("stockid") Long stockId)
	{
		
		StockDetails desired = service.findStockDetailsById(stockId);
		return desired;
	}
}
