package service;

import java.sql.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.xml.ws.RespectBinding;
import javax.xml.ws.Response;

import config.Config;
import entity.Auteur;
import entity.Livre;

public class ServiceLivre {
	static EntityManager em = Config.getInstanceEntityManager();

	public static void createLivre(Livre livre, Integer idAuteur) {
		
		livre.setAuteur(em.find(Auteur.class, idAuteur));
		
		System.out.println(livre);
		em.getTransaction().begin();
		em.persist(livre);
		em.getTransaction().commit();
	}

	public static Livre DetailsLivre(Integer idLivre) {
		// TODO Auto-generated method stub
		return em.find(Livre.class, idLivre);
	}

	public static void updateLivre(Map<String, String> dataMap, Integer idLivre) {

		Livre unLivreToUpdate = em.find(Livre.class, idLivre);

		if (dataMap.containsKey("titre")) {
			unLivreToUpdate.setTitre(dataMap.get("titre"));
		}
		if (dataMap.containsKey("categorie")) {
			unLivreToUpdate.setCategorie(dataMap.get("categorie"));
		}
		if (dataMap.containsKey("datePublication")) {
			unLivreToUpdate.setDatePublication(Date.valueOf(dataMap.get("datePublication")));
		}
		if (dataMap.containsKey("description")) {
			unLivreToUpdate.setDescription(dataMap.get("description"));
		}
		if (dataMap.containsKey("nombreExemplaire")) {
			unLivreToUpdate.setNombreExemplaire(Integer.parseInt(dataMap.get("nombreExemplaire")));
		}
		if (dataMap.containsKey("nombreExemplaireDisponible")) {
			unLivreToUpdate.setNombreExemplaireDisponible(Integer.parseInt(dataMap.get("nombreExemplaireDisponible")));
		}
		em.getTransaction().begin();
		em.persist(unLivreToUpdate);
		em.getTransaction().commit();
		
		
	}

}
