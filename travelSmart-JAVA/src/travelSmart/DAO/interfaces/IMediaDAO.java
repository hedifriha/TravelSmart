/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import java.util.List;
import travelSmart.CLASSES.entities.Commentaire;
import travelSmart.CLASSES.entities.Media;

/**
 *
 * @author Amri
 */
public interface IMediaDAO {
    
    public boolean ajouterMediaPays(Media M);
    public boolean ajouterMediaVille(Media M);
    public boolean ajouterMediaHotel(Media M);
    public boolean ajouterMediaRestaurant(Media M);
    public boolean ajouterMedia(Media M, Commentaire C);
    public Media getMedia(int id);
    
    public List<Media> getMediaByPays(int idPays);
    public List<Media> getMediaByVille(int idVille);
    public List<Media> getMediaByHotel(int idPays);
    public List<Media> getMediaByRestaurant(int idPays);
    
    public Media[] getAllMedia();
    public boolean supprimerMedia(int id);
    public boolean supprimerAllMedia();
    
}
