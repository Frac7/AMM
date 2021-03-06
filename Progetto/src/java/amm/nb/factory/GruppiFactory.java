/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nb.factory;
import amm.nb.entita.Gruppi;
import amm.nb.entita.UtentiRegistrati;
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
    public boolean iscritto(UtentiRegistrati u, Gruppi g)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " select * from utenti_gruppi "
                    + "where utenti_gruppi.id_u = ? and utenti_gruppi.id_g = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.setInt(2, g.getId());
            ResultSet rs = ps.executeQuery();
            boolean flag = false;
            if(rs.next())
                flag = true;
            ps.close();
            c.close();
            return flag;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean cancella(int id)
    {
        //cancellazione post
        /*try {
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
                }*/
        Connection c = null;
        try {
            c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            c.setAutoCommit(false);
            String query = 
                      " delete from post "
                    + "where post.gruppo = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            query = 
                      " delete from utenti_gruppi "
                    + "where utenti_gruppi.id_g = ? ";
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            query = 
                          " delete from gruppi "
                        + "where gruppi.id = ? ";
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            c.commit();
            c.setAutoCommit(true);
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
            String query = "INSERT INTO utenti_gruppi(id, id_u, id_g) VALUES (default, ?, ?)";
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
                if(rs.getString("urlProPic") == null || rs.getString("urlProPic").equals(""))
                    g.setUrlProPic("img/g.png");
                else
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
                if(rs.getString("urlProPic") == null || rs.getString("urlProPic").equals(""))
                    g.setUrlProPic("img/g.png");
                else
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
                if(rs.getString("urlProPic") == null || rs.getString("urlProPic").equals(""))
                    g.setUrlProPic("img/g.png");
                else
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
    public boolean creaGruppo(Gruppi g)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "insert into gruppi(id, nome, descrizione, urlProPic, founder) values (default, ?, ?, ?, ?)";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //esecuzione query
            ps.setString(1, g.getNome());
            ps.setString(2, g.getDescrizione());
            if(g.getUrlProPic() == null)
                g.setUrlProPic("img/g.png");
            ps.setString(3, g.getUrlProPic());
            ps.setInt(4, g.getFounder().getId());
            boolean flag = false;
            if(ps.executeUpdate() == 1)
                flag = true;
            //ciclare sui risultati
            ps.close();
            c.close();
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

