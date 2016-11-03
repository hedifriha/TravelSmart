/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelsmart.entities;

/**
 *
 * @author Amri
 */
public class Guide {
    
    private int id;
    
    private String titre;
    
    private String destination1;
    
    private String desc1;
    
    private String destination2;
    
    private String desc2;
    
    private String destination3;
    
    private String desc3;
    
    private String destination4;
    
    private String desc4;
    
    private String destination5;
    
    private String desc5;
    
    private String chemin;

    public Guide() {
    }

    
    
    public Guide(int id, String titre, String destination1, String desc1, String destination2, String desc2, String destination3, String desc3, String destination4, String desc4, String destination5, String desc5, String chemin) {
        this.id = id;
        this.titre = titre;
        this.destination1 = destination1;
        this.desc1 = desc1;
        this.destination2 = destination2;
        this.desc2 = desc2;
        this.destination3 = destination3;
        this.desc3 = desc3;
        this.destination4 = destination4;
        this.desc4 = desc4;
        this.destination5 = destination5;
        this.desc5 = desc5;
        this.chemin = chemin;
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

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3;
    }

    public String getDestination4() {
        return destination4;
    }

    public void setDestination4(String destination4) {
        this.destination4 = destination4;
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4;
    }

    public String getDestination5() {
        return destination5;
    }

    public void setDestination5(String destination5) {
        this.destination5 = destination5;
    }

    public String getDesc5() {
        return desc5;
    }

    public void setDesc5(String desc5) {
        this.desc5 = desc5;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
    
    
    

}
