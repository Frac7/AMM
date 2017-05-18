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
                <jsp:include page="side.jsp"/>
        <div id="post">
            <c:if test="${f == true && x.getFraseBio() != null}">
            <div class="gr">
                <div class="gr">
                    <img src="${x.getUrlProPic()}" alt="${x.getNome()}" class="utente" class="proPic" id="utente">
                    <label for="utente">${x.getNome()}: Frase personale</label>
                </div>
                <div class="gr">
                    <p>${x.getFraseBio()}</p>
                </div>
            </div>
            </c:if>
            <c:if test="${f != true}">
            <div class="gr">
                <div class="gr">
                    <img src="${x.getUrlProPic()}" alt="${x.getNome()}" class="utente" class="proPic" id="gruppo">
                    <label for="gruppo">${x.getNome()}: Descrizione</label>
                </div>
                <div class="gr">
                    <p>${x.getDescrizione()}</p>
                </div>
            </div>
            </c:if>
            <div id="insPost">
            <form action="bacheca.html?visualizza_bacheca=${x.getId()}" method="get">
                <div>
                    <h1>Nuovo post su questa bacheca</h1>
                </div>
                <c:if test="${erroretipo == true}">
                    <div id="errati">
                        <h1>Errore inserimento post</h1>
                        <p>È stato scelto un tipo di post che prevede l'allegato e la sua tipologia.</p>
                    </div>
                </c:if>
                <c:if test="${inspost == true}">
                    <h2>Riepilogo Post</h2>
                    <p><strong>Autore:</strong> ${(n.getAutore()).getNome()}</p>
                    <p><strong>Destinatario:</strong>
                        <c:if test="${f == true}"> ${(n.getDestinatario()).getNome()}</c:if>
                        <c:if test="${f != true}"> ${(n.getGruppo()).getNome()}</c:if>
                    </p>
                    <p><strong>Messaggio:</strong> 
                        <c:if test="${multimedia == 1}">
                            <p>${n.getContenuto()}</p><img class = "postpic" alt="Post" src="${n.getAllegato()}">
                        </c:if>
                        <c:if test="${multimedia == 2}">
                        <a href="${n.getAllegato()}">${n.getAllegato()}</a><p>${n.getContenuto()}</p>
                        </c:if> 
                        <c:if test="${multimedia != 2 && multimedia != 1}">
                            ${n.getContenuto()}
                        </c:if> 
                    </p>
                    <div>
                        <c:if test="${f == true}">
                        <input type="hidden" name = "visualizza_bacheca" value="${x.getId()}"/>
                        </c:if>
                        <c:if test="${f != true}">
                        <input type="hidden" name = "visualizza_gruppo" value="${x.getId()}"/>
                        </c:if>
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
                   <c:if test="${f == true}">
                        <input type="hidden" name = "visualizza_bacheca" value="${x.getId()}"/>
                    </c:if>
                    <c:if test="${f != true}">
                        <input type="hidden" name = "visualizza_gruppo" value="${x.getId()}"/>
                    </c:if>
                </div>
                <button class="post" type="submit">Crea post</button>
                <button class="post" type="reset">Pulisci campi</button>
            </form>
                </c:if>
        </div>
            <c:forEach var="el_post" items="${post}">
            <div>
                <div>
                    <c:if test="${f == true}">
                        <img src="${x.getUrlProPic()}" alt="${x.getNome()}" class="utente" class="proPic">
                    </c:if>
                    <c:if test="${f != true}">
                        <img src="${el_post.getAutore().getUrlProPic()}" alt="${x.getNome()}" class="utente" class="proPic" id="utente">
                    </c:if>
                    <label for="utente">
                        <c:if test="${f == true && el_post.getDestinatario() == null && el_post.getGruppo() == null}">${x.getNome()}</c:if> <%--nome utente bacheca utente--%>
                        <c:if test="${f == true && el_post.getDestinatario() != null}">${el_post.getAutore().getNome()}: ${x.getNome()}</c:if> <%--nome utente bacheca utente--%>
                        <c:if test="${f == true && el_post.getGruppo() != null}">${x.getNome()}: ${(el_post.getGruppo()).getNome()}</c:if>
                        <c:if test="${f != true}">${el_post.getAutore().getNome()}: ${x.getNome()}</c:if>
                    </label>
                </div>
                <c:if test="${el_post.getTipologia() == 'TEXT'}">
                <div>
                    <p>${el_post.getContenuto()}</p>
                </div>
                </c:if>
                <c:if test="${el_post.getTipologia() == 'IMAGE'}">
                <div>
                    <p>${el_post.getContenuto()}</p><img alt="Immagine" src="${el_post.getAllegato()}" class="postpic">
                </div>
                </c:if>
                <c:if test="${el_post.getTipologia() == 'URL'}">
                <div>
                    <a alt="URL" href="${el_post.getAllegato()}">${el_post.getAllegato()}</a><p>${el_post.getContenuto()}</p>
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

