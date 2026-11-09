package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-601. The browser is sent to the url request parameter.
 */
public class CWE601_OpenRedirectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		response.sendRedirect(url);
	}
}
