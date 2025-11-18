package fr.lo02.jest;

public class Joueur {
	
	private ConteneurCarte jest;
	private ConteneurCarte offre;
	private String nom;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.offre = new ConteneurCarte();
		this.jest = new ConteneurCarte();
	}
	
	public ConteneurCarte getJest() {
		return jest;
	}
	
	public ConteneurCarte getOffre() {
		return offre;
	}
	
	public String getNom() {
		return nom;
	}

}
