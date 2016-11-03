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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import travelSmart.CLASSES.entities.Hotel;
import travelSmart.CLASSES.entities.Pays;
import travelSmart.CLASSES.entities.User;
import travelSmart.CLASSES.interfaces.InterfaceScreenController;
import travelSmart.DAO.entities.HotelDAO;
import travelSmart.DAO.entities.MessagerieDAO;
import travelSmart.DAO.entities.PaysDAO;
import travelSmart.DAO.entities.UserDAO;
import static travelSmart.GUI.FXML.AfficherRestaurantsController.FILE_PATH;
import travelSmart.LIBS.FileHandler;
import travelSmart.LIBS.StackEcrans;
import travelSmart.PIDEV.Launcher;

/**
 * FXML Controller class
 *
 * @author Hadhoud
 */
public class AfficherAllHotelsController implements Initializable, InterfaceScreenController {

    /**
     * Initializes the controller class.
     */
        private StackEcrans stackEcrans;

    @FXML
    ListView hotel_list;
    @FXML
    ListView hotel_list1;
    
    @FXML
    Button homeBtn;
    @FXML
    Button logoutBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HotelDAO hd = new HotelDAO();
               
        
      //  int idhotel = 2;
        ObservableList<Pane> panes = FXCollections.observableArrayList();
        ObservableList<Pane> panes1 = FXCollections.observableArrayList();

       // panes.add(loadOneHotel(idhotel));

        ArrayList<Hotel> listHotel = new ArrayList<Hotel>();
        listHotel = hd.displayAllHotels();
        for (int i = 0; i < listHotel.size(); i++) {
                                        int idhotel = listHotel.get(i).getIdHotel();

           // if (listHotel.get(i).getIdHotel()!= idhotel) {
                //panes.add(loadPays(listPays.get(i)));
                        if ((idhotel % 2) == 0) {

                panes1.add(loadOneHotel(listHotel.get(i).getIdHotel(), listHotel.get(i).getNomHotel()));
                System.out.println(listHotel.get(i).getNomHotel()+"\n");
                  hotel_list1.setItems(panes1);
                  hotel_list1.setSelectionModel(null);
            }
                        else {
                panes.add(loadOneHotel(listHotel.get(i).getIdHotel(), listHotel.get(i).getNomHotel()));

                hotel_list.setItems(panes);
                hotel_list.setSelectionModel(null);
            }
        }
        /*hotel_list.setItems(panes);
        hotel_list.setSelectionModel(null);*/
            //       Pane.a
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
    
    private Pane loadOneHotel(int idHotel , String NomHotel) {

        HotelDAO DAO = new HotelDAO();
        Hotel h = new Hotel();

        h = DAO.findHotelById(idHotel);

        Pane pane = new Pane();
        Image img = new Image("/travelSmart/RESSOURCES/images/icon.png");
        ImageView imageView = new ImageView(img);

        // Pane Size
        pane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.setPrefWidth(422);
        pane.setPrefHeight(114);
        String style = "-fx-padding: 8 15 15 15;\n"
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 1.1em;";
        pane.setStyle(style);
        pane.setId("pane_oneHotel");

        // ImageView Proprieties
        imageView.setFitHeight(65);
        imageView.setFitWidth(65);

        imageView.setLayoutX(14);
        imageView.setLayoutY(7);
        setPicture(img, imageView);
        String style_btn = "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 0 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-font-size: 0.8em;";

        Label nom_hotel = new Label(h.getNomHotel());
        Label note = new Label("4.5");
        Button btn = new Button("Visiter");
        btn.getStyleClass().add("btn-primary");


            // Label Proprieties
        //nom_pays.setPrefSize(314, 61);
        nom_hotel.setLayoutX(93);
        nom_hotel.setLayoutY(7);
        nom_hotel.setId("nom_Hotel");

        note.setLayoutX(93);
        note.setLayoutY(20);

        // Button Position
        btn.setLayoutX(341);
        btn.setLayoutY(78);
        btn.setId("btn_visiter");
        btn.setStyle(style_btn);
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                 FileHandler.saveText("idHotel", String.valueOf(idHotel));
                try {
                    stackEcrans.loadScreen("afficheHotel", "AfficherHotels.fxml");
                } catch (Exception ex) {
                    Logger.getLogger(AfficherAllHotelsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stackEcrans.setScreen("afficheHotel");
            }
        });

        pane.getChildren().add(imageView);
        pane.getChildren().add(nom_hotel);
        pane.getChildren().add(note);
        pane.getChildren().add(btn);

        return pane;

        
    } 
    
    
    public void showHome(ActionEvent e){
        stackEcrans.setScreen("home");
        stackEcrans.unloadScreen("listeHotels");
    }

    
    public void handleExitButtonAction(ActionEvent event) {
        Platform.exit();
    }
    
    
    public void setScreenPane(StackEcrans screenPage) {
        stackEcrans = screenPage;
    }
}
    

