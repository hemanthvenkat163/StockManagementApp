package com.trainingapps.stockapp.suppliedstockms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainingapps.stockapp.suppliedstockms.dao.ISuppliedStockRepository;
import com.trainingapps.stockapp.suppliedstockms.dto.AddSupplyStockRequest;
import com.trainingapps.stockapp.suppliedstockms.dto.SuppliedStockDetails;
import com.trainingapps.stockapp.suppliedstockms.entities.SuppliedStock;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidSupplierIdException;
import com.trainingapps.stockapp.suppliedstockms.util.DateConverter;
import com.trainingapps.stockapp.suppliedstockms.util.SuppliedStockUtil;

/**
 * Testing Class for suppliedStockMs
 * 
 * @author pavan
 *
 */

@ExtendWith(MockitoExtension.class)
class SuppliedStockServiceImplTest {

	@Mock
	private DateConverter dateConverter;

	@Mock
	ISuppliedStockRepository suppliedStockRepo;

	@Mock
	private SuppliedStockUtil suppliedStockUtil;

	@InjectMocks
	@Spy
	SuppliedStockServiceImpl service;

	/**
	 * Scenario : Adding SuppliedStockDetails Expectation:SuppliedStockDetails has
	 * to be added
	 */

	@Test
	void testAdd_1() {

		AddSupplyStockRequest request = new AddSupplyStockRequest();

		request.setStockId(100L);
		request.setSuppliedCost(50000D);
		request.setSupplierId(1000L);
		request.setUnits(200);

		DateConverter converter = new DateConverter();

		SuppliedStock suppliedStock = mock(SuppliedStock.class);
		LocalDate suppliedDate = LocalDate.of(2021, 07, 13);
		String supplied = converter.toText(suppliedDate);
		request.setSuppliedDate(supplied);
		when(dateConverter.toDate(supplied)).thenReturn(suppliedDate);
		doReturn(suppliedStock).when(service).newSuppliedStock();
		when(suppliedStockRepo.save(suppliedStock)).thenReturn(suppliedStock);
		SuppliedStockDetails details = mock(SuppliedStockDetails.class);
		when(suppliedStockUtil.toDetails(suppliedStock)).thenReturn(details);
		SuppliedStockDetails result = service.add(request);
		assertEquals(details, result);
	}

	/**
	 * Scenario : Finding SuppliedStocks By a SuppliedStockId
	 * Expectation:SuppliedStockDetails of id 1
	 */
	@Test
	void testFindById_1() {
		long id = 1L;
		SuppliedStock suppliedStock = mock(SuppliedStock.class);
		Optional<SuppliedStock> optional = Optional.of(suppliedStock);
		when(suppliedStockRepo.findById(id)).thenReturn(optional);
		SuppliedStockDetails details = mock(SuppliedStockDetails.class);
		when(suppliedStockUtil.toDetails(suppliedStock)).thenReturn(details);
		SuppliedStockDetails result = service.findSuppliedStockDetailsById(id);
		assertEquals(details, result);
		verify(suppliedStockRepo).findById(id);
		verify(suppliedStockUtil).toDetails(suppliedStock);

	}

	/**
	 * Scenario :Finding SuppliedStocks By a SupplierId
	 * Expectation:SuppliedStockDetails of supplierId 1000
	 */

	@Test
	void testFindSuppliedStockDetailsBySupplierId_1() {
		Long id = 1L;
		List<SuppliedStock> suppliedStocks = mock(ArrayList.class);
		List<SuppliedStockDetails> details = mock(ArrayList.class);
		when(suppliedStockRepo.findBySupplierId(id)).thenReturn(suppliedStocks);
		when(suppliedStockUtil.toDetailsList(suppliedStocks)).thenReturn(details);
		List<SuppliedStockDetails> result = service.findSuppliedStockDetailsBySupplierId(id);
		assertEquals(details, result);
		verify(suppliedStockRepo).findBySupplierId(id);

	}

	/**
	 * Scenario : SuppliedStock is not found for SupplierId Expectation:
	 * InvalidSupplierIdException has to be thrown
	 */

	@Test
	void testFindSuppliedStockDetailsBySupplierId_2() {
		Long id = 1L;
		List<SuppliedStock> suppliedStocks = mock(ArrayList.class);
		List<SuppliedStockDetails> details = null;
		when(suppliedStockRepo.findBySupplierId(id)).thenReturn(suppliedStocks);
		when(suppliedStockUtil.toDetailsList(suppliedStocks)).thenReturn(details);
		Executable executable = () -> {
			service.findSuppliedStockDetailsBySupplierId(id);
		};
		assertThrows(InvalidSupplierIdException.class, executable);
		verify(suppliedStockRepo).findBySupplierId(id);

	}

}