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
        <title>Conferma pubblicazione post NerdBook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img/favicon.png" type="image/png" />
    </head>
    <body>
        <header>
            <nav>
                <a href="riepilogopost.jsp">Ok!</a>
            </nav>
            <h1>Riepilogo post NerdBook</h1>
        </header>
            Autore: <c:out value="${z}"/>
            Messaggio: <c:out value="${messaggio}"/>
            <a href="okpost.jsp">Conferma...</a></p>
    </body>
</html>
