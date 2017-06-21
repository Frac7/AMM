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
        <c:if test="${negato == false}">
            <title>Crea gruppo NerdBook</title>
        </c:if>
        <c:if test="${negato == true}">
            <title>Accesso negato</title>
        </c:if>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
	<meta name="keywords" content="profilo nerdbook nerd amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <script src="../js/jquery-3.2.1.min.js"></script>
        <script src="../js/javascript.js"></script>
    </head>
    <body>
        <c:if test="${negato == false}">
        <header>
            <nav>
                <c:set var="t" value="Profilo" scope="request"></c:set>
                <c:set var="c" value="profilo" scope="request"></c:set>
                <jsp:include page="nav.jsp"/>
            </nav>
        </header>
        <jsp:include page="side.jsp"/>
        <c:if test="${erroredati == true}">
            <h1>Errore</h1>
            <p>Si è verificato un errore nella creazione del gruppo.</p>
        </c:if>
        <c:if test = "${erroredati == false}">
        <div id="conferma">
            <div>
                <h2>Conferma creazione gruppo NerdBook</h2>
                <p>Creazione avvenuta correttamente!</p>
            </div>
            <div>
                <p class="dati" <c:if test="${nome == true}">id = "new" </c:if>><strong>Nome:</strong> ${g.getNome()}</p>
                <p class="dati" <c:if test="${descrizione == true}">id = "new" </c:if>><strong>Descrizione:</strong> ${g.getDescrizione()}</p>
                <p class="dati" <c:if test="${foto == true}">id = "new" </c:if>><strong>Immagine del gruppo:</strong> ${g.getUrlProPic()}<img class="utente" alt="Profilo" src="${g.getUrlProPic()}"></p>
            </div>
        </div>
        </c:if>
        <c:if test="${erroredati == null}">
        <div id="info">
            <form action="" method="post">
                <div>
                    <label for="nome">Nome</label> <input type='text' name="nome" id="nome" placeholder="Inserire il nome">
                </div>
                <div>
                    <label for="foto">URL immagine del gruppo</label> <input type='url' name="foto" id="foto" placeholder="Immagine del gruppo">
                </div>
                <div>
                    <label for="compleanno">Descrizione</label> <input type='text' name="descrizione" placeholder="Descrizione">
                </div>
                <div>
                    <button id="crea" type="submit">Crea gruppo</button>
                </div>
            </form>
        </div>
        </c:if>
        </c:if>
        <c:if test="${negato == true}">
            <h1 class="negato">Accesso negato</h1>
            <p class="negato">Non si disponde delle autorizzazioni necessarie per accedere alla
                pagina.</p>
        </c:if>
    </body>
</html>
