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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.MessagerieDAO;
import travelSmart.DAO.entities.PaysDAO;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.DAO.entities.VilleDAO;
import travelSmart.LIBS.FileHandler;
import travelSmart.LIBS.StackEcrans;
import travelSmart.PIDEV.Launcher;

public class AfficherTousVillesController implements Initializable, InterfaceScreenController {

    @FXML
    ListView ville_list;
    @FXML
    ListView ville_list1;

    private StackEcrans stackEcrans;

    public Timer timer = new Timer();

    public String navLogin = "LoginForm.fxml";
    public String navSignUp = "SignUpForm.fxml";
    public String lostPwd = "UserLostPWD.fxml";

    public String navAdmin = "Administration.fxml";
    public String navHome = "HomeScreen.fxml";

    final static String FILE_PATH = "idUser";
    //public static String data;

    @FXML
    private ToggleButton newsletterToggleBtn;
    @FXML
    private Button homeBtn;
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
    private Button stats_btn;
    @FXML
    private MenuItem logout_btn;

    @FXML
    private MenuItem consulterMsgL;
    @FXML
    private MenuItem consulterMsgNL;
    @FXML
    private MenuItem allUsers;
    @FXML
    private MenuItem newUsers;

    @FXML
    private MenuItem newMessages;
    @FXML
    private HBox hboxNav;

    @FXML
    private ListView showPane;
    @FXML
    private Label breadcrumb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VilleDAO villeDAO = new VilleDAO();

        ObservableList<Pane> panes = FXCollections.observableArrayList();
        ObservableList<Pane> panes1 = FXCollections.observableArrayList();

        List<Ville> listVille = new ArrayList<Ville>();
        listVille = villeDAO.displayAllVilles();
        for (int i = 0; i < listVille.size(); i++) {
            int idPays = listVille.get(i).getIdVille();

            if ((idPays % 2) == 0) {
                panes1.add(loadOnePays(listVille.get(i).getIdVille(), listVille.get(i).getNomPays()));

                ville_list1.setItems(panes1);
                ville_list1.setSelectionModel(null);
            } else {
                panes.add(loadOnePays(listVille.get(i).getIdVille(), listVille.get(i).getNomPays()));

                ville_list.setItems(panes);
                ville_list.setSelectionModel(null);
            }
        }
        ville_list.setItems(panes);
        ville_list.setSelectionModel(null);

        //ListView comment_list = new ListView();
        // comment_list.setItems(panes);
        //comment_pane.getChildren().add(comment_list);
    }

    private void setPicture(Image img, ImageView imageView) {

        Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
        );

        imageView.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imageView.setClip(null);

        // apply a shadow effect.
        imageView.setEffect(new DropShadow(10, Color.BLACK));
        imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        // store the rounded image in the imageView.
        imageView.setImage(image);

    }

    private Pane loadOnePays(int idVille, String nomPays) {

        VilleDAO vDAO = new VilleDAO();
        Ville ville = new Ville();

        ville = vDAO.findVilleById(idVille);

        PaysDAO pDAO = new PaysDAO();
        Pays pays = new Pays();

        pays = pDAO.findPaysByName(nomPays);

        Pane pane = new Pane();
        Image img = new Image("/travelSmart/RESSOURCES/images/pays.png");
        ImageView imageView = new ImageView(img);

        // Pane Size
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setPrefWidth(300);
        pane.setPrefHeight(114);
        String style = "-fx-padding: 8 15 15 15;\n"
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 16;";
        pane.setStyle(style);
        pane.setId("pane_oneVille");

        // ImageView Proprieties
        imageView.setFitHeight(65);
        imageView.setFitWidth(65);

        imageView.setLayoutX(14);
        imageView.setLayoutY(7);
        setPicture(img, imageView);
        String style_btn = "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 0 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-font-size: 0.8em;";

        String styleNom = ""
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 18px;";

        Label nom_ville = new Label(ville.getNomVille());
        Label nom_pays = new Label("Pays: " + ville.getNomPays());
        Label monnaie = new Label("Monnaie: " + pays.getMonnaie());
        Label langue = new Label("Langue officielle: " + pays.getLangue());
        Button btn = new Button("Visiter");

        // Label Proprieties
        //nom_pays.setPrefSize(314, 61);
        nom_pays.setId("nom_pays");
        nom_ville.setLayoutX(88);
        nom_ville.setLayoutY(5);
        nom_ville.setId("nom_ville");
        nom_ville.setStyle(styleNom);

        nom_pays.setLayoutX(93);
        nom_pays.setLayoutY(30);
        nom_pays.setId("nom_pays");

        monnaie.setLayoutX(93);
        monnaie.setLayoutY(50);
        monnaie.setId("monnaie");

        langue.setLayoutX(93);
        langue.setLayoutY(70);
        langue.setId("langue");

        // Button Position
        btn.setLayoutX(270);
        btn.setLayoutY(30);
        btn.setId("btn_visiter");
        btn.getStyleClass().add("btn-primary");
        
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                FileHandler.saveText("idVille", String.valueOf(idVille));
                try {
                    stackEcrans.loadScreen("afficheVille", "AfficherVille.fxml");
                } catch (Exception ex) {
                    Logger.getLogger(AfficherTousPaysController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stackEcrans.setScreen("afficheVille");
            }
        });

        pane.getChildren().add(imageView);
        pane.getChildren().add(nom_ville);
        pane.getChildren().add(nom_pays);
        pane.getChildren().add(monnaie);
        pane.getChildren().add(langue);
        pane.getChildren().add(btn);

        return pane;
    }

    public void showHome(ActionEvent e) {
        stackEcrans.setScreen("home");
        stackEcrans.unloadScreen("listeVilles");
    }

    public void logoutButton(ActionEvent e) {
        FileHandler.deleteFile(FILE_PATH);

        try {
            userMenu.getScene().getWindow().hide();
        } catch (Exception ex) {
            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }

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
    
    public void showStats(ActionEvent e) throws Exception {
        stackEcrans.loadScreen("admin", "Administration.fxml");
        stackEcrans.setScreen("admin");
        stackEcrans.unloadScreen("listeVilles");
        
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

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcrans = screenPage;
    }

}
