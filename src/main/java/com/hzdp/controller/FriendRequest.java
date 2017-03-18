package com.hzdp.controller;

import com.hzdp.web.api.IRequest;

public class FriendRequest implements IRequest {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
