package com.hzdp.web.api;

public interface IAction<T extends IRequest> {

	IResponse execute(T request);
}
