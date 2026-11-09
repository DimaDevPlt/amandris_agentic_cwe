package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-319. The password and card number are sent over unencrypted HTTP.
 */
public class CWE319_CleartextTransmissionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String cardNumber = request.getParameter("cardNumber");
		sendPasswordOverHttp(login, password);
		sendCardOverSocket(cardNumber);
		response.setContentType("text/plain");
		response.getWriter().write("sent");
	}

	private void sendPasswordOverHttp(String login, String password) throws IOException {
		URL url = new URL("http://auth.amandris.example/login");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		OutputStream body = connection.getOutputStream();
		try {
			body.write(("login=" + login + "&password=" + password).getBytes("UTF-8"));
		} finally {
			body.close();
		}
		connection.getResponseCode();
	}

	private void sendCardOverSocket(String cardNumber) throws IOException {
		Socket socket = new Socket("payments.amandris.example", 80);
		try {
			OutputStream body = socket.getOutputStream();
			body.write(("POST /charge HTTP/1.1\r\nHost: payments.amandris.example\r\n\r\ncard=" + cardNumber).getBytes("UTF-8"));
			body.flush();
		} finally {
			socket.close();
		}
	}
}
