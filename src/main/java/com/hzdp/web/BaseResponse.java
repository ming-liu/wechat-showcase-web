package com.hzdp.web;

import com.hzdp.web.api.IResponse;
import com.hzdp.web.metatype.StatusCode;

public abstract class BaseResponse implements IResponse {

	private StatusCode statusCode;

	@Override
	public StatusCode getStatus() {
		return statusCode;
	}

	@Override
	public void setStatus(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

}
