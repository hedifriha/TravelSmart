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
public class Media {
    public static final String CURRENT_DIR = System.getProperty("user.dir") + "/src/travelSmart/RESSOURCES/uploads";
    public static final double MAX_IMAGE_SIZE = 512;
    
    private int id;
    private String chemin;
    private String NomFichier;
    private double fileSize;
    
    

    public Media() {
    }

    public Media(int id, String chemin, String NomFichier, double fileSize) {
        this.id = id;
        this.chemin = chemin;
        this.NomFichier = NomFichier;
        this.fileSize = fileSize;
    }

    
    public Media(String chemin, String NomFichier, double fileSize) {
        this.chemin = chemin;
        this.NomFichier = NomFichier;
        this.fileSize = fileSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getNomFichier() {
        return NomFichier;
    }

    public void setNomFichier(String NomFichier) {
        this.NomFichier = NomFichier;
    }

    
    
}
