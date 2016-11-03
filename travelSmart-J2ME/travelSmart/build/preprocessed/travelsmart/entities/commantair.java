/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.entities;

/**
 *
 * @author Wassila
 */
public class commantair {


    private int id;
    private String comment;
    private int user;

    public commantair() {
    }

    public commantair(int id, String comment, int user) {
        this.id = id;
        this.comment = comment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(int user) {
        this.user = user;
    }
    public void setUser (String user) {
        this.user = Integer.parseInt(user);
    }

      public void setId(String id) {
    
          this.id = Integer.parseInt(id);
    }

    public String toString() {
        return "commantair{" + "id=" + id + ", comment=" + comment + ", user=" + user + '}';
    }
    
    
    
}
