package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-306. Deleting a buyer account does not check the session.
 */
public class CWE306_MissingAuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Map buyers = new HashMap();

	public CWE306_MissingAuthenticationServlet() {
		buyers.put("10", "buyer-10");
		buyers.put("11", "buyer-11");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buyerId = request.getParameter("buyerId");
		Object removed = buyers.remove(buyerId);
		if (removed == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		response.setContentType("text/plain");
		response.getWriter().write("account deleted");
	}
}
