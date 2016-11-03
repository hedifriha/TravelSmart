/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import travelSmart.DAO.entities.HotelDAO;
import travelSmart.DAO.entities.PaysDAO;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.Ville;

/**
 * FXML Controller class
 *
 * @author Hadhoud
 */
public class AddHotelsController implements Initializable {
    
    @FXML
    private TextField nom;    
    
    @FXML
    private TextArea description;
    
    @FXML
    private Button add;
    
    @FXML
    private Label idVille;
    
    @FXML
    private ComboBox pays;
    
    @FXML
    private ComboBox ville;
    
    @FXML
    private ComboBox categorie;
    
    @FXML
    private WebView webView;

    //  MyBrowser myBrowser;
    @FXML
    public void processAdd(ActionEvent event) {
        Hotel h = new Hotel();
        HotelDAO hd = new HotelDAO();
        
        h.setNomHotel(nom.getText());
        h.setDescription(description.getText());
        h.setCategorie(categorie.getValue().toString());
       // System.out.println(ville.getValue().toString());
        hd.insertHotel(h,ville.getValue().toString());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        HotelDAO hd = new HotelDAO();
        
        ArrayList<Pays> X = hd.viewpays();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < X.size(); i++) {            
            list.add(X.get(i).getNomPays());            
        }
        ObservableList<String> alpha = FXCollections.<String>observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            alpha.add(list.get(i));
        }
        pays.setItems(alpha);        
        
        ArrayList<Ville> X1 = hd.viewville();
        System.out.println(X1.size());
        ArrayList<String> list1 = new ArrayList<String>();
        for (int i = 0; i < X1.size(); i++) {            
            list1.add(X1.get(i).getNomVille());            
        }
        ObservableList<String> beta = FXCollections.<String>observableArrayList();
        for (int i = 0; i < list1.size(); i++) {
            beta.add(list1.get(i));
        }
        ville.setItems(beta);        
        ObservableList<String> tesla = FXCollections.<String>observableArrayList();
        tesla.add("1" + " etoile");
        tesla.add("2" + " etoile");
        tesla.add("3" + " etoile");
        tesla.add("4" + " etoile");
        tesla.add("5" + " etoile");
        
        categorie.setItems(tesla);
        
        HBox toolbar;
        
        WebEngine webEngine = webView.getEngine();
        
        final URL urlGoogleMaps = getClass().getResource("googlemaps.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        
        
    }    
    
    public void getIdVille(ActionEvent e) {
        ville.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                idVille.setText("Hello");
            }
        });
    }



}

    /* class MyBrowser extends Region{
    
     HBox toolbar;
    
     WebEngine webEngine = webView.getEngine();
      
    
     public MyBrowser(){
        
     final URL urlGoogleMaps = getClass().getResource("googlemaps.html");
     webEngine.load(urlGoogleMaps.toExternalForm());
          
          

     getChildren().add(webView);
        
     }
    
     }*/
