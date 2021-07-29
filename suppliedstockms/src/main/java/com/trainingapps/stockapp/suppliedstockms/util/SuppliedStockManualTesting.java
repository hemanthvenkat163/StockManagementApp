package com.trainingapps.stockapp.suppliedstockms.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.suppliedstockms.dto.AddSupplyStockRequest;
import com.trainingapps.stockapp.suppliedstockms.dto.SuppliedStockDetails;
import com.trainingapps.stockapp.suppliedstockms.service.ISuppliedStockService;

/**
 * 
 * @author pavan
 * Manual Testing class for SuppliedStockModule
 *
 */

@Component
public class SuppliedStockManualTesting {
	/*
	 * private static final Logger Log =
	 * LoggerFactory.getLogger(SuppliedStockManualTesting.class);
	 * 
	 * @Autowired private ISuppliedStockService service;
	 * 
	 * @PostConstruct public void runTest() { addStock();
	 * suppliedStockDetailsById(1000L); }
	 * 
	 * private void addStock() { AddSupplyStockRequest request = new
	 * AddSupplyStockRequest(); request.setStockId(100L);
	 * request.setSuppliedCost(50000D); request.setSuppliedDate("24-07-2021");
	 * request.setSupplierId(1000L); request.setUnits(200); SuppliedStockDetails
	 * response = service.add(request); Log.info(response.toString());
	 * 
	 * 
	 * 
	 * }
	 * 
	 * private void suppliedStockDetailsById(long supplierId) {
	 * List<SuppliedStockDetails> detailsById =
	 * service.findSuppliedStockDetailsBySupplierId(1000L); for
	 * (SuppliedStockDetails details : detailsById) { Log.info(details.toString());
	 * } }
	 */
}
