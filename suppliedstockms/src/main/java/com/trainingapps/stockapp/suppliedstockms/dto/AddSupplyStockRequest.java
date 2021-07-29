package com.trainingapps.stockapp.suppliedstockms.dto;

import javax.validation.constraints.Min;

public class AddSupplyStockRequest {
	@Min(1)
	private Long stockId;
	@Min(1)
	private Long supplierId;
	@Min(1)
	private Double suppliedCost;
	@Min(1)
	private int units;
	private String suppliedDate;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Double getSuppliedCost() {
		return suppliedCost;
	}

	public void setSuppliedCost(Double suppliedCost) {
		this.suppliedCost = suppliedCost;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getSuppliedDate() {
		return suppliedDate;
	}

	public void setSuppliedDate(String suppliedDate) {
		this.suppliedDate = suppliedDate;
	}

}
