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
        <c:if test="${negato == false}">
            <title>Bacheca NerdBook  ${x.getNome()}</title>
        </c:if>
        <c:if test="${negato == true}">
            <title>Accesso negato</title>
        </c:if>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
        <meta name="keywords" content="nerbook social bacheca notizie">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img/favicon.png" type="image/png" />
    </head>
    <body>
        <c:if test="${negato == false}">
        <header>
            <nav>
                <c:set var="t" value="Bacheca" scope="request"></c:set>
                <c:set var="c" value="bacheca" scope="request"></c:set>
                <jsp:include page="nav.jsp"/>
            </nav>
        </header>
        <div id="insPost">
            <form action="bacheca.html?visualizza_bacheca=${x.getNome()}" method="get">
                <div>
                    <h1>Nuovo post su questa bacheca</h1>
                </div>
                <c:if test="${erroretipo == true}">
                    <div id="errati">
                        <h1>Errore inserimento post</h1>
                        <p>È stato scelto un tipo di post che prevede esclusivamente l'allegato e la sua tipologia.</p>
                    </div>
                </c:if>
                <c:if test="${inspost == true}">
                    <h2>Riepilogo Post</h2>
                    <p><strong>Autore:</strong> ${(n.getAutore()).getNome()}</p>
                    <p><strong>Destinatario:</strong> ${(n.getDestinatario()).getNome()}</p>
                    <p><strong>Messaggio:</strong> 
                        <c:if test="${multimedia == 1}">
                            <img alt="Post" src="${n.getContenuto()}">
                        </c:if>
                        <c:if test="${multimedia == 2}">
                            <a href="${n.getContenuto()}">Link</a>
                        </c:if> 
                        <c:if test="${multimedia != 2 && multimedia != 1}">
                            ${n.getContenuto()}
                        </c:if> 
                    </p>
                    <div>
                        <input type="hidden" name = "visualizza_bacheca" value="${x.getNome()}"/>
                    </div>
                    <button class = "post" type ='submit' name="conferma" value=true>Confermare</button>
                    <button class = "post" type ="submit" name="conferma" value=false>Annullare</button>
                </c:if>
                <c:if test="${conferma == true}">
                    <h2>Hai scritto sulla bacheca di ${x.getNome()}</h2>
                </c:if>
                <c:if test="${inspost != true}">
                <div>
                    <label for="stato">Testo:</label><textarea name="stato" rows="2" cols="3" id="stato" placeholder="Inserire il testo del post"></textarea>
                </div>
                <div>
                    <label for="link">Allegato:</label><input type="url" name="link" id="link" placeholder="Inserire il link dell'allegato">
                </div>
                <div>
                    <div class="tipoi">
                        <input type="radio" name="tipo" value="imm" id="immagine"><label for="immagine">Immagine</label>
                    </div>
                    <div class="tipou">
                        <input type="radio" name="tipo" value="url" id="url"><label for="url">Link</label>
                    </div>
                </div>
                <div>
                   <input type="hidden" name = "visualizza_bacheca" value="${x.getNome()}"/>
                </div>
                <button class="post" type="submit">Crea post</button>
                <button class="post" type="reset">Pulisci campi</button>
            </form>
                </c:if>
        </div>
                <jsp:include page="side.jsp"/>
        <div id="post">
            <c:forEach var="el_post" items="${post}">
            <div>
                <div>
                    <img src="${x.getUrlProPic()}" alt="${x.getNome()}" class="utente" id="utente">
                    <label for="utente">${x.getNome()}</label>
                </div>
                <c:if test="${el_post.getTipologia() == 'TEXT'}">
                <div>
                    <p>${el_post.getContenuto()}</p>
                </div>
                </c:if>
                <c:if test="${el_post.getTipologia() == 'IMAGE'}">
                <div>
                    <img alt="Immagine" src="${el_post.getContenuto()}" class="postpic">
                </div>
                </c:if>
                <c:if test="${el_post.getTipologia() == 'URL'}">
                <div>
                    <a alt="URL" href="${el_post.getContenuto()}">${el_post.getContenuto()}</a>
                </div>
                </c:if>
            </div>
            </c:forEach>
        </div>
        </c:if>
        <c:if test="${negato == true}">
            <h1>Accesso negato</h1>
            <p>Non si disponde delle autorizzazioni necessarie per accedere alla
                pagina.</p>
        </c:if>
    </body>
</html>
