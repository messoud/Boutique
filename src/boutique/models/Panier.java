package boutique.models;

import java.util.ArrayList;

public class Panier {

	ArrayList<Produit> liste;
	public Panier() {
		liste = new ArrayList<Produit>();
	}
	public ArrayList<Produit> getListe() {
		return liste;
	}
	public void setListe(ArrayList<Produit> liste) {
		this.liste = liste;
	}
	public void addProduit(Produit p)
	{
		liste.add(p);
	}
	public boolean chercheProduit(int idp)
	{
		for(Produit pro : liste)
			if(pro.getIdProduit()==idp)
				return true;
		return false;
	}
}
