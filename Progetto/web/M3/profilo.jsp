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
            <title>Profilo NerdBook</title>
        </c:if>
        <c:if test="${negato == true}">
            <title>Accesso negato</title>
        </c:if>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Francesca Cella">
	<meta name="keywords" content="profilo nerdbook nerd amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" href="img/favicon.png" type="image/png" />
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
        <c:if test = "${erroredati == false}">
        <div id="conferma">
            <div>
                <h2>Conferma inserimento dati NerdBook</h2>
                <p>Dati inseriti correttamente!</p>
            </div>
            <div>
                <p class="dati" <c:if test="${nome == true}">id = "new" </c:if>><strong>Nome:</strong> ${user.getNome()}</p>
                <p class="dati" <c:if test="${cognome == true}">id = "new" </c:if>><strong>Cognome:</strong> ${user.getCognome()}</p>
                <p class="dati" <c:if test="${compleanno == true}">id = "new" </c:if>><strong>Data di nascita:</strong> ${user.getDataNascita()}</p>
                <p class="dati" <c:if test="${foto == true}">id = "new" </c:if>><strong>Immagine del profilo:</strong> ${user.getUrlProPic()}<img class="utente" alt="Profilo" src="${user.getUrlProPic()}"></p>
                <p class="dati" <c:if test="${stato == true}">id = "new" </c:if>><strong>Frase di presentazione:</strong> ${user.getFraseBio()}</p>
            </div>
        </div>
        </c:if>
        <c:if test="${erroredati == true}">
            <div id="conferma">
                <div><h2 class="errore">Errore nell'inserimento dei dati</h2>
                <p>Le due password inserite non corrispondono.</p></div>
            </div>
        </c:if>
        <c:if test="${chiedi == 1}">
            <div id="conferma">
            <form action="" method="post">
                <h1>Conferma cancellazione</h1>
                <p class="confermacancella">Vuoi davvero cancellare il tuo profilo NerdBook?</p>
                <div>
                    <button id="canc" type="submit" name="davvero" value="1">Conferma</button>
                </div>
            </form>
        </div>
        </c:if>
        <c:if test="${erroredati == null && chiedi == null}">
        <div id="info">
            <div>
                <img src="${user.getUrlProPic()}" alt="utente" class="utente">
            </div>
            <form action="" method="post">
                <div>
                    <label for="nome">Nome</label> <input type='text' name="nome" id="nome" placeholder="Inserire il nome">
                </div>
                <div>
                    <label for="cognome">Cognome</label><input type='text' name="cognome" id="cognome" placeholder="Inserire il cognome">
                </div>
                <div>
                    <label for="foto">URL immagine del profilo</label> <input type='url' name="foto" id="foto" placeholder="Immagine del profilo">
                </div>
                <div>
                    <label for="stato">Frase di presentazione</label> <textarea name="stato" rows="1" id="stato" placeholder="A cosa stai pensando?"></textarea>
                </div>
                <div>
                    <label for="compleanno">Data di nascita</label> <input type='text' name="compleanno" id="compleanno" placeholder="Compleanno">
                </div>
                <div>
                    <label for="password">Password</label> <input type='password' name="password" id ="password" placeholder="Inserire la nuova password">
                </div>
                <div>
                    <label for="cpassword">Conferma password</label><input type='password' name="cpassword" id="cpassword" placeholder="Confermare la nuova password">
                </div>
                <div>
                    <button id="agg" type="submit">Aggiorna</button>
                </div>
                <div>
                    <button id="canc" type="submit" name="cancella" value="1">Cancella utente</button>
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
