package com.trainingapps.stockapp.orderms.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity class for CreatedOrder
 * 
 * @author sutukuru
 *
 */
@Entity
public class CreatedOrder {

	@Id
	@GeneratedValue
	private Long orderId;
	
	@Column(nullable = false)
	private LocalDate orderDate;

	@Column(nullable = false)
	private Double orderPrice;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderStockItem> items;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public List<OrderStockItem> getItems() {
		return items;
	}

	public void setItems(List<OrderStockItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CreatedOrder [orderId=" + orderId + ", orderDate=" + orderDate + ", orderPrice=" + orderPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreatedOrder other = (CreatedOrder) obj;
		return Objects.equals(orderId, other.orderId);
	}

}
