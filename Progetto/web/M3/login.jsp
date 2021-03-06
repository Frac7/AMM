<%-- 
    Document   : login
    Created on : 24-apr-2017, 21.12.12
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login NerdBook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
	<meta name="keywords" content="login nerdbook nerd amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        <header>
            <nav>
                <a href="descrizione.html">Descrizione</a>
                <a href="profilo.html">Profilo</a>
                <a href="bacheca.html">Bacheca</a>
            </nav>
            <h1 id="Ltitle">Login NerdBook</h1>
        </header>
        <div id="dati">
            <form action="" method="post">
                <c:if test="${errore == true}">
                <div id="errati">
                     <h2 class="errore">Errore autenticazione</h2>
                     <p>Username o password errati.</p>
                </div>
                </c:if>
                <div>
                    <label for="username">Username</label> <input type="text" name="user" id="username" placeholder="Inserire il nome utente">
                </div>
                <div>
                    <label for="password">Password</label> <input type="password" name="password" id="password" placeholder="Inserire la password">
                </div>
                <div>
                    <button type="submit" value="login">Login</button>
                </div>
            </form>
        </div>
    </body>
</html>



