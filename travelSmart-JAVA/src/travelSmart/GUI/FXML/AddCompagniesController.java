/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package travelSmart.GUI.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import travelSmart.CLASSES.entities.Compagnies;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.CompagniesDAO;
import travelSmart.LIBS.StackEcrans;

/**
 * FXML Controller class
 *
 * @author bechir
 */
public class AddCompagniesController implements Initializable, InterfaceScreenController {
    private StackEcrans stackEcrans;
    @FXML
    TextField nom;
    @FXML
    Button add;
    @FXML
    Label erreur;
    @FXML
    ComboBox typeCB;

   /* @FXML
    public void getCompagnies(ActionEvent event) {

        List<Compagnies> listesComp = new ArrayList<Compagnies>();
        CompagniesDAO CompagniesDAO = new CompagniesDAO();
        listesComp = CompagniesDAO.DisplayAllCompagnies();

        ObservableList<String> options = FXCollections.observableArrayList();
      
            options.add("aerienne"); 
            options.add("maritime");
        
        final ComboBox comboBox = new ComboBox(options);

    }*/

    @FXML
    public void processAdd(ActionEvent event) {

        Compagnies Compagnies = new Compagnies();

       CompagniesDAO Compagniesdao = new CompagniesDAO();

        Compagnies = Compagniesdao.findCompagniesByName(nom.getText());

    
            Compagnies.setNom_comp(nom.getText());
            Compagnies.setType("" + typeCB.getValue());
            Compagniesdao.insertCompagnies(Compagnies);
            
    }

  /*  @Override
    public void initialize(URL url, ResourceBundle rb) {
        PaysDAO paysDAO = new PaysDAO();

        List<Pays> listPays = paysDAO.displayAllPays();
        ArrayList<String> listNomPays = new ArrayList<String>();
        for (int i = 0; i < listPays.size(); i++) {
            listNomPays.add(listPays.get(i).getNomPays());
        }
        ObservableList<String> list = FXCollections.<String>observableArrayList();
        for (int i = 0; i < listNomPays.size(); i++) {
            list.add(listNomPays.get(i));
        }
        paysCB.setItems(list);

    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        CompagniesDAO CompDAO = new CompagniesDAO();
        
      /*  ArrayList<String> listcomp = new ArrayList<String>();
       
            listcomp.add("maritime");
            listcomp.add("aerienne");    
    */
         ObservableList<String> tesla = FXCollections.<String>observableArrayList();
        tesla.add("aerienne");
        tesla.add("maritime");
      
        
        typeCB.setItems(tesla); 
       /* for (int i = 0; i < listcomp.size(); i++) {
            list.add(listcomp.get(i));
        }*/
     
         
    }

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcrans=screenPage;
    }
}

