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
                <c:set var="t" value="Bacheca" scope="request"></c:set>
                <c:set var="c" value="bacheca" scope="request"></c:set>
                <jsp:include page="nav.jsp"/>
                <a href="descrizione.html">Descrizione</a>
                <a href="login.html">Login</a>
            </nav>
        </header>
        <div id="insPost">
            <form action="Bacheca" method="get">
                <div>
                    <h2>Nuovo post su questa bacheca</h2>
                </div>
                <div>
                    <label for="stato">Testo:</label><textarea name="stato" rows="2" cols="3" id="stato"></textarea>
                </div>
                <div>
                    <label for="link">Allegato:</label><input type="url" name="link" id="link">
                </div>
                <div>
                    <div class="tipoi">
                        <input type="radio" name="tipo" id="immagine"><label for="immagine">Immagine</label>
                    </div>
                    <div class="tipou">
                        <input type="radio" name="tipo" id="url"><label for="url">Link</label>
                    </div>
                </div>
                    <div>
                    <input type="file" name="file" id="file">
                </div>
                <button type="submit">Crea post</button>
            </form>
        </div>
                <jsp:include page="side.jsp"/>
        <div id="post">
            <c:forEach var="el_post" items="${post}">
            <div>
                <div>
                    <img src="${x.urlProPic}" alt="Utente" class="utente" id="utente">
                    <label for="utente">${x.nome}</label>
                </div>
                <c:if test="${el_post.tipologia == 'TEXT'}">
                <div>
                    <p>${el_post.contenuto}</p>
                </div>
                </c:if>
                <c:if test="${el_post.tipologia == 'IMAGE'}">
                <div>
                    <img alt="Immagine" src="${el_post.contenuto}" class="postpic">
                </div>
                </c:if>
                <c:if test="${el_post.tipologia == 'URL'}">
                <div>
                    <a alt="URL" href="${el_post.contenuto}">${el_post.contenuto}</a>
                </div>
                </c:if>
            </div>
            </c:forEach>
        </div>
    </body>
</html>

