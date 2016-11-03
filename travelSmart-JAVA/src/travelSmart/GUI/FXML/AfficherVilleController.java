/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.GUI.FXML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
import travelSmart.CLASSES.entities.Commentaire;
import travelSmart.CLASSES.entities.Media;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.entities.Ville;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.CommentaireDAO;
import travelSmart.DAO.entities.MediaDAO;
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
public class AfficherVilleController implements Initializable, InterfaceScreenController {
    StackEcrans StackEcrans;
    @FXML
    private Label nomVille;
    @FXML
    private Label nomPays;
    @FXML
    private Label monnaie;
    @FXML
    private Label langue;
    
    private StackEcrans stackEcrans;
    
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
    
    /*******  Commentaire **********/
    @FXML
    private ListView comment_list;
     @FXML
    private HBox pane_imgV;
    
    /*******  Commentaire **********/
    private int idVille;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roundCheckInternet();
        
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        initSession();

        idVille = 0;
        int idUser = 0;
        
        if(FileHandler.fileExists("idVille")){
            idVille=Integer.valueOf(FileHandler.getText("idVille"));
            //Affichage d'un pays
            VilleDAO vDAO = new VilleDAO();
            Ville ville = new Ville();

            ville = vDAO.findVilleById(idVille);

            PaysDAO pDAO = new PaysDAO();
            Pays pays = new Pays();

            pays = pDAO.findPaysByName(ville.getNomPays());

            nomVille.setText(ville.getNomVille());
            nomPays.setText(ville.getNomPays());
            monnaie.setText("Monnaie: " + pays.getMonnaie());
            langue.setText("Langue officielle: " + pays.getLangue());
        }
        /*******/
        if(FileHandler.fileExists("idUser")){
            idUser = Integer.valueOf(FileHandler.getText("idUser"));
            
            panes.add(loadUserComment(idUser, idVille));

            CommentaireDAO cDAO = new CommentaireDAO();

            List<Commentaire> listComments = new ArrayList<Commentaire>(cDAO.getAllCommentsByVille(idVille));
            for(int i=0; i< listComments.size(); i++){
                if (listComments.get(i).getIdUser() != idUser)
                    panes.add(loadComment(listComments.get(i)));
            }
            comment_list.setItems(panes);
        }
        else{
            CommentaireDAO cDAO = new CommentaireDAO();

            List<Commentaire> listComments = new ArrayList<Commentaire>(cDAO.getAllCommentsByVille(idVille));
            for(int i=0; i< listComments.size(); i++){
                if (listComments.get(i).getIdUser() != idUser)
                    panes.add(loadComment(listComments.get(i)));
            }
            comment_list.setItems(panes);
        }
        comment_list.setSelectionModel(null);
    }    
    
    public void showStats(ActionEvent e) throws Exception {
        stackEcrans.loadScreen("admin", "Administration.fxml");
        stackEcrans.setScreen("admin");
        
    }
    
    public void showHome(ActionEvent e) throws Exception{
        stackEcrans.loadScreen("listeVilles", "AfficherTousVilles.fxml");
        stackEcrans.setScreen("listeVilles");
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
                        //internetConnFlag();
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

    @Override
    public void setScreenPane(StackEcrans screenPage) {
       stackEcrans = screenPage;
    }
    
    
    /************** Commentaire   *************/
    private void setProfileRound(Image img, ImageView imageView){
        
       
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
            );
            clip.setArcWidth(200);
            clip.setArcHeight(200);
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
          
    private void updateComment(MouseEvent event){
    
        //System.out.println("comment: " + comment_list.getText());
        Pane paneee = (Pane) comment_list.getItems().get(0);
        TextArea tt = (TextArea) paneee.getChildren().get(1);
        Label l_user = (Label) paneee.getChildren().get(4);
        Label l_emp = (Label) paneee.getChildren().get(5);
        Label l_comment = (Label) paneee.getChildren().get(6);
        //System.out.println("sz: " + Integer.parseInt(l_user.getText()));
        
        Commentaire comment = new Commentaire(Integer.parseInt(l_comment.getText()), tt.getText(), Integer.parseInt(l_user.getText()), 0, Integer.parseInt(l_emp.getText()), 0, 0);
        
       CommentaireDAO cDAO = new CommentaireDAO();
        if(!tt.getText().equals("")){
           cDAO.updateCommentaireVille(comment);
        }
        else
            System.out.println("Commentaire vide");
        
    }
    
    private void saveComment(MouseEvent event){
    
        //System.out.println("comment: " + comment_list.getText());
        Pane paneee = (Pane) comment_list.getItems().get(0);
        TextArea tt = (TextArea) paneee.getChildren().get(1);
        Label l_user = (Label) paneee.getChildren().get(3);
        Label l_emp = (Label) paneee.getChildren().get(4);
        //System.out.println("sz: " + Integer.parseInt(l_user.getText()));
        
        Commentaire comment = new Commentaire(0, tt.getText(), Integer.parseInt(l_user.getText()), 0, Integer.parseInt(l_emp.getText()), 0, 0);
        
       CommentaireDAO cDAO = new CommentaireDAO();
        if(!tt.getText().equals("")){
           cDAO.addCommentaireVille(comment);
        }
        else
            System.out.println("Commentaire vide");
        
    }
    
    private Pane loadUserComment(int idUser, int idVille){
       
        CommentaireDAO cDAO = new CommentaireDAO();
        Commentaire commentaire = new Commentaire();
        commentaire = cDAO.getCommentByUserByPays(idUser, idVille);
        
        User user = new User();
        UserDAO uDAO = new UserDAO();
        user = uDAO.getUserById(idUser);
        
        Pane pane = new Pane();
        
        ImageView imageView = null;
        Image img = null;
        if (user.getClass().getSimpleName().equals("Administrateur")) {
            img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_gold.jpg").toExternalForm());
            imageView = new ImageView(img);
        }
        //Image img = new Image("/travelSmart/RESSOURCES/images/user.png");
        
        if (user.getClass().getSimpleName().equals("Abonne")) {
            img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_silver.jpg").toExternalForm());
            imageView = new ImageView(img);
        }
        
        if (user.getClass().getSimpleName().equals("AbonnePrivilegie")) {
            img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_gold.jpg").toExternalForm());
            imageView = new ImageView(img);
        }
        
         // Pane Size
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setPrefWidth(422);
        pane.setPrefHeight(114);
        String style = "-fx-padding: 8 15 15 15;" +
"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
"    -fx-background-color: #1E8BC3;" +
"    -fx-border-color: #fff;" +
"    -fx-border: 3px;" +
"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
"    -fx-font-size: 1.1em;";
        pane.setStyle(style);
        pane.setId("pane_oneComment");
        
        // ImageView Proprieties
        imageView.setFitHeight(65);
        imageView.setFitWidth(65);
        
        imageView.setLayoutX(14);
        imageView.setLayoutY(7);
        setProfileRound(img, imageView);
        
        
        
        if (commentaire.getIdComment() != 0)
        {
        TextArea comment = new TextArea(commentaire.getCommentText());
        Label nom_user = new Label("posté par: " + user.getNom() + " " + user.getPrenom());
        Button btn = new Button("Publier");
        
        // TextArea Proprieties
        comment.setPrefSize(314, 61);
        comment.setLayoutX(93);
        comment.setLayoutY(7);
        comment.setId("comment_user");
        
        // Button Position
        btn.setLayoutX(329);
        btn.setLayoutY(78);
        btn.setId("btn_user");
        btn.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        btn.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        btn.setPrefSize(75, 30);
        String st_btn = "-fx-text-fill: #000;\n" +
"    -fx-background-color: #fff;\n" +
"    -fx-border-color: #bfbfbf;";
        btn.setStyle(st_btn);
       
        btn.setOnMouseClicked(this::updateComment);
        
        /***********************/
        Label l_user = new Label(""+idUser);
        l_user.setLayoutX(btn.getLayoutX()-100);
        l_user.setLayoutY(78);
       Label l_emplacement = new Label(""+idVille);
        l_emplacement.setLayoutX(btn.getLayoutX()-100);
        l_emplacement.setLayoutY(78);
        Label l_comment = new Label(""+commentaire.getIdComment());
        l_comment.setLayoutX(btn.getLayoutX()-100);
        l_comment.setLayoutY(78);
        
        l_user.setVisible(false);
        l_emplacement.setVisible(false);
        l_comment.setVisible(false);
        
        /**********************************/
        
        // Label name and posté par
        
        nom_user.setLayoutX(109);
        nom_user.setLayoutY(70);
        nom_user.setDisable(true);
        
        pane.getChildren().add(imageView);
        pane.getChildren().add(comment);
        pane.getChildren().add(btn);
        pane.getChildren().add(nom_user);
        
        pane.getChildren().addAll(l_user,l_emplacement,l_comment);
        
        return pane;
        }
        
        else{
        
        TextArea comment = new TextArea("");
        Button btn = new Button("Publier");
        
        
        // TextArea Proprieties
        comment.setPrefSize(314, 61);
        comment.setLayoutX(93);
        comment.setLayoutY(7);
        comment.setId("comment_user");
        
        // Button Position
        btn.setLayoutX(329);
        btn.setLayoutY(78);
        btn.setId("btn_user");
        btn.setMinSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        btn.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        btn.setPrefSize(75, 30);
        String st_btn = "-fx-text-fill: #000;\n" +
"    -fx-background-color: #fff;\n" +
"    -fx-border-color: #bfbfbf;";
        btn.setStyle(st_btn);
        
        btn.setOnMouseClicked(this::saveComment);
        /***********************/
        Label l_user = new Label(""+idUser);
        l_user.setLayoutX(btn.getLayoutX()-100);
        l_user.setLayoutY(78);
       Label l_emplacement = new Label(""+idVille);
        l_emplacement.setLayoutX(btn.getLayoutX()-100);
        l_emplacement.setLayoutY(78);
        
        l_user.setVisible(false);
        l_emplacement.setVisible(false);
        
        /**********************************/
        
        
        pane.getChildren().add(imageView);
        pane.getChildren().add(comment);
        pane.getChildren().add(btn);
        
        pane.getChildren().addAll(l_user,l_emplacement);
        
        return pane;
    }
    }
    
    private Pane loadComment(Commentaire C){
        
        User user = new User();
        UserDAO uDAO = new UserDAO();
        user = uDAO.getUserById(C.getIdUser());
        
        Pane pane = new Pane();
        //pane.getStyleClass().add("comment_pane");
        TextArea comment = new TextArea(C.getCommentText());
        Label nom_user = new Label("posté par: " + user.getNom() + " " + user.getPrenom());
       // Button btn = new Button("Publier");
        ImageView imageView = null;
        Image img = null;
        if (user.getClass().getSimpleName().equals("Administrateur")) {
            img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_gold.jpg").toExternalForm());
            imageView = new ImageView(img);
        }
        //Image img = new Image("/travelSmart/RESSOURCES/images/user.png");
        
        if (user.getClass().getSimpleName().equals("Abonne")) {
            img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_silver.jpg").toExternalForm());
            imageView = new ImageView(img);
        }
        
        if (user.getClass().getSimpleName().equals("AbonnePrivilegie")) {
            img = new Image(getClass().getResource("/travelSmart/RESSOURCES/images/user_gold.jpg").toExternalForm());
            imageView = new ImageView(img);
        }
        
        // Pane Size
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setPrefWidth(422);
        pane.setPrefHeight(114);
        String style = "-fx-padding: 8 15 15 15;" +
"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
"    -fx-background-color: #A2DED0;" +
"    -fx-border-color: #fff;" +
"    -fx-border: 3px;" +
"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
"    -fx-font-size: 1.1em;";
        pane.setStyle(style);
        
        // ImageView Proprieties
        imageView.setFitHeight(65);
        imageView.setFitWidth(65);
        
        imageView.setLayoutX(14);
        imageView.setLayoutY(7);
        imageView.setId("profile_pic");
        setProfileRound(img, imageView);
        
        // TextArea Proprieties
        comment.setPrefSize(314, 61);
        comment.setLayoutX(93);
        comment.setLayoutY(7);
        
        // Button Position
       // btn.setLayoutX(377);
       // btn.setLayoutY(88);
        
        // Label name and posté par
        
        nom_user.setLayoutX(109);
        nom_user.setLayoutY(70);
        nom_user.setDisable(true);
        
        
        pane.getChildren().add(imageView);
        pane.getChildren().add(comment);
       // pane.getChildren().add(btn);
        pane.getChildren().add(nom_user);
        pane.setDisable(true);
        return pane;
    }
    /************** Commentaire   *************/
    
    private void getAll(){
        MediaDAO mediaDAO = new MediaDAO();
        List<Media> list = new ArrayList<Media>(mediaDAO.getMediaByVille(idVille));
        ArrayList<ImageView> liste_V;
        
        if(list.size() > 0){
            liste_V= new ArrayList<ImageView>();
            
            for (int i = 0; i < list.size(); i++) {
                Image image = setImageM(list.get(i).getChemin());
                if (image != null){
                    ImageView img_v = new ImageView(image);
                    img_v.setFitHeight(120);
                    img_v.setFitWidth(120);
                    liste_V.add(img_v);
                }
                
            }
            
            pane_imgV.getChildren().addAll(liste_V);
        }
        
        
    }
    
    private Image setImageM(String chemin){
        InputStream inputStream = null;
        Image image = null;
                try {
                    
                    inputStream = new FileInputStream(chemin);
                    image = new Image(inputStream);
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(AfficherVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherVilleController.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
    }
        return image;
    }
}
