package com.hzdp.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestFactory {

	public static RequestContext createRequestContext(HttpServletRequest req) {
		RequestContext context = new RequestContext();
		Map<String,String> paramsMap = new HashMap<String, String>();
		parseGetParams(req, paramsMap);
		Map params = req.getParameterMap();
		int size = params.size();
		if (size < 1) {

		} else {
		}
		
		// TODO post参数到parametermap
		return context;
	}

	private static void parseGetParams(HttpServletRequest req, Map<String, String> paramsMap) {
		@SuppressWarnings("unchecked")
		Enumeration<String> enums = req.getParameterNames();
		while (enums.hasMoreElements()) {
			String key = enums.nextElement();
			paramsMap.put(key, req.getParameter(key));
		}
	}
}
