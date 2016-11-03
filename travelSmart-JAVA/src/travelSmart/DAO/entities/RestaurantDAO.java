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
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.Restaurant;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.DAO.interfaces.IRestaurantDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author Hadhoud
 */
public class RestaurantDAO implements IRestaurantDAO{
     private Connection connection;
     ArrayList<Pays> ListePays = new ArrayList<Pays>();
ArrayList<Ville> ListeVille= new ArrayList<Ville>();
ArrayList<Restaurant> ListerRestaurants= new ArrayList<Restaurant>();
 ArrayList<Restaurant> listerestaurant= new ArrayList<Restaurant>();



    public RestaurantDAO(Connection connection) {
        this.connection = MyConnection.getInstance().getConnection();
    }

    public RestaurantDAO() {
        this.connection = MyConnection.getInstance().getConnection(); 
    }


@Override
    public boolean insertRestaurant(Restaurant restaurant , String nomVille ) {
         String requete1 = "select idVille from villes where nomVille ='" + nomVille +  "';";
        int id_seek = 0;
         try{
            PreparedStatement prep = connection.prepareStatement(requete1);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                id_seek = result.getInt("idVille");
            }
        } catch (SQLException ex) {
        }
 String requete = "insert into restaurants (idrestaurant,nomrestaurant,categorie,description,VILLES_idVille) values (null,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
           // ps.setInt(null, restaurant.getIdRestaurant());
            ps.setString(1, restaurant.getNomRestaurant());
            ps.setString(2, restaurant.getDescription());
            ps.setString(3, restaurant.getCategorie());
            ps.setInt(4, id_seek);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            return false;
        }    
    }


    @Override
    public void updateRestaurant(Restaurant restaurant) {
     String requete = "update hotel set nomrestaurant=? where idrestaurant=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(2, restaurant.getIdRestaurant());
            ps.setString(1, restaurant.getNomRestaurant());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
        public ArrayList<Restaurant> displayAllRestaurant() {
   //     ArrayList<Hotel> listehotel = new ArrayList<Hotel>();

        String requete = "select * from restaurants";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Restaurant h = new Restaurant();
                h.setIdRestaurant(resultat.getInt(1));
                h.setNomRestaurant(resultat.getString(2));
                h.setCategorie(resultat.getString(3));
                h.setDescription(resultat.getString(4));
                h.setId_ville(resultat.getInt(5));

                listerestaurant.add(h);
            }
            return listerestaurant;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des pays " + ex.getMessage());
            return null;
        }
    }
        public Restaurant findRestaurantById(int id) {
        Restaurant h = new Restaurant();
        String requete = "select * from restaurants where idRestaurant=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                h.setIdRestaurant(resultat.getInt(1));
                h.setNomRestaurant(resultat.getString(2));
                h.setCategorie(resultat.getString(3));
                h.setDescription(resultat.getString(4));
                h.setId_ville(resultat.getInt(5));
            }
            return h;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
    }
    @Override
    public void deleteRestaurant(int id) {
         String requete = "delete from restaurants where idrestaurant=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("RESTAURANT supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
    public ArrayList<Restaurant> viewRestaurants(){
               //String requete = "select * from hotel ";
        Restaurant hotel = new Restaurant();
        String query = "select * from hotels;";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            ResultSet rs = pSt.executeQuery(query);
            
            while(rs.next())
            {
                 Restaurant h = new Restaurant(rs.getString("nomrestaurant") );
           //     Hotel h = new Hotel(rs.getInt("idHotel"),rs.getString("nomHotel") ,rs.getString("categorie"),rs.getInt("VILLES_idVille"));
                ListerRestaurants.add(h);
                //return ListeHotel;
            }
  }
              catch (SQLException ex) {
            System.out.println("base vide!!");  
    }
        return ListerRestaurants;
    }
    
    public ArrayList<Pays> viewpays(){
               //String requete = "select * from hotel ";
        String query = "select * from pays;";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            ResultSet rs = pSt.executeQuery(query);
            
            while(rs.next())
            {
                Pays p = new Pays(rs.getString("nomPays"));
                ListePays.add(p);
                //return ListeHotel;
            }
  }
              catch (SQLException ex) {
            System.out.println("base vide!!");  
    }
        return ListePays;
    } 
    
    public ArrayList<Ville> viewville(){
               //String requete = "select * from hotel ";
        String query = "select * from villes;";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            ResultSet rs = pSt.executeQuery(query);
            
            while(rs.next())
            {
                Ville v = new Ville(rs.getString("nomVille"));
                ListeVille.add(v);
                //return ListeHotel;
            }
  }
              catch (SQLException ex) {
            System.out.println("base vide!!");  
    }
        return ListeVille;
    } 
    
}
