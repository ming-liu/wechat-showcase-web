package com.hzdp.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hzdp.web.constants.EncrptConstants;
import com.ming.crypto.EncryptionUtil;
import com.ming.io.StreamUtil;

public class RequestFactory {

	private static final Logger logger = Logger.getLogger(MainServlet.class);

	public static RequestContext createRequestContext(HttpServletRequest req) {
		RequestContext context = new RequestContext();
		Map<String, String> paramsMap = new HashMap<String, String>();
		parseGetParams(req, paramsMap);

		try {
			InputStream is = req.getInputStream();
			byte[] bytes = StreamUtil.readAll2Byte(is);
			String params = EncryptionUtil.decrptToStr(bytes, EncrptConstants.key, EncrptConstants.iv);
			if (StringUtils.isNotEmpty(params)) {
				String[] split = params.split("&");
				for (String pairs : split) {
					String[] pair = pairs.split("=");
					paramsMap.put(pair[0], pair[1]);
				}
			}
		} catch (IOException e) {
			logger.error(e, e);
		}

		context.setParameterMap(paramsMap);
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
