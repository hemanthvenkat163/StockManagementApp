package com.trainingapps.stockapp.deliveryms.entities;


import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.trainingapps.stockapp.deliveryms.constant.DeliveryStatus;

/**
 * Entity class for Delivery Ms
 * 
 * @author saika
 */
@Entity
public class Delivery {

	@GeneratedValue
	@Id
	private Long deliveryId;
	private Long orderId;

	private LocalDate deliveredDate;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

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

	public LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deliveryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(deliveryId, other.deliveryId);
	}

	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", orderId=" + orderId + ", deliveredDate=" + deliveredDate
				+ ", deliveryStatus=" + deliveryStatus + "]";
	}

}
