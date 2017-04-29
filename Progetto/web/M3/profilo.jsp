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
    </head>
    <body>
        <c:if test="${negato == false}">
        <header>
            <nav>
                <c:set var="t" value="Profilo" scope="request"></c:set>
                <c:set var="c" value="profilo" scope="request"></c:set>
                <jsp:include page="nav.jsp"/>
                <a href='descrizione.html'>Descrizione</a>
                <a href='login.html'>Login</a>
            </nav>
        </header>
        <jsp:include page="side.jsp"/>
            <c:if test="${erroredati == false}">
                <div>
                    <h1>Conferma inserimento dati NerdBook</h1>
                    <p>Dati inseriti correttamente!</p>
                </div>
            <div>
                <table>
                <tr>
                    <td>
                        <p>Nome</p>
                    </td>
                    <td>
                        <p><c:out value="${utente.getNome()}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Cognome</p>
                    </td>
                    <td>
                        <p><c:out value="${utente.getCognome()}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Data di nascita</p>
                    </td>
                    <td>
                        <p><c:out value="${utente.getDataNascita()}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>URL immagine del profilo</p>
                    </td>
                    <td>
                        <p><c:out value="${utente.getUrlProPic()}"/></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Frase di presentazione</p>
                    </td>
                    <td>
                        <p><c:out value="${utente.getFraseBio()}"/></p>
                    </td>
                </tr>
            </table>
            </div>
            </c:if>
        <c:if test="${erroredati == true}">
            <div>
                    <h1>Errore nell'inserimento dei dati</h1>
                <p>Le due password inserite non corrispondono.</p>
            </div>
        </c:if>
        <div id="info">
            <div>
                <img src="${user.getUrlProPic()}" alt="utente" id="utente">
            </div>
            <form action="" method="get">
                <div>
                    <label for="nome">Nome</label> <input type='text' name="nome" id="nome">
                </div>
                <div>
                    <label for="cognome">Cognome</label><input type='text' name="cognome" id="cognome">
                </div>
                <div>
                    <label for="foto">URL immagine del profilo</label> <input type='url' name="foto" id="foto">
                </div>
                <div>
                    <label for="stato">Frase di presentazione</label> <textarea name="stato" rows="1" id="stato"></textarea>
                </div>
                <div>
                    <label for="compleanno">Data di nascita</label> <input type='date' name="compleanno" id="compleanno">
                </div>
                <div>
                    <label for="password">Password</label> <input type='password' name="password" id ="password">
                </div>
                <div>
                    <label for="cpassword">Conferma password</label><input type='password' name="cpassword" id="cpassword">
                </div>
                <div>
                    <button type="submit" value="profilo">Aggiorna</button>
                </div>
            </form>
        </div>
        </c:if>
        <c:if test="${negato == true}">
            <h1>Accesso negato</h1>
            <p>Non si disponde delle autorizzazioni necessarie per accedere alla
                pagina.</p>
        </c:if>
    </body>
</html>
