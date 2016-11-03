/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.CLASSES.entities;

/**
 *
 * @author Hadhoud
 */
public class Restaurant {
   private int idRestaurant;
   private String nomRestaurant;
   private String categorie;
    private int id_ville;
    private String description;
    
    public Restaurant() {
    }

    public Restaurant(int idRestaurant, String nomRestaurant, String adresseRestaurant) {
        this.idRestaurant = idRestaurant;
        this.nomRestaurant = nomRestaurant;
    }

    public Restaurant(int idRestaurant, String nomRestaurant, String categorie, int id_ville, String description) {
        this.idRestaurant = idRestaurant;
        this.nomRestaurant = nomRestaurant;
        this.categorie = categorie;
        this.id_ville = id_ville;
        this.description = description;
    }

    public Restaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }
    

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getId_ville() {
        return id_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
   
   
    
}
