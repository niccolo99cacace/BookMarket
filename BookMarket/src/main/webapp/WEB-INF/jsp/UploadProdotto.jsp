
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Inserimento nuovo Prodotto"/>
</jsp:include>
<h5 id="notifica">${notifica}</h5>
<section>
    <h1>Inserimento nuovo prodotto</h1>
    <form action="UploadProdotto" method="post" enctype="multipart/form-data">
        <label>Immagine copertina:</label>
        <input type="file" name="file" required/>
        <label> Titolo </label>
        <input type="text" name="titolo" required>
        <label> Autore </label>
        <select name="autore" required>
            <c:forEach items = "${autori}" var = "autore">
                <option value="${autore.id}"> <c:out value="${autore.nome.charAt(0)}.${autore.cognome}"/> </option>
            </c:forEach>
        </select>
        <label> Editore </label>
        <input type="text" name="editore" required>
        <label> Genere </label>
        <select name="genere" required>
            <c:forEach items = "${generi}" var = "genere">
                <option value="${genere.nome}"> <c:out value="${genere.nome}" /> </option>
            </c:forEach>
        </select>
        <label> Descrizione </label>
        <textarea name="descrizione" required> </textarea>
        <label> Numero copie disponibili </label>
        <input type="number" name="copie" min="0" required>
        <label> prezzo (centesimi)</label>
        <input type="number" name="prezzo" min="1" required>
        <input type="submit" name="Upload" value="Upload">
    </form>
</section>
<%@include file="footer.html"%>