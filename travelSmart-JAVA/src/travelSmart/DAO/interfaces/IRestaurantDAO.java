/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import travelSmart.CLASSES.entities.Restaurant;



/**
 *
 * @author Hadhoud
 */
public interface IRestaurantDAO {
    
    boolean insertRestaurant(Restaurant restaurant , String nomVille);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(int id);
    
}
