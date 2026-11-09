package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-94. expression is evaluated by the script engine.
 */
public class CWE94_CodeInjectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String expression = request.getParameter("expression");
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
			Object value = engine.eval(expression);
			response.setContentType("text/plain");
			response.getWriter().write(String.valueOf(value));
		} catch (Exception exception) {
			throw new ServletException(exception);
		}
	}
}
