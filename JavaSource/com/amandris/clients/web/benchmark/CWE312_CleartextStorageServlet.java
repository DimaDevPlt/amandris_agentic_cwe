package com.amandris.clients.web.benchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-312. The card number and password are stored without encryption.
 */
public class CWE312_CleartextStorageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String cardNumber = request.getParameter("cardNumber");
		storePassword(login, password);
		storeCard(login, cardNumber);
		response.setContentType("text/plain");
		response.getWriter().write("saved");
	}

	private void storePassword(String login, String password) throws ServletException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/amandris",
				System.getProperty("amandris.db.user"),
				System.getProperty("amandris.db.password"));
			statement = connection.prepareStatement("INSERT INTO buyer (login, password) VALUES (?, ?)");
			statement.setString(1, login);
			statement.setString(2, password);
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new ServletException(exception);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ignored) {
			}
		}
	}

	private void storeCard(String login, String cardNumber) throws IOException {
		FileWriter writer = new FileWriter("C:/amandris/card-numbers.txt", true);
		try {
			writer.write(login + ":" + cardNumber + "\n");
		} finally {
			writer.close();
		}
	}
}
