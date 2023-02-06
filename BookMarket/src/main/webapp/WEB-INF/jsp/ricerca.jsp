
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${param.q}"/>
</jsp:include>
<section>
    <grid>
        <c:forEach items="${prodotti}" var="prodotto">
            <div class="colonna" col="1/3">
                <a href="Prodotto?id=${prodotto.id}"><img src="img/prodotti/${prodotto.id}.jpg"></a>
            </div>
            <div class="colonna" col="2/3">
                <h3>
                    <a href="Prodotto?id=${prodotto.id}">${prodotto.titolo}</a>
                </h3>
                <h4>
                    <a href="Autore?id=${prodotto.autore.id}"> ${prodotto.autore.nome.charAt(0)}.${prodotto.autore.cognome}</a>
                </h4>
                <a href="Genere?id=${prodotto.genere}">${prodotto.genere}</a>
                <h4>Prezzo: ${prodotto.prezzoEuro} &euro;</h4>
            </div>
        </c:forEach>
        <c:if test="${empty prodotti}"> //se è stato cercato un libro che nn ci sta , oppure è stato cercato un libro impostando il genere sbagliato
            <div col="1">Nessun prodotto trovato.</div>
        </c:if>
    </grid>
</section>
<%@include file="footer.html"%>
