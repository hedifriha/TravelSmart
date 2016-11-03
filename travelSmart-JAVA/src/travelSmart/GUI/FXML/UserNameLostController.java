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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.EmailDAO;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.LIBS.StackEcrans;

/**
 * FXML Controller class
 *
 * @author Wassila
 */
public class UserNameLostController implements Initializable, InterfaceScreenController {

    private StackEcrans stackEcran;
    
    @FXML
    private Hyperlink link_retour;
    @FXML
    TextField usernameText;
    @FXML
    private Button btn_exit;
   @FXML
    private Button btn_envoyer;
   @FXML
   private ImageView imgInfo;
   
   @FXML
    public void envoyerMail(ActionEvent event){
        EmailDAO emailDAO = new EmailDAO();
        User user = new User();
        UserDAO userDAO = new UserDAO();
        
        user = userDAO.findUserByEmail(usernameText.getText());
        emailDAO.UserRecoveryUserName(user);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcran = screenPage;
    }
    
    public void clicked_alpha(MouseEvent e){
        if((Hyperlink)e.getSource() == link_retour){
            stackEcran.setScreen("SignUp");
        }     
    }
    
    public void handleExitButtonAction(ActionEvent event){
        btn_exit.getScene().getWindow().hide(); //1ere méthode
        //Platform.exit(); //2eme méthode
    }
    
}
