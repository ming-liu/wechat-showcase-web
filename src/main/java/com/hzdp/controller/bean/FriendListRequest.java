package com.hzdp.controller.bean;

import com.hzdp.web.api.IRequest;

public class FriendListRequest implements IRequest {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
