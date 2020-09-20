package com.fabione.steam.model.generic;

public class PageInfoRequest {

	private int pageNumber;
	private int rowsByPage;
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getRowsByPage() {
		return rowsByPage;
	}
	
	public void setRowsByPage(int rowsByPage) {
		this.rowsByPage = rowsByPage;
	}
	
}
