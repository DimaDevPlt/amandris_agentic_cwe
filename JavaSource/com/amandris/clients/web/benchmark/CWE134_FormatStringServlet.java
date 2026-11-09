package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-134. template from the request is used as a format string.
 */
public class CWE134_FormatStringServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String template = request.getParameter("template");
		Formatter formatter = new Formatter();
		try {
			formatter.format(template, "buyer");
			response.setContentType("text/plain");
			response.getWriter().write(formatter.toString());
		} finally {
			formatter.close();
		}
	}
}
