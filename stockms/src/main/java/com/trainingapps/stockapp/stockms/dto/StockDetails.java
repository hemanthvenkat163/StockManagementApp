package com.trainingapps.stockapp.stockms.dto;

public class StockDetails {

	

	private long id;
	private String stockname;
	private int units;
	private double price;

	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

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
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stockname == null) ? 0 : stockname.hashCode());
		result = prime * result + units;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDetails other = (StockDetails) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (stockname == null) {
			if (other.stockname != null)
				return false;
		} else if (!stockname.equals(other.stockname))
			return false;
		if (units != other.units)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "StockDetails [id=" + id + ", stockname=" + stockname + ", units=" + units + ", price=" + price + "]";
	}

}
