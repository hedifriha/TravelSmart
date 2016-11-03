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
public class aeroport {
    private int idaeroport;
    private String nomaeroport;
    private String nbrpiste;
    private String chemin;
    private String adresse;
    private String description;

    public aeroport(int idaeroport, String nomaeroport, String nbrpiste, String chemin, String adresse, String description) {
        this.idaeroport = idaeroport;
        this.nomaeroport = nomaeroport;
        this.nbrpiste = nbrpiste;
        this.chemin = chemin;
        this.adresse = adresse;
        this.description = description;
    }

    public aeroport() {
    }

    public int getIdaeroport() {
        return idaeroport;
    }

    public void setIdaeroport(int idaeroport) {
        this.idaeroport = idaeroport;
    }

    public String getNomaeroport() {
        return nomaeroport;
    }

    public void setNomaeroport(String nomaeroport) {
        this.nomaeroport = nomaeroport;
    }

    public String getNbrpiste() {
        return nbrpiste;
    }

    public void setNbrpiste(String nbrpiste) {
        this.nbrpiste = nbrpiste;
    }

    public String getChemin() {
        return chemin;
    }


    
    

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
        public String toString() {
        return "aeroport{" + "idaeroport=" + idaeroport + ", nomaeroport=" + nomaeroport + ", nbrpiste=" + nbrpiste + ", chemin=" + chemin + ", adresse=" + adresse + ", description=" + description + '}';
    }

}
