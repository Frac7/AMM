/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nb.entita;

/**
 *
 * @author Asus
 */
public class Post {

    /**
     * @return the allegato
     */
    public String getAllegato() {
        return allegato;
    }

    /**
     * @param allegato the allegato to set
     */
    public void setAllegato(String allegato) {
        this.allegato = allegato;
    }

    /**
     * @return the destinatario
     */
    public UtentiRegistrati getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(UtentiRegistrati destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the gruppo
     */
    public Gruppi getGruppo() {
        return gruppo;
    }

    /**
     * @param gruppo the gruppo to set
     */
    public void setGruppo(Gruppi gruppo) {
        this.gruppo = gruppo;
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
     * @return the autore
     */
    public UtentiRegistrati getAutore() {
        return autore;
    }

    /**
     * @param autore the autore to set
     */
    public void setAutore(UtentiRegistrati autore) {
        this.autore = autore;
    }

    /**
     * @return the contenuto
     */
    public String getContenuto() {
        return contenuto;
    }

    /**
     * @param contenuto the contenuto to set
     */
    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    /**
     * @return the tipologia
     */
    public pType getTipologia() {
        return tipologia;
    }

    /**
     * @param tipologia the tipologia to set
     */
    public void setTipologia(pType tipologia) {
        this.tipologia = tipologia;
    }
    //tipologie di post
    public enum pType
    {
        TEXT, IMAGE, URL
    };
    //attributi
    private UtentiRegistrati autore;
    private String contenuto;
    private pType tipologia;
    private int id;
    private Gruppi gruppo;
    private UtentiRegistrati destinatario;
    private String allegato;
    //costruttore
    public Post()
    {
        autore = null;
        contenuto = "";
        tipologia = pType.TEXT;
        id = 0;
        gruppo = null;
        destinatario = null;
        allegato = null;
    }
}
