function ricerca(str) { //str è la stringa digitata dall'utente
    var dataList = document.getElementById('ricerca-datalist'); //mi prendo il riferimento al datalist di suggerimento
    //del form ricerca (che si trova nell'header)

    /** comando inutile
    if (str.length == 0) {
        dataList.innerHTML = '';
        return;
    }  **/

    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.responseType = 'json'; //setto il tipo di risposta voluta a json
    xmlHttpReq.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            dataList.innerHTML = '';
            for (var i in this.response) { // for sulla risposta inviata dal server, in questo caso è un JSONArray
                var option = document.createElement('option'); //creo un tag HTML
                option.value = this.response[i];//con il for le option prenderanno tutti i valori dell'array
                dataList.appendChild(option);//aggiungo l'opzione (quindi un altro nodo nella lista) alla lista di suggerimenti dataList
            }
        }

    }
    xmlHttpReq.open("GET", "RicercaAjax?q=" + encodeURIComponent(str), true); //q è il nome dell'elemento <input>
    //della barra di ricerca (quindi quello che contiene il parametro)
    xmlHttpReq.send();

}