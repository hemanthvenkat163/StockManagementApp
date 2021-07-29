package com.trainingapps.stockapp.reportms.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

/**
 * Creating Report request class
 * 
 * @author Hemanth Venkat
 *
 */
@Validated
public class CreateReportRequest {

	@NotBlank
	private String startPeriod;
	@NotBlank
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
