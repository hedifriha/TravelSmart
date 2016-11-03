/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import travelSmart.CLASSES.entities.Compagnies;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.CompagniesDAO;
import travelSmart.DAO.entities.MessagerieDAO;
import travelSmart.DAO.entities.PaysDAO;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.DAO.entities.VilleDAO;
import travelSmart.LIBS.FileHandler;
import travelSmart.LIBS.StackEcrans;
import travelSmart.PIDEV.Launcher;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class AfficheCompagniesController implements Initializable, InterfaceScreenController {
    
    @FXML
    private Label nomComp;
    @FXML
    private Label typeComp;

    
    private StackEcrans stackEcrans;
    /*
    public String navLogin = "LoginForm.fxml";
    public String navSignUp = "SignUpForm.fxml";
    public String lostPwd = "UserLostPWD.fxml";
    
    public Timer timer = new Timer();
    
    final static String FILE_PATH = "idUser";
    
    @FXML
    private Button btn_exit;
    @FXML
    private Button internet_btn;
    @FXML
    private Label online_label;
    @FXML
    private Label etatNewsletter;
    @FXML
    private MenuButton userMenu;
    @FXML
    private Button login_btn;
    @FXML
    private MenuButton notif_btn;
    @FXML
    private Button dashbord_btn;
    @FXML
    private MenuItem logout_btn;
    @FXML
    private MenuItem newMessages;
    @FXML
    private HBox hboxNav;
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //roundCheckInternet();
        
        //initSession();

        int idCompagnie = 1;
        //Affichage d'un pays
        CompagniesDAO compDAO = new CompagniesDAO();
        Compagnies Compagnies = new Compagnies();

        Compagnies = compDAO.findCompagniesById(idCompagnie);

        
        
        nomComp.setText(Compagnies.getNom_comp());
        typeComp.setText(Compagnies.getType());

        /*******/
        
    }    
    
    public void showStats(ActionEvent e) {
        stackEcrans.setScreen("admin");
        
    }
    
    public void showHome(ActionEvent e){
        stackEcrans.setScreen("home");
    }
    
    public void logoutButton(ActionEvent e) {
        //FileHandler.deleteFile(FILE_PATH);

        try {
            //userMenu.getScene().getWindow().hide();
        } catch (Exception ex) {
            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }
    /*
    public void roundCheckInternet() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // Check connexion
                        internetConnFlag();
                        //check unread messages
                        MessageNotificationsFlag();
                    }
                });
            }
        }, 0, 5000);
    }
    
    public void internetConnFlag() {
        if (Launcher.getInternetStatus() != 0) {
            internet_btn.setDisable(false);
            online_label.setText("En Ligne");
        } else {
            online_label.setText("Hors Ligne");
            internet_btn.setDisable(true);
        }
    }
    
    public void MessageNotificationsFlag() {
        //Messages
        MessagerieDAO msg = new MessagerieDAO();
        int count = msg.countMessages(0); //Unread

        if (count != 0) {
            notif_btn.setDisable(false);
            newMessages.setText("Nouveaux Messages (" + count + ")");
        } else {
            notif_btn.setDisable(true);
        }
    }
    
    public void initSession() {
        User user = new User();

        if (FileHandler.fileExists(FILE_PATH)) {
            setProfile(user);
        } 
    }
    
    public void setProfile(User user) {
        hboxNav.getChildren().remove(login_btn);
        UserDAO userDAO = new UserDAO();

        int idUser = Integer.valueOf(FileHandler.getText(FILE_PATH));
        user = userDAO.getUserById(idUser);

        userMenu.setText(user.getNom());
        if (user.getClass().getSimpleName().equals("Administrateur") == false) {
            hboxNav.getChildren().remove(dashbord_btn);
        }

    }   
*/
    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcrans = screenPage;
    }

}
