<%-- 
    Document   : header
    Created on : 20-apr-2017, 20.06.57
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 id="nb">NerdBook</h1>
<c:choose>
    <c:when test="${page=='bacheca.jsp'}">
        <h1 id="corrente">Bacheca NerdBook</h1>
    </c:when>
        <c:when test="${page=='descrizione.jsp'}">
        <h1 id="corrente">About NerdBook</h1>
    </c:when>
    <c:when test="${page=='login.jsp'}">
        <h1 id="corrente">Login NerdBook</h1>
    </c:when>
    <c:when test="${page=='profilo.jsp'}">
        <h1 id="corrente">Profilo NerdBook</h1>
    </c:when>
</c:choose>
<img src="img/mok.png" alt="utente" class="logout">
<a href="login.html" class="logout">Logout</a>