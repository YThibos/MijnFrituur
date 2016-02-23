package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.SausDAO;
import be.vdab.entities.Saus;

/**
 * Servlet implementation class SauzenServlet
 */
@WebServlet("/sauzen.htm")
public class SauzenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sauzen.jsp";
	private static final String REDIRECT_VIEW = "%s/sauzen.htm";

	private static final SausDAO sausDAO = new SausDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Saus> sauzen = new ArrayList<>();
		
		// Als geen ingredienten zijn geselecteerd, geef volledige lijst weer
		if (request.getParameterValues("ingredient") == null) {
			sauzen = sausDAO.findAll();
		}
		else {
		// Geef enkel de sauzen weer met geselecteerde ingredienten
			for (String ingredient : request.getParameterValues("ingredient")) {
				sausDAO.findByIngredient(ingredient).stream().forEach(sauzen::add);
			}
			
		}
		if (sauzen.isEmpty()) {
			request.setAttribute("fout", "Geen sauzen gevonden");
		}
		request.setAttribute("sauzen", sauzen);
		
		// Ingredienten verzamelen om ze door te geven naar VIEW (voor checkboxes)
		List<String> ingredienten = new ArrayList<>();
		sausDAO.findAll().stream().forEach(saus -> {
			saus.getIngredienten().forEach(ingredient -> {
				if (!ingredienten.contains(ingredient)) ingredienten.add(ingredient);
				});
			});
		request.setAttribute("ingredienten", ingredienten);
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<String> teVerwijderenSauzen = Arrays.asList(request.getParameterValues("saus"));
		
		if (!teVerwijderenSauzen.isEmpty()) {
			sausDAO.deleteByName(teVerwijderenSauzen);
		}
		else {
			request.setAttribute("verwijderen", "geen sauzen aangevinkt");
		}
		
		response.sendRedirect(String.format(REDIRECT_VIEW, request.getContextPath()));
		
	}

}
