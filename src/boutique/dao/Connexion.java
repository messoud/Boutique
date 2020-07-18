package boutique.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import boutique.models.Client;
import boutique.models.Produit;



/**
 * Servlet implementation class Connexion
 */
public class Connexion  {
	private static final long serialVersionUID = 1L;
	static Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public static  Connection getCon() {
    	if(con==null)
    	{
	        try {
	            // chargement de la classe par son nom
	           Class c = Class.forName("com.mysql.jdbc.Driver") ;
	           Driver pilote = (Driver)c.newInstance() ;
	            // enregistrement du pilote aupr�s du DriverManager
	           DriverManager.registerDriver(pilote);
	            // Protocole de connexion
	           String protocole =  "jdbc:mysql:" ;
	            // Adresse IP de la base et port
	           String ip =  "localhost" ;  // d�pend du contexte
	           String port =  "3306" ;  // port MySQL par défaut
	            // Nom de la base ;
	           String nomBase =  "boutique" ;  // d�pend du contexte
	            // Chaîne de connexion
	           String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
	            // Identifiants de connexion et mot de passe
	           String nomConnexion =  "root" ;  // d�pend du contexte
	           String motDePasse =  "" ;  // d�pend du contexte
	            // Connexion
	           con = DriverManager.getConnection(conString, nomConnexion, motDePasse) ; 
	        }  catch (Exception e) {
	            // gestion des exceptions
	        	System.out.println("ERREUR : "+e.getMessage());
	        }
    	}
		return con;
    }
    /****
     * M�thode qui retourne tous les produits sous 
     * forme d'un ResultSet
     * ***/
    public static ResultSet listeProduits()
    {
    	// Envoi d’un requête générique
        String sql =  "select * from produit where quantite!=0" ;
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
    /****
     * M�thode qui supprime un Produit � la table produit selon son identifiant
     * ***/
    public static void supprimerProduit(int id)
    {
    	//String sql="delete from produit where idProduit="+id;
    	Produit p = rechercheProduit(id);
    	String sql="update produit set quantite="+(p.getQuantite()-1)+" where idProduit="+id;
    	System.out.println(sql);
    	try {
			Statement smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /****
     * M�thode qui modifie un Produit � la table produit
     * ***/
    public static void modifierProduit(Produit p)
    {
    	String sql="update produit set nom='"+p.getNom()+"', prix="+p.getPrix()+", idCategorie="+p.getIdCategorie()+", quantite="+p.getQuantite()+" where idProduit="+p.getIdProduit();
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
     * M�thode qui ajoute un Produit � la table produit
     * ***/
    public static void ajouterProduit(Produit p)
    {
    	String sql="insert into produit (idCategorie, nom, quantite,prix) values  ("+p.getIdCategorie()+",'"+p.getNom()+"', "+p.getQuantite()+","+p.getPrix()+")";
    	System.out.println(sql);
    	try {
			Statement smt = con.createStatement();
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /****
     * M�thode qui remplit un ArrayList de tous les produits de la BDD
     * ***/
    public static ArrayList<Produit> getProduits()
    {
    	ArrayList<Produit> listeProduits=null;
    	Connexion.getCon();
    	ResultSet rs = Connexion.listeProduits();
    	try {
    		listeProduits = new ArrayList<Produit>();
			while(rs.next())
			{
				listeProduits.add(new Produit(rs.getInt("idProduit"),rs.getInt("idCategorie"),rs.getInt("quantite"),rs.getString("nom"),rs.getFloat("prix")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return listeProduits;
    }
    /*****
     * Supprime les produits de la liste de la table prosuit
     * @param liste
     */
    public static void supprimerProduits(ArrayList <Produit> liste)
    {
    	for (Produit p : liste)
    	{
    		supprimerProduit(p.getIdProduit());
    	}
    }
    
    public static Produit rechercheProduit(int id)
    {
    	Produit produit=null;
    	String sql =  "select * from produit where idProduit="+id ;
    	 ResultSet rs=null  ;
         Statement smt;
 		try {
 			smt = getCon().createStatement();
 			rs = smt.executeQuery(sql) ;
 			if(rs.next())
 				produit= new Produit(rs.getInt("idProduit"),rs.getInt("idCategorie"),rs.getInt("quantite"),rs.getString("nom"),rs.getFloat("prix"));
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	return produit;
    }
}
