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
import entity.Auteur;

/**
 * Servlet implementation class Auteurs
 */
@WebServlet("/auteurs")
public class ServletAuteurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public ServletAuteurs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = Config.getInstanceEntityManager();
		
		TypedQuery<Auteur> query = em.createQuery("from Auteur", Auteur.class);
		List<Auteur> auteurs = query.getResultList();

		ObjectMapper mapper = new ObjectMapper();

		String jsoninString;
		jsoninString = mapper.writeValueAsString(auteurs);
		
		response.setContentType("application/json");
		response.getWriter().append(jsoninString);
		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
