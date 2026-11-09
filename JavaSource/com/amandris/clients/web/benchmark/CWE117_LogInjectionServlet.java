package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-117. The comment request parameter is written to the log.
 */
public class CWE117_LogInjectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = request.getParameter("comment");
		Logger.getLogger("amandris.benchmark").info(comment);
		response.setContentType("text/plain");
		response.getWriter().write("saved");
	}
}
