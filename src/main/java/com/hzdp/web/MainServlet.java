package com.hzdp.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2777342422528886950L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp, false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest(req, resp, true);
	}

	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp, boolean isPost) {
		String actionKey = parseActionKey(req);
		IAction action = ActionFactory.createAction(actionKey);
		
		Map parameterMap = req.getParameterMap();
		
//		action.execute(request);
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
