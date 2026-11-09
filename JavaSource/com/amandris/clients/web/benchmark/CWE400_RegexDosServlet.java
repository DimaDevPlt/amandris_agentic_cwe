package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-400. The pattern request parameter is compiled and matched as a regular expression.
 */
public class CWE400_RegexDosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pattern = request.getParameter("pattern");
		String text = request.getParameter("text");
		boolean matched = Pattern.compile(pattern).matcher(text).matches();
		response.setContentType("text/plain");
		response.getWriter().write(Boolean.toString(matched));
	}
}
