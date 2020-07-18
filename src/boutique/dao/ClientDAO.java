package boutique.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import boutique.models.Client;
import boutique.models.Produit;

public class ClientDAO {

    /****
     * M�thode qui retourne tous les clients sous 
     * forme d'un ResultSet
     * ***/
	
    public static Connection getCon() {
		return (Connection) Connexion.getCon();
	}
	public static ResultSet listeClient()
    {
    	// Envoi d'une requ�te g�n�rique
        String sql =  "select * from client" ;
        ResultSet rs=null  ;
        Statement smt;
		try {
			smt = getCon().createStatement();
			rs = smt.executeQuery(sql) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return rs;
    }
	
	public static ArrayList<Client> lesClients()
	{
		ArrayList<Client> liste = new ArrayList<Client>();
		ResultSet rs = listeClient();
		try {
			while(rs.next())
			{
				liste.add(new Client(rs.getInt("idClient"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(liste);
		return liste;
	}
    /****
     * M�thode qui supprime un client � la table client selon son identifiant
     * ***/
    public static void supprimerClient(int id)
    {
    	String sql="delete from client where idClient="+id;
    	System.out.println(sql);
    	try {
			Statement smt = getCon().createStatement();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /****
     * M�thode qui modifie un client � la table client
     * ***/
    public static void modifierProduit(Client c)
    {
    	
    }
    /****
     * A FAIRE : M�thode qui ajoute un Client � la table client
     * ***/
    public static void ajouterClient(Client c)
    {
    	String sql="insert into client (nom, prenom, identifiant, motdepasse, email) "
    			+ "values  ('"+c.getNom()+"', '"+c.getPrenom()+"', '" +c.getIdentifiant() +"', '"+c.getMdp() +"', '"+c.getEmail() + "')";
    	System.out.println(sql);
    	try {
			Statement smt = getCon().createStatement();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /******
     * A FAIRE : Recherche un client dans la table client
     */
    public static Client rechercheClient(String ident, String mdp)
    {
    	Client client=null;
    	String sql =  "select * from client where identifiant='"+ident+"' and motdepasse='"+mdp+"'"  ;
    	 ResultSet rs=null  ;
         Statement smt;
 		try {
 			smt = getCon().createStatement();
 			rs = smt.executeQuery(sql) ;
 			if(rs.next())
 				client = new Client(rs.getInt("idClient"),rs.getString("nom"),rs.getString("prenom"),rs.getString("identifiant"),rs.getString("motdepasse"),rs.getString("email"));
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	return client;
    }
    /******
     * A FAIRE : ins�rer tous les produits choisi d'un client � la table acheter
     */
    public static void acheterProduits(Client client, ArrayList<Produit> produits)
    {
    	for(Produit p : produits)
    	{
    		String sql ="insert into acheter (idProduit, idClient) values ("+p.getIdProduit()+","+client.getIdClient()+")";
    		System.out.println(sql);
        	try {
    			Statement smt = getCon().createStatement();
    			smt.executeUpdate(sql);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
}
