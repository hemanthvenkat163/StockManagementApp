package com.trainingapps.stockapp.stockms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.stockms.dto.FutureAvailableStockDetails;
import com.trainingapps.stockapp.stockms.dto.StockDetails;
import com.trainingapps.stockapp.stockms.entities.FutureAvailableStock;
import com.trainingapps.stockapp.stockms.entities.Stock;

/**
 * 
 * Util class for StockDetails and FutureAvailableStockDetails
 * 
 * @author harii
 *
 */
@Component
public class StockUtil {
	
	@Autowired
	private DateConverter dateConvert;

	public StockDetails toDetails(Stock stock)
	{
		StockDetails stockDetails = new StockDetails();
		stockDetails.setId(stock.getId());
		stockDetails.setStockname(stock.getStockName());
		stockDetails.setUnits(stock.getUnits());
		stockDetails.setPrice(stock.getPrice());
		return stockDetails;
	}
	
	
	public FutureAvailableStockDetails toFutureDetails(FutureAvailableStock futureStock)
	{
		FutureAvailableStockDetails futureStockDetails = new FutureAvailableStockDetails();
		futureStockDetails.setAvailableDate(dateConvert.toText(futureStock.getAvailableDate()));
		futureStockDetails.setId(futureStock.getId());
		futureStockDetails.setStockName(futureStock.getStock().getStockName());
		return futureStockDetails;
		
	}
	
}
