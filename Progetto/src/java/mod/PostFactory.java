/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
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
    //databse
    private String connectionString;
        public void setConnectionString(String s){
            this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }
    //metodi factory
    public Post getPostById(int id)
    {
        //utente
        UtentiRegistratiFactory u = UtentiRegistratiFactory.getInstance(); 
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from post " 
                    + "join tipologiaPost on post.tipo = tipologiaPost.id "
                    + "where post.id = ?";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setInt(1, id);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            if (rs.next()) {
                Post p = new Post();
                //oggetto.metodo(nome colonna)
                p.setId(rs.getInt("id"));
                p.setContenuto(rs.getString("contenuto"));
                p.setTipologia(this.postTypeFromString(rs.getString("nome")));
                UtentiRegistrati autore = UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("autore"));
                p.setAutore(autore);
                p.setAllegato(rs.getString("allegato"));
                p.setGruppo(GruppiFactory.getInstance().getGroupById(rs.getInt("gruppo")));
                UtentiRegistrati destinatario = UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("destinatario"));
                p.setDestinatario(destinatario);
                ps.close();
                c.close();
                return p;
            }
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Post.pType postTypeFromString(String type){
        
        if(type.equals("IMMAGINE"))
            return Post.pType.IMAGE;
        else if (type.equals("URL"))
            return Post.pType.URL;
        else
            return Post.pType.TEXT;
    }
    
    private int postTypeFromEnum(Post.pType type){
        if(type == Post.pType.TEXT)
                return 1;
        else if (type == Post.pType.IMAGE)
                return 2;
        else
            return 3;
    }
    public List getPostByUser(UtentiRegistrati u)
    {
        List<Post> l = new ArrayList<Post>();
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "select * from post "
                    + "join tipologiaPost on post.tipo = tipologiaPost.id "
                    + "where post.autore = ? "
                    + "and post.destinatario is null";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setContenuto(rs.getString("contenuto"));
                p.setTipologia(this.postTypeFromString(rs.getString("nome")));
                p.setAutore(u);
                p.setAllegato(rs.getString("allegato"));
                p.setGruppo(GruppiFactory.getInstance().getGroupById(rs.getInt("gruppo")));
                UtentiRegistrati destinatario = UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("destinatario"));
                p.setDestinatario(destinatario);
                l.add(p);
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }
    public List getPostByDest(UtentiRegistrati u)
    {
        List<Post> l = getPostByUser(u);
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "select * from post "
                    + "join tipologiaPost on post.tipo = tipologiaPost.id "
                    + "where post.destinatario = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setContenuto(rs.getString("contenuto"));
                p.setTipologia(this.postTypeFromString(rs.getString("nome")));
                p.setDestinatario(u);
                p.setAutore(UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("autore")));
                p.setAllegato(rs.getString("allegato"));
                p.setGruppo(GruppiFactory.getInstance().getGroupById(rs.getInt("gruppo")));
                l.add(p);
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
        //confrontare l'id dell'utente con quello del parametro
    }
    public List getPostByGroup(Gruppi g)
    {
        List<Post> l = new ArrayList<Post>();
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "select * from post "
                    + "join tipologiaPost on post.tipo = tipologiaPost.id "
                    + "where post.gruppo = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, g.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setContenuto(rs.getString("contenuto"));
                p.setTipologia(this.postTypeFromString(rs.getString("nome")));
                p.setDestinatario(UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("destinatario")));
                p.setAutore(UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("autore")));
                p.setAllegato(rs.getString("allegato"));
                p.setGruppo(g);
                l.add(p);
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }
    public void addNewPost(Post n)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      "insert into post (id ,autore, contenuto, tipo, gruppo, destinatario, allegato) "
                    + "values (default, ? , ? , ?, ?, ?, ? )";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, n.getAutore().getId());
            if(n.getContenuto() != null)
                ps.setString(2, n.getContenuto());
            else
                ps.setString(2, null);
            ps.setInt(3, this.postTypeFromEnum(n.getTipologia()));
            if(n.getGruppo() != null)
                ps.setInt(4, n.getGruppo().getId());
            else
                ps.setString(4, null);
            if(n.getDestinatario() != null)
                ps.setInt(5, n.getDestinatario().getId());
            else
                ps.setString(5, null);
            if(n.getAllegato() != null)
                ps.setString(6, n.getAllegato());
            else
                ps.setString(6, null);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean deleteAllByUser(UtentiRegistrati u)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      "delete from post "
                    + "where post.autore = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
