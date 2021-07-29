package com.trainingapps.stockapp.stockms.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.trainingapps.stockapp.stockms.dto.AddFutureAvailableRequest;
import com.trainingapps.stockapp.stockms.dto.FutureAvailableStockDetails;
import com.trainingapps.stockapp.stockms.dto.UpdateFutureAvailableRequest;

@Validated
public interface IFutureAvailableStockService {
	
	FutureAvailableStockDetails add(@NotNull @Valid AddFutureAvailableRequest request);
	FutureAvailableStockDetails update(@NotNull @Valid UpdateFutureAvailableRequest request);

}
