package com.trainingapps.stockapp.orderms.dto;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class OrderDetails {

	@Min(1)
	private Long orderId;

	@NotNull
	private String orderDate;

	@Min(1)
	private Double orderPrice;
	
	@NotNull
	private Set<OrderItemDetails> items;

	public Set<OrderItemDetails> getItems() {
		return items;
	}

	public void setItems(Set<OrderItemDetails> items) {
		this.items = items;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", orderDate=" + orderDate + ", orderPrice=" + orderPrice
				+ ", items=" + items.toString() + "]";
	}
	
	

}
