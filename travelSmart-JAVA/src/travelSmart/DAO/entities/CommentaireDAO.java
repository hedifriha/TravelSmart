/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;

import java.util.List;
import travelSmart.CLASSES.entities.Commentaire;
import travelSmart.DAO.interfaces.ICommentaireDAO;
import travelSmart.SETTINGS.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amri
 */
public class CommentaireDAO implements ICommentaireDAO{

    
    
    private Connection connection;

    public CommentaireDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }
    //t
    
     
    @Override
    public List<Commentaire> getAllCommentsByPays(int idPays) {
            
        List<Commentaire> list = new ArrayList<Commentaire>();
        
        String requete = "select idCommentaire, textCommentaire,AVIS_EXP_USERS_idUser from commentaires where PAYS_idPays = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idPays);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                list.add(new Commentaire(resultat.getInt(1), resultat.getString(2), resultat.getInt(3), idPays, 0, 0, 0));
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("user " + i + ": " + list.get(i).getIdUser());
            }
            return list;
            

        } catch (SQLException ex) {
            System.out.println("Erreur Lecture des Commentaires" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Commentaire> getAllCommentsByVille(int idVille) {
       List<Commentaire> list = new ArrayList<Commentaire>();
        
        String requete = "select idCommentaire, textCommentaire,AVIS_EXP_USERS_idUser from commentaires where VILLES_idVille = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idVille);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                list.add(new Commentaire(resultat.getInt(1), resultat.getString(2), resultat.getInt(3), 0, idVille, 0, 0));
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("user " + i + ": " + list.get(i).getIdUser());
            }
            return list;
            

        } catch (SQLException ex) {
            System.out.println("Erreur Lecture des Commentaires" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Commentaire> getAllCommentsByHotel(int idHotel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> getAllCommentsByRestaurant(int idRestaurant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire getCommentByUserByPays(int idUser, int idPays) {
        
        Commentaire comment = new Commentaire();
        String requete = "select idCommentaire, textCommentaire from commentaires where PAYS_idPays = ? and AVIS_EXP_USERS_idUser=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idPays);
            ps.setInt(2, idUser);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                
                comment.setIdComment(resultat.getInt(1));
                comment.setCommentText(resultat.getString(2));
                               
            }
            comment.setIdUser(idUser);
            comment.setIdPays(idPays);
            return comment;
            

        } catch (SQLException ex) {
            System.out.println("Erreur Lecture du Commentaire" + ex.getMessage());
            return null;
        }
    }

    @Override
    public Commentaire getCommentByUserByVille(int idUser, int idVille) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire getCommentByUserByHotel(int idUser, int idHotel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire getCommentByUserByRestaurant(int idUser, int idRestaurant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCommentairePays(Commentaire C) {
        
        String query = "INSERT INTO `commentaires`(`idCommentaire`,  `PAYS_idPays`, `AVIS_EXP_USERS_idUser`, `textCommentaire`) VALUES (null,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, C.getIdPays());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
   }

      

    @Override
    public void addCommentaireVille(Commentaire C) {
        String query = "INSERT INTO `commentaires`(`idCommentaire`,  `VILLES_idVille`, `AVIS_EXP_USERS_idUser`, `textCommentaire`) VALUES (null,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, C.getIdVille());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    @Override
    public void addCommentaireHotel(Commentaire C) {
        String query = "INSERT INTO `commentaires`(`idCommentaire`,  `HOTELS_idHotel`, `AVIS_EXP_USERS_idUser`, `textCommentaire`) VALUES (null,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, C.getIdHotel());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    @Override
    public void addCommentaireRestaurant(Commentaire C) {
        String query = "INSERT INTO `commentaires`(`idCommentaire`,  `RESTAURANTS_idRestaurant`, `AVIS_EXP_USERS_idUser`, `textCommentaire`) VALUES (null,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, C.getIdRestaurant());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    @Override
    public void updateCommentairePays(Commentaire C) {
    
        String query = "UPDATE `commentaires` SET `PAYS_idPays`=?,`AVIS_EXP_USERS_idUser`=?,`textCommentaire`=? WHERE `idCommentaire`=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, C.getIdPays());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.setInt(4, C.getIdComment());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }  
    
    @Override
    public void updateCommentaireVille(Commentaire C) {
        String query = "UPDATE `commentaires` SET `VILLES_idVille`=?,`AVIS_EXP_USERS_idUser`=?,`textCommentaire`=? WHERE `idCommentaire`=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, C.getIdVille());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.setInt(4, C.getIdComment());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    @Override
    public void updateCommentaireHotel(Commentaire C) {
        String query = "UPDATE `commentaires` SET `HOTELS_idHotel`=?,`AVIS_EXP_USERS_idUser`=?,`textCommentaire`=? WHERE `idCommentaire`=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, C.getIdHotel());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.setInt(4, C.getIdComment());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    @Override
    public void updateCommentaireRestaurant(Commentaire C) {
        String query = "UPDATE `commentaires` SET `RESTAURANTS_idRestaurant`=?,`AVIS_EXP_USERS_idUser`=?,`textCommentaire`=? WHERE `idCommentaire`=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, C.getIdRestaurant());
            statement.setInt(2, C.getIdUser());
            statement.setString(3, C.getCommentText());
            statement.setInt(4, C.getIdComment());
            statement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(CommentaireDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
