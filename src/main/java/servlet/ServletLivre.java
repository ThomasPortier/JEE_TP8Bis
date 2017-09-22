package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import config.Config;
import entity.Livre;
import service.ServiceLivre;
import utils.DataFromPut;

/**
 * Servlet implementation class ServletLivre
 */
@WebServlet(urlPatterns = { "/livre", "/livre/*" })
public class ServletLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EntityManager em = Config.getInstanceEntityManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLivre() {
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
		// EntityManager em = Config.getInstanceEntityManager();

		String pathInfo = request.getPathInfo();

		Integer idLivre = Integer.parseInt(pathInfo.replace("/", ""));

		if ((em.find(Livre.class, idLivre)) == null) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"message\":\"Livre non trouve\"}");
		} else {
			Livre livreAdetailler = ServiceLivre.DetailsLivre(idLivre);

			ObjectMapper mapper = new ObjectMapper();

			String jsoninString;
			jsoninString = mapper.writeValueAsString(livreAdetailler);

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
		// TODO Auto-generated method stub

		Livre unLivre = new Livre();

		unLivre.setTitre(request.getParameter("titre"));

		unLivre.setDatePublication(Date.valueOf(request.getParameter("datePublication")));

		unLivre.setDescription(request.getParameter("description"));

		unLivre.setCategorie(request.getParameter("categorie"));

		try {
			unLivre.setNombreExemplaire(Integer.parseInt(request.getParameter("nombreExemplaire")));
			unLivre.setNombreExemplaireDisponible(Integer.parseInt(request.getParameter("nombreExemplaireDisponible")));
			ServiceLivre.createLivre(unLivre, Integer.parseInt(request.getParameter("idAuteur")));
		} catch (NumberFormatException e) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			response.getWriter().append("{\"message\":\"le nombre d'exemplaire doit etre un nombre\"}");
			// TODO Auto-generated catch block
		}

		// doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		Integer idLivre = Integer.parseInt(pathInfo.replace("/", ""));

		if ((em.find(Livre.class, idLivre)) == null) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().append("{\"message\":\"Livre non trouve\"}");
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

			Map<String, String> dataMap = DataFromPut.getParameterMap(br);
			System.out.println(idLivre);

			ServiceLivre.updateLivre(dataMap, idLivre);

		}
	}
}
