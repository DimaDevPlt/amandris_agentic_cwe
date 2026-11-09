package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-325. The password hash uses PBKDF2 with a single iteration.
 */
public class CWE325_LowIterationPasswordHashServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		try {
			byte[] salt = new byte[16];
			new SecureRandom().nextBytes(salt);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1, 128);
			byte[] hash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(spec).getEncoded();
			response.setContentType("application/octet-stream");
			response.getOutputStream().write(hash);
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
