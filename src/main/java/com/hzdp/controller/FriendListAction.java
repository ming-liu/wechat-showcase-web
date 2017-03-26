package com.hzdp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hzdp.controller.bean.FriendListRequest;
import com.hzdp.controller.bean.FriendListResponse;
import com.hzdp.controller.bean.FriendResponse;
import com.hzdp.web.annotation.Action;
import com.hzdp.web.api.IAction;
import com.hzdp.web.api.IResponse;

@Action(url = "friends.ac", value = "friends.ac")
public class FriendListAction implements IAction<FriendListRequest> {

	private static Map<String, List<FriendResponse>> friendsMap = new HashMap<String, List<FriendResponse>>();
	static {
		List<FriendResponse> friends = new ArrayList<FriendResponse>();
		FriendResponse request = new FriendResponse();
		request.setId("1");
		request.setName("Michael Jordan");
		request.setAge("23");
		friends.add(request);

		request = new FriendResponse();
		request.setId("2");
		request.setName("David Beckham");
		request.setAge("23");
		friends.add(request);

		request = new FriendResponse();
		request.setId("3");
		request.setName("Ziddan");
		request.setAge("24");
		friends.add(request);

		request = new FriendResponse();
		request.setId("4");
		request.setName("Torres");
		request.setAge("22");
		friends.add(request);

		request = new FriendResponse();
		request.setId("5");
		request.setName("Ronaldo");
		request.setAge("9");
		friends.add(request);

		for (FriendResponse friendRequest : friends) {
			friendsMap.put(friendRequest.getId(), new ArrayList<FriendResponse>());
		}

		for (FriendResponse friendRequest : friends) {
			String id = friendRequest.getId();
			Set<String> keySet = friendsMap.keySet();
			for (String string : keySet) {
				if (string.equals(id)) {
					continue;
				}
				friendsMap.get(string).add(friendRequest);
			}
		}
	}

	@Override
	public IResponse execute(FriendListRequest request) {
		String id = request.getId();
		List<FriendResponse> list = friendsMap.get(id);
		FriendListResponse response = new FriendListResponse();
		response.setFriends(list);
		return response;
	}

}
