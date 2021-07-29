package com.trainingapps.stockapp.suppliedstockms.dto;

public class SuppliedStockDetails {
	private Long id;
	private Long stockId;
	private Long supplierId;
	private String suppliedDate;
	private Double suppliedcost;
	private int units;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSuppliedDate() {
		return suppliedDate;
	}

	public void setSuppliedDate(String suppliedDate) {
		this.suppliedDate = suppliedDate;
	}

	public Double getSuppliedcost() {
		return suppliedcost;
	}

	public void setSuppliedcost(Double suppliedcost) {
		this.suppliedcost = suppliedcost;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "SuppliedStockDetails [id=" + id + ", stockId=" + stockId + ", supplierId=" + supplierId
				+ ", suppliedDate=" + suppliedDate + ", suppliedcost=" + suppliedcost + ", units=" + units + "]";
	}

}
