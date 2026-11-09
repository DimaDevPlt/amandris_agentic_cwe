package com.amandris.clients.web.benchmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-1004. The session cookie is sent without the HttpOnly flag.
 */
public class CWE1004_CookieHttpOnlyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionValue = request.getSession().getId();
		Cookie cookie = new Cookie("buyerSession", sessionValue);
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		response.setContentType("text/plain");
		response.getWriter().write("ok");
	}
}
