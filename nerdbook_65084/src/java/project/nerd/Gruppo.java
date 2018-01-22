/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.nerd;

import java.util.ArrayList;

/**
 *
 * @author Ste2016
 */
public class Gruppo {

    private int id;
    private String nome;
    private String urlFoto;
    private ArrayList<Integer> utenti;

    public Gruppo() {
        id = 0;
        nome = "";
        urlFoto = "";
        utenti = null;
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
     * @return the urlFoto
     */
    public String getUrlFoto() {
        return urlFoto;
    }

    /**
     * @param urlFoto the urlFoto to set
     */
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    /**
     * @return the utenti
     */
    public ArrayList<Integer> getUtenti() {
        return utenti;
    }

    /**
     * @param utenti the utenti to set
     */
    public void setUtenti(ArrayList<Integer> utenti) {
        this.utenti = utenti;
    }

}
