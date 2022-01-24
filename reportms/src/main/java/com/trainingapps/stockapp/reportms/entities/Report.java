package com.trainingapps.stockapp.reportms.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.trainingapps.stockapp.reportms.constants.ReportType;

/**
 * Entity class for Report
 * 
 * @author Hemanth Venkat
 *
 */

@Entity
public class Report {

	@GeneratedValue
	@Id
	private Long id;
	@Column(nullable = false)
	private LocalDate startPeriod;
	@Column(nullable = false)
	private LocalDate endPeriod;
	@Column(nullable = false)
	private LocalDate createdDate;

	@Enumerated(EnumType.STRING)
	private ReportType reportType;

	@ElementCollection
	private Set<Long> orders;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(LocalDate startPeriod) {
		this.startPeriod = startPeriod;
	}

	public LocalDate getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(LocalDate endPeriod) {
		this.endPeriod = endPeriod;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Set<Long> getOrders() {
		return orders;
	}

	public void setOrders(Set<Long> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
