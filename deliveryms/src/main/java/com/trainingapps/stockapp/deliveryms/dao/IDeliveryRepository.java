package com.trainingapps.stockapp.deliveryms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainingapps.stockapp.deliveryms.constant.DeliveryStatus;
import com.trainingapps.stockapp.deliveryms.entities.Delivery;

/**
 * Repository for Delivery Ms
 * 
 * @author saika
 */
public interface IDeliveryRepository extends JpaRepository<Delivery, Long> {
	Delivery findDeliveryByOrderId(Long orderId);
	boolean existsByOrderId(Long orderId);
	
	List<Delivery> findAllByDeliveryStatus(DeliveryStatus status);
}
