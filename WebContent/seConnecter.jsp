<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"  type="text/css" href="styles.css" />
<title>Se connecter</title>
</head>
<body>
<h1>Veuillez vous connecter :</h1>
<form action="Dispach?oper=connect" method="post">
<table> 
  <caption>Se connecter</caption>
<tr><td>Identifiant</td><td><input type="text" name="ident"></td></tr>
<tr><td>Mot de passe</td><td><input type="text" name="mdp"></td></tr>
<tr><td colspan="2"> <input type="submit" value="Se connecter"></td></tr>
</table>
</form>
</body>
</html>