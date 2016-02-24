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

			//request.setAttribute("disableKnop", false);
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

		if (request.getParameter("nieuwSpel") == null) {
			
			HttpSession session = request.getSession();
			
			String teRadenSaus = (String) session.getAttribute("teRadenSaus");
			
			// Haal gegokte letter op en check of ze voor komt
			char gegokteLetter = request.getParameter("gegokteLetter").charAt(0);
			List<Character> geradenLetters = (List<Character>) session.getAttribute("geradenLetters");
			if (isLetterInWoord(teRadenSaus, gegokteLetter) && !geradenLetters.contains(gegokteLetter)) {
				// Voeg gegokte letter toe bij juiste letters
				
				geradenLetters.add(gegokteLetter);
				request.setAttribute("status", "Succes, je letter komt voor in de saus ..");
				session.setAttribute("geradenLetters", geradenLetters);
				
				boolean gewonnen = true;
				for (int i = 0; i < teRadenSaus.length(); i++) {
					if (!geradenLetters.contains(teRadenSaus.charAt(i))) gewonnen = false ;
				}
				
				if (gewonnen) request.setAttribute("status", "Gewonnen!");
				
			}
			else {
				// dood ? "dood!" : ++foutegokken
				int aantalFouteGokken = (int) session.getAttribute("aantalFouteGokken") + 1;
				
				request.setAttribute("status", "Fout gegokt, probeer nogeens");
				
				if (aantalFouteGokken >= 10) {
					request.setAttribute("status", "Dood! Het te raden woord was: " + session.getAttribute(teRadenSaus) + "\n.Start een nieuw spel.");
					request.setAttribute("disableKnop", true);
				}
				
				session.setAttribute("aantalFouteGokken", aantalFouteGokken);
			}
			

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
	
	private boolean isLetterInWoord(String woord, char letter) {
		
		for (int i = 0; i < woord.length(); i++) {
			if (woord.charAt(i) == letter)
				return true;
		}
		
		return false;
	}

}
