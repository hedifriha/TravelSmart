/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import java.util.ArrayList;
import java.util.List;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.CLASSES.entities.Restaurant;

/**
 *
 * @author reznov
 */
public interface INewsletterDAO {
    public void inscriptionNewsletter(String email);
    public void desinscriptionNewsletter(String email);
    public ArrayList<String> getListeInscriptions();
    public boolean estInscrit(String email);
     public List<Hotel> GetLastHotels();
     public List<Restaurant> GetLastResturant();
     public List<Object> GetNewsletter();
     
}
