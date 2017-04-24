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
                <a href="descrizione.jsp">Descrizione</a>
                <a href="login.jsp">Login</a>
            </nav>
        </header>
                <jsp:include page="side.jsp"/>
        <div id="post">
            <div>
                <div>
                    <img src="img/ok.png" alt="Utente" class="utente">
                    <label>Utente 1</label>
                </div>
                <div>
                    <p>Cisco: "It looks like the Vacuum."
                   Jesse: "Uh, what's the Vacumm?"
                   Cisco: "No Fringe on Earth-2. Noted."</p>
                </div>
            </div>
            <div>
                <div>
                    <img src="img/ok.png" alt="Utente" class="utente">
                    <label>Utente 2</label>
                </div>
                <div>
                    <p>Peter, mi sbagliavo, non è troppo tardi! Puoi salvare entrambi i mondi, possiamo ricominciare daccapo. Stavolta dovrai semplicemente compiere una scelta diversa, e se qualcosa andasse storto, Olivia sarebbe la nostra ancora di salvezza...</p>
                    <div><img src="img/o.jpg" alt="Olivia" class="postpic"></div>
                </div>
            </div>
            <div>
                <div>
                    <img src="img/ok.png" alt="Utente" class="utente">
                    <label>Utente 3</label>
                </div>
                <div>
                    <p><a href="https://www.masseffect.com/it-it/andromeda-initiative" >https://www.masseffect.com/it-it/andromeda-initiative</a>Unisciti all'iniziativa Andromeda!</p>
                </div>
            </div>
        </div>
    </body>
</html>

