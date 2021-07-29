package com.trainingapps.stockapp.orderms.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.trainingapps.stockapp.orderms.constants.AvailabilityStatus;

/**
 * Entity class for OrderStockInfo
 * 
 * @author sutukuru
 *
 */
@Entity
public class OrderStockItem {

	@Id
	@GeneratedValue
	private Long id;

	private Long stockId;

	private int requestedUnits;

	private int providedUnits;

	private double pricePerUnit;

	@ManyToOne
	private CreatedOrder order;

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

	public int getRequestedUnits() {
		return requestedUnits;
	}

	public void setRequestedUnits(int requestedUnits) {
		this.requestedUnits = requestedUnits;
	}

	public int getProvidedUnits() {
		return providedUnits;
	}

	public void setProvidedUnits(int providedUnits) {
		this.providedUnits = providedUnits;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public CreatedOrder getOrder() {
		return order;
	}

	public void setOrder(CreatedOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderStockItem [id=" + id + ", stockId=" + stockId + ", requestedUnits=" + requestedUnits
				+ ", providedUnits=" + providedUnits + ", pricePerUnit=" + pricePerUnit + ", order=" + order
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStockItem other = (OrderStockItem) obj;
		return Objects.equals(id, other.id);
	}

}
