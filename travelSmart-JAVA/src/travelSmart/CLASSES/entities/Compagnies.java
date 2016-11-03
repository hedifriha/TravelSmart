/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelSmart.CLASSES.entities;

import java.util.Objects;

/**
 *
 * @author bechir
 */
public class Compagnies {
    public int idCompagnie;
    public String type;
    public String nom_comp;

    public Compagnies(String type) {
        this.type = type;
    }

    public Compagnies() {
    }

    public Compagnies(int idCompagnie, String type, String nom_comp) {
        this.idCompagnie = idCompagnie;
        this.type = type;
        this.nom_comp = nom_comp;
    }

    public int getIdCompagnie() {
        return idCompagnie;
    }

    public void setIdCompagnie(int idCompagnie) {
        this.idCompagnie = idCompagnie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom_comp() {
        return nom_comp;
    }

    public void setNom_comp(String nom_comp) {
        this.nom_comp = nom_comp;
    }

    @Override
    public String toString() {
        return "Compagnies{" + "idCompagnie=" + idCompagnie + ", type=" + type + ", nom_comp=" + nom_comp + '}';
    }



    @Override 
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compagnies other = (Compagnies) obj;
        if (this.idCompagnie != other.idCompagnie) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.nom_comp, other.nom_comp)) {
            return false;
        }
        return true;
    }
    

    
}
