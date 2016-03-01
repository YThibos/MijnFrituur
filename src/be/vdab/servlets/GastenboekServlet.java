package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.GbentryDAO;
import be.vdab.entities.Gbentry;

/**
 * Servlet implementation class GastenboekServlet
 */
@WebServlet("/gastenboek.htm")
public class GastenboekServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW = "/WEB-INF/JSP/gastenboek.jsp";
	private static final String REDIRECT = "%s/gastenboek.htm";
	
	private static final GbentryDAO gbentryDAO = new GbentryDAO();

	@Resource(name = GbentryDAO.JNDI_NAME)
	public void setDataSource(DataSource dataSource) {
		gbentryDAO.setDataSource(dataSource);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Gbentry> entries = gbentryDAO.findAll();
		
		request.setAttribute("entries", entries);
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Haal form data op en steek ze in een Gbentry object
		if (request.getParameter("naam") != null && request.getParameter("bericht") != null) {
			Gbentry entry = new Gbentry(request.getParameter("naam"), request.getParameter("bericht"));
			
			// Wegschrijven naar database via DAO
			gbentryDAO.write(entry);
		}
		
		response.sendRedirect(String.format(REDIRECT, request.getContextPath()));
		
	}

}
