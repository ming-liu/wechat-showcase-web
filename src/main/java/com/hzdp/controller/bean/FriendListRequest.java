package com.hzdp.controller.bean;

import com.hzdp.web.api.IRequest;

public class FriendListRequest implements IRequest {

	private String id;
	private int offset;
	private int limit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
