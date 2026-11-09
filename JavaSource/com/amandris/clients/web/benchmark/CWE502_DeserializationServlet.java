package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-502. The request body is deserialized with ObjectInputStream.
 */
public class CWE502_DeserializationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectInputStream input = new ObjectInputStream(request.getInputStream());
		try {
			Object value = input.readObject();
			response.setContentType("text/plain");
			response.getWriter().write(String.valueOf(value));
		} catch (ClassNotFoundException exception) {
			throw new ServletException(exception);
		} finally {
			input.close();
		}
	}
}
