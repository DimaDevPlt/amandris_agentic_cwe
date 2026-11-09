package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-327. Card data is encrypted with DES.
 */
public class CWE327_BrokenCryptoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cardNumber = request.getParameter("cardNumber");
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(56, new SecureRandom());
			SecretKey key = generator.generateKey();
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encrypted = cipher.doFinal(cardNumber.getBytes("UTF-8"));
			response.setContentType("application/octet-stream");
			response.getOutputStream().write(encrypted);
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
