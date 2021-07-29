package com.trainingapps.stockapp.suppliedstockms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trainingapps.stockapp.suppliedstockms.dto.AddSupplyStockRequest;
import com.trainingapps.stockapp.suppliedstockms.dto.SuppliedStockDetails;
import com.trainingapps.stockapp.suppliedstockms.service.ISuppliedStockService;

@RequestMapping("/suppliedstockms")

@RestController
public class SuppliedStockController {

	@Autowired
	private ISuppliedStockService service;

	@ResponseStatus(HttpStatus.CREATED)

	@PostMapping("/AddSupplyStockRequest")
	public SuppliedStockDetails add(@RequestBody AddSupplyStockRequest requestData) {
		SuppliedStockDetails response = service.add(requestData);
		return response;

	}
	
	@GetMapping("/findBySupplietId/{supplierId}")
	public List<SuppliedStockDetails> fetchSuppliedStockDetailsBySupplierId(@PathVariable("supplierId")Long supplierId) {
		List<SuppliedStockDetails> response = service.findSuppliedStockDetailsBySupplierId(supplierId);
		return response;
	}

	@GetMapping("/findById/{id}")
	public SuppliedStockDetails fetchSuppliedStockDetailsById(@PathVariable("id") Long id) {
		SuppliedStockDetails response = service.findSuppliedStockDetailsById(id);
		return response;
	}

	@GetMapping("/findSuppliedStockDetailsBySupplierId/{supplierId}/{startDate}/{endDate}")
	public List<SuppliedStockDetails> fetchSuppliedStockDetailsBySupplierId(@PathVariable("supplierId") Long supplierId,
			@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
		List<SuppliedStockDetails> response = service.findSuppliedStocksBySupplierId(supplierId, startDate, endDate);
		return response;
	}

}