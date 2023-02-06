
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${utente.nome}"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/profiloStyle.css"%></style>
<h5 id="notifica">${notifica}</h5>
<section>
    <h4> Nome: <c:out value="${utente.nome}" /> </h4>
    <h4> E-mail: <c:out value="${utente.email}" /> </h4>

    <h5> id: <c:out value="${utente.id}" /> </h5>

    <a href="Logout">
        <button> Logout </button>
    </a>

    <a href="ModificaUtente">
        <button> Modifica informazioni</button>
    </a>

    <a href="ModificaPassword">
        <button> Modifica password </button>
    </a>
    <br>

    <%-- viene fatto il controllo per vedere se l'autore del login è l'admin
     Se è l'admin allora vengono aggiunti i bottoni "Modifica utenti" e "Inserimento prodotto --%>
    <c:if test="${utente.admin == true}">
        <a href="AdminUtenti">
            <button>Modifica utenti</button>
        </a>
        <%--
        <a href="UploadProdotto">
            <button>Inserimento prodotto</button>
        </a> --%>
    </c:if>
</section>