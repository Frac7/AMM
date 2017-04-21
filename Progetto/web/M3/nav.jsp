<%-- 
    Document   : header
    Created on : 20-apr-2017, 20.06.57
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav>
    <c:if test="${page=='bacheca.html'}">id="corrente"</c:if><h1>Bacheca NerdBook</h1>
    <c:if test="${page=='descrizione.html'}">id="corrente"</c:if><h1>About NerdBook</h1>
    <c:if test="${page=='profilo.html'}">id="corrente"</c:if><h1>Profilo NerdBook</h1>
    <c:if test="${page=='login.html'}">id="corrente"</c:if><h1>Login NerdBook</h1>
</nav>
