package entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Livre {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String titre;
	private Date datePublication;
	private String description;
	private String categorie;
	
	@ManyToOne
	@JoinColumn(name="ID_AUTEUR")
	private Auteur auteur;
	private Integer nombreExemplaire;
	private Integer nombreExemplaireDisponible;
	
	
	
	
	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Livre(String titre, Date date, String description, String categorie,
			Auteur auteur, Integer nombreExemplaires, Integer nombreExemplaireDisponible) {
		super();
		this.titre = titre;
		this.datePublication = date;
		this.description = description;
		this.categorie = categorie;
		this.auteur = auteur;
		this.nombreExemplaire = nombreExemplaires;
		this.nombreExemplaireDisponible = nombreExemplaireDisponible;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Date getDatePublication() {
		return datePublication;
	}


	public void setDatePublication(Date date) {
		this.datePublication = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public Auteur getAuteur() {
		return auteur;
	}


	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}


	public Integer getNombreExemplaire() {
		return nombreExemplaire;
	}


	public void setNombreExemplaire(Integer nombreExemplaires) {
		this.nombreExemplaire = nombreExemplaires;
	}


	public Integer getNombreExemplaireDisponible() {
		return nombreExemplaireDisponible;
	}


	public void setNombreExemplaireDisponible(Integer nombreExemplaireDisponible) {
		this.nombreExemplaireDisponible = nombreExemplaireDisponible;
	}


	public void setId(Integer idLivre) {
		// TODO Auto-generated method stub
		
	}


	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}


	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", datePublication=" + datePublication + ", description="
				+ description + ", categorie=" + categorie + ", auteur=" + auteur + ", nombreExemplaire="
				+ nombreExemplaire + ", nombreExemplaireDisponible=" + nombreExemplaireDisponible + "]";
	}



	
	

}
