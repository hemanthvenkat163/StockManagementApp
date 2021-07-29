package com.trainingapps.stockapp.deliveryms.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

/**
 * change delivery status request dto
 *
 * @author saika
 */
@Validated
public class ChangeDeliveryStatus {
	@NotNull
	@Min(1)
	private Long orderId;
	@NotNull
	private String deliveryStatus;

	

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

}
