/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import travelSmart.CLASSES.entities.Hotel;



/**
 *
 * @author Hadhoud
 */
public interface IHotelDAO  {
   
    boolean insertHotel(Hotel hotel , String nomVille);

    boolean updateHotel(Hotel hotel);

    void deleteHotel(int id);
 
    
}
