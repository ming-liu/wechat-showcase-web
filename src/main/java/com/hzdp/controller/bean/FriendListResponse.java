package com.hzdp.controller.bean;

import java.util.List;

import com.hzdp.web.BaseResponse;

public class FriendListResponse extends BaseResponse {

	private List<FriendResponse> friends;

	public List<FriendResponse> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendResponse> friends) {
		this.friends = friends;
	}

}
