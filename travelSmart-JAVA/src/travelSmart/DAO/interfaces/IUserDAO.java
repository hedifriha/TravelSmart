/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.interfaces;


import java.util.ArrayList;
import java.util.List;
import travelSmart.CLASSES.entities.User;

/**
 *
 * @author reznov
 */
public interface IUserDAO {
    
    public boolean inscriptionUser(User U, String pwd);
    
    public User getUserById(int idUser);
    
    public User getMinimalUserById(int idUser);
    
    public ArrayList<User> getAllUsers(int type);
    
    public boolean modifierUser();
    
    public boolean supprimerUser(int idUser);
    
    public boolean supprimerAllUsers();
    
    public User connexionUser(String username, String Password);
    
    public User findUserByEmail(String email);
    
    public int countUsersbyType(int type);
    
    public int countUserbyStatus(int status);
    
    public void validerUser(int idUser);
   
}
