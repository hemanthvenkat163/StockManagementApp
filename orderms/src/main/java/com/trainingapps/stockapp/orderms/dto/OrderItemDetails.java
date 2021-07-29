package com.trainingapps.stockapp.orderms.dto;

public class OrderItemDetails {

	private long stockId;

	private int providedUnits;

	private int unAvailableUnits;

	private double totalProvidedPrice;

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}

	public int getProvidedUnits() {
		return providedUnits;
	}

	public void setProvidedUnits(int providedUnits) {
		this.providedUnits = providedUnits;
	}

	public int getUnAvailableUnits() {
		return unAvailableUnits;
	}

	public void setUnAvailableUnits(int unAvailableUnits) {
		this.unAvailableUnits = unAvailableUnits;
	}

	public double getTotalProvidedPrice() {
		return totalProvidedPrice;
	}

	public void setTotalProvidedPrice(double totalProvidedPrice) {
		this.totalProvidedPrice = totalProvidedPrice;
	}

	@Override
	public String toString() {
		return "OrderItemDetails [stockId=" + stockId + ", providedUnits=" + providedUnits + ", unAvailableUnits="
				+ unAvailableUnits + ", totalProvidedPrice=" + totalProvidedPrice + "]";
	}

}
