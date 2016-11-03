/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import travelSmart.CLASSES.entities.Message;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.MessagerieDAO;
import travelSmart.LIBS.StackEcrans;

/**
 * FXML Controller class
 *
 * @author reznov
 */
public class EmailContactController implements Initializable, InterfaceScreenController {
    private StackEcrans stackEcran;
    
    @FXML
    private Button closeBtn;
    @FXML
    private Button sendBtn;
    
    @FXML
    private Hyperlink aPropos;
    
    @FXML
    private TextField emailText;
    @FXML
    private TextField sujetText;
    @FXML
    private TextArea contentText;
    @FXML
    private Label labelStatus;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcran = screenPage;
    }
    
    public void closeScene(ActionEvent e){
        closeBtn.getScene().getWindow().hide();
    }
    
    public void showAbout(ActionEvent e){
        stackEcran.setScreen("aPropos");
    }
    
    public void sendMessage(){
        Message msg = new Message(emailText.getText(), sujetText.getText(), contentText.getText(), 0);
        if(checkField()){
            MessagerieDAO msgDAO = new MessagerieDAO();
            if(msgDAO.sendMessage(msg)){
                labelStatus.getStyleClass().remove("label-warning");
                labelStatus.getStyleClass().add("label-info");
                labelStatus.setText("Message Envoyé!");
            }
        }
        
    }
    
    public boolean checkField(){
        if(emailText.getText().isEmpty() ||emailText.getText().indexOf("@") == -1){
            labelStatus.setText("Champs Invalides!");
            labelStatus.getStyleClass().add("label-warning");
            return false;
        }
        if(sujetText.getText().isEmpty()){
            labelStatus.setText("Champs Invalides!");
            labelStatus.getStyleClass().add("label-warning");
            return false;
        }
        if(contentText.getText().isEmpty()){
            labelStatus.setText("Champs Invalides!");
            labelStatus.getStyleClass().add("label-warning");
            return false;
        }
    return true;
    }
    
}
