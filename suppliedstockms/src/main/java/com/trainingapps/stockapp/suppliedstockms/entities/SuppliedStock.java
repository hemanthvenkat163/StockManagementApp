package com.trainingapps.stockapp.suppliedstockms.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * @author pakadava
 *
 * Entity Class for Supplied Stock Module
 */
@Entity
public class SuppliedStock {

    @GeneratedValue
    @Id
    private Long id;
    private Long stockId;
    private Long supplierId;
    private LocalDate suppliedDate;
    private Double suppliedCost;
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
	public LocalDate getSuppliedDate() {
		return suppliedDate;
	}
	public void setSuppliedDate(LocalDate suppliedDate) {
		this.suppliedDate = suppliedDate;
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
		SuppliedStock other = (SuppliedStock) obj;
		return Objects.equals(id, other.id);
	}
    
}