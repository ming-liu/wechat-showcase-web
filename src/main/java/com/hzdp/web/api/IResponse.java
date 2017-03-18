package com.hzdp.web.api;

import com.hzdp.web.metatype.StatusCode;

public interface IResponse {

	StatusCode getStatus();

	void setStatus(StatusCode statusCode);
}
