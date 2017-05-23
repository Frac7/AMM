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
                u.setTipUtente(utentiTypeFromString(rs.getString("tipo")));
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
    
    private UtentiRegistrati.uType utentiTypeFromString(String type){
        
        if(type.equals("0"))
            return UtentiRegistrati.uType.ADMIN;
        else 
            return UtentiRegistrati.uType.USER;
    }
    
    private int utentiTypeFromEnum(UtentiRegistrati.uType type){
        if(type == UtentiRegistrati.uType.ADMIN)
                return 0;
        else 
            return 1;
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
                u.setTipUtente(utentiTypeFromString(rs.getString("tipo")));
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
    public List getUserList()
    {
        List<UtentiRegistrati> l = new ArrayList<>();
        try {
            //connessione al db
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select utenti.id, utenti.nome, utenti.urlpropic from utenti ";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
            //esecuzione query
            ResultSet rs = ps.executeQuery();
            //ciclare sui risultati
            while (rs.next()) {
                UtentiRegistrati u = new UtentiRegistrati();
                //oggetto.metodo(nome colonna)
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                //u.setCognome(rs.getString("cognome"));
                //u.setPassword(rs.getString("password"));
                u.setUrlProPic(rs.getString("urlProPic"));
                //u.setDataNascita(rs.getString("dataNascita"));
                //u.setTipUtente(utentiTypeFromString(rs.getString("tipo")));
                //u.setFraseBio(rs.getString("fraseBio"));
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
        try {
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      "delete from gruppi "
                    + "where gruppi.founder = ?";
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
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      "delete from utenti_gruppi "
                    + "where utenti_gruppi.id_u = ?";
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
            Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
            String query = 
                      "delete from utenti_utenti "
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
            try {
                Connection c = DriverManager.getConnection(connectionString, "Frac7", "amm");
                String query = 
                          "delete from utenti "
                        + "where utenti.id = ?";
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
        return false;
    }
}
