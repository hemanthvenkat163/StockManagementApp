package com.trainingapps.stockapp.stockms.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

@Validated
public class UpdateQuantity {

	@NotBlank
	private String stockName;
	@Min(1)
	private int units;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

}
