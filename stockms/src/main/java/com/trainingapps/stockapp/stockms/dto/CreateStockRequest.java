package com.trainingapps.stockapp.stockms.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * Request DTO
 * 
 * @author harii
 *
 */

public class CreateStockRequest {

	@NotNull(message="provide some stockname")
	@Length(max=200, min=1)
	private String stockname;
	@NotNull
	private int units;
	@NotNull
	private double price;


	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

}
