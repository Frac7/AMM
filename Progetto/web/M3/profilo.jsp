<%-- 
    Document   : profilo
    Created on : 20-apr-2017, 19.37.52
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profilo NerdBook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
	<meta name="keywords" content="profilo nerdbook nerd amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img/favicon.png" type="image/png" />
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="nav.jsp"/>
                <a href='descrizione.jsp'>Descrizione</a>
                <a href='login.jsp'>Login</a>
            </nav>
        </header>
        <jsp:include page="side.jsp"/>
        <div id="info">
            <div>
                <img src="img/ok.png" alt="utente" id="utente">
            </div>
            <form>
                <div>
                    <label for="nome">Nome</label> <input type='text' id="nome">
                </div>
                <div>
                    <label for="cognome">Cognome</label><input type='text' id="cognome">
                </div>
                <div>
                    <label for="foto">URL immagine del profilo</label> <input type='url' id="foto">
                </div>
                <div>
                    <label for="stato">Frase di presentazione</label> <textarea id="stato" rows="1"></textarea>
                </div>
                <div>
                    <label for="compleanno">Data di nascita</label> <input type='date' id="compleanno">
                </div>
                <div>
                    <label for="password">Password</label> <input type='password' id="password">
                </div>
                <div>
                    <label for="cpassword">Conferma password</label><input type='password' id="cpassword">
                </div>
                <div>
                    <button type="submit">Aggiorna</button>
                </div>
            </form>
        </div>
    </body>
</html>
