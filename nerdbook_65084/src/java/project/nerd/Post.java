/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.nerd;

/**
 *
 * @author Ste2016
 */
public class Post {

    public static enum Type {
        TESTO, LINK, IMMAGINE
    }

    private int id;
    private Utente autore;
    private Utente bacheca_ut;
    private Gruppo gruppo;
    private String contenuto;
    private String allegato;
    private Type type;

    public Post() {
        id = 0;
        autore = null;
        bacheca_ut = null;
        gruppo = null;
        contenuto = "";
        allegato = "";
        type = Type.TESTO;
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
    public Utente getAutore() {
        return autore;
    }

    /**
     * @param autore the autore to set
     */
    public void setAutore(Utente autore) {
        this.autore = autore;
    }

    /**
     * @return the bacheca_ut
     */
    public Utente getBacheca_ut() {
        return bacheca_ut;
    }

    /**
     * @param bacheca_ut the bacheca_ut to set
     */
    public void setBacheca_ut(Utente bacheca_ut) {
        this.bacheca_ut = bacheca_ut;
    }

    /**
     * @return the gruppo
     */
    public Gruppo getGruppo() {
        return gruppo;
    }

    /**
     * @param gruppo the gruppo to set
     */
    public void setGruppo(Gruppo gruppo) {
        this.gruppo = gruppo;
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
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

}
