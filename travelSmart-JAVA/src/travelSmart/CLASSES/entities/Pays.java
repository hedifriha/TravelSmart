/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.CLASSES.entities;

import java.util.*;

/**
 *
 * @author Ines
 */
public class Pays {

    private int idPays;
    private String nomPays;
    private String capital;
    private String monnaie;
    private String langue;

    public Pays() {
    }

    public Pays(String nomPays) {
        this.nomPays = nomPays;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public String toString() {
        return "Pays{" + "idPays=" + idPays + ", nomPays=" + nomPays + ", capital=" + capital + ", monnaie=" + monnaie + ", langue=" + langue + '}';
    }

    

}