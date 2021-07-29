package com.trainingapps.stockapp.deliveryms.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.trainingapps.stockapp.deliveryms.dto.AddDeliveryRequest;
import com.trainingapps.stockapp.deliveryms.dto.ChangeDeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dto.DeliveryDetails;

@Validated
public interface IDeliveryService {
	DeliveryDetails add(@NotNull @Valid AddDeliveryRequest request);

	DeliveryDetails findByOrderId(@NotNull @Min(1) Long orderId);
	
	List<DeliveryDetails> findAllDetailsByStatus(@NotNull String deliveryStatus);
	
	DeliveryDetails delivered(@Min(1) long orderId);

	DeliveryDetails dispatched(@Min(1) long orderId);
	
}
