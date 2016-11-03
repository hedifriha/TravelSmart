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
public class Hotel {
    private int idHotel;
    private String nomHotel;
    private String categorie;
    private int id_ville;
    private String description;

    public Hotel() {
    }

    public Hotel(int idHotel, String nomHotel) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        
    }

    public Hotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }
    
    public Hotel(int idHotel, String nomHotel, String categorie, int id_ville) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.categorie = categorie;
        this.id_ville = id_ville;
    }

    public Hotel(int idHotel, String nomHotel, String categorie, int id_ville, String description) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.categorie = categorie;
        this.id_ville = id_ville;
        this.description = description;
    }
    
    

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
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

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idHotel;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        if (this.idHotel != other.idHotel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hotel{" + "idHotel=" + idHotel + ", nomHotel=" + nomHotel + ", categorie=" + categorie + ", id_ville=" + id_ville + '}';
    }

   
   
    
    
    
    
}
