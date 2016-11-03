/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.CLASSES.entities;

/**
 *
 * @author Amri
 */
public class Commentaire {
   
    private int idComment;
    private String CommentText;
    
    private int idUser;
    
    private int idPays;
    private int idVille;
    private int idHotel;
    private int idRestaurant;
    

    public Commentaire() {
    }

    
    public Commentaire(int idComment, String CommentText, int idUser, int idPays, int idVille, int idHotel, int idRestaurant) {
        this.idComment = idComment;
        this.CommentText = CommentText;
        this.idUser = idUser;
        this.idPays = idPays;
        this.idVille = idVille;
        this.idHotel = idHotel;
        this.idRestaurant = idRestaurant;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getCommentText() {
        return CommentText;
    }

    public void setCommentText(String CommentText) {
        this.CommentText = CommentText;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }



    
    
    
    
}
