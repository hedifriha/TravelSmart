/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.PIDEV;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import travelSmart.DAO.entities.EmailDAO;
import travelSmart.DAO.entities.UserDAO;
import travelSmart.GUI.FXML.HomeScreenController;
import travelSmart.LIBS.StackEcrans;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author reznov
 */
public class Launcher extends Application {
    public String navAdmin = "Administration.fxml";
    public String navHome = "HomeScreen.fxml";
    public String navPays = "AfficherTousPays.fxml";
    public String navVilles = "AfficherTousVilles.fxml";
    public String navOnePays = "AfficherPays.fxml";

    public static final String APPLICATION_ICON
            = "/travelSmart/RESSOURCES/images/logo-travelSmart.png";
    public static final String SPLASH_IMAGE
            = "/travelSmart/RESSOURCES/images/logo-travelSmart.png";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 982;
    private static final int SPLASH_HEIGHT = 239;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() throws IOException, URISyntaxException, Exception {
        ImageView splash = new ImageView(new Image(SPLASH_IMAGE));

        loadProgress = new ProgressBar();
        loadProgress.setStyle("-fx-accent: #1e5fac; -fx-border-color: black; -fx-border-radius: 3px;");
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        progressText = new Label("Loading Modules . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle("-fx-background-color: rgba(255,255,255, 0.1);");

    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> Task = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundModules
                        = FXCollections.<String>observableArrayList();
                ObservableList<String> availableModules
                        = FXCollections.observableArrayList(
                                //"Internet Connection", "Database Status"
                                "Internet Connection"
                        );

                updateMessage("Startup Check . . .");
                for (int i = 0; i < availableModules.size(); i++) {
                    Thread.sleep(800);
                    updateProgress(i + 1, availableModules.size());
                    String nextModule = availableModules.get(i);
                    /* Internet check */
                    if(nextModule.equals("Internet Connection")){
                        
                        if(Launcher.getInternetStatus() != 0)
                        {
                            updateMessage("You are Online ");
                        }
                        else
                        {
                            updateMessage("You are Offline ");
                        }
                        //Thread.sleep(500);
                    }
                    /* Databse check */
                    /*else{ 
                        if(MyConnection.getInstance().getConnection().isValid(100)){
                            foundModules.add(nextModule);
                            updateMessage("Database OK");
                            Thread.sleep(800);
                        }
                    }
                    */
                }
                Thread.sleep(400);
                updateMessage("All modules loaded.");

                return foundModules;
            }
        };
        showSplash(initStage,
                Task, () -> showMainStage()
        );
        new Thread(Task).start();
    }

    public void showMainStage() {
        
        
        try {          
            StackEcrans container = new StackEcrans();

            container.loadScreen("home", navHome);           

            container.setScreen("home");

            Scene scene = new Scene(container);
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Home | travelSmart");
            stage.resizableProperty().setValue(false); // Desactiver le resize
            stage.centerOnScreen();
            stage.show();

        //TEST CONNEXION BD
            //UserDAO x = new UserDAO();

        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) throws Exception {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(splashLayout);
        splashScene.setFill(null);
        initStage.initStyle(StageStyle.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.show();
    }

    public interface InitCompletionHandler {

        public void complete();
    }
    
    public static int getInternetStatus(){
    try {
        URL siteURL = new URL("http", "www.google.com", 80, "www.google.com"); 
        HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                return code;
            }
        } catch (Exception e) {
            System.out.println("No available Internet Connection : " + e.getMessage());
        }
        return 0;
    }

}
