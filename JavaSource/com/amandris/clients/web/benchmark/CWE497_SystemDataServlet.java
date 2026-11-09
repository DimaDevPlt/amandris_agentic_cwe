package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-497. The process environment is returned to the caller.
 */
public class CWE497_SystemDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.getWriter().write(System.getProperty("user.home"));
		response.getWriter().write("\n");
		response.getWriter().write(String.valueOf(System.getenv("PATH")));
	}
}
