/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.CLASSES.entities;

/**
 *
 * @author reznov
 */
public class AbonnePrivilegie extends Abonne {

    public AbonnePrivilegie() {
    }

    public AbonnePrivilegie(int type, int idUser, String nom, String prenom, String email, String adresse, String tel, String profile_pic, int points, int badge) {
        super(type, idUser, nom, prenom, email, adresse, tel, profile_pic, points, badge);
    }

    @Override
    public String toString() {
        return "AbonnePrivilegie{" + super.toString() +  '}';
    }
    
    
}
