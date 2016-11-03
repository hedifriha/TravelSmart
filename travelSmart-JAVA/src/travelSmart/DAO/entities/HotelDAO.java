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
import java.util.List;
import javax.swing.JOptionPane;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.DAO.interfaces.IHotelDAO;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.Ville;

import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author Hadhoud
 */
public class HotelDAO implements IHotelDAO{
ArrayList<Hotel> ListeHotel = new ArrayList<Hotel>();
ArrayList<Pays> ListePays = new ArrayList<Pays>();
ArrayList<Ville> ListeVille= new ArrayList<Ville>();
ArrayList<Hotel> listehotel = new ArrayList<Hotel>();


   
 private Connection connection;

    public HotelDAO(Connection connection) {
        this.connection = MyConnection.getInstance().getConnection(); 
    }

    public HotelDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }
 
    
    @Override
    public boolean insertHotel(Hotel hotel, String nomVille) {
        String requete1 = "select idVille from villes where nomVille ='" + nomVille +  "' ;";
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
        
        String requete = "insert into hotels (idhotel,nomhotel,description,categorie,VILLES_idVille) values (null,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
        //    ps.setInt(1, hotel.getIdHotel());
            ps.setString(1, hotel.getNomHotel());
            ps.setString(2, hotel.getDescription());
            ps.setString(3, hotel.getCategorie());
            ps.setInt(4, id_seek);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("erreur lors de l'insertion " + ex.getMessage());
        return false;
        }
    
    }
    public ArrayList<Hotel> viewHotel(){
               //String requete = "select * from hotel ";
        Hotel hotel = new Hotel();
        String query = "select * from hotels;";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            ResultSet rs = pSt.executeQuery(query);
            
            while(rs.next())
            {
                 Hotel h = new Hotel(rs.getString("nomHotel") );
           //     Hotel h = new Hotel(rs.getInt("idHotel"),rs.getString("nomHotel") ,rs.getString("categorie"),rs.getInt("VILLES_idVille"));
                ListeHotel.add(h);
                //return ListeHotel;
            }
  }
              catch (SQLException ex) {
            System.out.println("base vide!!");  
    }
        return ListeHotel;
    } 
     public ArrayList<Hotel> displayAllHotels() {
   //     ArrayList<Hotel> listehotel = new ArrayList<Hotel>();

        String requete = "select * from hotels";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Hotel h = new Hotel();
                h.setIdHotel(resultat.getInt(1));
                h.setNomHotel(resultat.getString(2));
                h.setDescription(resultat.getString(3));
                h.setCategorie(resultat.getString(4));
                h.setId_ville(resultat.getInt(5));

                listehotel.add(h);
            }
            return listehotel;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des pays " + ex.getMessage());
            return null;
        }
    }
        public Hotel findHotelById(int id) {
        Hotel h = new Hotel();
        String requete = "select * from hotels where idHotel=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                h.setIdHotel(resultat.getInt(1));
                h.setNomHotel(resultat.getString(2));
            }
            return h;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
    }
        
        public Hotel getHotelIdByName(String name) {
        Hotel h = new Hotel();
        String requete = "select * from hotels where nomhotel=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, name);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                h.setIdHotel(resultat.getInt(1));
            }
            return h;
            
        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
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
    
    @Override
    public boolean updateHotel(Hotel hotel) {
         String requete = "update hotels set nomHotel=? , description = ?, categorie= ? where idHotel=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            //ps.setInt(1, hotel.getIdHotel());
            ps.setString(1, hotel.getNomHotel());
            ps.setString(2, hotel.getDescription());
            ps.setString(3, hotel.getCategorie());
            ps.setInt(4, hotel.getIdHotel());
            

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void deleteHotel(int id) {
      String requete = "delete from hotels where idHotel=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    
    }
    
    private static IHotelDAO iHotelDao;

    public static IHotelDAO getInstance() {
        if (iHotelDao == null) {
            iHotelDao = new HotelDAO();
        }
        return iHotelDao;
    }
    
}
