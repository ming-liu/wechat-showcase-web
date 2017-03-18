package com.hzdp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hzdp.web.api.IAction;
import com.hzdp.web.api.IResponse;

public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2777342422528886950L;
	public static final String ENCODING = "utf-8";
	public static final String CONTENTTYPE_BINARY = "application/binary; charset=" + ENCODING;

	private static final Logger logger = Logger.getLogger(MainServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp, false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp, true);
	}

	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp, boolean isPost) {
		logger.debug("handleRequest in .");
		String actionKey = parseActionKey(req);
		IAction action = ActionFactory.createAction(actionKey);
		RequestContext context = RequestFactory.createRequestContext(req);
		try {
			IResponse response = ActionExecutor.execute(action, context);
			byte[] wrap = ResponseWrapper.wrap(response);
			resp.setCharacterEncoding(ENCODING);
			resp.setContentType(CONTENTTYPE_BINARY);
			resp.setContentLength(wrap.length);
			resp.setStatus(200);
			resp.getOutputStream().write(wrap);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private String parseActionKey(HttpServletRequest req) {
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();

		if (uri.length() <= contextPath.length() + 1) {
			return null;
		}

		return uri.substring(contextPath.length() + 1).toLowerCase();
	}
}
