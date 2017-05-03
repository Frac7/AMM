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
        post1.setDestinatario(null);
        post1.setAllegato(null);
        
        Post post2 = new Post();
        post2.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 2"));
        post2.setContenuto("Ooliiviaa!");
        post2.setAllegato("img/o.jpg");
        post2.setTipologia(Post.pType.IMAGE);
        post2.setId(2);
        post2.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        post2.setDestinatario(null);
        
        Post post3 = new Post();
        post3.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 3"));
        post3.setAllegato("https://www.masseffect.com/it-it/andromeda-initiative");
        post3.setContenuto("Unisciti all'iniziativa Andromeda! ");
        post3.setTipologia(Post.pType.URL);
        post3.setId(3);
        post3.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        post3.setDestinatario(null);
        
        Post post4 = new Post();
        post4.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 1"));
        post4.setContenuto("Corri Barry, corri!");
        post4.setTipologia(Post.pType.TEXT);
        post4.setId(4);
        post4.setGruppo(GruppiFactory.getInstance().getGroupByName("Gruppo 1")); //il post si trova sulla bacheca di un gruppo
        post4.setDestinatario(null);
        post4.setAllegato(null);
        
        Post post5 = new Post();
        post5.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 2"));
        post5.setContenuto("Chiamano questi eventi \"Lo schema\", come se qualcuno facesse esperimenti usando il mondo come laboratorio! Adesso ha visto! Adesso sa!");
        post5.setTipologia(Post.pType.TEXT);
        post5.setId(5);
        post5.setGruppo(GruppiFactory.getInstance().getGroupByName("Gruppo 2")); //il post si trova sulla bacheca di un gruppo
        post5.setDestinatario(null);
        post5.setAllegato(null);
        
        Post post6 = new Post();
        post6.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 3"));
        post6.setContenuto("Benvenuti al Presidium, posso essere la vostra guida?");
        post6.setTipologia(Post.pType.TEXT);
        post6.setId(6);
        post6.setGruppo(GruppiFactory.getInstance().getGroupByName("Gruppo 3")); //il post si trova sulla bacheca di un gruppo
        post6.setDestinatario(null);
        post6.setAllegato(null);
        
        Post post7 = new Post();
        post7.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Incompleto"));
        post7.setContenuto("Hey, ciao!");
        post7.setTipologia(Post.pType.TEXT);
        post7.setId(7);
        post7.setGruppo(null); //il post si trova sulla bacheca di un gruppo
        post7.setDestinatario(null);
        post7.setAllegato(null);
        
        Post post8 = new Post();
        post8.setAutore(UtentiRegistratiFactory.getInstance().getUserByName("Utente 1"));
        post8.setContenuto("Benvenuto!");
        post8.setTipologia(Post.pType.TEXT);
        post8.setId(8);
        post8.setGruppo(null); //il post si trova sulla bacheca di un gruppo
        post8.setDestinatario(UtentiRegistratiFactory.getInstance().getUserByName("Incompleto"));
        post8.setAllegato(null);
        
        //aggiunta post alla lista
        post.add(post1);
        post.add(post2);
        post.add(post3);
        post.add(post4);
        post.add(post5);
        post.add(post6);
        post.add(post7);
        post.add(post8);
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
            if(elemento.getDestinatario() == null)
                if(elemento.getAutore().equals(u))
                    l.add(elemento);
        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getPostByDest(UtentiRegistrati u)
    {
        List<Post> l = getPostByUser(u);
        if(l == null)
            l = new ArrayList<Post>();
        //lista di appoggio per restituzione post
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post elemento:post)
            if(elemento.getDestinatario() != null)
                if(elemento.getDestinatario().equals(u))
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
