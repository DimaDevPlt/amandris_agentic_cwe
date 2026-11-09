package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Benchmark case CWE-90. login is concatenated into an LDAP search filter.
 */
public class CWE90_LdapInjectionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:389");
		DirContext context = null;
		try {
			context = new InitialDirContext(env);
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration results = context.search(
				"ou=buyers,dc=amandris,dc=example",
				"(uid=" + login + ")",
				controls);
			if (results.hasMore()) {
				SearchResult result = (SearchResult) results.next();
				response.setContentType("text/plain");
				response.getWriter().write(result.getName());
			}
		} catch (Exception exception) {
			throw new ServletException(exception);
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (Exception ignored) {
				}
			}
		}
	}
}
