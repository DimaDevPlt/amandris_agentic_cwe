package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-328. A profile field is digested with MD5.
 */
public class CWE328_WeakHashServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String profile = request.getParameter("profile");
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] hash = digest.digest(profile.getBytes("UTF-8"));
			response.setContentType("application/octet-stream");
			response.getOutputStream().write(hash);
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
