package boutique.controlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boutique.models.Panier;
import boutique.models.Produit;

/**
 * Servlet implementation class TraiterPanier
 */
@WebServlet(name="/TraiterPanier" ,urlPatterns="/mesServlets/panier")
public class TraiterPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraiterPanier() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * M�thode utilitaire g�rant la r�cup�ration de la valeur d'un cookie donn�
     * depuis la requ�te HTTP.
     */
    public static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && nom.equals( cookie.getName() ) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/affichePanier.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String [] tab = request.getParameterValues("choix");
		HttpSession session = request.getSession(true);
		
		Panier panier=(Panier)session.getAttribute("panier");
		if(panier!=null)
			session.removeAttribute("panier");
		panier=null;
		if(tab!=null)
		{
		    panier = new Panier();
			ArrayList<Produit> listeProduits = (ArrayList<Produit>) session.getAttribute("listeProduits");
			for(String c : tab)
			{
				for(Produit p: listeProduits)
				{
					if(p.getIdProduit()==Integer.parseInt(c))
					{
						panier.addProduit(new Produit(p.getIdProduit(),p.getIdCategorie(),p.getQuantite(),p.getNom(),p.getPrix()));
						break;
					}
				}
			}
			session.setAttribute("panier", panier);
		}
		request.getRequestDispatcher("/affichePanier.jsp").forward(request, response);
	}

}
