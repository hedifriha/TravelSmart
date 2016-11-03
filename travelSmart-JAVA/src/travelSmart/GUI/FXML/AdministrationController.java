/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import sun.plugin.dom.exception.NoModificationAllowedException;
import travelSmart.CLASSES.entities.Compagnies;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.CLASSES.entities.Media;
import travelSmart.CLASSES.entities.Message;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.Restaurant;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.CompagniesDAO;
import travelSmart.DAO.entities.EmailDAO;
import travelSmart.DAO.entities.HotelDAO;
import travelSmart.DAO.entities.MediaDAO;
import travelSmart.DAO.entities.MessagerieDAO;
import travelSmart.DAO.entities.PaysDAO;
import travelSmart.DAO.entities.RestaurantDAO;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.DAO.entities.VilleDAO;
import static travelSmart.GUI.FXML.HomeScreenController.FILE_PATH;
import travelSmart.LIBS.FileHandler;
import travelSmart.LIBS.Ftp;
import travelSmart.LIBS.StackEcrans;
import travelSmart.PIDEV.Launcher;

/**
 * FXML Controller class
 *
 * @author reznov
 */
public class AdministrationController implements Initializable, InterfaceScreenController {

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
    private Pane PaneImageHotel;
    @FXML
    private Pane PaneImageRestaurant;

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
    private MenuItem listVilles;
    @FXML
    private MenuItem listPays;
    @FXML
    private MenuItem listHotels;
    @FXML
    private MenuItem listRestaurants;
    @FXML
    private MenuItem listCompagnies;

    @FXML
    private MenuItem addPays;
    @FXML
    private MenuItem editPays;
    @FXML
    private MenuItem addVille;
    @FXML
    private MenuItem editVille;
    @FXML
    private MenuItem addHotel;
    @FXML
    private MenuItem editHotel;
    @FXML
    private MenuItem addRestaurant;
    @FXML
    private MenuItem addCompagnie;
    @FXML
    private MenuItem editRestaurant;
    @FXML
    private MenuItem showStats;
    @FXML
    private MenuItem sendNewsBtn;

    @FXML
    private MenuItem newMessages;
    @FXML
    private HBox hboxNav;

    @FXML
    private ListView showPane;
    @FXML
    private Label breadcrumb;

    /**
     * ************** Media   *******************
     */
    /**
     * **** Pays *****
     */
    @FXML
    private Pane PaneImagePays;
    @FXML
    private Label label_infoPays;

    // Add btns
    @FXML
    private Button btn_plusPays1;
    @FXML
    private Button btn_plusPays2;
    @FXML
    private Button btn_plusPays3;

    // Hover btns
    @FXML
    private Button btn_moinsPays1;
    @FXML
    private Button btn_moinsPays2;
    @FXML
    private Button btn_moinsPays3;

    // Delete btns
    @FXML
    private Button btnmPays1;
    @FXML
    private Button btnmPays2;
    @FXML
    private Button btnmPays3;

    // ImageViews
    @FXML
    private ImageView imgViewPays1;
    @FXML
    private ImageView imgViewPays2;
    @FXML
    private ImageView imgViewPays3;

    // Liste Media
    private List<Media> listeMediaPays = new ArrayList<Media>();
    private List<Integer> listeRandPays = new ArrayList<Integer>();
    /**
     * **** Pays *****
     */

    /**
     * **** Ville *****
     */
    @FXML
    private Pane PaneImageVille;

    @FXML
    private Label label_infoVille;

    // Add btns
    @FXML
    private Button btn_plusVille1;
    @FXML
    private Button btn_plusVille2;
    @FXML
    private Button btn_plusVille3;

    // Hover btns
    @FXML
    private Button btn_moinsVille1;
    @FXML
    private Button btn_moinsVille2;
    @FXML
    private Button btn_moinsVille3;

    // Delete btns
    @FXML
    private Button btnmVille1;
    @FXML
    private Button btnmVille2;
    @FXML
    private Button btnmVille3;

    // ImageViews
    @FXML
    private ImageView imgViewVille1;
    @FXML
    private ImageView imgViewVille2;
    @FXML
    private ImageView imgViewVille3;

    // Liste Media
    private List<Media> listeMediaVille = new ArrayList<Media>();
    private List<Integer> listeRandVille = new ArrayList<Integer>();

    /**
     * **** Ville *****
     */

    /**
     * ************** Media   *******************
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roundCheckInternet();
        //setMessagesPane(0);
        //System.out.println("eg: "+PaneImageVille.getParent().getParent().toString());

    }

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcrans = screenPage;
    }
    
    public void sendEmailNews(ActionEvent e) throws Exception{
        EmailDAO email = new EmailDAO();
        email.SendNewsletter();
    }

    public void showHome(ActionEvent e) {
        stackEcrans.unloadScreen("admin");
        stackEcrans.setScreen("home");
    }

    public void handleExitButtonAction(ActionEvent event) {
        timer.cancel();
        Platform.exit();

    }

    public void logoutButton(ActionEvent e) {
        FileHandler.deleteFile(FILE_PATH);

        try {
            userMenu.getScene().getWindow().hide();
            timer.cancel();
            StackEcrans container = new StackEcrans();

            container.loadScreen("SignUp", navSignUp);
            container.loadScreen("Login", navLogin);
            container.loadScreen("Oublie", lostPwd);
            container.setScreen("Login");

            Scene scene = new Scene(container);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Sign Up - Sign In");
            stage.resizableProperty().setValue(false); // Desactiver le resize
            stage.centerOnScreen();
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(HomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Pane showStats() {
        Pane pane = new Pane();
        UserDAO user = new UserDAO();
        PaysDAO pays = new PaysDAO();
        VilleDAO ville = new VilleDAO();

        ObservableList<PieChart.Data> usersData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Abonnés", user.countUsersbyType(0)),
                        new PieChart.Data("Abonnés Privilégiés", user.countUsersbyType(1)),
                        new PieChart.Data("Administrateurs", user.countUsersbyType(2)));
        final PieChart chartUsers = new PieChart(usersData);
        chartUsers.setTitle("Type de Users");
        chartUsers.setLayoutX(5);
        chartUsers.setLayoutY(5);
        chartUsers.setPrefWidth(350);

        ObservableList<PieChart.Data> contentData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Utilisateurs", user.countUserbyStatus(1)),
                        new PieChart.Data("Pays", pays.countPays()),
                        new PieChart.Data("Villes", ville.countVilles()));
        final PieChart chartContent = new PieChart(contentData);
        chartContent.setTitle("Type de Données");
        chartContent.setLayoutX(370);
        chartContent.setLayoutY(5);
        chartContent.setPrefWidth(350);

        pane.getChildren().addAll(chartContent, chartUsers);
        
        return pane;

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
        }, 0, 60000);
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

    public void setMessagesPane(int type) {
        MessagerieDAO messagerie = new MessagerieDAO();
        ArrayList<Message> messages = messagerie.getMessages(type);
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        for (int i = 0; i < messages.size(); i++) {
            panes.add(getMessagesInPane(messages.get(i), type));
        }
        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        if (type == 0) {
            breadcrumb.setText("Nouveaux Messages (" + messagerie.countMessages(0) + ")");
        } else {
            breadcrumb.setText("Tous les Messages (" + messagerie.countMessages(1) + ")");
        }
    }

    public void setUsersPane(int type) {
        UserDAO users = new UserDAO();
        ArrayList<User> listeUsers = users.getAllUsers(type);
        ObservableList<Pane> panes = FXCollections.observableArrayList();

        for (int i = 0; i < listeUsers.size(); i++) {
            panes.add(getUsersInPane(listeUsers.get(i), type));
        }

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        if (type == 0) {
            breadcrumb.setText("Nouveaux Utilisateurs à Valider (" + users.countUserbyStatus(0) + ")");
        } else {
            breadcrumb.setText("Tous les Utilisateurs (" + users.countUserbyStatus(1) + ")");
        }
    }

    public void setVillesPane() {
        VilleDAO ville = new VilleDAO();
        ArrayList<Ville> listeVilles = ville.displayAllVilles();
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        for (int i = 0; i < listeVilles.size(); i++) {
            panes.add(getVillesInPane(listeVilles.get(i)));
        }

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Toutes les Villes (" + ville.countVilles() + ")");
    }

    public void setPaysPane() {
        PaysDAO pays = new PaysDAO();
        ArrayList<Pays> listePays = pays.displayAllPays();
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        for (int i = 0; i < listePays.size(); i++) {
            panes.add(getPaysInPane(listePays.get(i)));
        }

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Tous les Pays (" + pays.countPays() + ")");
    }
    
    public void setHotelsPane() {
        HotelDAO hotel = new HotelDAO();
        ArrayList<Hotel> listeHotels = hotel.displayAllHotels();
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        for (int i = 0; i < listeHotels.size(); i++) {
            panes.add(getHotelsInPane(listeHotels.get(i)));
        }

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Tous les Hotels");
    }
    
    public void setAddHotelPane(){
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(addHotelInPane());

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Ajouter Un Hotel");
    }
    
    public void setCompagniesPane() {
        CompagniesDAO compagnie = new CompagniesDAO();
        ArrayList<Compagnies> listeCompagnies = compagnie.DisplayAllCompagnies();
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        for (int i = 0; i < listeCompagnies.size(); i++) {
            panes.add(getCompagniesInPane(listeCompagnies.get(i)));
        }

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Toutes les Compagnies");
    }
    
    public void setAddCompagniePane(){
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(addCompagnieInPane());

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Ajouter Une Compagnie");
    }
    
    public void setRestaurantsPane() {
        RestaurantDAO rest = new RestaurantDAO();
        ArrayList<Restaurant> listeRest = rest.displayAllRestaurant();
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        for (int i = 0; i < listeRest.size(); i++) {
            panes.add(getRestaurantsInPane(listeRest.get(i)));
        }

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Tous les Restaurants");
    }
    
    public void setAddRestaurantPane(){
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(addRestaurantInPane());

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Ajouter Un Restaurant");
    }
    
    public void setEditRestaurantPane(){
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(EditRestaurantInPane());

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Modifier Un Restaurant");
    }
    
    public void setEditHotelPane(){
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(EditHotelInPane());

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Modifier Un Hotel");
    }
    
    public void setStatsPane(){
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(showStats());

        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Statistiques");
    }
    
    public void setAddPaysPane() {
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(addPaysInPane());
        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Ajouter un Pays");

        /**
         * ******* Media **********
         */
        Random rand = new Random();
        listeRandPays.add(rand.nextInt(5000) + 1);
        listeRandPays.add(rand.nextInt(5000) + 1);
        listeRandPays.add(rand.nextInt(5000) + 1);

        listeMediaPays.add(null);
        listeMediaPays.add(null);
        listeMediaPays.add(null);
        /**
         * ******* Media **********
         */
    }

    public void setEditPaysPane() {
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(EditPaysInPane());
        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Modifier un Pays");

    }

    public void setAddVillePane() {
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(addVilleInPane());
        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Ajouter une Ville");

        /**
         * ******* Media **********
         */
        Random rand = new Random();
        listeRandVille.add(rand.nextInt(5000) + 1);
        listeRandVille.add(rand.nextInt(5000) + 1);
        listeRandVille.add(rand.nextInt(5000) + 1);

        listeMediaVille.add(null);
        listeMediaVille.add(null);
        listeMediaVille.add(null);
        /**
         * ******* Media **********
         */
    }

    public void setEditVillePane() {
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        panes.add(EditVilleInPane());
        showPane.setItems(panes);
        showPane.setSelectionModel(null);

        breadcrumb.setText("Modifier une Ville");

    }

    public Pane getUsersInPane(User U, int type) {
        UserDAO users = new UserDAO();
        Pane pane = new Pane();

        Label nom = new Label(U.getNom());
        nom.setLayoutX(5);
        nom.setLayoutY(5);

        Label prenom = new Label(U.getPrenom());
        prenom.setLayoutX(100);
        prenom.setLayoutY(5);

        Label email = new Label(U.getEmail());
        email.setLayoutX(200);
        email.setLayoutY(5);

        Label badge = new Label();

        Button supprimer = new Button("Supprimer");
        supprimer.getStyleClass().add("btn-danger");
        supprimer.setLayoutX(600);
        supprimer.setLayoutY(5);

        Button valider = new Button("Valider");
        valider.getStyleClass().add("btn-success");
        valider.setLayoutX(480);
        valider.setLayoutY(5);

        if (type == 0) {
            supprimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    users.supprimerUser(U.getIdUser());
                    setUsersPane(0);
                }
            });

            valider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    users.validerUser(U.getIdUser());
                    setUsersPane(0);
                }
            });
            pane.getChildren().addAll(nom, prenom, email, supprimer, valider);
        } else {
            supprimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    users.supprimerUser(U.getIdUser());
                    setUsersPane(1);
                }
            });
            if (U.getBadge() == 1) {
                badge.setText("Abonné");
                badge.setLayoutX(430);
                badge.setLayoutY(5);
                badge.getStyleClass().add("label-silver");
            } else {
                badge.setText("Abonné Privilégié");
                badge.setLayoutX(430);
                badge.setLayoutY(5);
                badge.getStyleClass().add("label-gold");
            }
            pane.getChildren().addAll(nom, prenom, email, badge, supprimer);
        }

        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 18px;");

        return pane;
    }

    public Pane getMessagesInPane(Message M, int type) {
        MessagerieDAO msg = new MessagerieDAO();
        Pane pane = new Pane();

        Label from = new Label(M.getFrom());
        from.setLayoutX(5);
        from.setLayoutY(5);

        Label subject = new Label(M.getSubject());
        subject.setLayoutX(150);
        subject.setLayoutY(5);
        subject.setPrefWidth(150);
        subject.setWrapText(true);

        TextArea content = new TextArea(M.getContent());
        content.setWrapText(true);
        content.setPrefWidth(220);
        content.setLayoutX(300);
        content.setLayoutY(5);
        content.setEditable(false);
        content.setMaxHeight(100);

        Button read = new Button("Lire");
        read.getStyleClass().add("btn-success");
        read.setLayoutX(550);
        read.setLayoutY(5);

        Button delete = new Button("Supprimer");
        delete.getStyleClass().add("btn-danger");
        delete.setLayoutX(620);
        delete.setLayoutY(5);
        if (type == 0) {

            read.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    msg.readMessage(M.getContent());
                    setMessagesPane(0);
                }
            });

            delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    msg.deleteMessage(M.getContent());
                    setMessagesPane(0);
                }
            });
            pane.getChildren().addAll(from, subject, content, read, delete);
        } else {
            delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    msg.deleteMessage(M.getContent());
                    setMessagesPane(1);
                }
            });
            pane.getChildren().addAll(from, subject, content, delete);
        }

        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 18px;");

        return pane;
    }
    
    public Pane getCompagniesInPane(Compagnies C){
        Pane pane = new Pane();
        CompagniesDAO comp = new CompagniesDAO();
        
        Label nomCompagnie = new Label("Nom : " + C.getNom_comp());
        nomCompagnie.setLayoutX(5);
        nomCompagnie.setLayoutY(5);

        Label typeCompagnie = new Label("Type : " + C.getType());
        typeCompagnie.setLayoutX(150);
        typeCompagnie.setLayoutY(5);
        
        Button delete = new Button("Supprimer");
        delete.getStyleClass().add("btn-danger");
        delete.setLayoutX(620);
        delete.setLayoutY(5);
        
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                comp.deleteCompagnies(C.getIdCompagnie());
                setCompagniesPane();
            }
        });
        
        pane.getChildren().addAll(nomCompagnie, typeCompagnie, delete);
        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 18px;");
        return pane;
    }

    public Pane getPaysInPane(Pays P) {
        PaysDAO pays = new PaysDAO();
        Pane pane = new Pane();

        Label nomPays = new Label("Nom : " + P.getNomPays());
        nomPays.setLayoutX(5);
        nomPays.setLayoutY(5);

        Label capitalePays = new Label("Capitale : " + P.getCapital());
        capitalePays.setLayoutX(150);
        capitalePays.setLayoutY(5);

        Label languePays = new Label("Langue : " + P.getLangue());
        languePays.setLayoutX(300);
        languePays.setLayoutY(5);

        Label villeInPays = new Label("Nombre Villes : " + pays.countVillesInPays(P.getIdPays()));
        villeInPays.setLayoutX(470);
        villeInPays.setLayoutY(5);

        Button remove = new Button("Supprimer");
        remove.setLayoutX(610);
        remove.setLayoutY(5);
        remove.getStyleClass().add("btn-danger");

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                pays.deletePays(P.getIdPays());
                setPaysPane();
            }
        });

        pane.getChildren().addAll(nomPays, capitalePays, remove, languePays, villeInPays);
        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 16px;");

        return pane;
    }
    
    public Pane addCompagnieInPane(){
        Pane pane = new Pane();
        CompagniesDAO comp = new CompagniesDAO();
        
        Label nomComp = new Label("Nom Compagnie");
        nomComp.setLayoutX(50);
        nomComp.setLayoutY(50);

        TextField nomC = new TextField();
        nomC.setPromptText("Nom de la compagnie . . .");
        nomC.setLayoutX(200);
        nomC.setLayoutY(50);

        Label typeComp = new Label("Type");
        typeComp.setLayoutX(50);
        typeComp.setLayoutY(100);

        TextField typeC = new TextField();
        typeC.setPromptText("Type de la compagnie . . .");
        typeC.setLayoutX(200);
        typeC.setLayoutY(100);
        
        Button add = new Button("Ajouter");
        add.setLayoutX(200);
        add.setLayoutY(150);
        add.getStyleClass().add("btn-success");
        
        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(20);
        
        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Compagnies C = new Compagnies();
                C.setNom_comp(nomC.getText());
                C.setType(typeC.getText());
                
                if(comp.insertCompagnies(C)){                
                    info.setText("Ajout Efféctué!");
                    info.getStyleClass().add("label-success");
                }
                else{
                    info.setText("Existe Deja!");
                    info.getStyleClass().add("label-warning!");
                }
                    
            }
        });
        
        pane.getChildren().addAll(nomComp, nomC, typeComp, typeC, add, info);
        pane.setStyle("-fx-font-size: 16px;");
        return pane;
    }

    public Pane addPaysInPane() {
        PaysDAO pays = new PaysDAO();
        Pane pane = new Pane();

        Label nomPays = new Label("Nom Pays");
        nomPays.setLayoutX(50);
        nomPays.setLayoutY(50);

        TextField nomP = new TextField();
        nomP.setPromptText("Nom du pays . . .");
        nomP.setLayoutX(200);
        nomP.setLayoutY(50);

        Label capitalePays = new Label("Capitale");
        capitalePays.setLayoutX(50);
        capitalePays.setLayoutY(100);

        TextField capitaleP = new TextField();
        capitaleP.setPromptText("Capitale du pays . . .");
        capitaleP.setLayoutX(200);
        capitaleP.setLayoutY(100);

        Label monnaiePays = new Label("Monnaie");
        monnaiePays.setLayoutX(50);
        monnaiePays.setLayoutY(150);

        TextField monnaieP = new TextField();
        monnaieP.setPromptText("Monnaie du pays . . .");
        monnaieP.setLayoutX(200);
        monnaieP.setLayoutY(150);

        Label languePays = new Label("Langue Officielle");
        languePays.setLayoutX(50);
        languePays.setLayoutY(200);

        TextField langueP = new TextField();
        langueP.setPromptText("Langue Officielle du pays . . .");
        langueP.setLayoutX(200);
        langueP.setLayoutY(200);

        Button add = new Button("Ajouter");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(200);
        add.setLayoutY(250);

        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Pays P = new Pays();

                P.setNomPays(nomP.getText());
                P.setCapital(capitaleP.getText());
                P.setLangue(langueP.getText());
                P.setMonnaie(monnaieP.getText());

                System.out.println(P.toString());

                if (pays.insertPays(P)) {
                    info.setText("Ajout Efféctué!");
                    info.getStyleClass().add("label-success");
                    //Ajout Media
                    saveAll(1);
                } else {
                    info.setText("Pays Existe Déja!");
                    info.getStyleClass().add("label-warning");
                }
            }
        });

        pane.getChildren().addAll(nomPays, nomP, capitaleP, capitalePays, monnaieP, monnaiePays, langueP, languePays, add, info, PaneImagePays);

        pane.setStyle("-fx-font-size: 18px;");

        // Media
        PaneImagePays.setVisible(true);
        PaneImagePays.setLayoutX(add.getLayoutX());
        PaneImagePays.setLayoutY(add.getLayoutY() + add.getPrefWidth() + 40);

        return pane;
    }

    public Pane EditPaysInPane() {
        PaysDAO pays = new PaysDAO();
        Pane pane = new Pane();

        Label nomPays = new Label("Nom Pays");
        nomPays.setLayoutX(50);
        nomPays.setLayoutY(50);

        TextField nomP = new TextField();
        nomP.setPromptText("Nom du pays . . .");
        nomP.setLayoutX(200);
        nomP.setLayoutY(50);
        //nomP.setOnKeyPressed(this::getInfo);

        Label capitalePays = new Label("Capitale");
        capitalePays.setLayoutX(50);
        capitalePays.setLayoutY(100);

        TextField capitaleP = new TextField();
        capitaleP.setPromptText("Capitale du pays . . .");
        capitaleP.setLayoutX(200);
        capitaleP.setLayoutY(100);

        Label monnaiePays = new Label("Monnaie");
        monnaiePays.setLayoutX(50);
        monnaiePays.setLayoutY(150);

        TextField monnaieP = new TextField();
        monnaieP.setPromptText("Monnaie du pays . . .");
        monnaieP.setLayoutX(200);
        monnaieP.setLayoutY(150);

        Label languePays = new Label("Langue Officielle");
        languePays.setLayoutX(50);
        languePays.setLayoutY(200);

        TextField langueP = new TextField();
        langueP.setPromptText("Langue Officielle du pays . . .");
        langueP.setLayoutX(200);
        langueP.setLayoutY(200);

        Label nouveauNom = new Label("Nouveau Nom");
        nouveauNom.setLayoutX(50);
        nouveauNom.setLayoutY(250);

        TextField nouveauP = new TextField();
        nouveauP.setPromptText("Nouveau Nom . . .");
        nouveauP.setLayoutX(200);
        nouveauP.setLayoutY(250);

        Button edit = new Button("Modifier");
        edit.getStyleClass().add("btn-success");
        edit.setLayoutX(200);
        edit.setLayoutY(300);

        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(20);

        edit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Pays P = new Pays();

                P.setNomPays(nouveauP.getText());
                P.setCapital(capitaleP.getText());
                P.setLangue(langueP.getText());
                P.setMonnaie(monnaieP.getText());
                P.setIdPays(pays.findPaysByName(nomP.getText()).getIdPays());

                System.out.println(P.toString());

                if (pays.updatePays(P)) {
                    info.setText("Modification Efféctué!");
                    info.getStyleClass().add("label-success");
                }

            }
        });

        pane.getChildren().addAll(nomPays, nomP, capitaleP, capitalePays, monnaieP, monnaiePays, langueP, nouveauP, nouveauNom, languePays, edit, info);
        pane.getChildren().get(1).setOnKeyReleased(this::getInfoPays);
        pane.setStyle("-fx-font-size: 18px;");

        return pane;
    }
    
    public Pane getVillesInPane(Ville V) {
        VilleDAO ville = new VilleDAO();
        Pane pane = new Pane();

        Label nomVille = new Label("Nom : " + V.getNomVille());
        nomVille.setLayoutX(5);
        nomVille.setLayoutY(5);

        Label nomPays = new Label("Pays : " + V.getNomPays());
        nomPays.setLayoutX(200);
        nomPays.setLayoutY(5);

        Button remove = new Button("Supprimer");
        remove.setLayoutX(600);
        remove.setLayoutY(5);
        remove.getStyleClass().add("btn-danger");

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ville.deleteVille(V.getIdVille());
                setVillesPane();
            }
        });

        pane.getChildren().addAll(nomVille, nomPays, remove);
        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 18px;");

        return pane;
    }

    public Pane addVilleInPane() {
        VilleDAO ville = new VilleDAO();
        PaysDAO pays = new PaysDAO();

        ObservableList<String> comboPays = FXCollections.observableArrayList();
        ArrayList<Pays> listePays = pays.displayAllPays();

        for (int i = 0; i < listePays.size(); i++) {
            comboPays.add(listePays.get(i).getNomPays());
        }

        Pane pane = new Pane();

        Label nomVille = new Label("Nom Ville");
        nomVille.setLayoutX(50);
        nomVille.setLayoutY(50);

        TextField nomV = new TextField();
        nomV.setPromptText("Nom de la Ville . . .");
        nomV.setLayoutX(200);
        nomV.setLayoutY(50);

        Label nomPays = new Label("Nom Pays");
        nomPays.setLayoutX(50);
        nomPays.setLayoutY(100);

        ComboBox paysComb = new ComboBox(comboPays);
        paysComb.setLayoutX(200);
        paysComb.setLayoutY(100);

        Button add = new Button("Ajouter");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(200);
        add.setLayoutY(150);

        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Ville V = new Ville();

                V.setNomVille(nomV.getText());
                V.setNomPays(paysComb.getValue().toString());

                if (ville.insertVille(V)) {
                    info.setText("Ajout Efféctué!");
                    info.getStyleClass().add("label-success");
                    //Ajout Media
                    saveAll(2);
                } else {
                    info.setText("Ville Existe Déja!");
                    info.getStyleClass().add("label-warning");
                }
            }
        });

        pane.getChildren().addAll(nomV, paysComb, add, info, nomPays, nomVille, PaneImageVille);

        pane.setStyle("-fx-font-size: 18px;");

        // Media
        PaneImageVille.setVisible(true);
        PaneImageVille.setLayoutX(add.getLayoutX());
        PaneImageVille.setLayoutY(add.getLayoutY() + add.getPrefWidth() + 40);
        return pane;
    }

    public Pane EditVilleInPane() {
        VilleDAO ville = new VilleDAO();
        PaysDAO pays = new PaysDAO();

        ObservableList<String> comboPays = FXCollections.observableArrayList();
        ObservableList<String> comboVilles = FXCollections.observableArrayList();
        ArrayList<Pays> listePays = pays.displayAllPays();
        ArrayList<Ville> listeVilles = ville.displayAllVilles();

        for (int i = 0; i < listePays.size(); i++) {
            comboPays.add(listePays.get(i).getNomPays());
        }

        for (int i = 0; i < listeVilles.size(); i++) {
            comboVilles.add(listeVilles.get(i).getNomVille());
        }

        Pane pane = new Pane();

        Label ancienNom = new Label("Ancien Nom Ville");
        ancienNom.setLayoutX(50);
        ancienNom.setLayoutY(50);

        ComboBox villesComb = new ComboBox(comboVilles);
        villesComb.setLayoutX(250);
        villesComb.setLayoutY(50);

        Label nomVille = new Label("Nouveau Nom Ville");
        nomVille.setLayoutX(50);
        nomVille.setLayoutY(100);

        TextField nomV = new TextField();
        nomV.setPromptText("Nom de la Ville . . .");
        nomV.setLayoutX(250);
        nomV.setLayoutY(100);

        Label nomPays = new Label("Nom Pays");
        nomPays.setLayoutX(50);
        nomPays.setLayoutY(150);

        ComboBox paysComb = new ComboBox(comboPays);
        paysComb.setLayoutX(250);
        paysComb.setLayoutY(150);

        Button add = new Button("Ajouter");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(250);
        add.setLayoutY(200);

        Label info = new Label();
        info.setLayoutX(250);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Ville V = new Ville();

                V.setNomVille(nomV.getText());
                V.setNomPays(paysComb.getValue().toString());
                V.setIdVille(ville.findVilleByName(villesComb.getValue().toString()).getIdVille());

                if (ville.updateVille(V)) {
                    info.setText("Modification Efféctué!");
                    info.getStyleClass().add("label-success");
                }
            }
        });

        pane.getChildren().addAll(nomV, paysComb, villesComb, add, info, nomPays, nomVille, ancienNom);

        pane.setStyle("-fx-font-size: 18px;");
        return pane;
    }
    
    public Pane getHotelsInPane(Hotel H) {
        HotelDAO hotel = new HotelDAO();
        VilleDAO ville = new VilleDAO();
        
        Pane pane = new Pane();

        Label nomHotel = new Label("Nom : " + H.getNomHotel());
        nomHotel.setLayoutX(5);
        nomHotel.setLayoutY(5);

        Label catHotel = new Label("Catégorie : " + H.getCategorie());
        catHotel.setLayoutX(250);
        catHotel.setLayoutY(5);
        
        Label villeHotel= new Label("Ville : " + ville.findVilleById(H.getId_ville()).getNomVille());
        villeHotel.setLayoutX(450);
        villeHotel.setLayoutY(5);
        
        Label descHotel = new Label("Description : " + H.getDescription());
        descHotel.setLayoutX(5);
        descHotel.setLayoutY(30);

        Button remove = new Button("Supprimer");
        remove.setLayoutX(600);
        remove.setLayoutY(5);
        remove.getStyleClass().add("btn-danger");

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                hotel.deleteHotel(H.getIdHotel());
                setHotelsPane();
            }
        });

        pane.getChildren().addAll(nomHotel, catHotel, villeHotel, descHotel, remove);
        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 18px;");

        return pane;
    }
    
    public Pane getRestaurantsInPane(Restaurant R) {
        RestaurantDAO rest = new RestaurantDAO();
        VilleDAO ville = new VilleDAO();
        
        Pane pane = new Pane();

        Label nomHotel = new Label("Nom : " + R.getNomRestaurant());
        nomHotel.setLayoutX(5);
        nomHotel.setLayoutY(5);

        Label catHotel = new Label("Catégorie : " + R.getCategorie());
        catHotel.setLayoutX(250);
        catHotel.setLayoutY(5);
        
        Label villeHotel= new Label("Ville : " + ville.findVilleById(R.getId_ville()).getNomVille());
        villeHotel.setLayoutX(450);
        villeHotel.setLayoutY(5);
        
        Label descHotel = new Label("Description : " + R.getDescription());
        descHotel.setLayoutX(5);
        descHotel.setLayoutY(30);

        Button remove = new Button("Supprimer");
        remove.setLayoutX(600);
        remove.setLayoutY(5);
        remove.getStyleClass().add("btn-danger");

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                rest.deleteRestaurant(R.getIdRestaurant());
                setRestaurantsPane();
            }
        });

        pane.getChildren().addAll(nomHotel, catHotel, villeHotel, descHotel, remove);
        pane.setStyle("-fx-border-style: dotted; -fx-border-radius: 5px; -fx-padding: 5px; -fx-font-size: 18px;");

        return pane;
    }

    public Pane addHotelInPane() {
        HotelDAO hotel = new HotelDAO();
        VilleDAO ville = new VilleDAO();

        ObservableList<String> comboVille = FXCollections.observableArrayList();
        ArrayList<Ville> listeVille = ville.displayAllVilles();

        for (int i = 0; i < listeVille.size(); i++) {
            comboVille.add(listeVille.get(i).getNomVille());
        }

        Pane pane = new Pane();

        Label nomVille = new Label("Nom Ville");
        nomVille.setLayoutX(50);
        nomVille.setLayoutY(50);
        
        ComboBox villeComb = new ComboBox(comboVille);
        villeComb.setLayoutX(250);
        villeComb.setLayoutY(50);
        
        Label nomHotel = new Label("Nom Hotel");
        nomHotel.setLayoutX(50);
        nomHotel.setLayoutY(100);

        TextField nomH = new TextField();
        nomH.setPromptText("Nom de l'hotel . . .");
        nomH.setLayoutX(250);
        nomH.setLayoutY(100);

        Label catHotel = new Label("Catégorie de l'Hotel");
        catHotel.setLayoutX(50);
        catHotel.setLayoutY(150);

        TextField catH = new TextField();
        catH.setPromptText("Catégorie de l'hotel . . .");
        catH.setLayoutX(250);
        catH.setLayoutY(150);
        
        Label descHotel = new Label("Description");
        descHotel.setLayoutX(50);
        descHotel.setLayoutY(200);

        TextArea descH = new TextArea();
        descH.setPromptText("Description . . .");
        descH.setLayoutX(250);
        descH.setLayoutY(200);
        descH.setPrefRowCount(2);
        descH.setPrefColumnCount(15);
        

        Button add = new Button("Ajouter");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(250);
        add.setLayoutY(280);

        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Hotel H = new Hotel();
                
                H.setNomHotel(nomH.getText());
                H.setCategorie(catH.getText());
                H.setDescription(descH.getText());

                if (hotel.insertHotel(H, villeComb.getValue().toString())) {
                    info.setText("Ajout Efféctué!");
                    info.getStyleClass().add("label-success");
                    //Ajout Media
                    //saveAll(2);
                } else {
                    info.setText("Hotel Existe Déja!");
                    info.getStyleClass().add("label-warning");
                }
            }
        });

        pane.getChildren().addAll(nomH,descH, nomHotel, descHotel,catH, catHotel, add, info, nomVille,villeComb, PaneImageVille);

        pane.setStyle("-fx-font-size: 18px;");

        // Media
        PaneImageVille.setVisible(true);
        PaneImageVille.setLayoutX(add.getLayoutX());
        PaneImageVille.setLayoutY(add.getLayoutY() + add.getPrefWidth() + 40);
        return pane;
    }
    
    public Pane addRestaurantInPane(){
        RestaurantDAO rest = new RestaurantDAO();
        VilleDAO ville = new VilleDAO();

        ObservableList<String> comboVille = FXCollections.observableArrayList();
        ArrayList<Ville> listeVille = ville.displayAllVilles();

        for (int i = 0; i < listeVille.size(); i++) {
            comboVille.add(listeVille.get(i).getNomVille());
        }

        Pane pane = new Pane();

        Label nomVille = new Label("Nom Ville");
        nomVille.setLayoutX(50);
        nomVille.setLayoutY(50);
        
        ComboBox villeComb = new ComboBox(comboVille);
        villeComb.setLayoutX(250);
        villeComb.setLayoutY(50);
        
        Label nomRest = new Label("Nom Restaurant");
        nomRest.setLayoutX(50);
        nomRest.setLayoutY(100);

        TextField nomR = new TextField();
        nomR.setPromptText("Nom du restaurant . . .");
        nomR.setLayoutX(250);
        nomR.setLayoutY(100);

        Label catRest = new Label("Catégorie du Restaurant");
        catRest.setLayoutX(50);
        catRest.setLayoutY(150);

        TextField catR = new TextField();
        catR.setPromptText("Catégories du Restaurant . . .");
        catR.setLayoutX(250);
        catR.setLayoutY(150);
        
        Label descRest = new Label("Description");
        descRest.setLayoutX(50);
        descRest.setLayoutY(200);

        TextArea descR = new TextArea();
        descR.setPromptText("Description . . .");
        descR.setLayoutX(250);
        descR.setLayoutY(200);
        descR.setPrefRowCount(2);
        descR.setPrefColumnCount(15);
        

        Button add = new Button("Ajouter");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(250);
        add.setLayoutY(280);

        Label info = new Label();
        info.setLayoutX(200);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Restaurant R = new Restaurant();
                
                R.setNomRestaurant(nomR.getText());
                R.setCategorie(catR.getText());
                R.setDescription(descR.getText());

                if (rest.insertRestaurant(R, villeComb.getValue().toString())) {
                    info.setText("Ajout Efféctué!");
                    info.getStyleClass().add("label-success");
                    //Ajout Media
                    //saveAll(2);
                } else {
                    info.setText("Restaurant Existe Déja!");
                    info.getStyleClass().add("label-warning");
                }
            }
        });

        pane.getChildren().addAll(nomR,descR, nomRest, descRest,catR, catRest, add, info, nomVille,villeComb, PaneImageVille);

        pane.setStyle("-fx-font-size: 18px;");

        // Media
        PaneImageVille.setVisible(true);
        PaneImageVille.setLayoutX(add.getLayoutX());
        PaneImageVille.setLayoutY(add.getLayoutY() + add.getPrefWidth() + 40);
        return pane;
    }
   
    public Pane EditHotelInPane() {
        HotelDAO hotel = new HotelDAO();
        VilleDAO ville = new VilleDAO();

        ObservableList<String> comboHotels = FXCollections.observableArrayList();

        ArrayList<Hotel> listeHotels = hotel.displayAllHotels();

        for (int i = 0; i < listeHotels.size(); i++) {
            comboHotels.add(listeHotels.get(i).getNomHotel());
        }

        Pane pane = new Pane();

        Label ancienNom = new Label("Ancien Nom Hotel");
        ancienNom.setLayoutX(50);
        ancienNom.setLayoutY(50);

        ComboBox hotelComb = new ComboBox(comboHotels);
        hotelComb.setLayoutX(250);
        hotelComb.setLayoutY(50);

        Label nomHotel = new Label("Nouveau Nom Hotel");
        nomHotel.setLayoutX(50);
        nomHotel.setLayoutY(100);

        TextField nomH = new TextField();
        nomH.setPromptText("Nom de l'hotel . . .");
        nomH.setLayoutX(250);
        nomH.setLayoutY(100);

        
        Label catHotel = new Label("Categorie Hotel");
        catHotel.setLayoutX(50);
        catHotel.setLayoutY(150);

        TextField catH = new TextField();
        catH.setPromptText("Catégorie de l'hotel . . .");
        catH.setLayoutX(250);
        catH.setLayoutY(150);
        
        Label descHotel = new Label("Description");
        descHotel.setLayoutX(50);
        descHotel.setLayoutY(200);

        TextArea descH = new TextArea();
        descH.setPromptText("Description . . .");
        descH.setLayoutX(250);
        descH.setLayoutY(200);
        descH.setPrefRowCount(2);
        descH.setPrefColumnCount(15);

        Button add = new Button("Modifier");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(250);
        add.setLayoutY(250);

        Label info = new Label();
        info.setLayoutX(250);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Hotel H = new Hotel();
                
                H.setNomHotel(nomH.getText());
                H.setCategorie(catH.getText());
                H.setDescription(descH.getText());
                H.setIdHotel(hotel.getHotelIdByName(hotelComb.getValue().toString()).getIdHotel());
                
                if(hotel.updateHotel(H)){
                    info.setText("Modification Efféctué!");
                    info.getStyleClass().add("label-success");
                }
                else{
                    info.setText("Modification NonEffectuée!");
                    info.getStyleClass().add("label-warning");
                }
            }
        });

        pane.getChildren().addAll(nomH, nomHotel,catH,catHotel, ancienNom, hotelComb, info, add, descH, descHotel);

        pane.setStyle("-fx-font-size: 18px;");
        return pane;
    } // WORKING
    
    public Pane EditRestaurantInPane() {
        VilleDAO ville = new VilleDAO();
        PaysDAO pays = new PaysDAO();

        ObservableList<String> comboPays = FXCollections.observableArrayList();
        ObservableList<String> comboVilles = FXCollections.observableArrayList();
        ArrayList<Pays> listePays = pays.displayAllPays();
        ArrayList<Ville> listeVilles = ville.displayAllVilles();

        for (int i = 0; i < listePays.size(); i++) {
            comboPays.add(listePays.get(i).getNomPays());
        }

        for (int i = 0; i < listeVilles.size(); i++) {
            comboVilles.add(listeVilles.get(i).getNomVille());
        }

        Pane pane = new Pane();

        Label ancienNom = new Label("Ancien Nom Ville");
        ancienNom.setLayoutX(50);
        ancienNom.setLayoutY(50);

        ComboBox villesComb = new ComboBox(comboVilles);
        villesComb.setLayoutX(250);
        villesComb.setLayoutY(50);

        Label nomVille = new Label("Nouveau Nom Ville");
        nomVille.setLayoutX(50);
        nomVille.setLayoutY(100);

        TextField nomV = new TextField();
        nomV.setPromptText("Nom de la Ville . . .");
        nomV.setLayoutX(250);
        nomV.setLayoutY(100);

        Label nomPays = new Label("Nom Pays");
        nomPays.setLayoutX(50);
        nomPays.setLayoutY(150);

        ComboBox paysComb = new ComboBox(comboPays);
        paysComb.setLayoutX(250);
        paysComb.setLayoutY(150);

        Button add = new Button("Ajouter");
        add.getStyleClass().add("btn-success");
        add.setLayoutX(250);
        add.setLayoutY(200);

        Label info = new Label();
        info.setLayoutX(250);
        info.setLayoutY(20);

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Ville V = new Ville();

                V.setNomVille(nomV.getText());
                V.setNomPays(paysComb.getValue().toString());
                V.setIdVille(ville.findVilleByName(villesComb.getValue().toString()).getIdVille());

                if (ville.updateVille(V)) {
                    info.setText("Modification Efféctué!");
                    info.getStyleClass().add("label-success");
                }
            }
        });

        pane.getChildren().addAll(nomV, paysComb, villesComb, add, info, nomPays, nomVille, ancienNom);

        pane.setStyle("-fx-font-size: 18px;");
        return pane;
    } // NOT WORKING

    public void getInfoPays(KeyEvent e) {
        PaysDAO pays = new PaysDAO();
        // Pane x = EditPaysInPane();
        //TextField a = (TextField)x.getChildren().get(1);
        //TextField b = (TextField)EditPaysInPane().getChildren().get(2);
        Pane P = (Pane) showPane.getItems().get(0);
        TextField nomP = (TextField) P.getChildren().get(1);
        TextField capitaleP = (TextField) P.getChildren().get(2);
        TextField monnaieP = (TextField) P.getChildren().get(4);
        TextField langueP = (TextField) P.getChildren().get(6);
        TextField nouveauP = (TextField) P.getChildren().get(7);
        if (nomP.getText() != "") {
            langueP.setText(pays.findPaysByName(nomP.getText()).getLangue());
            capitaleP.setText(pays.findPaysByName(nomP.getText()).getCapital());
            monnaieP.setText(pays.findPaysByName(nomP.getText()).getMonnaie());
            nouveauP.setText(pays.findPaysByName(nomP.getText()).getNomPays());
        }
    }

    public void viewMessages(ActionEvent e) {
        if ((MenuItem) e.getSource() == consulterMsgL) {
            setMessagesPane(1);
        } else if ((MenuItem) e.getSource() == consulterMsgNL) {
            setMessagesPane(0);
        }

    }

    public void viewUsers(ActionEvent e) {
        if ((MenuItem) e.getSource() == newUsers) {
            setUsersPane(0);
        } else if ((MenuItem) e.getSource() == allUsers) {
            setUsersPane(1);
        }
    }

    public void viewVilles(ActionEvent e) {
        if ((MenuItem) e.getSource() == listVilles) {
            setVillesPane();
        }
        if ((MenuItem) e.getSource() == addVille) {
            setAddVillePane();
        }
        if ((MenuItem) e.getSource() == editVille) {
            setEditVillePane();
        }
    }

    public void viewPays(ActionEvent e) {
        if ((MenuItem) e.getSource() == listPays) {
            setPaysPane();
        }
        if ((MenuItem) e.getSource() == addPays) {
            setAddPaysPane();
        }
        if ((MenuItem) e.getSource() == editPays) {
            setEditPaysPane();
        }
    }

    public void viewStats(ActionEvent e){
        if ((MenuItem) e.getSource() == showStats) {
            setStatsPane();
        }
    }
    
    public void viewHotels(ActionEvent e){
        if ((MenuItem) e.getSource() == listHotels) {
            setHotelsPane();
        }
        if ((MenuItem) e.getSource() == addHotel) {
            setAddHotelPane();
        }
        if ((MenuItem) e.getSource() == editHotel) {
            setEditHotelPane();
        }
    }
    
    public void viewRestaurants(ActionEvent e){
        if ((MenuItem) e.getSource() == listRestaurants) {
            setRestaurantsPane();
        }
        if ((MenuItem) e.getSource() == addRestaurant) {
            setAddRestaurantPane();
        }
        if ((MenuItem) e.getSource() == editRestaurant) {
            setEditRestaurantPane();
        }
    }
    
    public void viewCompagnies(ActionEvent e){
        if ((MenuItem) e.getSource() == listCompagnies) {
            setCompagniesPane();
        }
        if ((MenuItem) e.getSource() == addCompagnie) {
            setAddCompagniePane();
        }
    }
    /**
     * ************ Media ******************
     */
    // FXML Actions
    @FXML
    private void ImageHandler(MouseEvent event) {
        if ((Button) event.getSource() == btn_plusPays1) {
            String str1 = addImage(1, 1);
            if (!str1.equals("")) {
                InputStream inputStream1 = null;
                try {

                    inputStream1 = new FileInputStream(str1);
                    Image image1 = new Image(inputStream1);
                    btn_plusPays1.setVisible(false);
                    imgViewPays1.setImage(image1);
                    imgViewPays1.setVisible(true);
                    btnmPays1.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream1.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if ((Button) event.getSource() == btn_plusPays2) {
            String str2 = addImage(2, 1);
            if (!str2.equals("")) {

                InputStream inputStream2 = null;
                try {

                    inputStream2 = new FileInputStream(str2);
                    Image image2 = new Image(inputStream2);
                    btn_plusPays2.setVisible(false);
                    imgViewPays2.setImage(image2);
                    imgViewPays2.setVisible(true);
                    btnmPays2.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream2.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if ((Button) event.getSource() == btn_plusPays3) {
            String str3 = addImage(3, 1);
            if (!str3.equals("")) {

                InputStream inputStream3 = null;
                try {

                    inputStream3 = new FileInputStream(str3);
                    Image image3 = new Image(inputStream3);
                    btn_plusPays3.setVisible(false);
                    imgViewPays3.setImage(image3);
                    imgViewPays3.setVisible(true);
                    btnmPays3.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream3.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        // Ville

        if ((Button) event.getSource() == btn_plusVille1) {
            String str1 = addImage(1, 2);
            if (!str1.equals("")) {
                InputStream inputStream1 = null;
                try {

                    inputStream1 = new FileInputStream(str1);
                    Image image1 = new Image(inputStream1);
                    btn_plusVille1.setVisible(false);
                    imgViewVille1.setImage(image1);
                    imgViewVille1.setVisible(true);
                    btnmVille1.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream1.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if ((Button) event.getSource() == btn_plusVille2) {
            String str2 = addImage(2, 2);
            if (!str2.equals("")) {

                InputStream inputStream2 = null;
                try {

                    inputStream2 = new FileInputStream(str2);
                    Image image2 = new Image(inputStream2);
                    btn_plusVille2.setVisible(false);
                    imgViewVille2.setImage(image2);
                    imgViewVille2.setVisible(true);
                    btnmVille2.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream2.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if ((Button) event.getSource() == btn_plusVille3) {
            String str3 = addImage(3, 2);
            if (!str3.equals("")) {

                InputStream inputStream3 = null;
                try {

                    inputStream3 = new FileInputStream(str3);
                    Image image3 = new Image(inputStream3);
                    btn_plusVille3.setVisible(false);
                    imgViewVille3.setImage(image3);
                    imgViewVille3.setVisible(true);
                    btnmVille3.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream3.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    @FXML
    private void hoverImageViewEnter(MouseEvent event) {
        //Pays
        if (event.getSource() == btnmPays1) {
            if (imgViewPays1.getImage() != null) {
                btn_moinsPays1.setVisible(true);
                btn_moinsPays1.setMouseTransparent(true);
            }

        }
        if (event.getSource() == btnmPays2 && imgViewPays2.getImage() != null) {
            btn_moinsPays2.setVisible(true);
            btn_moinsPays2.setMouseTransparent(true);
        }

        if (event.getSource() == btnmPays3 && imgViewPays3.getImage() != null) {
            btn_moinsPays3.setVisible(true);
            btn_moinsPays3.setMouseTransparent(true);
        }

        //Ville
        if (event.getSource() == btnmVille1) {
            if (imgViewVille1.getImage() != null) {
                btn_moinsVille1.setVisible(true);
                btn_moinsVille1.setMouseTransparent(true);
            }

        }
        if (event.getSource() == btnmVille2 && imgViewVille2.getImage() != null) {
            btn_moinsVille2.setVisible(true);
            btn_moinsVille2.setMouseTransparent(true);
        }

        if (event.getSource() == btnmVille3 && imgViewVille3.getImage() != null) {
            btn_moinsVille3.setVisible(true);
            btn_moinsVille3.setMouseTransparent(true);
        }
    }

    @FXML
    private void hoverImageViewExit(MouseEvent event) {
        // Pays
        btn_moinsPays1.setVisible(false);
        btn_moinsPays2.setVisible(false);
        btn_moinsPays3.setVisible(false);

        // Ville
        btn_moinsVille1.setVisible(false);
        btn_moinsVille2.setVisible(false);
        btn_moinsVille3.setVisible(false);
    }

    @FXML
    private void DelImageHandler(ActionEvent event) {

        // Pays
        if ((Button) event.getSource() == btnmPays1) {
            DeleteImageHandler(1, 1);
        }

        if ((Button) event.getSource() == btnmPays2) {
            DeleteImageHandler(2, 1);
        }

        if ((Button) event.getSource() == btnmPays3) {
            DeleteImageHandler(3, 1);
        }

        // Ville
        if ((Button) event.getSource() == btnmVille1) {
            DeleteImageHandler(1, 2);
        }

        if ((Button) event.getSource() == btnmVille2) {
            DeleteImageHandler(2, 2);
        }

        if ((Button) event.getSource() == btnmVille3) {
            DeleteImageHandler(3, 2);
        }
    }

    private void saveAll(int type) {

        // Pays
        if (type == 1) {
            label_infoPays.setPrefWidth(250);
            if (imgViewPays1.getImage() == null && imgViewPays2.getImage() == null && imgViewPays3.getImage() == null) {
                label_infoPays.setText("Essayez d'insérer des images");

            } else {
                MediaDAO mediaDAO = new MediaDAO();
                for (int i = 0; i < listeMediaPays.size(); i++) {
                    if (listeMediaPays.get(i) != null) {
                        if (mediaDAO.ajouterMediaPays(listeMediaPays.get(i))) {
                            label_infoPays.setText("Ajout Local réussi");
                        } else {
                            label_infoPays.setText("Erreur Ajout Local");
                        }
                    }
                }
                if (Launcher.getInternetStatus() != 0) {
                    uploadAll((ArrayList< Media>) listeMediaPays);
                    label_infoPays.setText("Ajout FTP réussi");

                } else {
                    label_infoPays.setText("Vérifier votre connexion Internet");
                }

            }
        } // Ville
        else if (type == 2) {
            label_infoVille.setPrefWidth(250);
            if (imgViewVille1.getImage() == null && imgViewVille2.getImage() == null && imgViewVille3.getImage() == null) {
                label_infoVille.setText("Essayez d'insérer des images");

            } else {
                MediaDAO mediaDAO = new MediaDAO();
                for (int i = 0; i < listeMediaVille.size(); i++) {
                    if (listeMediaVille.get(i) != null) {
                        if (mediaDAO.ajouterMediaVille(listeMediaVille.get(i))) {
                            label_infoVille.setText("Ajout Local réussi");
                        } else {
                            label_infoVille.setText("Erreur Ajout Local");
                        }
                    }
                }
                if (Launcher.getInternetStatus() != 0) {
                    uploadAll((ArrayList< Media>) listeMediaVille);
                    label_infoVille.setText("Ajout FTP réussi");

                } else {
                    label_infoVille.setText("Vérifier votre connexion Internet");
                }

            }
        }
    }

    // Additional Methods
    // type: 1=pays  2=ville 3=hotel 4=restaurant
    private String addImage(int indice, int type) {
        String Copystr = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir image...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg"));
        File selectedFile = null;
        if (type == 1) {
            selectedFile = fileChooser.showOpenDialog(imgViewPays1.getScene().getWindow());
        } else if (type == 2) {
            selectedFile = fileChooser.showOpenDialog(imgViewVille1.getScene().getWindow());
        }
        double FileSize = (selectedFile.length() / 1024);
        if ((selectedFile.length() / 1024) > Media.MAX_IMAGE_SIZE) {
            label_infoPays.setText("Taille Volumineuse. Essayez Encore");
            label_infoPays.setPrefWidth(200);
            return Copystr;
        }
        if (selectedFile != null && (selectedFile.length() / 1024) < Media.MAX_IMAGE_SIZE) {
            label_infoPays.setText("");
            try {

                // if the directory does not exist, create it
                if (!new File(Media.CURRENT_DIR).exists()) {
                    System.out.println("creating directory: ");

                    boolean result = false;

                    try {
                        new File(Media.CURRENT_DIR).mkdir();
                        result = true;
                    } catch (SecurityException se) {
                        Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, se);
                    }
                    if (result) {
                        System.out.println("DIR created");
                    }
                }
                int ran = 0;
                if (type == 1) {
                    ran = listeRandPays.get(indice - 1);
                } else if (type == 2) {
                    ran = listeRandVille.get(indice - 1);
                }
                Copystr = Media.CURRENT_DIR + "/m" + ran + indice + ".jpg";
                copyFile(selectedFile, new File(Copystr));

                if (type == 1) {
                    listeMediaPays.set(indice - 1, new Media(Copystr, "/m" + ran + indice + ".jpg", FileSize));
                } else if (type == 2) {
                    listeMediaVille.set(indice - 1, new Media(Copystr, "/m" + ran + indice + ".jpg", FileSize));
                }

            } catch (IOException ex) {
                Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Copystr;
        }
        return Copystr;
    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            // previous code: destination.transferFrom(source, 0, source.size());
            // to avoid infinite loops, should be:
            long count = 0;
            long size = source.size();
            while ((count += destination.transferFrom(source, count, size
                    - count)) < size)
                ;
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    private Image setImageM(String chemin) {
        InputStream inputStream = null;
        Image image = null;
        try {

            inputStream = new FileInputStream(chemin);
            image = new Image(inputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AdministrationController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return image;
    }

    private void uploadAll(ArrayList<Media> liste) {

        Ftp ftp = new Ftp();
        ftp.uploadFile(liste);
    }

    // type: 1=pays  2=ville 3=hotel 4=restaurant
    private void DeleteImageHandler(int indice, int type) {
        // Pays  
        if (type == 1) {
            if (indice == 1) {
                listeMediaPays.set(indice - 1, null);
                String Delstr = Media.CURRENT_DIR + "/m" + listeRandPays.get(indice - 1) + indice + ".jpg";
                new File(Delstr).delete();
                imgViewPays1.setImage(null);
                btnmPays1.setVisible(false);
                btn_plusPays1.setVisible(true);
                label_infoPays.setText("");
            }
            if (indice == 2) {
                listeMediaPays.set(indice - 1, null);
                String Delstr = Media.CURRENT_DIR + "/m" + listeRandPays.get(indice - 1) + indice + ".jpg";
                new File(Delstr).delete();
                imgViewPays2.setImage(null);
                btnmPays2.setVisible(false);
                btn_plusPays2.setVisible(true);
                label_infoPays.setText("");
            }
            if (indice == 3) {

                listeMediaPays.set(indice - 1, null);
                String Delstr = Media.CURRENT_DIR + "/m" + listeRandPays.get(indice - 1) + indice + ".jpg";
                new File(Delstr).delete();
                imgViewPays3.setImage(null);
                btnmPays3.setVisible(false);
                btn_plusPays3.setVisible(true);
                label_infoPays.setText("");
            }
        } // Ville
        else if (type == 2) {
            if (indice == 1) {
                listeMediaVille.set(indice - 1, null);
                String Delstr = Media.CURRENT_DIR + "/m" + listeRandVille.get(indice - 1) + indice + ".jpg";
                new File(Delstr).delete();
                imgViewVille1.setImage(null);
                btnmVille1.setVisible(false);
                btn_plusVille1.setVisible(true);
                label_infoVille.setText("");
            }
            if (indice == 2) {
                listeMediaVille.set(indice - 1, null);
                String Delstr = Media.CURRENT_DIR + "/m" + listeRandVille.get(indice - 1) + indice + ".jpg";
                new File(Delstr).delete();
                imgViewVille2.setImage(null);
                btnmVille2.setVisible(false);
                btn_plusVille2.setVisible(true);
                label_infoVille.setText("");
            }
            if (indice == 3) {

                listeMediaVille.set(indice - 1, null);
                String Delstr = Media.CURRENT_DIR + "/m" + listeRandVille.get(indice - 1) + indice + ".jpg";
                new File(Delstr).delete();
                imgViewVille3.setImage(null);
                btnmVille3.setVisible(false);
                btn_plusVille3.setVisible(true);
                label_infoVille.setText("");
            }
        }
    }

    /**
     * ************ Media ******************
     */
}
