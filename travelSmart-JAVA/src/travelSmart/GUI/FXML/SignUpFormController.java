/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.LIBS.StackEcrans;

/**
 * FXML Controller class
 *
 * @author reznov
 */
public class SignUpFormController implements Initializable, InterfaceScreenController {
    private StackEcrans stackEcran;
    public static int verifData=0;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label labelStatus;
    @FXML
    private Hyperlink link_signIn;
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_signUp;
    @FXML
    TextField nomText;
    @FXML
    TextField prenomText;
    @FXML
    TextField passwordText;
    @FXML
    TextField passwordVerifText;
    @FXML
    TextField emailText;
    @FXML
    TextField adresseText;
    @FXML
    private TextField phoneText;
    @FXML
    private ImageView imgInfo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgInfo.setVisible(false);
    }    

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcran = screenPage;
    }
    
    public void clicked(MouseEvent e){
        if((Hyperlink)e.getSource() == link_signIn){
            stackEcran.setScreen("Login");
        }     
    }
    public void handleExitButtonAction(ActionEvent event){
        btn_exit.getScene().getWindow().hide(); //1ere méthode
        //Platform.exit(); //2eme méthode
    }
    
    public boolean verifDataEntered(KeyEvent event){
        imgInfo.setVisible(true);
        if((TextField)event.getSource() == emailText){
            if(emailText.getText().isEmpty() || (emailText.getText().indexOf("@") == -1 && emailText.getText().indexOf(".") == -1 )){
                emailText.getStyleClass().add("alert-warning");
                emailText.setTooltip(new Tooltip("Saisir une @ valide!"));
                verifData++;
                btn_signUp.setDisable(true);
            }
            else
            {
                emailText.getStyleClass().remove("alert-warning");
                emailText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        
        if((TextField)event.getSource() == nomText){
            if(nomText.getText().isEmpty()){
                nomText.getStyleClass().add("alert-warning");
                nomText.setTooltip(new Tooltip("Saisir un nom valide!"));
                verifData++;
                btn_signUp.setDisable(true);
            }
            else
            {
                nomText.getStyleClass().remove("alert-warning");
                nomText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        
        if((TextField)event.getSource() == prenomText){
            if(prenomText.getText().isEmpty()){
                prenomText.getStyleClass().add("alert-warning");
                prenomText.setTooltip(new Tooltip("Saisir un prenom valide!"));
                verifData++;
                btn_signUp.setDisable(true);
            }
            else
            {
                prenomText.getStyleClass().remove("alert-warning");
                prenomText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        
        if((TextField)event.getSource() == passwordText){
            if(passwordText.getText().isEmpty()){
                passwordText.getStyleClass().add("alert-warning");
                passwordText.setTooltip(new Tooltip("Saisir un mot de passe valide!"));
                verifData++;
                btn_signUp.setDisable(true);
            } 
            else
            {
                passwordText.getStyleClass().remove("alert-warning");
                passwordText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        
        if((TextField)event.getSource() == passwordVerifText){
            if(passwordVerifText.getText().isEmpty() || (passwordVerifText.getText().equals(passwordText) == false)){
                passwordVerifText.getStyleClass().add("alert-danger");
                passwordVerifText.setTooltip(new Tooltip("Les mots de passe ne correspondent pas!"));
                verifData++;
                btn_signUp.setDisable(true);
            } 
            else
            {
                passwordVerifText.getStyleClass().remove("alert-danger");
                passwordVerifText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        
        if((TextField)event.getSource() == adresseText){
            if(adresseText.getText().isEmpty() || adresseText.getText().length() < 10){
                adresseText.getStyleClass().add("alert-warning");
                adresseText.setTooltip(new Tooltip("Addresse non valide!!"));
                verifData++;
                btn_signUp.setDisable(true);
            } 
            else
            {
                adresseText.getStyleClass().remove("alert-warning");
                adresseText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        
        if((TextField)event.getSource() == phoneText){
            if(phoneText.getText().isEmpty() || phoneText.getText().length() < 8){
                phoneText.getStyleClass().add("alert-warning");
                phoneText.setTooltip(new Tooltip("Numéro non valide!!"));
                verifData++;
                btn_signUp.setDisable(true);
            } 
            else
            {
                phoneText.getStyleClass().remove("alert-warning");
                phoneText.setTooltip(null);
                verifData=0;
                btn_signUp.setDisable(false);
            }
        }
        if(verifData == 0){
            labelStatus.setText("Données Accéptées!");
            labelStatus.getStyleClass().remove("alert-danger");
            labelStatus.getStyleClass().add("alert-info");
        }
        else{
            labelStatus.setText("Verifiez les données saisies avant de commencer!");
            labelStatus.getStyleClass().add("alert-danger");
            btn_signUp.setDisable(true);
            return false;
        }
        
        return true;
    }
    
    public void Inscription(ActionEvent e)
    {
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setNom(nomText.getText());
        user.setPrenom(prenomText.getText());
        user.setAdresse(adresseText.getText());
        user.setBadge(1);
        user.setPoints(0);
        user.setEmail(emailText.getText());
        user.setProfile_pic("");
        user.setTel(phoneText.getText());
        
        if(userDAO.inscriptionUser(user, passwordText.getText()))
            stackEcran.setScreen("Login");
        else{
            labelStatus.setText("Existe Déja!");
            labelStatus.getStyleClass().add("alert-warning");
            btn_signUp.setDisable(true);            
        }
        
        
    }
    
    
}
