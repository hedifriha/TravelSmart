/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.LIBS.StackEcrans;
import static travelSmart.PIDEV.Launcher.SPLASH_IMAGE;

/**
 * FXML Controller class
 *
 * @author reznov
 */
public class AProposController implements Initializable, InterfaceScreenController  {
    private StackEcrans stackEcran;
    public Desktop desktop = Desktop.getDesktop();
    
    @FXML
    private Button closeBtn;
    @FXML
    private Hyperlink contactForm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcran = screenPage;
    }
    
    public void closeScene(ActionEvent e){
        closeBtn.getScene().getWindow().hide();
    }
    
    public void contactForm(ActionEvent e){
        stackEcran.setScreen("contactForm");

    }   
}
