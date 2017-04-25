<%-- 
    Document   : bacheca
    Created on : 20-apr-2017, 19.37.28
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bacheca NerdBook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
        <meta name="keywords" content="nerbook social bacheca notizie">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img/favicon.png" type="image/png" />
    </head>
    <body>
        <header>
            <nav>
                <jsp:include page="nav.jsp"/>
                <a href="descrizione.html">Descrizione</a>
                <a href="login.html">Login</a>
            </nav>
        </header>
                <jsp:include page="side.jsp"/>
        <div id="post">
            <div>
                <div>
                    <img src="" alt="Utente" class="utente">
                    <label></label>
                </div>
                <div>
                    <p></p>
                </div>
            </div>
            <div>
                <div>
                    <img src="" alt="Utente" class="utente">
                    <label></label>
                </div>
                <div>
                    <p></p>
                    <div><img src="" alt="" class="postpic"></div>
                </div>
            </div>
            <div>
                <div>
                    <img src="" alt="Utente" class="utente">
                    <label></label>
                </div>
                <div>
                    <p><a href="" ></a></p>
                </div>
            </div>
        </div>
    </body>
</html>

