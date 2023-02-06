<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${autore.nome.charAt(0)}.${autore.cognome}"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/autoreStyle.css"%></style>
<section>
    <grid class="g">

        <div col="1/4">
            <img src="img/autori/${autore.id}.jpg" alt="Autore_${prodotto.id}"/>
        </div>

        <div class="autore" col="3/4">
            <h3 style="font-weight: bold">${autore.nome} ${autore.cognome}</h3>

            <c:forEach items = "${prodotti}" var = "prodotto">
                <div col = "1/3">
                    <h3>
                        <a href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out value="${prodotto.titolo}"/> </a>
                    </h3>
                    <a href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg" alt="Libro_<c:out value="${prodotto.id}"/>"> </a>
                    <h4> Prezzo: <c:out value="${prodotto.prezzoEuro}" /> &euro; </h4>
                </div>
            </c:forEach>
        </div>

        <div col="2/3" txt="c" style="background-color:#E8E8E8">  <%-- "&larr;" sarebbe il modo di rappresentare la freccia a sinistra --%>
            <%-- ritorna alla stessa servlet i parametri aggiornati--%>
            <%--si fa il controllo poischè se la pagina è unica e oppure se stiamo alla prima la freccia a sinistra nn ci vuole
            , la freccia ci sarà lo stesso ma nn adotterà la funzione di ancora (stesso discorso,ma inverso, per freccia a sinistra)--%>
            <a <c:if test="${pag > 1}">href="Autore?id=${param.id}&pag=${pag - 1}&perpag=${perpag}"</c:if>>&larr;</a>
            &emsp;
            <c:forEach begin="1" end="${npag}" varStatus="loop"> <%--genera i numeri di tutte le pagine --%>
                <c:choose>
                    <c:when test="${loop.index == pag}"><%--se è il corrispondente della pagina è in grassetto --%>
                        <b>${loop.index}</b>
                    </c:when>
                    <c:otherwise><%--se no è un ancora(giustemente dobbiamo poter premere sulle altre pagine) --%>
                        <a href="Autore?id=${param.id}&pag=${loop.index}&perpag=${perpag}">${loop.index}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <a <c:if test="${pag < npag}">href="?id=${param.id}&pag=${pag + 1}&perpag=${perpag}"</c:if>>&rarr;</a>
        </div>


    </grid>
</section>
<%@include file="footer.html"%>
