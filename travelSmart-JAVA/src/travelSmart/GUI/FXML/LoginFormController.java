/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.LIBS.FileHandler;
import travelSmart.LIBS.StackEcrans;
import travelSmart.PIDEV.Launcher;


/**
 * FXML Controller class
 *
 * @author reznov
 */
public class LoginFormController implements Initializable, InterfaceScreenController {
    private StackEcrans stackEcran;
    final static String FILE_PATH ="idUser";
    
    @FXML
    private Hyperlink link_signUp;
    @FXML
    private Hyperlink link_oublie;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_sign;
    @FXML
    private Label labelStatus;
    @FXML
    private ImageView imgInfo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgInfo.setVisible(false);
    }    

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcran = screenPage;
    }
    
    public void clicked_alpha(MouseEvent e){
        if((Hyperlink)e.getSource() == link_signUp){
            stackEcran.setScreen("SignUp");
        }
        if((Hyperlink)e.getSource() == link_oublie){
            stackEcran.setScreen("Oublie");
        }
    }
    
    public void handleExitButtonAction(ActionEvent event){
        btn_exit.getScene().getWindow().hide(); //1ere méthode
        //Platform.exit(); //2eme méthode
    }
    
    public void signInButton(ActionEvent e){
        UserDAO user = new UserDAO();
        User tmp = user.connexionUser(username.getText(), password.getText());
        if(tmp == null){
            imgInfo.setVisible(true);
            labelStatus.setText("Verifiez vos paramètres de connexion! Ou votre compte n'est pas encore validé!");
            labelStatus.getStyleClass().add("alert-warning");
        }
        else{
        String idUser = String.valueOf(tmp.getIdUser());
        FileHandler.saveText(FILE_PATH, idUser);
        //System.out.println(FileHandler.getText(FILE_PATH));
        
        btn_sign.getScene().getWindow().hide();
        Launcher L = new  Launcher();
        L.showMainStage();
        }
        
    }
    
}
