<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet"  type="text/css" href="../styles.css" />
<title>Insert title here</title>
 <link rel="stylesheet"  type="text/css" href="styles.css" />
</head>
<body>

<h1>Les produits choisis</h1>
<jsp:useBean id="panier" class="boutique.models.Panier" scope="session" />

<table> 
  <caption>Liste des produits</caption>
  <tr><th>id</th><th>Désignation</th><th>Prix</th></tr>
<c:set var="montant" value="0"></c:set>
<c:forEach var="p" items="${panier.liste}"><p>
<c:set var="montant" value="${montant+p.prix }"></c:set>
<tr>
	<td><c:out value="${p.idProduit}"></c:out></td>
	<td><c:out value="${p.nom}"></c:out></td>
	<td><c:out value="${p.prix}"></c:out></td>
</tr>
</c:forEach>

<tr><td colspan="3"><fmt:formatNumber value="${montant }" pattern="#,#00.0#" type=""/></td></tr>
</table>

<a href="../AcheterProduits" > ACHETER</a><br />
<a href="../index.jsp">Accueil</a>
</body>
</html>