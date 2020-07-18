<%@page import="boutique.dao.CategorieDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*, boutique.models.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 
Produit prod=new Produit(0,0,0,"rien",0);
String op=request.getParameter("op");
String nomOp="";
ArrayList<Categorie> categories=null;
if(op.equals("mod"))
{
	categories = boutique.dao.CategorieDAO.lesCategories();
	String idS=request.getParameter("id");
	int id=0;
	if(idS!=null)
		 id=Integer.parseInt(idS);
	System.out.println("id="+id);
	ArrayList<Produit> listeProduits = (ArrayList<Produit>)session.getAttribute("listeProduits");
	
	for(Produit p:listeProduits)
		if (id==p.getIdProduit())
		{
			prod = p;
			break;
		}
	out.println("<h1>Modifier Produit</h1>");
	nomOp="Modifier";
}
if(op.equals("ajout"))
{
	out.println("<h1>Ajouter Produit</h1>");
	nomOp="Ajouter";
}
%>
<form action="../mesServlets/operProduit?op=<%=op %>&id=<%=prod.getIdProduit()%>" method="post">
	Nom : <input type="text" name="nom" value="<%=prod.getNom() %>" /> <br />
	Catégorie : <select name="Categorie">
	<%
	for(Categorie c:categories)
	{
		String select = "";
		if(c.getIdCategorie()==prod.getIdCategorie())
			select="selected";
		
		%>
		<option value="<%=c.getIdCategorie() %>" <%=select %>><%=c.getNom()%> </option>
		<%
	}
	%>
	</select>
	<br />
	Prix : <input type="text" name="prix" value="<%=""+prod.getPrix() %>" /><br />
	
	Quantite : <input type="text" name="quantite" value="<%=""+prod.getQuantite() %>" /><br />
	<input type="submit" value="<%=nomOp %>" >
</form>
</body>
</html>