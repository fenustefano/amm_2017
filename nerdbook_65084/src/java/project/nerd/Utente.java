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
public class Utente
{
    private String nome;
    private String cognome;
    private String eMail;
    private String urlFoto;
    private String dataDiNascita;
    private String frase;
    private String password;
    private int id;
    
    public Utente()
    {
       nome = "";
       cognome = "";
       eMail = "";
       dataDiNascita = "";
       urlFoto = "";
       frase = "";
       password = "";
       id = 0;
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
        if (nome == null)
            this.nome = "";
        else 
            this.nome=nome;
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
          if (cognome == null)
            this.cognome = "";
        else 
            this.cognome=cognome;
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
          if (urlFoto == null)
            this.urlFoto = "";
        else 
            this.urlFoto=urlFoto;
    }

    /**
     * @return the frase
     */
    public String getFrase() {
        return frase;
    }

    /**
     * @param frase the frase to set
     */
    public void setFrase(String frase) {
         if (frase == null)
            this.frase = "";
        else 
            this.frase=frase;
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
     * @return the eMail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail the eMail to set
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if(!(object instanceof Utente))
            return false;
        Utente temp = (Utente)object;
        return temp.getId() == this.getId();
    }

    /**
     * @return the dataDiNascita
     */
    public String getDataDiNascita()
    {
        return dataDiNascita;
    }

    /**
     * @param dataDiNascita the dataDiNascita to set
     */
    public void setDataDiNascita(String dataDiNascita)
    {
        this.dataDiNascita = dataDiNascita;
    }
    
    
    
    
}
