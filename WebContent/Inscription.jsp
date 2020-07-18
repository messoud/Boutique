<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"  type="text/css" href="styles.css" />
<title>Inscription</title>
</head>
<body>
<form action="AjouterClient" method="post">
<table> 
  <caption>Ajouter un client</caption>

<tr><td>Nom</td><td><input type="text" name="nom"></td></tr> 
<tr><td>Prénom</td><td><input type="text" name="prenom"></td></tr>
<tr><td>Identifiant</td><td><input type="text" name="ident"></td></tr>
<tr><td>Mot de passe</td><td><input type="text" name="mdp"></td></tr>
<tr><td>Email</td><td><input type="text" name="email"></td></tr>
<tr><td colspan="2"> <input type="submit" value="Ajouter client"></td></tr>
</table>
</form>
</body>
</html>