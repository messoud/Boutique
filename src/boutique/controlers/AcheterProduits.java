package boutique.controlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Servlet implementation class AcheterProduits
 */
@WebServlet("/AcheterProduits")
public class AcheterProduits extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcheterProduits() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Panier panier = (Panier)session.getAttribute("panier");
		Client client = (Client)session.getAttribute("client");
		for(Produit p : panier.getListe())
		{
			Connexion.supprimerProduit(p.getIdProduit());
		}
		ClientDAO.acheterProduits(client, panier.getListe());
		ArrayList<Produit> listeProduits = Connexion.getProduits();
		session.setAttribute("listeProduits",listeProduits);
		response.sendRedirect(request.getContextPath()+"/espacePrive/afficheListeProduits.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
