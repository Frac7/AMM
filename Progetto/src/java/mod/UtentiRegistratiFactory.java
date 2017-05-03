/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Asus
 */
public class UtentiRegistratiFactory {
    //attributi
    private static UtentiRegistratiFactory singleton;
    //metodo
    public static UtentiRegistratiFactory getInstance()
    {
        if(singleton == null)
            singleton = new UtentiRegistratiFactory();
        return singleton;
    }
    //lista utenti
    private ArrayList<UtentiRegistrati> utenti = new ArrayList<UtentiRegistrati>();
    //costruttore
    private UtentiRegistratiFactory()
    {
        //dati fittizzi
        UtentiRegistrati utente1 = new UtentiRegistrati();
        utente1.setCognome("Cognome");
        utente1.setId(1);
        utente1.setNome("Utente 1");
        utente1.setDataNascita("24/04/2017");
        utente1.setPassword("password1");
        utente1.setTipUtente(UtentiRegistrati.uType.USER);
        utente1.setUrlProPic("img/ok.png");
        utente1.setFraseBio("Il tempo è estremamente fragile come struttura, qualsiasi deviazione, per quanto piccola può provocare un cataclisma.");
        
        UtentiRegistrati utente2 = new UtentiRegistrati();
        utente2.setCognome("Cognome");
        utente2.setId(2);
        utente2.setNome("Utente 2");
        utente2.setDataNascita("24/04/2017");
        utente2.setPassword("password2");
        utente2.setTipUtente(UtentiRegistrati.uType.USER);
        utente2.setUrlProPic("img/ok.png");
        utente2.setFraseBio("Peter, mi sbagliavo, non è troppo tardi! Puoi salvare entrambi i mondi, possiamo ricominciare daccapo. Stavolta dovrai semplicemente compiere una scelta diversa, e se qualcosa andasse storto, Olivia sarebbe la nostra ancora di salvezza...");
        
        UtentiRegistrati utente3 = new UtentiRegistrati();
        utente3.setCognome("Cognome");
        utente3.setId(3);
        utente3.setNome("Utente 3");
        utente3.setDataNascita("24/04/2017");
        utente3.setPassword("password3");
        utente3.setTipUtente(UtentiRegistrati.uType.USER);
        utente3.setUrlProPic("img/ok.png");
        utente3.setFraseBio("Sono il comandante Shepard e questo è il mio negozio preferito sulla Cittadella.");
        
        UtentiRegistrati incompleto = new UtentiRegistrati();
        incompleto.setId(4);
        incompleto.setNome("Incompleto");
        incompleto.setPassword("password");
        incompleto.setCognome(null);
        incompleto.setTipUtente(UtentiRegistrati.uType.USER);
        incompleto.setUrlProPic("img/ok.png");
        incompleto.setFraseBio("Ehilà! Sto utilizzando NerdBook.");
        
        UtentiRegistrati vuoto = new UtentiRegistrati();
        vuoto.setId(5);
        vuoto.setNome("Vuoto");
        vuoto.setPassword("password");
        vuoto.setCognome(null);
        vuoto.setTipUtente(UtentiRegistrati.uType.USER);
        vuoto.setUrlProPic("img/ok.png");
        vuoto.setFraseBio(null);
        
        //aggiunta utenti alla lista
        utenti.add(utente1);
        utenti.add(utente2);
        utenti.add(utente3);
        utenti.add(incompleto);
        utenti.add(vuoto);
    }
    public UtentiRegistrati getUserById(int id)
    {
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(UtentiRegistrati u : this.utenti)
        {
            if(u.getId() == id)
                return u;
        }
        return null;
        //confrontare l'id dell'utente con quello del parametro
    }
    public UtentiRegistrati getUserByName(String n)
    {
        for(UtentiRegistrati u : this.utenti)
        {
            if(u.getNome().equals(n))
                return u;
        }
        return null;
    }
    public List getUserList()
    {
        return utenti;
    }
}
