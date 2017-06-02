<%-- 
    Document   : listaJson
    Created on : 30-mag-2017, 10.20.09
    Author     : Asus
--%>

<%@ page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="el_utenti" items="${utenti}">
        <json:object>
            <json:property name="nome" value="${el_utenti.getNome()}"/>
            <json:property name="cognome" value="${el_utenti.getCognome()}"/>
            <json:property name="id" value="${el_utenti.getId()}"/>
            <json:property name="dataNascita" value="${el_utenti.getDataNascita()}"/>
            <json:property name="fraseBio" value="${el_utenti.getFraseBio()}"/>
            <json:property name="urlProPic" value="${el_utenti.getUrlProPic()}"/>
        </json:object>
    </c:forEach>
</json:array>
