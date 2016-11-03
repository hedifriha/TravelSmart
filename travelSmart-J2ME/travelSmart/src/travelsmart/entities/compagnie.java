/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelsmart;

/**
 *
 * @author bechir
 */
public class compagnie {
    private int idcompagnie ;
    private String nomcompagnie ;
    private String type ;
    
    
     public compagnie(int idcompagnie, String nomcompagnie, String type) {
        this.idcompagnie = idcompagnie;
        this.nomcompagnie = nomcompagnie;
        this.type = type;
        
    }

    public compagnie() {
    }

    public int getIdcompagnie() {
        return idcompagnie;
    }

    public String getNomcompagnie() {
        return nomcompagnie;
    }

    public String getType() {
        return type;
    }

    public void setIdcompagnie(int idcompagnie) {
        this.idcompagnie = idcompagnie;
    }

    public void setNomcompagnie(String nomcompagnie) {
        this.nomcompagnie = nomcompagnie;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "compagnie{" + "idcompagnie=" + idcompagnie + ", nomcompagnie=" + nomcompagnie + ", type=" + type + '}';
    }
     
     
    
}
