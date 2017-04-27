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
                <c:set var="t" value="Profilo" scope="request"></c:set>
                <c:set var="c" value="profilo" scope="request"></c:set>
                <jsp:include page="nav.jsp"/>
                <a href='descrizione.html'>Descrizione</a>
                <a href='login.html'>Login</a>
            </nav>
        </header>
        <jsp:include page="side.jsp"/>
        <div id="info">
            <div>
                <img src="img/ok.png" alt="utente" id="utente">
            </div>
            <form action="" method="get">
                <div>
                    <label for="nome">Nome</label> <input type='text' name="nome" id="nome">
                </div>
                <div>
                    <label for="cognome">Cognome</label><input type='text' name="cognome" id="cognome">
                </div>
                <div>
                    <label for="foto">URL immagine del profilo</label> <input type='url' name="foto" id="foto">
                </div>
                <div>
                    <label for="stato">Frase di presentazione</label> <textarea name="stato" rows="1" id="stato"></textarea>
                </div>
                <div>
                    <label for="compleanno">Data di nascita</label> <input type='date' name="compleanno" id="compleanno">
                </div>
                <div>
                    <label for="password">Password</label> <input type='password' name="password" id ="password">
                </div>
                <div>
                    <label for="cpassword">Conferma password</label><input type='password' name="cpassword" id="cpassword">
                </div>
                <div>
                    <button type="submit" value="profilo">Aggiorna</button>
                </div>
            </form>
        </div>
    </body>
</html>
