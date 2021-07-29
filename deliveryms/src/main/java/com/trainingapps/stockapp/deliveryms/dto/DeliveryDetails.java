package com.trainingapps.stockapp.deliveryms.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.trainingapps.stockapp.deliveryms.constant.DeliveryStatus;

public class DeliveryDetails {
	private Long deliveryId;
	private Long orderId;
	private String deliveredDate;
	private String deliveryStatus;
	
	public Long getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	@Override
	public String toString() {
		return "DeliveryDetails [deliveryId=" + deliveryId + ", orderId=" + orderId + ", deliveredDate=" + deliveredDate
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}
	
	
}
