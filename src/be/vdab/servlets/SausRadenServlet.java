package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.SausDAO;

/**
 * Servlet implementation class SausRadenServlet
 */
@WebServlet("/sausraden.htm")
public class SausRadenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausraden.jsp";
	private static final String REDIRECT = "%s/sausraden.htm";
	private final SausDAO sausDAO = new SausDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		
		String teRadenSaus = (String) session.getAttribute("teRadenSaus");

		if (teRadenSaus == null) {
			// Geen te raden saus: initializeer nieuw spel
			teRadenSaus = genereerTeRadenSaus();

			session.setAttribute("teRadenSaus", teRadenSaus);
		}

		int aantalFouteGokken = 0;
		if (session.getAttribute("aantalFouteGokken") == null) {
			session.setAttribute("aantalFouteGokken", aantalFouteGokken);
		} else {
			aantalFouteGokken = (int) session.getAttribute("aantalFouteGokken");
		}

		List<Character> geradenLetters = null;
		if (session.getAttribute("geradenLetters") == null) {
			geradenLetters = new ArrayList<>();
			session.setAttribute("geradenLetters", geradenLetters);
		} else {
			geradenLetters = (List<Character>) session.getAttribute("geradenLetters");
		}

		request.setAttribute("aantalFouteGokken", aantalFouteGokken);
		request.setAttribute("teRadenSaus", maskString(teRadenSaus, geradenLetters));

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getAttribute("nieuwSpel") == null) {
			
			HttpSession session = request.getSession();
			
			char gegokteLetter = (char) request.getAttribute("gegokteLetter");
			// TODO VALIDEER GEGOKTE LETTER
			

		} else {
			request.getSession().invalidate();
		}

		response.sendRedirect(request.getRequestURI());

	}

	private String genereerTeRadenSaus() {

		long aantalSauzen = sausDAO.findAll().size();
		long random = (long) (Math.random() * aantalSauzen) + 1;
		return sausDAO.read(random).getNaam();

	}

	private String maskString(String woord, List<Character> geradenLetters) {

		String maskedString = "";

		for (int i = 0; i < woord.length(); i++) {
			if (geradenLetters.contains(woord.charAt(i))) {
				maskedString += woord.charAt(i);
			} else {
				maskedString += '.';
			}
		}

		return maskedString;

	}
	
	private boolean zoekLetterInWoord(String woord, char letter) {
		
		for (int i = 0; i < woord.length(); i++) {
			if (woord.charAt(i) == letter)
				return true;
		}
		
		return false;
	}

}
