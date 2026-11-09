package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-501. buyerId from the request is stored in the session.
 */
public class CWE501_TrustBoundaryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buyerId = request.getParameter("buyerId");
		request.getSession().setAttribute("buyerId", buyerId);
		response.setContentType("text/plain");
		response.getWriter().write("session updated");
	}
}
