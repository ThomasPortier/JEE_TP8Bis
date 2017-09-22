package servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import config.Config;
import entity.Livre;

/**
 * Servlet implementation class Servlet
 */


@WebServlet("/livres")
public class ServletLivres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// INITIALISATION BASE TABLE //

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Config.initialiserDonnees();
		EntityManager em = Config.getInstanceEntityManager();
		TypedQuery<Livre> query = em.createQuery("from Livre", Livre.class);
		List<Livre> livres = query.getResultList();

		ObjectMapper mapper = new ObjectMapper();

		String jsoninString;
		jsoninString = mapper.writeValueAsString(livres);
		
		response.setContentType("application/json");
		response.getWriter().append(jsoninString);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
