package com.trainingapps.stockapp.deliveryms.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.deliveryms.constant.DeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dto.DeliveryDetails;
import com.trainingapps.stockapp.deliveryms.entities.Delivery;
import com.trainingapps.stockapp.deliveryms.exceptions.InvalidStatusException;

@Component
public class DeliveryUtil {

	public DeliveryStatus toDeliveryStatus(String statusText) {
		DeliveryStatus[] deliverystatus = DeliveryStatus.values();
		for (DeliveryStatus status : deliverystatus) {
			String text = status.name();
			if (text.equalsIgnoreCase(statusText)) {
				return status;
			}
		}
		throw new InvalidStatusException(statusText + " Invalid Delivery status");
	}

	public DeliveryDetails toDetails(Delivery delivery) {
		DeliveryDetails details = new DeliveryDetails();
		details.setDeliveryId(delivery.getDeliveryId());
		DateConverter converter = new DateConverter();
		if (delivery.getDeliveredDate() == null) {
			details.setDeliveredDate(null);
		} else {
			details.setDeliveredDate(converter.toText(delivery.getDeliveredDate()));
		}
		details.setOrderId(delivery.getOrderId());
		details.setDeliveryStatus(delivery.getDeliveryStatus().name());
		return details;
	}

	public List<DeliveryDetails> toDetailsList(Collection<Delivery> detailsList) {
		return detailsList.stream().map(n -> toDetails(n)).collect(Collectors.toList());
		/*
		 * List<DeliveryDetails> desired=new ArrayList<>(); 
		 * for(Delivery delivery:detailsList) { 
		 * 	DeliveryDetails details=toDetails(delivery);
		 * 	desired.add(details); 
		 *  }
		 *  return desired;
		 */
	}
}
