package boutique.controlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boutique.dao.ClientDAO;
import boutique.dao.Connexion;
import boutique.models.Client;
import boutique.models.Panier;
import boutique.models.Produit;



/**
 * Servlet implementation class Dispach
 */
@WebServlet("/Dispach")
public class Dispach extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int     COOKIE_MAX_AGE            = 60 * 60 * 24 * 365;  // 1 an
    private ArrayList<Produit> listeProduits ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dispach() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    /***
     * Charge des produits � partir d'un fichier text 
     * @param nom du fichier text
     */
    /**
    void charger(String nom)
    {
    	BufferedReader lecteurAvecBuffer = null;
    	String ligne;
    	listeProduits = new ArrayList<Produit>();
    	try {
    		lecteurAvecBuffer =new BufferedReader(new FileReader(nom));
			while ((ligne = lecteurAvecBuffer.readLine()) != null)
			{
				String []valeurs = ligne.split(","); 
				listeProduits.add(new Produit(Integer.parseInt(valeurs[0]),valeurs[1],Float.parseFloat(valeurs[2])));
			}
			lecteurAvecBuffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		String nom=" ";
		nom=config.getServletContext().getRealPath("/fic.txt");
		//charger(nom);
		listeProduits=Connexion.getProduits();
	}
	/****
	 * R�cup�re le panier : les produits s�lectionn�s dans la page afficheListeProduits.jsp
	 * @param tab : contenant les identifiants des produits choisis
	 * @return un objet panier
	 */
	public Panier recupPanier(String [] tab)
	{
		Panier panier=null;
		if(tab!=null)
		{
		    panier = new Panier();
			ArrayList<Produit> listeProduits = (ArrayList<Produit>) Connexion.getProduits();
			for(String c : tab)
			{
				for(Produit p: listeProduits)
				{
					try{
						if(p.getIdProduit()==Integer.parseInt(c))
						{
							panier.addProduit(new Produit(p.getIdProduit(),p.getIdCategorie(),p.getQuantite(),p.getNom(),p.getPrix()));
							break;
						}
					}
					catch(NumberFormatException ex)
					{
						
					}
				}
			}
		}
		return panier;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession(true);
		String ok=request.getParameter("ok");
		if(ok!=null)
			session.setAttribute("sessionUtilisateur", ok);
		
		// Rajouter la recherche pour la connection
		String oper = request.getParameter("oper");
		if(oper!=null && oper.equals("connect"))
		{
			String ident = request.getParameter("ident");
			String mdp = request.getParameter("mdp");
			Client client = ClientDAO.rechercheClient(ident, mdp);
			System.out.println(client);
			if(client!=null)
			{
				session.setAttribute("client", client);
				response.sendRedirect(request.getContextPath()+"/espacePrive/afficheListeProduits.jsp");
			}
			else
				response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
		else
		{
			session.setAttribute("listeProduits",Connexion.getProduits());
			
			Panier panier = (Panier)session.getAttribute("panier");
			if(panier==null)
			{
				String panierS = TraiterPanier.getCookieValue(request,"panier");
				// Reconstituer le panier selon le cookie
				
				if(panierS!=null)
				{
					panierS = panierS.substring(0, panierS.length());
					String [] tab = panierS.split(",");
					if(tab!=null)
					{
						panier = recupPanier(tab);
						session.setAttribute("panier",panier);
					}
				}
			}
			response.sendRedirect(request.getContextPath()+"/espacePrive/afficheListeProduits.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
	public void destroy() {
		super.destroy();
	}
}
