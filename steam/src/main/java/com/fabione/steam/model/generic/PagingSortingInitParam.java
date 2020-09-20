package com.fabione.steam.model.generic;

public class PagingSortingInitParam {

	private PageInfoRequest pageInfo;
	
	private OrderCriteria orderCriteria;

	public PageInfoRequest getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfoRequest pageInfo) {
		this.pageInfo = pageInfo;
	}

	public OrderCriteria getOrderCriteria() {
		return orderCriteria;
	}

	public void setOrderCriteria(OrderCriteria orderCriteria) {
		this.orderCriteria = orderCriteria;
	}
	
}
