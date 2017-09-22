package config;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Auteur;
import entity.Livre;

public class Config {
	
/*	String str = "2015-03-15";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate dateTime = LocalDate.parse(str, formatter);
*/

	private static EntityManager em;

	public static void initialiserEntityManager() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation");
		em = emf.createEntityManager();
	}

	public static EntityManager getInstanceEntityManager() {
		if (em == null) {
			Config.initialiserEntityManager();
		}
		return em;
	}

	public static void initialiserDonnees() {
		Config.getInstanceEntityManager();

		Livre unLivre = new Livre("The Final Empire", new Date(2000 - 07 - 11), "Fantasy", "Novel", null, 6, 3);
		Livre unLivre2 = new Livre("The Well Of Ascension", new Date(2004 - 10 - 04), "Fantasy", "Novel", null, 12, 3);
		Livre unLivre3 = new Livre("The Hero Of Ages", new Date(2009 - 12 - 05), "Fantasy", "Novel", null, 34, 6);
		Auteur unAuteur = new Auteur("Sanderson", "Brandon", "English");
		unLivre.setAuteur(unAuteur);
		unLivre2.setAuteur(unAuteur);
		unLivre3.setAuteur(unAuteur);

		em.getTransaction().begin();
		em.persist(unLivre);
		em.persist(unLivre2);
		em.persist(unLivre3);
		em.persist(unAuteur);

		Livre unLivre4 = new Livre("The Name Of The Wind", new Date(2007 - 06 - 05), "Fantasy", "Novel", null, 6, 3);
		Livre unLivre5 = new Livre("The Wise Man's Fear", new Date(2011 - 04 - 12), "Fantasy", "Novel", null, 12, 3);
		Livre unLivre6 = new Livre("The Door Of Stones", new Date(2018 - 06 - 11), "Fantasy", "Novel", null, 34, 6);
		Auteur unAuteur2 = new Auteur("Rothfuss", "Patrick", "English");
		unLivre4.setAuteur(unAuteur2);
		unLivre5.setAuteur(unAuteur2);
		unLivre6.setAuteur(unAuteur2);

		em.persist(unLivre4);
		em.persist(unLivre5);
		em.persist(unLivre6);
		em.persist(unAuteur2);
		em.getTransaction().commit();

	}

}
