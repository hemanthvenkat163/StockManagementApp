package com.trainingapps.stockapp.orderms.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class ItemOrderRequest {

	@NotNull
	private long stockId;
	
	@NotNull
	private int units;

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stockId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrderRequest other = (ItemOrderRequest) obj;
		return stockId == other.stockId;
	}

}
