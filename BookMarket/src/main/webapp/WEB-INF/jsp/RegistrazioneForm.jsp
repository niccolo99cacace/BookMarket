<%-- Qui c'è la schermata contenente la parte a sinistra di Login + la parte a destra di registrazione
     Si da all'utente la possibilità di fare il Login o registrarsi --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/registrazionestyle.css"%></style>

<div class="row">
    <div class="column">   <%-- è la parte in cui si può fare il LOGIN (nella parte di login non c'è JavaScript) --%>
        <div id="left">
    <h1 class="testo"> Login </h1>
    <form name="login" action="Login" method="post">
        <label>E-mail </label>
        <input type="text" name="email" ><br>
        <label>Password </label>
        <input type="password" name="password" ><br>
        <input type="submit" value="Login">
    </form>


        </div>
    </div>
    <div class="linea"></div>
    <div class="column">
        <div id="right">

            <%-- è la parte in cui ci si può REGISTRARE (qui è usato JavaScript per le validazioni)--%>
    <h1 class="testo"> Registrazione </h1>
    <form name="registrazione" action="Registrazione" method="post">
        <label>Email </label>
        <input type="text" name="email" oninput="validaEmail()">
        <label>Password (almeno 8 caratteri, una lettera maiuscola, una minuscola, un numero)</label>
        <input type="password" name="password" oninput="validaPassword()">
        <label>Password (conferma)</label>
        <input type="password" name="passwordConferma" oninput="validaPassword()">
        <label>Nome (prima lettera maiuscola, min 6 caratteri, max 15, caratteri speciali permessi [ .,_,+,: ])</label>
        <input type="text" name="nome" oninput="validaNome()">
        <input id="registrami" type="submit" value="Registrami" disabled><span id="registramimessaggio"></span>
    </form>
            </div>
    </div>
</div>



<script>
    var borderOk = '2px solid #008800';
    var borderNo = '2px solid #ff0000';
    var emailOk = false;
    var passwordOk = false;
    var nomeOk = false;

    <%-- se la password è scritta rispettando le condizioni emailOk diventa true , se no rimane false --%>
    function validaPassword() {
        var inputpw = document.forms['registrazione']['password'];
        <%-- prende dal form con name="registrazione" l'input con name="password" e lo retituisce in un array  --%>
        var inputpwconf = document.forms['registrazione']['passwordConferma'];
        var password = inputpw.value;
        <%-- inserisce nella variabile il valore inserito nel campo di input "password" --%>
        if (password.length >= 8 && password.toUpperCase() != password
            && password.toLowerCase() != password && /[0-9]/.test(password)) { //[0-9] : se c'è almeno un numero
            inputpw.style.border = borderOk;

            <%-- entra nell'if se la password è lunga almeno 8 caratteri, se non è tutta in maiuscolo ne tutta in minuscolo
            e se contiene almeno un numero. Il bordo della password allora diventa verde--%>


            if (password == inputpwconf.value) {
                inputpwconf.style.border = borderOk;
                passwordOk = true;
            } else {
                inputpwconf.style.border = borderNo;
                passwordOk = false;
            } <%-- se la conferma di password è uguale alla password allora anche essa diventa verde e la password viene
            confermata con passwordOk=true --%>
        }
        else {
            inputpw.style.border = borderNo;
            inputpwconf.style.border = borderNo;
            passwordOk = false;
        }
        cambiaStatoRegistrami();
    }

    <%-- se il nome è scritto rispettando le condizioni nomeOk diventa true , se no rimane false --%>
    function validaNome() {
        var input = document.forms['registrazione']['nome'];
        if (input.value.trim().length > 0
            && input.value.match(/^[A-Z][\w._+:]{5,14}$/)) {    /** /^[ a-zA-Z\u00C0-\u00ff]+$/ **/
            input.style.border = borderOk;
            nomeOk = true;
        } else {
            input.style.border = borderNo;
            nomeOk = false;
        }
        cambiaStatoRegistrami();
    }

    <%-- se l'email è scritta rispettando le condizioni emailOk diventa true , se no rimane false --%>
    function validaEmail() {
        var input = document.forms['registrazione']['email'];
        if (input.value.match(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)) {
            input.style.border = borderOk;
            emailOk = true;
        } else {
            input.style.border = borderNo;
            emailOk = false;
        }
        cambiaStatoRegistrami();
    }


    <%-- Se tutti i campi della registrazione sono stati compilati in modo corretto e quindi validati(passwordOk==true
     && nomeOk==true && emailOk==true) allora
     il bottone della registrazione diventa funzionante , se no è disabilitato --%>
    function cambiaStatoRegistrami() {
        if (passwordOk && nomeOk && emailOk) {
            document.getElementById('registrami').disabled = false;
            document.getElementById('registramimessaggio').innerHTML = '';
        } else {
            document.getElementById('registrami').disabled = true;
            document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
        }
    }
</script>
<%@include file="footer.html"%>
