
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Modifica Utente"/>
</jsp:include>
<section>
    <h1>Modifica Utente</h1>
    <form action="AdminUtenti" method="post">
        <input type="hidden" name="id" value="${cliente.id}">
        <label>Nome</label>
        <input type="text" name="nome" value="${cliente.nome}" required>
        <label>e-mail</label>
        <input type="text" name="email" value="${cliente.email}" required>
        <label>Passwordhash</label>
        <input type="text" name="passwordhash" value="${cliente.passwordhash}"disabled>
        <label> nuova password </label>
        <input type="text" name="password">

        <c:if test="${cliente != null}">
            <input type="submit" name="modifica" value="Modifica">
        </c:if>
    </form>
</section>
<%@include file="footer.html"%>