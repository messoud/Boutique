package boutique.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import boutique.models.Categorie;
import boutique.models.Client;

public class CategorieDAO {
	 public static Connection getCon() {
			return (Connection) Connexion.getCon();
		}
		public static ResultSet listeCategorie()
	    {
	    	// Envoi d'une requ�te g�n�rique
	        String sql =  "select * from categorie" ;
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
		
		public static ArrayList<Categorie> lesCategories()
		{
			ArrayList<Categorie> liste = new ArrayList<Categorie>();
			ResultSet rs = listeCategorie();
			try {
				while(rs.next())
				{
					liste.add(new Categorie(rs.getInt("idCategorie"),rs.getString("nom")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(liste);
			return liste;
		}
		
		 public static Categorie rechercheCategorie(int id)
		    {
		    	Categorie categorie=null;
		    	String sql =  "select * from categorie where idCategorie="+id;
		    	 ResultSet rs=null  ;
		         Statement smt;
		 		try {
		 			smt = getCon().createStatement();
		 			rs = smt.executeQuery(sql) ;
		 			if(rs.next())
		 				categorie = new Categorie(rs.getInt("idCategorie"),rs.getString("nom"));
		 		} catch (SQLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		    	return categorie;
		    }
}
