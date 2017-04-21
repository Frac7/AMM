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
        utente1.setCognome("");
        utente1.setId(1);
        utente1.setNome("Utente 1");
        utente1.setDataNascita("00/00/0000");
        utente1.setPassword("password1");
        utente1.setTipUtente(UtentiRegistrati.uType.USER);
        utente1.setUrlProPic("");
        
        UtentiRegistrati utente2 = new UtentiRegistrati();
        utente2.setCognome("");
        utente2.setId(2);
        utente2.setNome("Utente 2");
        utente2.setDataNascita("00/00/0000");
        utente2.setPassword("password2");
        utente2.setTipUtente(UtentiRegistrati.uType.USER);
        utente2.setUrlProPic("");
        
        UtentiRegistrati utente3 = new UtentiRegistrati();
        utente3.setCognome("");
        utente3.setId(3);
        utente3.setNome("Utente 3");
        utente3.setDataNascita("00/00/0000");
        utente3.setPassword("password3");
        utente3.setTipUtente(UtentiRegistrati.uType.USER);
        utente3.setUrlProPic("");
        //aggiunta utenti alla lista
        utenti.add(utente1);
        utenti.add(utente2);
        utenti.add(utente3);
    }
    public UtentiRegistrati getUserById(int id)
    {
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(UtentiRegistrati u : this.utenti)
            if(u.getId() == id)
                return u;
        return null;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getUserByName(String n)
    {
        //lista per restituzione valore
        List<UtentiRegistrati> l = new ArrayList<UtentiRegistrati>();
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(UtentiRegistrati u : this.utenti)
            if(u.getNome().equals(n))
                l.add(u);
        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
}
