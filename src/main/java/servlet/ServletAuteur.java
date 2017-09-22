package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import config.Config;
import entity.Auteur;
import service.ServiceAuteur;
import utils.DataFromPut;

/**
 * Servlet implementation class ServletAuteur
 */
@WebServlet(urlPatterns = { "/auteur", "/auteur/*" })
public class ServletAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EntityManager em = Config.getInstanceEntityManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAuteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String pathInfo = request.getPathInfo();

		Integer idAuteur = Integer.parseInt(pathInfo.replace("/", ""));

		if ((em.find(Auteur.class, idAuteur)) == null) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"message\":\"Auteur non trouve\"}");
		} else {

			Auteur auteurAdetailler = ServiceAuteur.DetailsAuteur(idAuteur);

			ObjectMapper mapper = new ObjectMapper();

			String jsoninString;
			jsoninString = mapper.writeValueAsString(auteurAdetailler);

			response.setContentType("application/json");
			response.getWriter().append(jsoninString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Auteur unAuteur = new Auteur();
		unAuteur.setNom(request.getParameter("nom"));
		unAuteur.setPrenom(request.getParameter("prenom"));
		unAuteur.setLangue(request.getParameter("langue"));
		System.out.println(unAuteur);
		ServiceAuteur.createAuteur(unAuteur);

		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Config.initialiserDonnees();
		// Config.getInstanceEntityManager();
		String pathInfo = request.getPathInfo();

		Integer idAuteur = Integer.parseInt(pathInfo.replace("/", ""));

		if ((em.find(Auteur.class, idAuteur)) == null) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"message\":\"Auteur non trouve\"}");
		} else {

			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

			Map<String, String> dataMap = DataFromPut.getParameterMap(br);

			System.out.println(dataMap.get("nom"));
			System.out.println(dataMap.get("prenom"));
			System.out.println(dataMap.get("langue"));

			ServiceAuteur.updateAuteur(dataMap, idAuteur);
		}
	}

}
