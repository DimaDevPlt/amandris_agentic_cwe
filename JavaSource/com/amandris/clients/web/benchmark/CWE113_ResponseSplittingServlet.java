package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-113. buyerName is copied into a response header.
 */
public class CWE113_ResponseSplittingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buyerName = request.getParameter("buyerName");
		response.addHeader("X-Buyer-Name", buyerName);
		response.setContentType("text/plain");
		response.getWriter().write("ok");
	}
}
