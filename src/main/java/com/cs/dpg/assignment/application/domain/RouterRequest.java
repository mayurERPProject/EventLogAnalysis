package com.cs.dpg.assignment.application.domain;

import java.util.List;

public class RouterRequest {

	private List<EventDataDomain> itemList;

	public List<EventDataDomain> getItemList() {
		return itemList;
	}

	public void setItemList(List<EventDataDomain> itemList) {
		this.itemList = itemList;
	}

}
