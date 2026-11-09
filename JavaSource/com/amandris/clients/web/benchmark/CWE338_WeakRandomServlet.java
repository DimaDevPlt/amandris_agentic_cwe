package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-338. A password-reset token is created with java.util.Random.
 */
public class CWE338_WeakRandomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		Random random = new Random();
		String resetToken = Long.toHexString(random.nextLong());
		request.getSession().setAttribute("resetToken", resetToken);
		response.setContentType("text/plain");
		response.getWriter().write(login);
	}
}
