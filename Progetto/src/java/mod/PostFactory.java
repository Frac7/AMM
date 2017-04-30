/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Asus
 */
public class PostFactory {
    //attributi
    private static PostFactory singleton;
    //metodo
    public static PostFactory getInstance()
    {
        if(singleton == null)
            singleton = new PostFactory();
        return singleton;
    }
    //lista utenti
    private ArrayList<Post> post = new ArrayList<Post>();
    //costruttore
    private PostFactory()
    {
        //dati fittizzi
        //aggiungere id content ecc
        Post post1 = new Post();
        post1.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 1"));
        post1.setContenuto("Cisco: \"It looks like the Vacuum.\"\n" +
"                   Jesse: \"Uh, what's the Vacumm?\"\n" +
"                   Cisco: \"No Fringe on Earth-2. Noted.\"");
        post1.setTipologia(Post.pType.TEXT);
        post1.setId(1);
        post1.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        
        Post post2 = new Post();
        post2.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 2"));
        post2.setContenuto("img/o.jpg");
        post2.setTipologia(Post.pType.IMAGE);
        post2.setId(2);
        post2.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        
        Post post3 = new Post();
        post3.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 3"));
        post3.setContenuto("https://www.masseffect.com/it-it/andromeda-initiative");
        post3.setTipologia(Post.pType.URL);
        post3.setId(3);
        post3.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        
        Post post4 = new Post();
        post4.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 1"));
        post4.setContenuto("Corri Barry, corri!");
        post4.setTipologia(Post.pType.TEXT);
        post4.setId(4);
        post4.setGruppo(GruppiFactory.getInstance().getGroupByName("Gruppo 1")); //il post non si trova sulla bacheca di un gruppo
        
        Post post5 = new Post();
        post5.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 2"));
        post5.setContenuto("Chiamano questi eventi \"Lo schema\", come se qualcuno faccia esperimenti usando il mondo come laboratorio! Adesso ha visto! Adesso sa!");
        post5.setTipologia(Post.pType.TEXT);
        post5.setId(5);
        post5.setGruppo(GruppiFactory.getInstance().getGroupByName("Gruppo 2")); //il post non si trova sulla bacheca di un gruppo
        
        Post post6 = new Post();
        post6.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 3"));
        post6.setContenuto("Benvenuti al Presidium, posso essere la vostra guida?");
        post6.setTipologia(Post.pType.TEXT);
        post6.setId(6);
        post6.setGruppo(GruppiFactory.getInstance().getGroupByName("Gruppo 3")); //il post non si trova sulla bacheca di un gruppo
        //aggiunta utenti alla lista
        post.add(post1);
        post.add(post2);
        post.add(post3);
        post.add(post4);
        post.add(post5);
        post.add(post6);
    }
    public Post getPostById(int id)
    {
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post p : this.post)
        {
            if(p.getId() == id)
                return p;
        }
        return null;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getPostByUser(UtentiRegistrati u)
    {
        List<Post> l = new ArrayList<Post>();
        //lista di appoggio per restituzione post
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post elemento:post)
                if(elemento.getAutore().equals(u))
                    l.add(elemento);
        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getPostByGroup(Gruppi g)
    {
         List<Post> l = new ArrayList<Post>();
        //lista di appoggio per restituzione post
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post elemento:post)
        {
            if(elemento.getGruppo() != null)
            {
                if(elemento.getGruppo().equals(g))
                    l.add(elemento);
            }
        }
        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
}
