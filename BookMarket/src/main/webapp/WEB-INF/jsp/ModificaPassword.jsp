
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Modifica Password"/>
</jsp:include>
<section>
    <h1>Cambiare Password</h1>
    <form action="ModificaPassword" method="post">
        <input type="hidden" name="id" value="${utente.id}">
        <label>Vecchia password</label>
        <input type="password" name="password" required>
        <label>Nuova password</label>
        <input type="password" name="nuovapassword" required>

        <c:if test="${utente != null}"><%--viene fatto un ulteriore controllo per vedere se l'utente è registrato già --%>
            <input type="submit" name="modifica" value="Modifica">
        </c:if>
    </form>
</section>
<%@include file="footer.html"%>
