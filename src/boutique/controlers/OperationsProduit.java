package boutique.controlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boutique.dao.Connexion;
import boutique.models.Produit;

/**
 * Servlet implementation class OperationsProduit
 */
@WebServlet(name="/OperationsProduit", urlPatterns="/mesServlets/operProduit")  
public class OperationsProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationsProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		HttpSession session=request.getSession(true);
		if(op.equals("sup"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			Connexion.supprimerProduit(id);
			ArrayList<Produit>listeProduits=Connexion.getProduits();
			session.setAttribute("listeProduits", listeProduits);
			//request.getRequestDispatcher("/espacePrive/afficheListeProduits.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/espacePrive/afficheListeProduits.jsp");
		}
		if(op.equals("mod"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			int idC=Integer.parseInt(request.getParameter("Categorie"));
			int quantite =Integer.parseInt(request.getParameter("quantite"));
			String nom=request.getParameter("nom");
			float prix=Float.parseFloat(request.getParameter("prix"));
			
			Produit prod=new Produit(id,idC,quantite,nom,prix);
			Connexion.modifierProduit(prod);
			ArrayList<Produit>listeProduits=Connexion.getProduits();
			session.setAttribute("listeProduits", listeProduits);
			//request.getRequestDispatcher("/espacePrive/afficheListeProduits.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/espacePrive/afficheListeProduits.jsp");
		}
		if(op.equals("ajout"))
		{
			String nom=request.getParameter("nom");
			float prix=Float.parseFloat(request.getParameter("prix"));
			int idC=Integer.parseInt(request.getParameter("idCategorie"));
			int quantite =Integer.parseInt(request.getParameter("quantite"));
			Produit prod=new Produit(0,idC,quantite,nom,prix);
			Connexion.ajouterProduit(prod);
			ArrayList<Produit>listeProduits=Connexion.getProduits();
			session.setAttribute("listeProduits", listeProduits);
			//request.getRequestDispatcher("/espacePrive/afficheListeProduits.jsp").forward(request, response);
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

}
