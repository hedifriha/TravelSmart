/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import java.util.List;
import travelSmart.CLASSES.entities.Commentaire;

/**
 *
 * @author Amri
 */
public interface ICommentaireDAO {
    
    public Commentaire getCommentByUserByPays(int idUser, int idPays);
    public Commentaire getCommentByUserByVille(int idUser, int idVille);
    public Commentaire getCommentByUserByHotel(int idUser, int idHotel);
    public Commentaire getCommentByUserByRestaurant(int idUser, int idRestaurant);
    
    public List<Commentaire> getAllCommentsByPays(int idPays);
    public List<Commentaire> getAllCommentsByVille(int idVille);
    public List<Commentaire> getAllCommentsByHotel(int idHotel);
    public List<Commentaire> getAllCommentsByRestaurant(int idRestaurant);
    
    public void addCommentairePays(Commentaire C);
    public void addCommentaireVille(Commentaire C);
    public void addCommentaireHotel(Commentaire C);
    public void addCommentaireRestaurant(Commentaire C);
    
    
    public void updateCommentairePays(Commentaire C);
    public void updateCommentaireVille(Commentaire C);
    public void updateCommentaireHotel(Commentaire C);
    public void updateCommentaireRestaurant(Commentaire C);
}
