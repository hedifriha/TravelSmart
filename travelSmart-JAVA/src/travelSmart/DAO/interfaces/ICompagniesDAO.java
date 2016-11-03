/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelSmart.DAO.interfaces;

import java.util.List;
import travelSmart.CLASSES.entities.Compagnies;

/**
 *
 * @author bechir
 */
public interface ICompagniesDAO {
    boolean insertCompagnies(Compagnies comp);
    void updatrCompagnies(Compagnies c);
    boolean deleteCompagnies(int id);
    Compagnies findCompagniesById(int id);
    Compagnies findCompagniesByName(String name);
    
    List<Compagnies> DisplayAllCompagnies();
    
}
