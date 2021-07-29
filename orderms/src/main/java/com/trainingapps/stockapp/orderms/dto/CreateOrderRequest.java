package com.trainingapps.stockapp.orderms.dto;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class CreateOrderRequest {

	@NotNull
	private Set<ItemOrderRequest> items;

	public Set<ItemOrderRequest> getItems() {
		return items;
	}

	public void setItems(Set<ItemOrderRequest> items) {
		this.items = items;
	}

}
