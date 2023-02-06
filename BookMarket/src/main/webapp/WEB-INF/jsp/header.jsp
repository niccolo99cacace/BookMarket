

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style><%@include file="/WEB-INF/css/headerstyle.css"%></style>
<!DOCTYPE html>
<html>
<head>
    <title>Bookshop Store - ${param.pageTitle}</title>
    <meta charset="UTF-8"> <%-- codifica dei caratteri --%>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <%--è una coppia nome valore(name(nome) content(valore)) ci serve per fare in modo che il sito sia responsive --%>
    <meta name="description" content="Ecommerce libri" />
    <link href="bare.min.css" rel="stylesheet" type="text/css">
    <script src="ricerca.js"></script>
</head>

<body>
<nav id=""body>
    <label>
        <header>
            <a id="logo" href="."><img src="img/logo/logo.jpg" alt="logo"/> Bookshop </a>
        </header>
        <ul>

            <li>   <%-- RICERCA CON AJAX --%>
                <label for="ricerca"></label>
                <form id="ricerca" action="Ricerca" method="get">
                    <select id="category" name="category">
                        <option value="" label="" selected> generi </option> <%--selected indica che l'opzione iniziale è inizialmente selezionata --%>
                        <c:forEach items="${generi}" var="genere">
                            <option value ="<c:out value="${genere.nome}"/>"> <c:out value="${genere.nome}"/> </option>
                        </c:forEach>
                    </select>                                                                 <%-- onkeyup fa partire la funzione ricerca ogni volta che viene inserito un carattere --%>
                    <input id="barraRic" type="text" name="q" autocomplete="off" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" value="<c:out value="${param.q}" />">
                    <datalist id="ricerca-datalist"></datalist> <%-- il datalist di suggertimento riempito live da AJAX con ricerca.js --%>
                    <input type="submit" value="CERCA" >
                </form>
            </li>



            <li>
                <%-- quando utente==null allora ci sarà in questa posizione il tasto "Login"
                invece quando utente!=null allora nella medesima posizione ci sara invece il tasto "Profilo"--%>
                <c:choose>
                    <c:when test="${utente == null}">
                        <a id="login" href="RegistrazioneForm"> Login </a>
                    </c:when>
                    <c:otherwise>
                        <a id="profile" href="Utente"> Profilo </a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>    <%-- se premo il bottone carrello mi porta alla Servlet CarrelloServlet --%>
                <a id="cart" href="Carrello"> Carrello </a>
            </li>
        </ul>
    </label>
</nav>