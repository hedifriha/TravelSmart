/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;


import java.sql.*;
import java.util.*;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.DAO.interfaces.IPaysDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author Ines
 */
public class PaysDAO implements IPaysDAO {

    private Connection connection;

    public PaysDAO() {
        connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public boolean insertPays(Pays p) {

        String query = "Insert into pays(`id`, `nomPays`, `capitale`, `monnaie`, `langue`) "
                + "values (NULL, ?, ?, ?, ?);";

        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setString(1, p.getNomPays());
            pSt.setString(2, p.getCapital());
            pSt.setString(3, p.getMonnaie());
            pSt.setString(4, p.getLangue());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Pays non ajouté!");
            return false;
        }
        
    }

    @Override
    public boolean updatePays(Pays p) {
        String requete = "update pays set nomPays=?, capitale=?, monnaie=?, langue=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, p.getNomPays());
            ps.setString(2, p.getCapital());
            ps.setString(3, p.getMonnaie());
            ps.setString(4, p.getLangue());
            ps.setInt(5, p.getIdPays());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            //System.out.println("erreur lors de la mise à jour " + ex.getMessage());
            return false;
        }
    }

    @Override
    public void deletePays(int id) {
        String requete = "delete from pays where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Pays supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Pays findPaysById(int id) {
        Pays pays = new Pays();
        String requete = "select * from pays where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                pays.setIdPays(resultat.getInt(1));
                pays.setNomPays(resultat.getString(2));
                pays.setCapital(resultat.getString(3));
                pays.setMonnaie(resultat.getString(4));
                pays.setLangue(resultat.getString(5));
            }
            return pays;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Pays findPaysByName(String name) {
        Pays pays = new Pays();
        String requete = "select * from pays where nomPays=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, name);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                pays.setIdPays(resultat.getInt(1));
                pays.setNomPays(resultat.getString(2));
                pays.setCapital(resultat.getString(3));
                pays.setMonnaie(resultat.getString(4));
                pays.setLangue(resultat.getString(5));
            }
            return pays;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            pays.setIdPays(0);
            return pays;
        }
    }

    @Override
    public ArrayList<Pays> displayAllPays() {
        ArrayList<Pays> listepays = new ArrayList<Pays>();

        String requete = "select * from pays";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Pays pays = new Pays();
                pays.setIdPays(resultat.getInt(1));
                pays.setNomPays(resultat.getString(2));
                pays.setCapital(resultat.getString(3));
                pays.setMonnaie(resultat.getString(4));
                pays.setLangue(resultat.getString(5));

                listepays.add(pays);
            }
            return listepays;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des pays " + ex.getMessage());
            return null;
        }
    }

    @Override
    public int countPays() {
        String query = "select count(*) as nbrPays from pays ;";
        int nombre = 0;
        try{
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                nombre = result.getInt("nbrPays");
            }
        } catch (SQLException ex) {
            return nombre;
        }
        return nombre;
    }

    @Override
    public int countVillesInPays(int idPays) {
        String query = "select count(*) as nbrVillesInPays from villes where idPays_id=" + idPays +" ;";
        int nombre = 0;
        try{
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                nombre = result.getInt("nbrVillesInPays");
            }
        } catch (SQLException ex) {
            return nombre;
        }
        return nombre;
    }

    @Override
    public ArrayList<Pays> top20Pays() {
        ArrayList<Pays> listepays = new ArrayList<Pays>();

        String requete = "select * from pays order by id desc limit 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Pays pays = new Pays();
                pays.setIdPays(resultat.getInt(1));
                pays.setNomPays(resultat.getString(2));
                pays.setCapital(resultat.getString(3));
                pays.setMonnaie(resultat.getString(4));
                pays.setLangue(resultat.getString(5));

                listepays.add(pays);
            }
            return listepays;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des pays " + ex.getMessage());
            return null;
        }
    }
    
}
