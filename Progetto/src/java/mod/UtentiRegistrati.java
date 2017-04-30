/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;

/**
 *
 * @author Asus
 */
public class UtentiRegistrati {

    /**
     * @return the fraseBio
     */
    public String getFraseBio() {
        return fraseBio;
    }

    /**
     * @param fraseBio the fraseBio to set
     */
    public void setFraseBio(String fraseBio) {
        this.fraseBio = fraseBio;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the urlProPic
     */
    public String getUrlProPic() {
        return urlProPic;
    }

    /**
     * @param urlProPic the urlProPic to set
     */
    public void setUrlProPic(String urlProPic) {
        this.urlProPic = urlProPic;
    }

    /**
     * @return the dataNascita
     */
    public String getDataNascita() {
        return dataNascita;
    }

    /**
     * @param dataNascita the dataNascita to set
     */
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipUtente
     */
    public uType getTipUtente() {
        return tipUtente;
    }

    /**
     * @param tipUtente the tipUtente to set
     */
    public void setTipUtente(uType tipUtente) {
        this.tipUtente = tipUtente;
    }
    //tipologie di utenti
    public enum uType
    {
        USER, ADMIN
    };
    //attributi degli utenti
    private int id;
    private String nome;
    private String cognome;
    private String urlProPic;
    private String dataNascita;
    private String password;
    private uType tipUtente; //utente o amministratore
    private String fraseBio;
    //costruttore
    public UtentiRegistrati()
    {
        id = 0;
        cognome = "";
        nome = "";
        urlProPic = "";
        dataNascita = "";
        password = "";
        tipUtente = uType.USER;
        fraseBio = "";
    }
    @Override
    public boolean equals(Object u) {
        if(u == null) return false;
        if (u instanceof UtentiRegistrati)
            if (this.getId() == ((UtentiRegistrati)u).getId()) return true;
        return false;
    }
}
