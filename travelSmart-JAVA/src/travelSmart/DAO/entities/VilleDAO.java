/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;


import java.sql.*;
import java.util.*;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.DAO.interfaces.IVilleDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author Ines
 */
public class VilleDAO implements IVilleDAO {

    private Connection connection;

    public VilleDAO() {
        connection = MyConnection.getInstance().getConnection();
    }

    @Override
    public boolean insertVille(Ville v) {

        String nomPays;
        nomPays = v.getNomPays();

        PaysDAO p1 = new PaysDAO();
        Pays p = new Pays();

        p = p1.findPaysByName(nomPays);

        String query = "Insert into villes(`id`, `nomVille`, `idPays_id`) values (NULL, ?, ?);";

        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            pSt.setString(1, v.getNomVille());
            pSt.setInt(2, p.getIdPays());
            pSt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean updateVille(Ville v) {

        String nomPays;
        nomPays = v.getNomPays();

        PaysDAO p1 = new PaysDAO();
        Pays p = new Pays();

        p = p1.findPaysByName(nomPays);

        String requete = "update villes set nomVille=?, idPays_id=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, v.getNomVille());
            ps.setInt(2, p.getIdPays());
            ps.setInt(3, v.getIdVille());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public void deleteVille(int id) {
        String requete = "delete from villes where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Ville supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public Ville findVilleById(int id) {
        Ville ville = new Ville();
        String requete = "select * from villes where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                PaysDAO p1 = new PaysDAO();
                Pays p = new Pays();

                p = p1.findPaysById(resultat.getInt(3));

                ville.setIdVille(resultat.getInt(1));
                ville.setNomVille(resultat.getString(2));
                ville.setNomPays(p.getNomPays());

            }
            return ville;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Ville findVilleByName(String name) {
        Ville ville = new Ville();
        String requete = "select * from villes where nomVille=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, name);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                PaysDAO p1 = new PaysDAO();
                Pays p = new Pays();

                p = p1.findPaysById(resultat.getInt(3));

                ville.setIdVille(resultat.getInt(1));
                ville.setNomVille(resultat.getString(2));
                ville.setNomPays(p.getNomPays());

            }
            return ville;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du pays " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Ville> displayAllVilles() {
        ArrayList<Ville> listevilles = new ArrayList<Ville>();

        String requete = "select * from villes";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Ville ville = new Ville();

                PaysDAO p1 = new PaysDAO();
                Pays p = new Pays();

                p = p1.findPaysById(resultat.getInt(3));
                
                ville.setIdVille(resultat.getInt(1));
                ville.setNomVille(resultat.getString(2));
                ville.setNomPays(p.getNomPays());
                listevilles.add(ville);
            }
            return listevilles;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des villes " + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public int countVilles() {
        String query = "select count(*) as nbrVilles from villes ;";
        int nombre = 0;
        try{
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                nombre = result.getInt("nbrVilles");
            }
        } catch (SQLException ex) {
            return nombre;
        }
        return nombre;
    }

    @Override
    public ArrayList<Ville> top20Ville() {
        ArrayList<Ville> listevilles = new ArrayList<Ville>();

        String requete = "select * from villes order by id desc limit 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Ville ville = new Ville();

                PaysDAO p1 = new PaysDAO();
                Pays p = new Pays();

                p = p1.findPaysById(resultat.getInt(3));
                
                ville.setIdVille(resultat.getInt(1));
                ville.setNomVille(resultat.getString(2));
                ville.setNomPays(p.getNomPays());
                listevilles.add(ville);
            }
            return listevilles;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des villes " + ex.getMessage());
            return null;
        }
    }
    
}
