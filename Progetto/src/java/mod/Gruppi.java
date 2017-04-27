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
public class Gruppi {

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
     * @return the founder
     */
    public UtentiRegistrati getFounder() {
        return founder;
    }

    /**
     * @param founder the founder to set
     */
    public void setFounder(UtentiRegistrati founder) {
        this.founder = founder;
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
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    //attributi
    private String nome;
    private String descrizione; //??
    private UtentiRegistrati founder;
    private int id;
    private String urlProPic;
    //costruttore
    public Gruppi()
    {
        nome = "";
        descrizione = "";
        founder = null;
        id = 0;
        urlProPic = "";
    }
    @Override
    public boolean equals(Object g) {
        if (g instanceof Gruppi)
            if (this.getId() == ((Gruppi)g).getId()) return true;
        return false;
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
}
