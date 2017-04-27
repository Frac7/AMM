<%-- 
    Document   : errore
    Created on : 25-apr-2017, 13.47.59
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Conferma inserimento dati NerdBook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img/favicon.png" type="image/png" />
    </head>
    <body>
        <header>
            <nav>
                <a href="conferma.jsp">Ok!</a>
            </nav>
            <h1>Conferma inserimento dati NerdBook</h1>
        </header>
            <p>Dati inseriti correttamente!</p>
            <table>
                <tr>
                    <td>
                        <p>Nome</p>
                    </td>
                    <td>
                        <p><c:out value="${nome}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Cognome</p>
                    </td>
                    <td>
                        <p><c:out value="${cognome}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Data di nascita</p>
                    </td>
                    <td>
                        <p><c:out value="${compleanno}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>URL immagine del profilo</p>
                    </td>
                    <td>
                        <p><c:out value="${urlProPic}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Frase di presentazione</p>
                    </td>
                    <td>
                        <p><c:out value="${stato}"/></p>
                    </td>
                </tr>
            </table>
                    <p><a href="profilo.html">Continuare...</a></p>
    </body>
</html>