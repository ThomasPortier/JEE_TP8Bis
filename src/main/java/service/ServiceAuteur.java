package service;

import java.io.IOException;
import java.util.Map;

import javax.persistence.EntityManager;

import config.Config;
import entity.Auteur;

public class ServiceAuteur {

	static EntityManager em = Config.getInstanceEntityManager();

	public static void createAuteur(Auteur auteur) {
		// EntityManager em = Config.getInstanceEntityManager();
		em.getTransaction().begin();
		em.persist(auteur);
		em.getTransaction().commit();

	}

	public static Auteur DetailsAuteur(Integer idAuteur) {
		// EntityManager em = Config.getInstanceEntityManager();
		// TODO Auto-generated method stub
		return em.find(Auteur.class, idAuteur);
	}

	public static void updateAuteur(Map<String, String> dataMap, Integer idAuteur) throws IOException {

		Auteur unAuteurToUpdate = em.find(Auteur.class, idAuteur);

		if (dataMap.containsKey("nom")) {
			unAuteurToUpdate.setNom(dataMap.get("nom"));
		}
		if (dataMap.containsKey("prenom")) {
			unAuteurToUpdate.setPrenom(dataMap.get("prenom"));
		}
		if (dataMap.containsKey("langue")) {
			unAuteurToUpdate.setLangue(dataMap.get("langue"));
		}

		em.getTransaction().begin();
		em.persist(unAuteurToUpdate);
		em.getTransaction().commit();

	}

}
