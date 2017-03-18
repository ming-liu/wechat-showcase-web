package com.hzdp.controller;

import org.apache.log4j.Logger;

import com.hzdp.web.annotation.Action;
import com.hzdp.web.api.IAction;
import com.hzdp.web.api.IResponse;

@Action(url = "friend.ac", value = "friend.ac")
public class FriendAction implements IAction<FriendRequest> {

	private static final Logger logger = Logger.getLogger(FriendAction.class);

	@Override
	public IResponse execute(FriendRequest request) {
		String id = request.getId();
		logger.info("id=" + id);
		FriendResponse entity = new FriendResponse();
		entity.setId(1);
		entity.setName("name for" + entity.getId());
		return entity;
	}

}
