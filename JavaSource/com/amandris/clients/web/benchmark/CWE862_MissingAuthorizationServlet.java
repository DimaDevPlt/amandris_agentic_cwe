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
 * Benchmark case CWE-862. Removing a seller is an administrator function.
 * The handler only checks that some buyer is logged in.
 */
public class CWE862_MissingAuthorizationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Map sellers = new HashMap();

	public CWE862_MissingAuthorizationServlet() {
		sellers.put("20", "seller-20");
		sellers.put("21", "seller-21");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("buyerId") == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String sellerId = request.getParameter("sellerId");
		Object removed = sellers.remove(sellerId);
		if (removed == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		response.setContentType("text/plain");
		response.getWriter().write("seller removed");
	}
}
