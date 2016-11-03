/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import travelSmart.*;
import travelSmart.CLASSES.entities.*;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.DAO.entities.*;
/**
 * FXML Controller class
 *
 * @author Hadhoud
 */
public class AddRestaurantsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nom ; 
    
    @FXML
    private TextArea description;
    
    @FXML
    private Button add;
    
    @FXML
    private ComboBox  pays;
    
    @FXML
    private ComboBox  ville;
    
    @FXML
    private ComboBox  categorie;
    
    @FXML
    public void processAdd(ActionEvent event){
        Restaurant h = new Restaurant();
        RestaurantDAO hd = new RestaurantDAO();

        h.setNomRestaurant(nom.getText());
        h.setDescription(description.getText());
        h.setCategorie(categorie.getValue().toString());
        hd.insertRestaurant(h,ville.getValue().toString()) ;
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RestaurantDAO hd = new RestaurantDAO();
        
        ArrayList<Pays> X = hd.viewpays();
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<X.size();i++){ 
            list.add(X.get(i).getNomPays()); 
        }
        ObservableList<String> alpha = FXCollections.<String>observableArrayList();
        for(int i=0; i<list.size();i++){
            alpha.add(list.get(i));
        }
        pays.setItems(alpha);        
    
        ArrayList<Ville> X1 = hd.viewville();
        System.out.println(X1.size());
        ArrayList<String> list1 = new ArrayList<String>();
        for(int i=0; i<X1.size();i++){ 
            list1.add(X1.get(i).getNomVille()); 
        }
        ObservableList<String> beta = FXCollections.<String>observableArrayList();
        for(int i=0; i<list1.size();i++){
            beta.add(list1.get(i));
        }
        ville.setItems(beta);        
        ObservableList<String> tesla = FXCollections.<String>observableArrayList();
        tesla.add("1"+" fourchette");
        tesla.add("2"+" fourchette");
        tesla.add("3"+" fourchette");
        tesla.add("4"+" fourchette");
        tesla.add("5"+" fourchette");
        
        categorie.setItems(tesla);
    }    
    
}
