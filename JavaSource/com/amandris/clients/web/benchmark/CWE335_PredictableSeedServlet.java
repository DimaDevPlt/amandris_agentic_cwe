package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-335. The reset token generator is reseeded with a constant.
 */
public class CWE335_PredictableSeedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(42L);
			byte[] token = new byte[16];
			random.nextBytes(token);
			request.getSession().setAttribute("resetToken", token);
			response.setContentType("text/plain");
			response.getWriter().write("issued");
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
