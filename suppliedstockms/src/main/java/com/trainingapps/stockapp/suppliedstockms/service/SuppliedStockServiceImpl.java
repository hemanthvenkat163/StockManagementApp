package com.trainingapps.stockapp.suppliedstockms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingapps.stockapp.suppliedstockms.dao.ISuppliedStockRepository;
import com.trainingapps.stockapp.suppliedstockms.dto.AddSupplyStockRequest;
import com.trainingapps.stockapp.suppliedstockms.dto.SuppliedStockDetails;
import com.trainingapps.stockapp.suppliedstockms.entities.SuppliedStock;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidSupplierIdDetailsException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidSupplierIdException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.SuppliedStockNotFoundException;
import com.trainingapps.stockapp.suppliedstockms.util.DateConverter;
import com.trainingapps.stockapp.suppliedstockms.util.SuppliedStockUtil;

/**
 * 
 * @author pavan
 *
 *         Service Implementation for Supplied Stock Ms
 */
@Transactional
@Service
public class SuppliedStockServiceImpl implements ISuppliedStockService {

	@Autowired
	private ISuppliedStockRepository suppliedStockRepo;

	@Autowired
	private SuppliedStockUtil suppliedStockUtil;

	@Autowired
	private DateConverter dateConverter;

	/**
	 * Adding Supplied Stock Details
	 */
	@Override
	public SuppliedStockDetails add(AddSupplyStockRequest request) {
		SuppliedStock stock = newSuppliedStock();
		stock.setStockId(request.getStockId());
		stock.setSupplierId(request.getSupplierId());
		LocalDate date = dateConverter.toDate(request.getSuppliedDate());
		stock.setSuppliedDate(date);
		stock.setUnits(request.getUnits());
		stock.setSuppliedCost(request.getSuppliedCost());
		stock = suppliedStockRepo.save(stock);
		return suppliedStockUtil.toDetails(stock);

	}
	public SuppliedStock newSuppliedStock() {
		return new SuppliedStock();
	}

	/**
	 * Finding Supplied Stock Details By supplier Id
	 */
	@Override
	public List<SuppliedStockDetails> findSuppliedStockDetailsBySupplierId(Long supplierId) {
		List<SuppliedStock> suppliedStocks = suppliedStockRepo.findBySupplierId(supplierId);
		List<SuppliedStockDetails> desired = suppliedStockUtil.toDetailsList(suppliedStocks);
		if (desired == null) {
			throw new InvalidSupplierIdException(
					"Supplied Stock Details are not found for the given supplierId" + supplierId);

		}
		return desired;

	}

	/**
	 * Finding Supplied Stock Details Based on suppliedStockId
	 */

	@Override
	public SuppliedStockDetails findSuppliedStockDetailsById(Long suppliedStockId) {
		Optional<SuppliedStock> optional = suppliedStockRepo.findById(suppliedStockId);
		if (!optional.isPresent()) {
			throw new SuppliedStockNotFoundException(
					"Supplied Stock is Not Found for the given suppliedId" + suppliedStockId);

		}

		SuppliedStock stock = optional.get();
		return suppliedStockUtil.toDetails(stock);

	}

	/**
	 * Finding Supplied Stocks By a Supplier between Certain Dates
	 */
	@Override
	public List<SuppliedStockDetails> findSuppliedStocksBySupplierId(Long supplierId, String startDate,
			String endDate) {

		LocalDate startPeriod = dateConverter.toDate(startDate);
		LocalDate endPeriod = dateConverter.toDate(endDate);
		List<SuppliedStock> details = suppliedStockRepo.findSuppliedStocksBySupplierId(supplierId, startPeriod,
				endPeriod);
		if (details == null) {
			throw new InvalidSupplierIdDetailsException(
					"Supplied Stock Details are not found for the given supplierId in the given range. Please check the Parameters entered");

		}

		return suppliedStockUtil.toDetailsList(details);
	}

}