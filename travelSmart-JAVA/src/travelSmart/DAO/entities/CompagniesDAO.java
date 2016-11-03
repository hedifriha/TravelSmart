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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;
import travelSmart.CLASSES.entities.Compagnies;
//import travelSmart.CLASSES.entities.Hotel;
import travelSmart.DAO.interfaces.ICompagniesDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author bechir
 */
public class CompagniesDAO implements ICompagniesDAO{
    ArrayList<Compagnies> ListeCompagnies = new ArrayList<Compagnies>();
    
   
     private Connection connection;
 
    public CompagniesDAO(Connection connection) {
        this.connection = MyConnection.getInstance().getConnection(); 
    }

    public CompagniesDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }
    @Override
    public boolean insertCompagnies(Compagnies comp) {
        String requete = "insert into compagnies (nomcomp,type) values (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
       
            ps.setString(1, comp.getNom_comp());
            ps.setString(2, comp.getType());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
                //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("erreur lors de l'insertion " + ex.getMessage());
            return false;
        }
    }

    @Override
    public void updatrCompagnies(Compagnies c) {
         String requete = "update compagnies set (nomcomp,type)values (?,?) where idCompagnie=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            //ps.setInt(1, hotel.getIdHotel());
            ps.setString(2, c.getNom_comp());
            ps.setString(3, c.getType());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public boolean deleteCompagnies(int id) {
         String requete = "delete from compagnies where idCompagnie=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
           // System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Compagnies findCompagniesById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Compagnies> DisplayAllCompagnies() {
                Compagnies comp = new Compagnies();
        String query = "select * from compagnies;";
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            ResultSet rs = pSt.executeQuery(query);
            
            while(rs.next())
            {
                Compagnies c = new Compagnies(rs.getString("type"));
                ListeCompagnies.add(c);
                //return ListeHotel;
            }
  }
              catch (SQLException ex) {
            System.out.println("base vide!!");  
    }
        return ListeCompagnies;
    }

    @Override
    public Compagnies findCompagniesByName(String name) {
        Compagnies Compagnies = new Compagnies();
        String requete = "select * from compagnies where nomcomp=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, name);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               

                

                Compagnies.setIdCompagnie(resultat.getInt(1));
                Compagnies.setNom_comp(resultat.getString(2));
                Compagnies.setType(resultat.getString(3));

            }
            return Compagnies;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche de la Compagnies " + ex.getMessage());
            return null;
        }
    }
    
}
