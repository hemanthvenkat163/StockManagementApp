package com.trainingapps.stockapp.reportms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

/**
 * Creating Report request class
 * 
 * @author Hemanth Venkat
 *
 */

@Validated
public class CreateReportRequest {

	@NotNull(message="please provide some valid date, dont provide null values")
	private String startPeriod;
	@NotNull(message="please provide some valid date, dont provide null values")
	private String endPeriod;

	public String getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	public String getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(String endPeriod) {
		this.endPeriod = endPeriod;
	}

}
