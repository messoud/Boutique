package boutique.models;

import java.io.Serializable;

public class Produit implements Serializable{
	private int idProduit;
	private int idCategorie;
	private int quantite;
	private String nom;
	private float prix;
	public Produit() {
		super();
	}
	


	public Produit(int idProduit, int idCategorie, int quantite, String nom, float prix) {
		super();
		this.idProduit = idProduit;
		this.idCategorie = idCategorie;
		this.quantite = quantite;
		this.nom = nom;
		this.prix = prix;
	}



	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", prix=" + prix + "]\n";
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
