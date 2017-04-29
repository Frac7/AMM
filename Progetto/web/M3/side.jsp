<%-- 
    Document   : side
    Created on : 23-apr-2017, 17.52.46
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id='barraL'>
                <div id="persone">
                    <h1>Persone</h1>
                    <div>
                        
                           <c:forEach var="el_utenti" items="${utenti}">
                            <li><img src="${el_utenti.getUrlProPic()}" alt="Utente" class="mini"><a href="bacheca.html?visualizza_bacheca=${el_utenti.getNome()}">${el_utenti.getNome()}</a></li>
                            </c:forEach>
                        
                    </div>
                </div>
                <div id="gruppi">
                    <h1>Gruppi</h1>
                    <div>
                        <ul>
                            <%--<c:forEach var="el_gruppi" items="${gruppi}">
                            <li><img src="${el_gruppi.urlProPic}" alt="Utente" class="mini">${el_gruppi.nome}</li>
                            </c:forEach>--%>
                        </ul>
                    </div>
                </div>
        </div>
