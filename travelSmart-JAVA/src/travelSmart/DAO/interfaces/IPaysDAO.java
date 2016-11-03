/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;

import java.util.ArrayList;
import travelSmart.CLASSES.entities.Pays;

/**
 *
 * @author Ines
 */
public interface IPaysDAO {
    boolean insertPays(Pays p);
    boolean updatePays(Pays p);
    void deletePays(int id);
    Pays findPaysById(int id);
    Pays findPaysByName(String name);
    ArrayList<Pays> displayAllPays();
    int countPays();
    int countVillesInPays(int idPays);
    ArrayList<Pays> top20Pays();
}
