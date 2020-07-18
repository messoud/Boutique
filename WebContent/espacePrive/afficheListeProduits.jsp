<%@page import="boutique.dao.ClientDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="boutique.models.*,boutique.dao.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet"  type="text/css" href="../styles.css" />
 
<title>Affiche liste des produits</title>
</head>
<body>
<%
ArrayList<Produit> listeProduits = (ArrayList<Produit>)session.getAttribute("listeProduits");
Panier panier=(Panier)session.getAttribute("panier");
Client client = (Client) session.getAttribute("client");
%>

Bonjour <!-- Mettre le nom et prénom du client connecté --> 
<%=client.getNom() %> <%=client.getPrenom() %>
<br/>

<form action="" method="get">

<%
/**
Rajouter une liste (un select) pour afficher tous les clients
*/
%>

</form>
<form action="../mesServlets/panier" method="post">
<table> 
  <caption>Liste des produits</caption>
  <tr><th></th><th>Catégorie</th><th>Désignation</th><th>Prix</th><th>Quantité</th><th></th><th></th></tr>
<%
for(Produit p : listeProduits)
{
	Categorie  categorie = CategorieDAO.rechercheCategorie(p.getIdCategorie());

%>


<tr>
	<td id="case"><input type="checkbox" name="choix" value="<%=p.getIdProduit() %>"  ></td>
	<td id="idCategorie"><%=categorie.getNom() %></td>
	<td id="nom"><%=p.getNom() %></td>
	
	<td><%=p.getPrix() %></td>
	<td id="quantite"><%=p.getQuantite() %></td>
	<td><a href="ModifierProduit.jsp?op=mod&id=<%=p.getIdProduit() %>" ><img src="../images/modifier.png" width="30" /></a></td>
	<td><a href="../mesServlets/operProduit?op=sup&id=<%=p.getIdProduit() %>"><img src="../images/supprimer.png" width="30"/></a></td>
</tr>
<%} %>
<tr><td colspan="7"><input type="submit" value="monPanier"></td></tr>
</table>
</form>
<a href="../index.jsp">Accueil</a>
</body>
</html>