package com.trainingapps.stockapp.deliveryms;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.trainingapps.stockapp.deliveryms.constant.DeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dao.IDeliveryRepository;
import com.trainingapps.stockapp.deliveryms.dto.AddDeliveryRequest;
import com.trainingapps.stockapp.deliveryms.dto.ChangeDeliveryStatus;
import com.trainingapps.stockapp.deliveryms.dto.DeliveryDetails;
import com.trainingapps.stockapp.deliveryms.entities.Delivery;
import com.trainingapps.stockapp.deliveryms.exceptions.DeliveryNotFoundException;
import com.trainingapps.stockapp.deliveryms.service.DeliveryServiceImpl;
import com.trainingapps.stockapp.deliveryms.util.DeliveryUtil;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplUnitTest {

	@Mock
	DeliveryUtil util;

	@Mock
	IDeliveryRepository repository;

	@Spy
	@InjectMocks
	DeliveryServiceImpl service;

	/**
	 * scenario 1: adding delivery details
	 */
	@Test
	public void testAdd_1() {
		AddDeliveryRequest adr = new AddDeliveryRequest();
		adr.setOrderId(109l);
		Delivery delivery = new Delivery();
		delivery.setDeliveryId(1l);
		doReturn(delivery).when(service).newDelivery();
		assertEquals(1, delivery.getDeliveryId());
		when(repository.save(delivery)).thenReturn(delivery);
		DeliveryDetails details = mock(DeliveryDetails.class);
		when(util.toDetails(delivery)).thenReturn(details);
		DeliveryDetails result = service.add(adr);
		assertEquals(109l, delivery.getOrderId());
		assertEquals(details, result);
	}

	/**
	 * scenario 2: find delivery details by order id
	 */
	@Test
	public void testFind_1() {
		long id = 109l;
		boolean exist = true;
		Delivery delivery = mock(Delivery.class);
		DeliveryDetails details = mock(DeliveryDetails.class);
		delivery.setOrderId(id);
		when(repository.existsByOrderId(id)).thenReturn(exist);
		when(repository.findDeliveryByOrderId(id)).thenReturn(delivery);
		when(util.toDetails(delivery)).thenReturn(details);
		DeliveryDetails desired = service.findByOrderId(id);
		assertEquals(details, desired);
	}

	/**
	 * scenario 3: delivery details by order id not found
	 */
	@Test
	public void testFind_2() {
		long id = 109l;
		Executable executable = () -> {
			boolean exist = false;
			Delivery delivery = mock(Delivery.class);
			// DeliveryDetails details = mock(DeliveryDetails.class);
			delivery.setOrderId(109l);
			when(repository.existsByOrderId(id)).thenReturn(exist);
			service.findByOrderId(id);
		};
		assertThrows(DeliveryNotFoundException.class, executable);
	}

	/**
	 * scenario 4: find list delivery details by status
	 */
	@Test
	public void testFindAllStatus_1() {
		String status = "dispatched";
		DeliveryStatus deliveryStatus = DeliveryStatus.DISPATCHED;
		when(util.toDeliveryStatus(status)).thenReturn(deliveryStatus);
		List<Delivery> deliverys = new ArrayList<>();
		when(repository.findAllByDeliveryStatus(deliveryStatus)).thenReturn(deliverys);
		List<DeliveryDetails> deliveryDetails = new ArrayList<>();
		when(util.toDetailsList(deliverys)).thenReturn(deliveryDetails);
		List<DeliveryDetails> result = service.findAllDetailsByStatus(status);
		assertEquals(deliveryDetails, result);
		verify(repository).findAllByDeliveryStatus(deliveryStatus);
		verify(util).toDetailsList(deliverys);
	}

	

}
