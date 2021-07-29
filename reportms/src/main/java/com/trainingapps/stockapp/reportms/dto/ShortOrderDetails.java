package com.trainingapps.stockapp.reportms.dto;
/**
 * 
 * Response Order Dto
 * 
 * @author Hemanth Venkat
 *
 */
public class ShortOrderDetails {

	private Long orderId;
	private String orderDate;
	private Double orderPrice;


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setCreatedDate(String orderDate) {
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
		return "ShortOrderDetails orderId=" + orderId + ", createdDate=" + orderDate + ", orderPrice=" + orderPrice;
	}
	
}
