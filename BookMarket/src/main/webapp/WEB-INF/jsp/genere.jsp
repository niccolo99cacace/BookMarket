<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${genere.nome}"/>
</jsp:include>

<section>
    <h2> ${genere.nome}</h2>
    <grid>
           <c:forEach items = "${prodotti}" var = "prodotto">
            <div col = "1/4">
                <h3>
                    <a href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out value="${prodotto.titolo}"/> </a>
                </h3>
                <a href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg" alt="Libro_<c:out value="${prodotto.id}"/>"> </a>
                <h5><a href="Autore?id=<c:out value="${prodotto.autore.id}"/>">  <c:out value="${prodotto.autore.nome}"/>  <c:out value="${prodotto.autore.cognome}"/></a></h5>
                <h4> Prezzo: <c:out value="${prodotto.prezzoEuro}" /> &euro; </h4>
            </div>
            </c:forEach>
        <%-- stesso ragionamento di autore.jsp --%>
        <div col="2/3" txt="c" style="background-color:#E8E8E8">
            <a <c:if test="${pag > 1}">href="?id=${param.id}&pag=${pag - 1}&perpag=${perpag}&ord=${ord}"</c:if>>&larr;</a>
            &emsp;
            <c:forEach begin="1" end="${npag}" varStatus="loop">
                <c:choose>
                    <c:when test="${loop.index == pag}">
                        <b>${loop.index}</b>
                    </c:when>
                    <c:otherwise>
                        <a href="?id=${param.id}&pag=${loop.index}&perpag=${perpag}&ord=${ord}">${loop.index}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            &emsp;
            <a <c:if test="${pag < npag}">href="?id=${param.id}&pag=${pag + 1}&perpag=${perpag}&ord=${ord}"</c:if>>&rarr;</a>
        </div>

    </grid>
</section>
<%@include file="footer.html"%>
