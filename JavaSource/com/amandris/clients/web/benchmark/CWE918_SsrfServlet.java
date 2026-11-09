package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-918. The server requests the URL supplied in target.
 */
public class CWE918_SsrfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getParameter("target");
		InputStream input = new URL(target).openStream();
		try {
			response.setContentType("application/octet-stream");
			byte[] buffer = new byte[4096];
			int read;
			while ((read = input.read(buffer)) != -1) {
				response.getOutputStream().write(buffer, 0, read);
			}
		} finally {
			input.close();
		}
	}
}
