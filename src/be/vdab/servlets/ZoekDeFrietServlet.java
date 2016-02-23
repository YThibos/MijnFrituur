package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ZoekDeFrietServlet
 */
@WebServlet("/zoekdefriet.htm")
public class ZoekDeFrietServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/zoekdefriet.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		Map<Long, String> deuren = (Map<Long, String>) session.getAttribute("deuren");

		if (deuren == null) {
			deuren = new HashMap<>();
			deuren.put(1L, "deurtoe");
			deuren.put(2L, "deurtoe");
			deuren.put(3L, "deurtoe");
			deuren.put(4L, "deurtoe");
			deuren.put(5L, "deurtoe");
			deuren.put(6L, "deurtoe");
			deuren.put(7L, "deurtoe");

			session.setAttribute("deuren", deuren);
		}
		request.setAttribute("deuren", deuren);

		if (session.getAttribute("deurMetFriet") == null) {
			int deurMetFriet = 1 + (int) (Math.random() * 7);
			session.setAttribute("deurMetFriet", deurMetFriet);
		}

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (request.getParameter("nieuwSpel") != null) {
			session.invalidate();
		}
		else {

		@SuppressWarnings("unchecked")
		Map<Long, String> deuren = (Map<Long, String>) session.getAttribute("deuren");

		long gekozenDeur = Long.parseLong(request.getParameter("deurnr"));
		if (gekozenDeur == (int) session.getAttribute("deurMetFriet")) {
			deuren.put(gekozenDeur, "gevonden");
			request.setAttribute("gewonnen", "Jieppie yay, gewonnen!");
		} else {
			deuren.put(gekozenDeur, "deuropen");
		}

		request.setAttribute("deuren", deuren);

		}
		
		response.sendRedirect(request.getRequestURI());

	}

}
