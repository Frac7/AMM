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
        //post1.setAutore();
        post1.setContenuto("");
        post1.setTipologia(Post.pType.TEXT);
        post1.setId(1);
        post1.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        Post post2 = new Post();
        //post1.setAutore();
        post2.setContenuto("");
        post2.setTipologia(Post.pType.IMAGE);
        post2.setId(2);
        post2.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        Post post3 = new Post();
        //post1.setAutore();
        post1.setContenuto("");
        post1.setTipologia(Post.pType.URL);
        post1.setId(3);
        post1.setGruppo(null); //il post non si trova sulla bacheca di un gruppo
        //aggiunta utenti alla lista
        post.add(post1);
        post.add(post2);
        post.add(post3);
    }
    public Post getPostById(int id)
    {
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post p : this.post)
            if(p.getId() == id)
                return p;
        return null;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getPostByUser(UtentiRegistrati u)
    {
        List<Post> l = new ArrayList<Post>();
        //lista di appoggio per restituzione post
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post p : this.post)
            if(p.getAutore().equals_u(u))
                l.add(p);
        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getPostByGroup(Gruppi g)
    {
         List<Post> l = new ArrayList<Post>();
        //lista di appoggio per restituzione post
        //sintassi for da vedere
        //scorrere la lista di utenti
        for(Post p : this.post)
            if(p.getGruppo().equals_g(g))
                l.add(p);
        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
}
