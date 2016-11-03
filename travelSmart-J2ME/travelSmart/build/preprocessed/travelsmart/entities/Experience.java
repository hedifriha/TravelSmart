/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.entities;

/**
 *
 * @author Ines
 */
public class Experience {
    
    private int id;
    private String titre;
    private String destination1;
    private float depense1;
    private String description1;
    private String destination2;
    private float depense2;
    private String description2;
    private String destination3;
    private float depense3;
    private String description3;

    public Experience(int id, String titre, String destination1, float depense1, String description1, String destination2, float depense2, String description2, String destination3, float depense3, String description3) {
        this.id = id;
        this.titre = titre;
        this.destination1 = destination1;
        this.depense1 = depense1;
        this.description1 = description1;
        this.destination2 = destination2;
        this.depense2 = depense2;
        this.description2 = description2;
        this.destination3 = destination3;
        this.depense3 = depense3;
        this.description3 = description3;
    }

    public Experience() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public float getDepense1() {
        return depense1;
    }

    public void setDepense1(float depense1) {
        this.depense1 = depense1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public float getDepense2() {
        return depense2;
    }

    public void setDepense2(float depense2) {
        this.depense2 = depense2;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
    }

    public float getDepense3() {
        return depense3;
    }

    public void setDepense3(float depense3) {
        this.depense3 = depense3;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }
    
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    
    public void setDepense1(String depense) {
        this.depense1 = Float.parseFloat(depense);
    }
    
    public void setDepense2(String depense) {
        this.depense2 = Float.parseFloat(depense);
    }
    
    public void setDepense3(String depense) {
        this.depense3 = Float.parseFloat(depense);
    }

    public String toString() {
        return "Experience{" + "id=" + id + ", titre=" + titre + ", destination1=" + destination1 + ", depense1=" + depense1 + ", description1=" + description1 + ", destination2=" + destination2 + ", depense2=" + depense2 + ", description2=" + description2 + ", destination3=" + destination3 + ", depense3=" + depense3 + ", description3=" + description3 + '}';
    }
    
    Experience getExperience(){
        return null;
    }
    
}
