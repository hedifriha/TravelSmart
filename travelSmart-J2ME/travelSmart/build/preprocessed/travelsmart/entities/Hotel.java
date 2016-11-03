/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.entities;

/**
 *
 * @author Hadhoud
 */
public class Hotel {
     private int idHotel;
    private int VILLES_idVille;
    private String nomHotel;
    private String categorie;
    private String description;
    private double Latitude;
    private double Longitude;
    private String chemin;

    public Hotel() {
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getVILLES_idVille() {
        return VILLES_idVille;
    }

    public void setVILLES_idVille(int VILLES_idVille) {
        this.VILLES_idVille = VILLES_idVille;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
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
        return "Hotel{" + "idHotel=" + idHotel + ", VILLES_idVille=" + VILLES_idVille + ", nomHotel=" + nomHotel + ", categorie=" + categorie + ", description=" + description + ", Latitude=" + Latitude + ", Longitude=" + Longitude + ", chemin=" + chemin + '}';
    }
    
}
