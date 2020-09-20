package com.fabione.steam.model.generic;

public class OrderCriteria {
	
	private String criteriaParam;
	
	private boolean asc;

	public String getCriteriaParam() {
		return criteriaParam;
	}

	public void setCriteriaParam(String criteriaParam) {
		this.criteriaParam = criteriaParam;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean isAsc) {
		this.asc = isAsc;
	}
	
}
