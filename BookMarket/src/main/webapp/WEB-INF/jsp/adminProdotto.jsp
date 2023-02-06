<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' :  (prodotto == null ? 'Aggiungi' : 'Modifica')}"/>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${operazione} prodotto"/>
</jsp:include>
<section>
    <h1>${operazione} prodotto</h1> <%-- questa {operazione} assume il valore dell'operazione che deve essere svolta,
    ovvero rimzione(prodotto rimosso) oppure modifica --%>
    <h5>${notifica}</h5>
    <c:if test="${param.rimuovi == null}">
        <form action="AdminProdotto" method="post">
            <input type="hidden" name="id" value="${prodotto.id}">
            <label>Titolo</label>
            <input type="text" name="titolo" value="${prodotto.titolo}">
            <label>Descrizione</label>
            <textarea name="descrizione">${prodotto.descrizione}</textarea>
            <label>Prezzo</label>
            <input type="number" name="prezzoCent" value="${prodotto.prezzoCent}">
            <label>Numero copie disponibili:</label>
            <input type="number" name="copie" min="0" value="${prodotto.copie}">
            <c:if test="${prodotto != null}">
                <input type="submit" name="modifica" value="Modifica">
            </c:if>
        </form>
    </c:if>
</section>
<%@include file="footer.html"%>