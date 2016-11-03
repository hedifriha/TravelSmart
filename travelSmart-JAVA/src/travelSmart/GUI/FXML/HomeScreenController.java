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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.MessagerieDAO;
import travelSmart.DAO.entities.NewsletterDAO;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.LIBS.FileHandler;
import travelSmart.LIBS.Ftp;
import travelSmart.LIBS.StackEcrans;
import travelSmart.PIDEV.Launcher;

/**
 * FXML Controller class
 *
 * @author reznov
 */
public class HomeScreenController implements Initializable, InterfaceScreenController {
    private StackEcrans stackEcrans;

    public String navLogin = "LoginForm.fxml";
    public String navSignUp = "SignUpForm.fxml";
    public String lostPwd = "UserLostPWD.fxml";

    public String navAbout = "aPropos.fxml";
    public String navContact = "emailContact.fxml";
    


    public Timer timer = new Timer();

    final static String FILE_PATH = "idUser";
    //public static String data;
    
    @FXML
    private ImageView sliderPart;
    
    @FXML
    private ToggleButton newsletterToggleBtn;
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
    
    
    
    @FXML
    private Button navAboutBtn;
    @FXML
    private Button navPaysBtn;
    @FXML
    private Button navVillesBtn;
    @FXML
    private Button navTop20Villes;
    @FXML
    private Button navTop20Hotels;
    @FXML
    private Button navTop20Restaurants;
    @FXML
    private Button navHotelsBtn;
    @FXML
    private Button navRestaurantsBtn;
    @FXML
    private Button navContactBtn;
            
            
            
            
    @FXML
    private Pane ProfilePane;

    @FXML
    private Label fName;
    @FXML
    private Label lName;
    @FXML
    private Label email;
    @FXML
    private Label numTel;
    @FXML
    private Label adresse;
    @FXML
    private Label typeC;
    @FXML
    private Label points;
    @FXML
    private ImageView profile_pic;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roundCheckInternet();
        // check connected & newsletter - SESSION LIKE
        initSession();
        
      Task taskDownloadMedia = new Task<String>() {

            @Override
            protected String call() throws Exception {
                Ftp.downloadAll();
                return "";
            }
        };
      new Thread(taskDownloadMedia).start();
    }
    
    public void test(){
        System.out.println("haha");
    }

    public void handleExitButtonAction(ActionEvent event) {
        timer.cancel();
        Platform.exit();

    }

    public void launchLoginForm(ActionEvent event) {
        try {
            login_btn.getScene().getWindow().hide();
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
    @FXML
    public void showAboutScreen(String screen) {
        try {
         StackEcrans container = new StackEcrans();

         container.loadScreen("aPropos", navAbout);
         container.loadScreen("contactForm", navContact);
         container.setScreen(screen);

         Scene scene = new Scene(container);
         Stage stage = new Stage(StageStyle.UNDECORATED);
         stage.setScene(scene);
         stage.setTitle("A Propos");
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

    public void showDashbord(ActionEvent e) throws Exception {
        stackEcrans.loadScreen("admin", "Administration.fxml");
        stackEcrans.setScreen("admin");
    }
    
    public void rotateLogo() {
        final Rotate rotationTransform = new Rotate(0, (sliderPart.getFitWidth() / 2) - 50, 0);
        sliderPart.getTransforms().add(rotationTransform);

        rotationTransform.setAxis(Rotate.Y_AXIS);

        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                )
                        )
                );

        rotationAnimation.play();
        //System.out.println(sliderPart.getId());
    }

    public void rotate(ActionEvent e) {
        final Rotate rotationTransform = new Rotate(0, newsletterToggleBtn.getWidth() / 2, 0);
        newsletterToggleBtn.getTransforms().add(rotationTransform);

        rotationTransform.setAxis(Rotate.Y_AXIS);

        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(0.5),
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                )
                        )
                );

        rotationAnimation.play();
        toggleSelection();

    }

    public void toggleSelection() {
        if (newsletterToggleBtn.isSelected()) {
            newsletterToggleBtn.setWrapText(true);
            newsletterToggleBtn.setTextAlignment(TextAlignment.CENTER);
            newsletterToggleBtn.setText("Se Désinscrire de la newsletter!");
            newsletterInsc(email.getText(), false);
        } else {
            newsletterToggleBtn.setWrapText(true);
            newsletterToggleBtn.setTextAlignment(TextAlignment.CENTER);
            newsletterToggleBtn.setText("S'inscrire à la newsletter!");
            newsletterInsc(email.getText(), true);
        }
    }

    public void newsletterInsc(String email, boolean flag) {
        NewsletterDAO news = new NewsletterDAO();
        if (flag) {
            news.desinscriptionNewsletter(email);
        } else {
            news.inscriptionNewsletter(email);
        }
    }

    public void initSession() {
        User user = new User();
        NewsletterDAO news = new NewsletterDAO();

        if (FileHandler.fileExists(FILE_PATH)) {
            setProfile(user, news);
        } else {
            setVisitor(news);
        }
    }

    public void setProfile(User user, NewsletterDAO news) {
        hboxNav.getChildren().remove(login_btn);
        UserDAO userDAO = new UserDAO();

        int idUser = Integer.valueOf(FileHandler.getText(FILE_PATH));
        user = userDAO.getUserById(idUser);

        userMenu.setText(user.getNom());
        if (user.getClass().getSimpleName().equals("Administrateur") == false) {
            hboxNav.getChildren().remove(dashbord_btn);
        }

        fName.setText(user.getPrenom());
        lName.setText(user.getNom());
        email.setText(user.getEmail());
        numTel.setText(user.getTel());
        points.setText(String.valueOf(user.getPoints()));
        if (user.getClass().getSimpleName().equals("Administrateur")) {
            typeC.setText("Administrateur");
            Image img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_gold.jpg").toExternalForm());
            profile_pic.setImage(img);
        }
        if (user.getClass().getSimpleName().equals("Abonne")) {
            typeC.setText("Abonné");
            Image img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_silver.jpg").toExternalForm());
            profile_pic.setImage(img);
        }
        if (user.getClass().getSimpleName().equals("AbonnePrivilegie")) {
            typeC.setText("Abonné Privilégié");
            Image img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_gold.jpg").toExternalForm());
            profile_pic.setImage(img);
        }

        adresse.setText(user.getAdresse());

        if (news.estInscrit(email.getText())) {
            newsletterToggleBtn.setSelected(true);
            newsletterToggleBtn.setText("Se Désinscrire de la newsletter!");
        } else {
            newsletterToggleBtn.setSelected(false);
            newsletterToggleBtn.setText("S'inscrire à la newsletter!");
        }
    }

    public void setVisitor(NewsletterDAO news) {
        hboxNav.getChildren().remove(userMenu);
        hboxNav.getChildren().remove(notif_btn);
        hboxNav.getChildren().remove(dashbord_btn);
        ProfilePane.getChildren().clear();
        ProfilePane.getStyleClass().remove("profile_pane");
        ProfilePane.getStyleClass().add("quickLinks_pane");

        /* NEWSLETTER */
        TextField emailNews = new TextField();
        emailNews.setLayoutX(12); //OK
        emailNews.setLayoutY(200); //OK
        emailNews.setId("fieldNews");
        emailNews.setPromptText("Saisir une @ email!");

        Button btnNews = new Button("Inscription Newsletter!");
        btnNews.setLayoutX(12); //OK
        btnNews.setLayoutY(230); //OK
        btnNews.getStyleClass().add("btn-success");
        btnNews.setId("btnNews");

        /* ABOUT & COTACT */
        Hyperlink btnAbout = new Hyperlink("A Propos de nous");
        btnAbout.setLayoutX(48);
        btnAbout.setLayoutY(25);

        Hyperlink btnContact = new Hyperlink("Contactez-nous!");
        btnContact.setLayoutX(48);
        btnContact.setLayoutY(55);

        /* INFO & CONTACT IMAGES */
        ImageView infoImg = new ImageView(new Image("/travelSmart/RESSOURCES/images/info.png"));
        infoImg.setLayoutX(12);
        infoImg.setLayoutY(18);
        infoImg.setFitWidth(40);
        infoImg.setPreserveRatio(true);

        ImageView emailImg = new ImageView(new Image("/travelSmart/RESSOURCES/images/email.png"));
        emailImg.setLayoutX(12);
        emailImg.setLayoutY(47);
        emailImg.setFitWidth(40);
        emailImg.setPreserveRatio(true);

        /* SOCIAL LINKS */
        Label followUs = new Label("Suivez nous!");
        followUs.setStyle("-fx-font-size: 14px;");
        followUs.setLayoutX((ProfilePane.getPrefWidth() / 2) - 60);
        followUs.setLayoutY(85);
        
        /* FB */
        
        /* TWITTER */
        

        ProfilePane.getChildren().addAll(emailNews, btnNews, btnAbout, btnContact, infoImg, emailImg, followUs);

        //CAST
        Hyperlink contact = (Hyperlink) ProfilePane.getChildren().get(3);
        Hyperlink about = (Hyperlink) ProfilePane.getChildren().get(2);
        Button btn = (Button) ProfilePane.getChildren().get(1);
        TextField text = (TextField) ProfilePane.getChildren().get(0);

        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (news.estInscrit(text.getText())) {
                    etatNewsletter.setText("Déja Inscrit!");
                    etatNewsletter.getStyleClass().removeAll("label-info", "label-danger");
                    etatNewsletter.getStyleClass().addAll("label-warning", "h3");
                } else if (text.getText().isEmpty() || text.getText().indexOf("@") == -1) {
                    etatNewsletter.setText("Veuillez saisir une adresse email valide!");
                    etatNewsletter.getStyleClass().removeAll("label-info", "label-warning");
                    etatNewsletter.getStyleClass().addAll("label-danger", "h3");
                } else {
                    newsletterInsc(text.getText(), false);
                    etatNewsletter.setText("Inscription à la newsletter éfféctuée avec succés!");
                    etatNewsletter.getStyleClass().removeAll("label-warning", "label-danger");
                    etatNewsletter.getStyleClass().addAll("label-info", "h3");
                }

            }
        });

        about.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                showAboutScreen("aPropos");
            }
        });

        contact.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                showAboutScreen("contactForm");
            }
        });
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
                        rotateLogo();
                    }
                });
            }
        }, 0, 5000);
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

    @Override
    public void setScreenPane(StackEcrans screenPage) {
        stackEcrans = screenPage;
    }
    
    public void setNavigation(ActionEvent e) throws Exception{
            
        if((Button)e.getSource() == navAboutBtn){
            showAboutScreen("aPropos");
        }
        if((Button)e.getSource() == navContactBtn){
            showAboutScreen("contactForm");
        }
        if((Button)e.getSource() == navPaysBtn){
            stackEcrans.loadScreen("listePays", "AfficherTousPays.fxml");
            stackEcrans.setScreen("listePays");
        }
        
        if((Button)e.getSource() == navVillesBtn){
            stackEcrans.loadScreen("listeVilles", "AfficherTousVilles.fxml");
            stackEcrans.setScreen("listeVilles");
        }
        
        if((Button)e.getSource() == navTop20Villes){
            stackEcrans.loadScreen("top20Villes", "Top20Villes.fxml");
            stackEcrans.setScreen("top20Villes");
        }
        
        if((Button)e.getSource() == navHotelsBtn || (Button)e.getSource() == navTop20Hotels){
            stackEcrans.loadScreen("listeHotels", "AfficherAllHotels.fxml");
            stackEcrans.setScreen("listeHotels");
        }
        
        if((Button)e.getSource() == navRestaurantsBtn || (Button)e.getSource() == navTop20Restaurants){
            stackEcrans.loadScreen("listeRestaurants", "AfficherAllRestaurants.fxml");
            stackEcrans.setScreen("listeRestaurants");
        }
    }

    

}
