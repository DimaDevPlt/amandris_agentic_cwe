package com.amandris.clients.web.benchmark;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Benchmark case CWE-639. A logged-in buyer can read any private message by supplying its id.
 */
public class CWE639_IdorMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Map messages = new HashMap();

	public CWE639_IdorMessageServlet() {
		messages.put("1001", new PrivateMessage("1001", "buyer-a", "Meet at the cafe."));
		messages.put("1002", new PrivateMessage("1002", "buyer-b", "The address is private."));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("buyerId") == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String currentBuyerId = (String) session.getAttribute("buyerId");
		String messageId = request.getParameter("messageId");
		String body = readMessage(currentBuyerId, messageId);
		if (body == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		response.setContentType("text/plain");
		response.getWriter().write(body);
	}

	private String readMessage(String currentBuyerId, String messageId) {
		PrivateMessage message = (PrivateMessage) messages.get(messageId);
		if (message == null) {
			return null;
		}
		return message.body;
	}

	private static final class PrivateMessage {
		private final String id;
		private final String ownerBuyerId;
		private final String body;

		private PrivateMessage(String id, String ownerBuyerId, String body) {
			this.id = id;
			this.ownerBuyerId = ownerBuyerId;
			this.body = body;
		}
	}
}
