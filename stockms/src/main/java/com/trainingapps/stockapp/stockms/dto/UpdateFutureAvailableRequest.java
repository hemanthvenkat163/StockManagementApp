package com.trainingapps.stockapp.stockms.dto;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;


@Validated
public class UpdateFutureAvailableRequest {

	@NotNull
	private String availableDate;
	@NotNull
	private String stockName;

	public String getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	
}
