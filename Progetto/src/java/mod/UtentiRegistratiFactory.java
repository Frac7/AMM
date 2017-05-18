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
            Connection c = DriverManager.getConnection(connectionString, "amm", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti " 
                    + "join tipologiaUtenti on utenti.tipo = tipologiaUtenti.id "
                    + "where id = ?";
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
                //u.setTipUtente(utentiTypeFromString(rs.getString("tipo")));
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
        
        if(type.equals("ADMIN"))
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
            Connection c = DriverManager.getConnection(connectionString, "amm", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti " 
                    + "join tipologiaUtenti on utenti.tipo = tipologiaUtenti.id "
                    + "where nome = ?";
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
                //u.setTipUtente(utentiTypeFromString(rs.getString("tipo")));
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
            Connection c = DriverManager.getConnection(connectionString, "amm", "amm");
            //tutti le colonne di post, unisci la tabella post e tipologia post, selezione le righe con un certo post id
            String query = "select * from utenti " 
                    + "join tipologiaUtenti on utenti.tipo = tipologiaUtenti.id ";
            //prepared statement (validare sintassi sql con caratteri speciali)
            PreparedStatement ps = c.prepareStatement(query);
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
                //u.setTipUtente(utentiTypeFromString(rs.getString("tipo")));
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
    public boolean deleteUser(UtentiRegistrati u)
    {
        try {
            Connection c = DriverManager.getConnection(connectionString, "amm", "amm");
            String query = 
                      "delete from utenti "
                    + "where id = ?";
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
