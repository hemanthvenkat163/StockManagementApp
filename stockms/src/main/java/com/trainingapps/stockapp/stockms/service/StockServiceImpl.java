package com.trainingapps.stockapp.stockms.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.trainingapps.stockapp.stockms.dao.IStockRepository;
import com.trainingapps.stockapp.stockms.dto.CreateStockRequest;
import com.trainingapps.stockapp.stockms.dto.StockDetails;
import com.trainingapps.stockapp.stockms.dto.UpdateQuantity;
import com.trainingapps.stockapp.stockms.entities.Stock;
import com.trainingapps.stockapp.stockms.exceptions.InvalidStockId;
import com.trainingapps.stockapp.stockms.exceptions.QuantityOutOfBound;
import com.trainingapps.stockapp.stockms.exceptions.StockNotFoundException;
import com.trainingapps.stockapp.stockms.util.StockUtil;

/**
 * 
 * Service Implementation for stockms
 * 
 * @author harii
 *
 */
@Transactional
@Service
public class StockServiceImpl implements IStockService {
	
	@Autowired
	private IStockRepository stockrepo;
	
	@Autowired
	private StockUtil stockUtil;
	
	public StockDetails add(CreateStockRequest request)
	{
		Stock stock = newStock();
		stock.setStockName(request.getStockname());
		stock.setUnits(request.getUnits());
		stock.setPrice(request.getPrice());
		stock = stockrepo.save(stock);
		return stockUtil.toDetails(stock);
	}
	
	public Stock newStock()
	{
		return new Stock();
	}

	@Override
	public StockDetails increaseQuantity(UpdateQuantity updateRequest) 
	{
		String stockName = updateRequest.getStockName();
		validateStockExistByName(stockName);
		Stock stock = stockrepo.findStockByStockName(updateRequest.getStockName());
		int unit = updateRequest.getUnits();
		stock.setUnits(stock.getUnits() + unit);
		stock = stockrepo.save(stock);
		return stockUtil.toDetails(stock);
		
	}

	@Override
	public StockDetails decreaseQuantity(UpdateQuantity updateRequest) 
	{		
		validateStockExistByName(updateRequest.getStockName());
		Stock stock = stockrepo.findStockByStockName(updateRequest.getStockName());
		int unit = updateRequest.getUnits();
		validateQuantityBound(unit, stock);
		stock.setUnits(stock.getUnits() - unit );
		stock = stockrepo.save(stock);
		return stockUtil.toDetails(stock);
	}
	
	

	@Override
	public StockDetails findStockDetailsById(Long stockId) {
		Stock stock = findById(stockId);
		return stockUtil.toDetails(stock);

	}
	
	
	public Stock findById(Long stockId) {
		Optional<Stock> optional = stockrepo.findById(stockId);// find the report in the database
		if (!optional.isPresent()) {
			throw new StockNotFoundException("Stock Not Found of id:" + stockId);
		}
		return optional.get();
	}
	
	void validateQuantityBound(int unit, Stock stock) 
	{
		if(unit > stock.getUnits())
		{
			throw new QuantityOutOfBound("The given Quantity is larger than the available.");

		}
	}
	
	void validateStockExistByName(String stockName) 
	{
		boolean exist = stockrepo.existsByStockName(stockName);
		if(! exist)
		{
			throw new StockNotFoundException("The Given Name is not found :"+stockName);
		}
	}
	
}