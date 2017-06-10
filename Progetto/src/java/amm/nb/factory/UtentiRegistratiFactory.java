/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nb.factory;
import amm.nb.entita.UtentiRegistrati;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
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
    private String connectionString;
        public void setConnectionString(String s){
            this.connectionString = s;
    }
    public String getConnectionString(){
            return this.connectionString;
    }
    
    //costruttore
    private UtentiRegistratiFactory()
    {
        
    }
    public boolean amici(int id1, int id2)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " select * from utenti_utenti "
                    + "where utenti_utenti.id_ua = ? and utenti_utenti.id_ub = ? or utenti_utenti.id_ua = ? and utenti_utenti.id_ub = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            ps.setInt(3, id2);
            ps.setInt(4, id1);
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
    public boolean aggiungi(int id1, int id2)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "INSERT INTO utenti_utenti(id, id_ua, id_ub) VALUES (default, ?, ?)";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setInt(1, id1);
            ps.setInt(2, id2);
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
    public UtentiRegistrati getUserById(int id)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti "
                    + "where utenti.id = ?";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setInt(1, id);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            if (rs.next()) {
                UtentiRegistrati u = new UtentiRegistrati();
                //oggetto.metodo(nome colonna)
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setPassword(rs.getString("password"));
                u.setUrlProPic(rs.getString("urlProPic"));
                u.setDataNascita(rs.getString("dataNascita"));
                u.setTipUtente(utentiTypeFromString(rs.getInt("tipo")));
                u.setFraseBio(rs.getString("fraseBio"));
                ps.close();
                c.close();
                return u;
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private UtentiRegistrati.uType utentiTypeFromString(int type){
        
        if(type == 1)
            return UtentiRegistrati.uType.ADMIN;
        else 
            return UtentiRegistrati.uType.USER;
    }
    
    private int utentiTypeFromEnum(UtentiRegistrati.uType type){
        if(type.equals(UtentiRegistrati.uType.ADMIN))
                return 1;
        else 
            return 2;
    }
   
    public UtentiRegistrati getUserByName(String n)
    {
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti " 
                    + "where utenti.nome = ?";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //associazione carattere speciale con id (ricerca post per id)
            ps.setString(1, n);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            if (rs.next()) {
                UtentiRegistrati u = new UtentiRegistrati();
                //oggetto.metodo(nome colonna)
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setPassword(rs.getString("password"));
                u.setUrlProPic(rs.getString("urlProPic"));
                u.setDataNascita(rs.getString("dataNascita"));
                u.setTipUtente(utentiTypeFromString(rs.getInt("tipo")));
                u.setFraseBio(rs.getString("fraseBio"));
                ps.close();
                c.close();
                return u;
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List getUserList(String nome)
    {
        List<UtentiRegistrati> l = new ArrayList<>();
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti where utenti.nome like ? or utenti.cognome like ?";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + nome + "%");
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            while (rs.next()) {
                UtentiRegistrati u = new UtentiRegistrati();
                //oggetto.metodo(nome colonna)
                u.setId(rs.getInt("id"));
                if(rs.getString("nome") != null)
                    u.setNome(rs.getString("nome"));
                if(rs.getString("cognome") != null)
                    u.setCognome(rs.getString("cognome"));
                u.setPassword(rs.getString("password"));
                u.setUrlProPic(rs.getString("urlProPic"));
                if(rs.getString("dataNascita") != null)
                    u.setDataNascita(rs.getString("dataNascita"));
                u.setTipUtente(utentiTypeFromString(rs.getInt("tipo")));
                if(rs.getString("fraseBio") != null)
                    u.setFraseBio(rs.getString("fraseBio"));
                l.add(u);
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
    public List getUserList()
    {
        List<UtentiRegistrati> l = new ArrayList<>();
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti ";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            while (rs.next()) {
                UtentiRegistrati u = new UtentiRegistrati();
                //oggetto.metodo(nome colonna)
                u.setId(rs.getInt("id"));
                if(rs.getString("nome") != null)
                    u.setNome(rs.getString("nome"));
                if(rs.getString("cognome") != null)
                    u.setCognome(rs.getString("cognome"));
                u.setPassword(rs.getString("password"));
                u.setUrlProPic(rs.getString("urlProPic"));
                if(rs.getString("dataNascita") != null)
                    u.setDataNascita(rs.getString("dataNascita"));
                u.setTipUtente(utentiTypeFromString(rs.getInt("tipo")));
                if(rs.getString("fraseBio") != null)
                    u.setFraseBio(rs.getString("fraseBio"));
                l.add(u);
            }

            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }
    private boolean delete(UtentiRegistrati u)
    {
        PostFactory.getInstance().deleteAllByUser(u);
        Connection c = null;
        try {
            c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " delete from utenti_gruppi "
                    + "where utenti_gruppi.id_u = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            ps.close();
            c.close();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = " DELETE FROM utenti_gruppi WHERE utenti_gruppi.id_g IN (SELECT gruppi.id FROM gruppi where founder = ?) ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " delete from gruppi "
                    + "where gruppi.founder = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      " delete from utenti_utenti "
                    + "where utenti_utenti.id_ua = ? or utenti_utenti.id_ub = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, u.getId());
            ps.setInt(2, u.getId());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean deleteUser(UtentiRegistrati u)
    {
        if(delete(u) == true)
        {
            Connection c = null;
            try {
                c = DriverManager.getConnection(connectionString, "Frac7", "amm");
                c.setAutoCommit(false);
                String query = 
                          " delete from utenti "
                        + "where utenti.id = ? ";
                PreparedStatement ps = c.prepareStatement(query);
                ps.setInt(1, u.getId());
                delete(u);
                ps.executeUpdate();
                c.commit();
                c.setAutoCommit(true);
                ps.close();
                c.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                try{
                    if(c != null)
                        c.rollback();
                }
                catch(SQLException s){
                    s.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }
    public void updateNome(String nome, int id)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "UPDATE utenti SET nome = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, nome);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateCognome(String cognome, int id)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "UPDATE utenti SET cognome = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, cognome);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateFraseBio(String frase, int id)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "UPDATE utenti SET fraseBio = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, frase);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateDataNascita(String data, int id)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "UPDATE utenti SET dataNascita = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, data);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateUrlProPic(String url, int id)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "UPDATE utenti SET urlProPic = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, url);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updatePassword(String psw, int id)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = "UPDATE utenti SET password = ? WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, psw);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
