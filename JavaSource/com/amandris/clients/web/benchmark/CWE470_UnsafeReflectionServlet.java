package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-470. className from the request is loaded and constructed.
 */
public class CWE470_UnsafeReflectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String className = request.getParameter("className");
		try {
			Object instance = Class.forName(className).newInstance();
			response.setContentType("text/plain");
			response.getWriter().write(instance.getClass().getName());
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
