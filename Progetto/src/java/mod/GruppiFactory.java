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
public class GruppiFactory {
    //attributi
    private static GruppiFactory singleton;
    //metodo
    public static GruppiFactory getInstance()
    {
        if(singleton == null)
            singleton = new GruppiFactory();
        return singleton;
    }
    //lista utenti
    private ArrayList<Gruppi> gruppi = new ArrayList<Gruppi>();
    //costruttore
    private GruppiFactory()
    {
        //dati fittizzi
        Gruppi gruppo1 = new Gruppi();
        gruppo1.setNome("Gruppo 1");
        gruppo1.setDescrizione("The Flash");
        gruppo1.setFounder(UtentiRegistratiFactory.getInstance().getUserByName("Utente 1"));
        gruppo1.setId(1);
        gruppo1.setUrlProPic("img/g.png");
        
        Gruppi gruppo2 = new Gruppi();
        gruppo2.setNome("Gruppo 2");
        gruppo2.setDescrizione("Fringe");
        gruppo2.setFounder(UtentiRegistratiFactory.getInstance().getUserByName("Utente 2"));
        gruppo2.setId(2);
        gruppo2.setUrlProPic("img/g.png");
        
        Gruppi gruppo3 = new Gruppi();
        gruppo3.setNome("Gruppo 3");
        gruppo3.setDescrizione("ME saga");
        gruppo3.setFounder(UtentiRegistratiFactory.getInstance().getUserByName("Utente 3"));
        gruppo3.setId(3);
        gruppo3.setUrlProPic("img/g.png");
        //aggiunta utenti alla lista
        gruppi.add(gruppo1);
        gruppi.add(gruppo2);
        gruppi.add(gruppo3);
    }
    public Gruppi getGroupById(int id)
    {
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Gruppi g : this.gruppi)
        {
            if(g.getId() == id)
                return g;
        }
        return null;
        //confrontare l'id dell'utente con quello del parametro
    }
    public Gruppi getGroupByName(String n)
    {
        for(Gruppi g : this.gruppi)
        {
            if(g.getNome().equals(n))
                return g;
        }
        return null;
    }
    public List getGroupList()
    {
        return gruppi;
    }
}

