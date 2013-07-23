package net.slipp.support.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

	private HandlerMapping mapping;

	@Override
	public void init() throws ServletException {
		mapping = new HandlerMapping();
		mapping.initialize();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String url = request.getRequestURI();
		log.debug("request url : {}", url);
		Controller handler = mapping.findController(url);
		
		if (handler == null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		String view;
		try {
			view = handler.execute(request, response);
			dispatch(request, response, view);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String viewPage)
			throws ServletException, IOException {
		if (viewPage.startsWith("redirect:")) {
			response.sendRedirect(viewPage.split("redirect:")[1]);
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
