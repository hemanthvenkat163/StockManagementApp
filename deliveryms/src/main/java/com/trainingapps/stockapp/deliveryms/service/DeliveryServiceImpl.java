package com.trainingapps.stockapp.deliveryms.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trainingapps.stockapp.deliveryms.constant.DeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dao.IDeliveryRepository;
import com.trainingapps.stockapp.deliveryms.dto.AddDeliveryRequest;
import com.trainingapps.stockapp.deliveryms.dto.ChangeDeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dto.DeliveryDetails;
import com.trainingapps.stockapp.deliveryms.entities.Delivery;
import com.trainingapps.stockapp.deliveryms.exceptions.DeliveryNotFoundException;
import com.trainingapps.stockapp.deliveryms.util.DeliveryUtil;





/**
 * Service Implementation for Delivery Ms
 * 
 * @author saika
 */
@Transactional
@Service
public class DeliveryServiceImpl implements IDeliveryService {

	@Autowired
	private IDeliveryRepository deliveryRepo;

	@Autowired
	private DeliveryUtil deliveryUtil;

	/**
	 * Adding delivery details
	 */
	@Override
	public DeliveryDetails add(AddDeliveryRequest request) {
		Delivery delivery = newDelivery();
		delivery.setOrderId(request.getOrderId());
		delivery.setDeliveryStatus(DeliveryStatus.NOT_DISPATCHED);
		delivery = deliveryRepo.save(delivery);
		return deliveryUtil.toDetails(delivery);
	}

	/**
	 * changing delivery status
	 */
	
	public DeliveryDetails changeDeliveryStatus(long orderId,DeliveryStatus status) {
		Delivery delivery = fetchDeliveryByOrderId(orderId);
		delivery.setDeliveryStatus(status);
		delivery = deliveryRepo.save(delivery);
		return deliveryUtil.toDetails(delivery);
	}
	
	@Override
	public DeliveryDetails dispatched(long orderId) {
		return changeDeliveryStatus(orderId,DeliveryStatus.DISPATCHED);
	}
	
	@Override
	public DeliveryDetails delivered(long orderId)
	{
		Delivery delivery = fetchDeliveryByOrderId(orderId);
		delivery.setDeliveryStatus(DeliveryStatus.DELIVERED);
		delivery.setDeliveredDate(LocalDate.now());
		delivery = deliveryRepo.save(delivery);
		return deliveryUtil.toDetails(delivery);
	}
	/**
	 * get delivery details by order id
	 */
	@Override
	public DeliveryDetails findByOrderId(Long orderId) {
		boolean exist = deliveryRepo.existsByOrderId(orderId);
		if (!exist) {
			throw new DeliveryNotFoundException("Delivery not found for order id " + orderId);
		}
		Delivery delivery = deliveryRepo.findDeliveryByOrderId(orderId);
		return deliveryUtil.toDetails(delivery);
	}

	/**
	 * find the list of delivery details by delivery status
	 */
	@Override
	public List<DeliveryDetails> findAllDetailsByStatus(@NotNull String status) {
		List<Delivery> details = deliveryRepo.findAllByDeliveryStatus(deliveryUtil.toDeliveryStatus(status));
		return deliveryUtil.toDetailsList(details);
	}

	public Delivery newDelivery() {
		return new Delivery();
	}

	public Delivery fetchDeliveryByOrderId(Long orderId) {
		boolean exist = deliveryRepo.existsByOrderId(orderId);
		if (!exist) {
			throw new DeliveryNotFoundException("Delivery not found for order id " + orderId);
		}
		Delivery delivery = deliveryRepo.findDeliveryByOrderId(orderId);
		return delivery;
	}

}
