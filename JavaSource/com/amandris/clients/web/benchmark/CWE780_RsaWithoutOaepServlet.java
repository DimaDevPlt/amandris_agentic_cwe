package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-780. Card data is encrypted with RSA PKCS#1 v1.5 instead of OAEP.
 */
public class CWE780_RsaWithoutOaepServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cardNumber = request.getParameter("cardNumber");
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(2048);
			KeyPair pair = generator.generateKeyPair();
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
			byte[] encrypted = cipher.doFinal(cardNumber.getBytes("UTF-8"));
			response.setContentType("application/octet-stream");
			response.getOutputStream().write(encrypted);
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
