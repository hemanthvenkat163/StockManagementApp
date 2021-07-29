package com.trainingapps.stockapp.stockms.dto;

/**
 * 
 * Class for FutureAvailableStockDetails
 * @author harii
 *
 */
public class FutureAvailableStockDetails {

	private Long id;
	private String availableDate;
	private String stockName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}

	@Override
	public String toString() {
		return "FutureAvailableStockDetails [id=" + id + ", availableDate=" + availableDate + ", stockName=" + stockName
				+ "]";
	}

}
