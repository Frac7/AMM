/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nb.factory;
import amm.nb.entita.Gruppi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String connectionString;
        public void setConnectionString(String s){
            this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }
    //costruttore
    private GruppiFactory()
    {
        
    }
    public boolean cancella(int id)
    {
        //cancellazione post
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " delete from post "
                    + "where post.gruppo = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        //cancellazione da tabella relazione con utenti
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " delete from utenti_gruppi "
                    + "where utenti_gruppi.id_g = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
                    e.printStackTrace();
                }
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                          " delete from gruppi "
                        + "where gruppi.id = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean iscrivi(int id_u, int id_g)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "INSERT INTO utenti_gruppi(id, id_u, id_ug) VALUES (default, ?, ?);";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setInt(1, id_u);
            ps.setInt(2, id_g);
            //esecuzione query
            int rs = ps.executeUpdate();
            if(rs == 1)
            {
                ps.close();
                c.close();
                return true;
            }
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Gruppi getGroupById(int id)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from gruppi " 
                    + "where gruppi.id = ?";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setInt(1, id);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            if (rs.next()) {
                Gruppi g = new Gruppi();
                //oggetto.metodo(nome colonna)
                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                g.setDescrizione(rs.getString("descrizione"));
                g.setFounder(UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("founder")));
                g.setUrlProPic(rs.getString("urlProPic"));
                ps.close();
                c.close();
                return g;
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Gruppi getGroupByName(String n)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from gruppi " 
                    + "where gruppi.nome = ?";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setString(1, n);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            if (rs.next()) {
                Gruppi g = new Gruppi();
                //oggetto.metodo(nome colonna)
                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                g.setDescrizione(rs.getString("descrizione"));
                g.setFounder(UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("founder")));
                g.setUrlProPic(rs.getString("urlProPic"));
                ps.close();
                c.close();
                return g;
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List getGroupList()
    {
        List<Gruppi> l = new ArrayList<>();
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from gruppi ";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            while (rs.next()) {
                Gruppi g = new Gruppi();
                //oggetto.metodo(nome colonna)
                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                g.setDescrizione(rs.getString("descrizione"));
                g.setFounder(UtentiRegistratiFactory.getInstance().getUserById(rs.getInt("founder")));
                g.setUrlProPic(rs.getString("urlProPic"));
                l.add(g);
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
}

