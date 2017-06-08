/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//creazione elemento per i risultati
function crea(utente) {
    var proPic = $("<img>") //creazione di img profilo e attr. html
            .attr("alt", "Utente")
            .attr("src", utente.urlProPic)
            .attr("class", "mini");
    var nome = $("<a>") //link al profilo
            .attr("href", "bacheca.html?visualizza_bacheca=" + utente.id)
            .html(utente.nome + " " + utente.cognome); //testo semplice
    //append dati appena creati
    var li = $("<li>")
            .append(proPic)
            .append(nome);
    return li;
}

function stateSuccess(data) {
    var lista = $("#js");
    $(lista).empty(); //svuotare la lista (li dentro ol)
    var fin = 0; //variabile di lavoro a cui si da un valore per testare poi la sua modifica
    for (var instance in data) {
        var fin = $(lista).append(crea(data[instance]));
    }
    if(fin === 0) //se non è avvenuta alcuna modifica significa che nessun utente corrisponde ai criteri di ricerca
    {
            var li = $("<li>")
                    .html("Nessun risultato...")
                    .attr("class","lista");
            $(lista).append(li);
    }
}
function stateFailure(state) {
    console.log(state);
}
//solo quando il documento è pronto
$(document).ready(function () {
    $(".ricerca_bottone").click(function () { //funzione al click del bottone
        var a = $(".ricerca_testo")[0].value; //valore in inputbox
        $.ajax({
            url: "filter.json", //url fittizia
            data: {
                cmd: "search", //comando
                q: a //stringa
            },
            dataType: "json", //tipo di dato atteso
            success: function (data) {
                stateSuccess(data);
            },
            error: function (state) {
                stateFailure(state);
            }
        });
    });
});

function cerca() //funzione onkeyup
{
 var a = $(".ricerca_testo")[0].value; //valore input

    $.ajax({
        url: "filter.json", //ind fittizio
        data: {
            cmd: "search", //comando
            q: a //stringa
        },
        dataType: "json", //dato atteso
        success: function (data) {
            stateSuccess(data);
        },
        error: function (state) {
            stateFailure(state);
        }
    });

}

