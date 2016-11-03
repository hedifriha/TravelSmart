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


public class Ville {
    private int idVille;
    private String nomVille;
    private String nomPays;
    private ArrayList listHotels = new ArrayList();
    private ArrayList listRestaurants = new ArrayList();

    public Ville() {
        
    }
    

    public Ville(String nomVille, String nomPays) {
        this.nomVille = nomVille;
        this.nomPays = nomPays;
    }

    public Ville(String nomVille) {
        this.nomVille = nomVille;
    }
    
    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public ArrayList getListHotels() {
        return listHotels;
    }

    public void setListHotels(ArrayList listHotels) {
        this.listHotels = listHotels;
    }

    public ArrayList getListRestaurants() {
        return listRestaurants;
    }

    public void setListRestaurants(ArrayList listRestaurants) {
        this.listRestaurants = listRestaurants;
    }

    @Override
    public String toString() {
        return "Ville: idVille=" + idVille + ", nomVille=" + nomVille + ", nomPays=" + nomPays;
    }
}