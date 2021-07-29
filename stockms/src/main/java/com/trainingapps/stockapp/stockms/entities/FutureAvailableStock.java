package com.trainingapps.stockapp.stockms.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * Entity class for Future Available Stock
 * 
 * @author harii
 *
 */
@Table(name = "futurestock")
@Entity
public class FutureAvailableStock {

	@GeneratedValue
	@Id
	private long id;
	
	private LocalDate availableDate;
	@OneToOne
	private Stock stock;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		FutureAvailableStock other = (FutureAvailableStock) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
