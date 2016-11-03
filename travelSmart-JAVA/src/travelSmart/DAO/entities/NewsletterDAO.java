/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.CLASSES.entities.Restaurant;
import travelSmart.DAO.interfaces.INewsletterDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author Wassila
 */
public class NewsletterDAO implements INewsletterDAO{
     private Connection connection;
      public NewsletterDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public void inscriptionNewsletter(String email) {
        String query = "insert into newsletter values (NULL,?)";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps2.setString(1, email);
            ps2.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Inscription effectuée!");
        }
    }

    @Override
    public void desinscriptionNewsletter(String email) {
        String query = "delete from newsletter where email=?";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps2.setString(1, email);
            ps2.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Inscription effectuée!");
        }
    }

    @Override
    public ArrayList<String> getListeInscriptions() {
        String requete = "SELECT email from newsletter;";
        String email="";
        
        ArrayList<String> listInscription = new ArrayList<String>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                email=resultat.getString("email");

                listInscription.add(email);
            }
            return listInscription;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des emails " + ex.getMessage());
        }
        
        return null;
    }

    @Override
    public boolean estInscrit(String email) {
        String query = "select * from newsletter where email='"+ email +"';";
        try{
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Wrong Credentials!");
            return false;
        }
        return false;
    }
      @Override
    public ArrayList<Hotel> GetLastHotels() {
        ArrayList<Hotel> listehotels = new ArrayList<Hotel>();
       
        String query ="SELECT  * from hotels order by idHotel desc limit 5";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                Hotel hotel = new Hotel();
                hotel.setIdHotel(resultat.getInt(1));
                hotel.setNomHotel(resultat.getString(2));
                

                listehotels.add(hotel);
            }
            return listehotels;
        } catch (SQLException ex) {
            
            System.out.println("erreur lors du chargement des hotels " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Restaurant> GetLastResturant() {
        ArrayList<Restaurant> listeRestaurant = new ArrayList<Restaurant>();
       
        String query ="SELECT  * from restaurants order by idRestaurant desc limit 5";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);

            while (resultat.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setIdRestaurant(resultat.getInt(1));
                restaurant.setNomRestaurant(resultat.getString(2));
                

                listeRestaurant.add(restaurant);
            }
            return listeRestaurant;
        } catch (SQLException ex) {
            
            System.out.println("erreur lors du chargement des restaurants " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Object> GetNewsletter() {
        ArrayList<Hotel> listehotels = new ArrayList<Hotel>();
        ArrayList<Restaurant> listeRestaurant = new ArrayList<Restaurant>();
        
        ArrayList<Object> listAll = new ArrayList<Object>();
        
        listehotels=this.GetLastHotels();
        listeRestaurant=this.GetLastResturant();
        
        listAll.addAll(listehotels);
        listAll.addAll(listeRestaurant);
        
        return listAll;
        
    }   
}
