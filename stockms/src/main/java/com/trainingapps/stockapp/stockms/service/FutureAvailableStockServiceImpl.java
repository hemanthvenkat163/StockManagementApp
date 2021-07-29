package com.trainingapps.stockapp.stockms.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingapps.stockapp.stockms.dao.IFutureAvailableStockRepository;
import com.trainingapps.stockapp.stockms.dao.IStockRepository;
import com.trainingapps.stockapp.stockms.dto.AddFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.FutureAvailableStockDetails;
import com.trainingapps.stockapp.stockms.dto.UpdateFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.entities.FutureAvailableStock;
import com.trainingapps.stockapp.stockms.entities.Stock;
import com.trainingapps.stockapp.stockms.exceptions.FutureAvailableStockNotFound;
import com.trainingapps.stockapp.stockms.exceptions.InvalidStockId;
import com.trainingapps.stockapp.stockms.exceptions.StockNotFoundException;
import com.trainingapps.stockapp.stockms.util.DateConverter;
import com.trainingapps.stockapp.stockms.util.StockUtil;

/**
 * 
 * Service Implementation for Future Available Stock
 * 
 * @author harii
 *
 */
@Transactional
@Service
public class FutureAvailableStockServiceImpl implements IFutureAvailableStockService {

	@Autowired
	private IFutureAvailableStockRepository futureStockRepo;
	
	@Autowired
	private StockUtil stockUtil;
	
	@Autowired
	private DateConverter dateConvert;
	
	@Autowired
	private IStockRepository stockRepo;
	
	@Override
	public FutureAvailableStockDetails add(AddFutureAvailableRequest request) {	
		validateStockExistByName(request.getStockName());
		FutureAvailableStock futureStock = newFutureStock();
		futureStock.setAvailableDate(dateConvert.toDate(request.getAvailableDate()));
		Stock stock = stockRepo.findStockByStockName(request.getStockName());
		futureStock.setStock(stock);
		futureStock = futureStockRepo.save(futureStock);
		return stockUtil.toFutureDetails(futureStock);
		
	}
	
	FutureAvailableStock newFutureStock() {
		return new FutureAvailableStock();
	}

	@Override
	public FutureAvailableStockDetails update(UpdateFutureAvailableRequest request)  {
		validateStockExistByName(request.getStockName());
		validateFutureStockExistByStock(request.getStockName());
		Stock stock = stockRepo.findStockByStockName(request.getStockName());
		FutureAvailableStock futureStock = futureStockRepo.findFutureAvailableStockByStock(stock);
		futureStock.setAvailableDate(dateConvert.toDate(request.getAvailableDate()));
		futureStockRepo.save(futureStock);
		return stockUtil.toFutureDetails(futureStock);
		
	}
	
	void validateFutureStockExistByStock(String stockName)
	{
		Stock stock = stockRepo.findStockByStockName(stockName);
		boolean exist = futureStockRepo.existsByStock(stock);
		if(!exist)
		{
			throw new FutureAvailableStockNotFound("The given name is not found.");
		}
	}
	

	void validateStockExistByName(String stockName) 
	{
		boolean exist = stockRepo.existsByStockName(stockName);
		if(! exist)
		{
			throw new StockNotFoundException("The Given Name is not found :"+stockName);
		}
	
	
}
}
