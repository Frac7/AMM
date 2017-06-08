<%-- 
    Document   : side
    Created on : 23-apr-2017, 17.52.46
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id='barraL'>
                <div id="ricerca">
                    <h1>Ricerca</h1>
                    <div id="search">
                        <input type="text" class="ricerca_testo" placeholder="Cerca Amici Nerd..." onkeyup=cerca()>
                        <button class="ricerca_bottone">Cerca</button>
                    </div>
                </div>
                <div id="persone">
                    <h1>Persone</h1>
                    <div id="utenti">
                        <ul>
                            <div id="js">
                                <c:forEach var="el_utenti" items="${utenti}">
                                <li><img src="${el_utenti.getUrlProPic()}" alt="Utente" class="mini"><a href="bacheca.html?visualizza_bacheca=${el_utenti.getId()}">${el_utenti.getNome()} ${el_utenti.getCognome()}</a></li>
                                </c:forEach>
                            </div>
                        </ul>
                    </div>
                </div>
                <div id="gruppi">
                    <h1>Gruppi</h1>
                    <div>
                        <ul>
                            <c:forEach var="el_gruppi" items="${gruppi}">
                                <li><img src="${el_gruppi.getUrlProPic()}" alt="Utente" class="mini"><a href="bacheca.html?visualizza_gruppo=${el_gruppi.getId()}">${el_gruppi.getNome()}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
        </div>
