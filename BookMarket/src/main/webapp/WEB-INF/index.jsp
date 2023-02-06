

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/homestyle.css"%></style>
<section id="StoreDescr">
    <h1> Benvenuto, scopri la nostra selezione di libri  </h1>

</section>

<section id="sect">
    <h2> Catalogo</h2>

    <grid>
        <c:forEach items = "${prodotti}" var = "prodotto">
            <div class="book"  col = "1/4"> <%-- col è un attributo inventato--%>
                <h3>
                    <a class="titolo" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out value="${prodotto.titolo}"/> </a>
                </h3>
                <a class="pic" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img class="pic" src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg" alt="Libro_<c:out value="${prodotto.id}"/>"> </a>
                <h5><a href="Autore?id=<c:out value="${prodotto.autore.id}"/>">  <c:out value="${prodotto.autore.nome}"/>  <c:out value="${prodotto.autore.cognome}"/></a></h5>
                <h4> Prezzo: <c:out value="${prodotto.prezzoEuro}" /> &euro; </h4>
            </div>
        </c:forEach>
    </grid>

    <h2> Ultimi arrivi</h2>

    <grid>
        <c:forEach items = "${ultimi}" var = "prodotto">
            <div class="book" col = "1/4">
                <h3>
                    <a class="titolo" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out value="${prodotto.titolo}"/> </a>
                </h3>
                <a class="pic" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img class="pic" src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg" alt="Libro_<c:out value="${prodotto.id}"/>"> </a>
                <h5><a href="Autore?id=<c:out value="${prodotto.autore.id}"/>">  <c:out value="${prodotto.autore.nome}"/>  <c:out value="${prodotto.autore.cognome}"/></a></h5>
                <h4> Prezzo: <c:out value="${prodotto.prezzoEuro}" /> &euro; </h4>
            </div>
        </c:forEach>
    </grid>
</section>

<%@include file="jsp/footer.html"%>
