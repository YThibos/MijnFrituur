package be.vdab.servlets;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.*;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = "/index.htm", name= "indexservlet")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final String HELPDESK_TELNR = "helpdesknummer";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DayOfWeek vandaag = LocalDate.now().getDayOfWeek();
		
		request.setAttribute("adres", new Adres("Grote Markt", "1", new Gemeente("Antwerpen", 2000)));
		request.setAttribute("openGesloten", (
				vandaag == DayOfWeek.TUESDAY || vandaag == DayOfWeek.THURSDAY) ? "gesloten" : "open"
					);
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(HELPDESK_TELNR, "0800/12321");
	}
	
}
