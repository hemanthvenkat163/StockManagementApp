package com.trainingapps.stockapp.suppliedstockms.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trainingapps.stockapp.suppliedstockms.dto.SuppliedStockDetails;
import com.trainingapps.stockapp.suppliedstockms.entities.SuppliedStock;

/**
 * 
 * @author pavan
 * Util Class for SuppliedStockDetails Module
 */

@Component
public class SuppliedStockUtil {
	@Autowired
	private DateConverter dateConverter;

	public SuppliedStockDetails toDetails(SuppliedStock stock) {
		SuppliedStockDetails response = new SuppliedStockDetails();
		response.setId(stock.getId());
		response.setStockId(stock.getStockId());
		response.setSuppliedcost(stock.getSuppliedCost());
		response.setSupplierId(stock.getSupplierId());
		response.setUnits(stock.getUnits());
		response.setSuppliedDate(dateConverter.toText(stock.getSuppliedDate()));

		return response;

	}

	public List<SuppliedStockDetails> toDetailsList(Collection<SuppliedStock> suppliedStock) {

		return suppliedStock.stream().map(n -> toDetails(n)).collect(Collectors.toList());
	}

}