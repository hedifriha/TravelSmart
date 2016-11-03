/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelsmart.entities;

/**
 *
 * @author ReZ_NoV
 */
public class User {
    private int idUser;
    private String username;
    private String password;

    public User() {
    }
    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = Integer.parseInt(idUser);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{" + "idUser=" + idUser + ", username=" + username + ", password=" + password + '}';
    }
    
    
    
}
