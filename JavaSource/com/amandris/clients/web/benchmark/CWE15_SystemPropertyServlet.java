package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-15. key and value from the request are applied as a system property.
 */
public class CWE15_SystemPropertyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		System.setProperty(key, value);
		response.setContentType("text/plain");
		response.getWriter().write("updated");
	}
}
