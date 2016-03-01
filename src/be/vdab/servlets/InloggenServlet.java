package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InloggenServlet
 */
@WebServlet("/inloggen.htm")
public class InloggenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/inloggen.jsp";
	private static final String REDIRECT = "%s/gastenboek.htm";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("password") != null
				&& request.getParameter("password").equals(this.getServletContext().getInitParameter("adminpassword"))) {

			request.getSession().setAttribute("admin", true);
			response.sendRedirect(String.format(REDIRECT, request.getContextPath()));

		} else {
			request.setAttribute("fout", "Onjuist wachtwoord");
			request.getRequestDispatcher(VIEW).forward(request, response);
		}

	}

}
