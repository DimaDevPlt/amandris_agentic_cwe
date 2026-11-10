package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Benchmark case CWE-862. Deleting every buyer account is an administrator function.
 * The handler checks that a buyer is logged in and does not check the admin role.
 */
public class CWE862_MissingAuthorizationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Map buyers = new HashMap();

	public CWE862_MissingAuthorizationServlet() {
		buyers.put("10", "buyer-10");
		buyers.put("11", "buyer-11");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("buyerId") == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String role = (String) session.getAttribute("role");
		deleteAllBuyers(role);
		response.setContentType("text/plain");
		response.getWriter().write("all buyer accounts deleted");
	}

	private void deleteAllBuyers(String role) {
		buyers.clear();
	}
}
