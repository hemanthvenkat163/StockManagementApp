package com.trainingapps.stockapp.stockms.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.trainingapps.stockapp.stockms.dto.CreateStockRequest;
import com.trainingapps.stockapp.stockms.dto.StockDetails;
import com.trainingapps.stockapp.stockms.dto.UpdateQuantity;
import com.trainingapps.stockapp.stockms.exceptions.InvalidStockId;
import com.trainingapps.stockapp.stockms.exceptions.QuantityOutOfBound;
import com.trainingapps.stockapp.stockms.exceptions.StockNotFoundException;

@Validated
public interface IStockService {

	StockDetails add(@NotNull @Valid CreateStockRequest request);
	
	StockDetails increaseQuantity(@NotNull @Valid UpdateQuantity quantity) throws InvalidStockId, StockNotFoundException;
	
	StockDetails decreaseQuantity(@NotNull @Valid UpdateQuantity quantity) throws InvalidStockId, QuantityOutOfBound, StockNotFoundException;

	/**
	 * Here finding the reportDetails by Id from repository
	 */
	StockDetails findStockDetailsById(@Min(1) Long stockId);
}
