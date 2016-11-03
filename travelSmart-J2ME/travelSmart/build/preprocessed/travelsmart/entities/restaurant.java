/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.entities;

/**
 *
 * @author Wassila
 */
public class restaurant {
    
    private int idRestaurant;
    private int VILLES_idVille;
    private String nomRestaurant;
    private String categorie;
    private String description;
    private double Latitude;
    private double Longitude;
    private String chemin;

    public restaurant() {
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public int getVILLES_idVille() {
        return VILLES_idVille;
    }

    public void setVILLES_idVille(int VILLES_idVille) {
        this.VILLES_idVille = VILLES_idVille;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        try{
            this.Latitude = Double.parseDouble(Latitude);
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        try{
            this.Longitude = Double.parseDouble(Longitude);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    

    public String toString() {
        return "restaurant{" + "idRestaurant=" + idRestaurant + ", VILLES_idVille=" + VILLES_idVille + ", nomRestaurant=" + nomRestaurant + ", categorie=" + categorie + ", description=" + description + ", Latitude=" + Latitude + ", Longitude=" + Longitude + ", chemin=" + chemin + '}';
    }
    
    
    
}
