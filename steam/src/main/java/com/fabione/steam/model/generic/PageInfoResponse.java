package com.fabione.steam.model.generic;

public class PageInfoResponse {

	private int totalNumberOfPages;
	
	private Long totalNumberOfElements;

	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public Long getTotalNumberOfElements() {
		return totalNumberOfElements;
	}

	public void setTotalNumberOfElements(Long totalNumberOfElements) {
		this.totalNumberOfElements = totalNumberOfElements;
	}
	
}
