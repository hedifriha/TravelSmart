/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import java.util.ArrayList;
import java.util.List;
import travelSmart.CLASSES.entities.Ville;

/**
 *
 * @author Ines
 */
public interface IVilleDAO {
    boolean insertVille(Ville v);
    boolean updateVille(Ville v);
    void deleteVille(int id);
    Ville findVilleById(int id);
    Ville findVilleByName(String name);
    ArrayList<Ville> displayAllVilles();
    public int countVilles();
    ArrayList<Ville> top20Ville();
}
