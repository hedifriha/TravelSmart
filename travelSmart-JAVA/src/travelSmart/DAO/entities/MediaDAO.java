/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;

import travelSmart.DAO.interfaces.IMediaDAO;
import travelSmart.CLASSES.entities.Commentaire;
import travelSmart.CLASSES.entities.Media;
import travelSmart.SETTINGS.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Amri
 */
public class MediaDAO implements IMediaDAO{

    private Connection connection;

    public MediaDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }
    
 

    @Override
    public boolean ajouterMedia(Media M, Commentaire C) {
        boolean isAdded=false;
        String queryMedia = "insert into medias (idMedia, chemin, size, type) values(NULL,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(queryMedia);
            statement.setString(1, M.getChemin());
            statement.setDouble(2, M.getFileSize());
            statement.executeUpdate();
            isAdded=true;
        } catch (SQLException exception) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
               // Ajout Commentaire
        String queryComment = "insert into commentaires (idCommentaire, MEDIAS_idMedia, textCommentaire) values (NULL,(select idMedia from medias ORDER BY idMedia DESC LIMIT 1),?)";
        
        try{
            PreparedStatement ps2 = connection.prepareStatement(queryComment);
            ps2.setString(1, C.getCommentText());
            ps2.executeUpdate();
            isAdded=true;
        }catch (SQLException exception) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }   
        return isAdded;    
    }
    
    @Override
    public Media getMedia(int id) {
        String requete = "select idMedia,chemin,size,type from medias where Id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            Media media = new Media();
            while (resultat.next()) {
                media.setId(resultat.getInt(1));
                media.setChemin(resultat.getString(2));
                media.setFileSize(resultat.getDouble(3));
            }
            return media;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    
    
    @Override
    public Media[] getAllMedia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimerMedia(int id) {
        boolean flag = true;
        String requete = "delete from medias where Id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
        return false;
    }
    

    @Override
    public boolean supprimerAllMedia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static IMediaDAO iMediaDAO;

    public static IMediaDAO getInstance() {
        if (iMediaDAO == null) {
            iMediaDAO = new MediaDAO();
        }
        return iMediaDAO;
    }

    @Override
    public boolean ajouterMediaPays(Media M) {
        boolean isAdded=false;
        String query_media = "insert into medias (idMedia, chemin, NomFichier, size, PAYS_idPays) values(NULL,?,?,?,(SELECT IdPays FROM Pays ORDER BY IdPays DESC LIMIT 1))";
        try{
            PreparedStatement statement = connection.prepareStatement(query_media);
            statement.setString(1, M.getChemin());
            statement.setString(2, M.getNomFichier());
            statement.setDouble(3, M.getFileSize());
            statement.executeUpdate();
            isAdded=true;
        } catch (SQLException exception) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
               
        return isAdded; 
    }

    @Override
    public boolean ajouterMediaVille(Media M) {
        boolean isAdded=false;
        String query_media = "insert into medias (idMedia, chemin, NomFichier, size, VILLES_idVille) values(NULL,?,?,?,(SELECT idVille FROM Villes ORDER BY idVille DESC LIMIT 1))";
        try{
            PreparedStatement statement = connection.prepareStatement(query_media);
            statement.setString(1, M.getChemin());
            statement.setString(2, M.getNomFichier());
            statement.setDouble(3, M.getFileSize());
            statement.executeUpdate();
            isAdded=true;
        } catch (SQLException exception) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
               
        return isAdded; 
    }

    @Override
    public boolean ajouterMediaHotel(Media M) {
        boolean isAdded=false;
        String query_media = "insert into medias (idMedia, chemin, NomFichier, size) values(NULL,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query_media);
            statement.setString(1, M.getChemin());
            statement.setString(2, M.getNomFichier());
            statement.setDouble(3, M.getFileSize());
            statement.executeUpdate();
            isAdded=true;
        } catch (SQLException exception) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
               
        return isAdded; 
    }

    @Override
    public boolean ajouterMediaRestaurant(Media M) {
        boolean isAdded=false;
        String query_media = "insert into medias (idMedia, chemin, NomFichier, size) values(NULL,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query_media);
            statement.setString(1, M.getChemin());
            statement.setString(2, M.getNomFichier());
            statement.setDouble(3, M.getFileSize());
            statement.executeUpdate();
            isAdded=true;
        } catch (SQLException exception) {
            Logger.getLogger(MediaDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
               
        return isAdded; 
    }

    @Override
    public List<Media> getMediaByPays(int idPays) {
        List<Media> list = new ArrayList<Media>();
        
        String requete = "select `idMedia`, `chemin`, `NomFichier`, `size` from medias where PAYS_idPays = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idPays);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                list.add(new Media(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getDouble(4)));
            }
            
            return list;
            

        } catch (SQLException ex) {
            System.out.println("Erreur Lecture des Medias" + ex.getMessage());
            return null;
        }
    }
  

    @Override
    public List<Media> getMediaByVille(int idVille) {
        List<Media> list = new ArrayList<Media>();
        
        String requete = "select `idMedia`, `chemin`, `NomFichier`, `size` from medias where VILLES_idVille = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idVille);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                list.add(new Media(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getDouble(4)));
            }
            
            return list;
            

        } catch (SQLException ex) {
            System.out.println("Erreur Lecture des Medias" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Media> getMediaByHotel(int idPays) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Media> getMediaByRestaurant(int idPays) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
