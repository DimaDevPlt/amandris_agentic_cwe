package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-78. The command request parameter is passed to the process API.
 */
public class CWE78_CommandInjectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		try {
			Process process = Runtime.getRuntime().exec(command);
			response.setContentType("text/plain");
			response.getWriter().write(String.valueOf(process.waitFor()));
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
