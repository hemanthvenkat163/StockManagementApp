package com.trainingapps.stockapp.deliveryms.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

/**
 * Add delivery Request dto
 * 
 * @author saika
 */
@Validated
public class AddDeliveryRequest {
	@NotNull
	@Min(1)
	private Long orderId;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
