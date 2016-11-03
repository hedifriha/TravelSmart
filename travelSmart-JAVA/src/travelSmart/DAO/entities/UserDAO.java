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
import java.util.logging.Level;
import java.util.logging.Logger;
import travelSmart.CLASSES.entities.Abonne;
import travelSmart.CLASSES.entities.AbonnePrivilegie;
import travelSmart.CLASSES.entities.Administrateur;
import travelSmart.CLASSES.entities.User;
import travelSmart.DAO.interfaces.IUserDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author reznov
 */
public class UserDAO implements IUserDAO{
    
    private Connection connection ;

    public UserDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public boolean inscriptionUser(User U, String pwd) {
        boolean flagInsert = false;
        //Creation d'un Profil
        String query_user = "insert into admins values(NULL,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query_user);
            ps.setString(1, U.getNom());
            ps.setString(2, U.getPrenom());
            ps.setString(3, U.getEmail());
            ps.setString(4, U.getAdresse());
            ps.setString(5, U.getTel());
            ps.setInt(6,1); //Type = 0 si Abonne - 1 si Abonne Privilegie - 2 Si Admin
            ps.setString(7, U.getProfile_pic());
            ps.setInt(8, 1); //Clé etrangere idBadge (Not yet set)
            ps.executeUpdate();
            flagInsert=true;
        } catch (SQLException exception) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
        //Création d'un compte pour le login
        String query_login_set = "insert into login values (?,?,(select idUser from admins ORDER BY idUser DESC LIMIT 1),0)";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query_login_set);
            ps2.setString(1, U.getEmail());
            ps2.setString(2, pwd);
            ps2.executeUpdate();
            flagInsert=true;
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EmailDAO email = new EmailDAO();
        try {
            email.SendConfirmationEmail(U);
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flagInsert;        
    }   
    
    @Override
    public User connexionUser(String username, String password) {
        String query = "select * from login where username='"+ username +"' AND password='"+ password + "' AND valide=1;";
        int id_seeked = 0;
        try{
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                id_seeked = result.getInt("idUser");
            }
        } catch (SQLException ex) {
            System.out.println("Wrong Credentials!");
            return null;
        }
    
        String query_user = "select * from admins where idUser =?";
        try{
            PreparedStatement ps = connection.prepareStatement(query_user);
            ps.setInt(1, id_seeked);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                switch(rs.getInt("type")){
                    case 0 : return new Abonne(0, rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"), rs.getString("tel"), rs.getString("profile_pic"), 0, rs.getInt("BADGES_idBadge"));
                             
                    case 1 : return new AbonnePrivilegie(1, rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"), rs.getString("tel"), rs.getString("profile_pic"), 0, rs.getInt("BADGES_idBadge"));
                             
                    case 2 : return new Administrateur(2, rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"), rs.getString("tel"), rs.getString("profile_pic"), 0, rs.getInt("BADGES_idBadge"));
                             
                    default: return null;
                }
                //return new User(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"), rs.getString("tel"), rs.getString("profile_pic"), rs.getInt("points"), rs.getString("badge"));
            }
            
        } catch (SQLException ex1) {
            System.out.println("erreur lors du chargement" + ex1.getMessage());
            return null;
        }
        System.out.println("Wrong Credentials!");
        return null;
    }


    @Override
    public ArrayList<User> getAllUsers(int type) {
        String requete = "SELECT u . * , l . valide FROM admins u, login l where l.valide="+ type +" AND l.username=u.email ORDER BY BADGES_idBadge DESC";
        
        ArrayList<User> listeUsers = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                User user = new User();
                user.setIdUser(resultat.getInt("idUser"));
                user.setNom(resultat.getString("nom"));
                user.setPrenom(resultat.getString("prenom"));
                user.setEmail(resultat.getString("email"));
                user.setBadge(resultat.getInt("BADGES_idBadge"));

                listeUsers.add(user);
            }
            return listeUsers;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des users " + ex.getMessage());
        }
        
        return null;
    }

    @Override
    public boolean modifierUser() {
        return true;
    }

    @Override
    public boolean supprimerUser(int idUser) {
        String requete = "delete from users where idUser ="+idUser+";";
        try{
            PreparedStatement ps2 = connection.prepareStatement(requete);
            ps2.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean supprimerAllUsers() {
        return true;
    }


     @Override
    public User findUserByEmail(String email) {
        User user = new User();
        String requete = "select * from admins where email=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, email);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                user.setIdUser(resultat.getInt(1));
                user.setNom(resultat.getString(2));
                user.setPrenom(resultat.getString(3));
                user.setEmail(resultat.getString(4));
                user.setAdresse(resultat.getString(5));
                user.setTel(resultat.getString(6));
                user.setProfile_pic(resultat.getString(7));
                user.setBadge(resultat.getInt(8));
             
           
            }
            return user;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
    
    }

    @Override
    public User getUserById(int idUser) {
        String requete = "select * from admins where IdUser=?";
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            
            while (resultat.next()) {
                
                switch (resultat.getInt("type")){
                    case 0:
                        user = new Abonne();
                        user.setIdUser(resultat.getInt(1));
                        user.setNom(resultat.getString(2));
                        user.setPrenom(resultat.getString(3));
                        user.setEmail(resultat.getString(4));
                        user.setAdresse(resultat.getString(5));
                        user.setTel(resultat.getString(6));
                        ((Abonne)user).setType(0);
                        user.setBadge(resultat.getInt(8));
                        break;
                    case 1:
                        user = new AbonnePrivilegie();
                        user.setIdUser(resultat.getInt(1));
                        user.setNom(resultat.getString(2));
                        user.setPrenom(resultat.getString(3));
                        user.setEmail(resultat.getString(4));
                        user.setAdresse(resultat.getString(5));
                        user.setTel(resultat.getString(6));
                        ((AbonnePrivilegie)user).setType(1);
                        user.setBadge(resultat.getInt(8));
                        break;
                    
                    case 2:
                        user = new Administrateur();
                        user.setIdUser(resultat.getInt(1));
                        user.setNom(resultat.getString(2));
                        user.setPrenom(resultat.getString(3));
                        user.setEmail(resultat.getString(4));
                        user.setAdresse(resultat.getString(5));
                        user.setTel(resultat.getString(6));
                        ((Administrateur)user).setType(2);
                        user.setBadge(resultat.getInt(8));
                        break;
                    default:
                        user= null;
                 }
                
            }
            return user;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement User" + ex.getMessage());
            return null;
        }
    }

    @Override
    public User getMinimalUserById(int idUser) {
    
        String requete = "select nom,prenom,profile_pic from admins where IdUser=?";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idUser);
            ResultSet resultat = ps.executeQuery();
            
            
            User user = new User();
            while (resultat.next()) {
                user.setIdUser(idUser);
                user.setNom(resultat.getString(1));
                user.setPrenom(resultat.getString(2));
                user.setProfile_pic(resultat.getString(3));
            }
            return user;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement User Minimal " + ex.getMessage());
            return null;
        }    
    
    }

    @Override
    public int countUsersbyType(int type) {
        String requete = "select count(*) from admins where type=?";
        int count=0;
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, type);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                count = resultat.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Users " + ex.getMessage());
            return 0;
        }  
    }
    

    @Override
    public void validerUser(int idUser) {
        String query_login_set = "update login set valide=1 where idUser=?";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query_login_set);
            ps2.setInt(1, idUser);
            ps2.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int countUserbyStatus(int status) {
        String requete = "select count(*) from login where valide=?";
        int count=0;
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, status);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                count = resultat.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des Users " + ex.getMessage());
            return 0;
        }  
    }
    
   
    
}
