package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-798. The database password is hard-coded in the servlet.
 */
public class CWE798_HardcodedCredentialsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String DB_PASSWORD = "benchmark-db-password";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/amandris",
				"amandris",
				DB_PASSWORD);
			response.setContentType("text/plain");
			response.getWriter().write(connection.getCatalog());
		} catch (Exception exception) {
			throw new ServletException(exception);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignored) {
				}
			}
		}
	}
}
