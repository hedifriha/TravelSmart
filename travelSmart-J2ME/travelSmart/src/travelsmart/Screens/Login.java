/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.Screens;

/**
 *
 * @author ReZ_NoV
 */
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import travelsmart.MainMidlet;
import travelsmart.entities.User;
import travelsmart.entities.globalVars;
import travelsmart.handlers.UserHandler;

public class Login extends Form implements CommandListener {

    private MainMidlet projectMidlet;
    public List mainList;
    private Display display;
    private TextField userName, password;
    public Form form;
    private Command login, cancel, signup;
    private Image img, imge, img2;

    public Login(MainMidlet projectMidlet, List mainList) {
        super("Login");
        this.projectMidlet = projectMidlet;
        this.mainList = mainList;
        init();
    }

    private void init() {

        userName = new TextField("Username:", "", 30, TextField.ANY);
        password = new TextField("Password:", "", 30, TextField.PASSWORD);
        cancel = new Command("Cancel", Command.CANCEL, 1);
        login = new Command("Login", Command.OK, 0);
        signup = new Command("Inscription", Command.OK, 2);
        try {
            img = Image.createImage("/res/logo.png");
            imge = Image.createImage("/res/front_left1_bad.png");
            img2 = Image.createImage("/res/Congratulations-1.png");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        append(img);
        append(userName);
        append(password);
        addCommand(login);
        addCommand(cancel);
        addCommand(signup);
        setCommandListener(this);

    }

    private void init_Signup() {
        setTitle("Inscription");
        userName = new TextField("Username:", "", 30, TextField.ANY);
        password = new TextField("Password:", "", 30, TextField.PASSWORD);
        cancel = new Command("Cancel", Command.CANCEL, 1);
        signup = new Command("Valider", Command.OK, 2);
        try {
            img = Image.createImage("/res/logo.png");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        append(img);
        append(userName);
        append(password);
        addCommand(cancel);
        addCommand(signup);
        setCommandListener(this);

    }

    public void validateUser(String name, String password) {
        UserHandler userH = new UserHandler();

        if (userH.checkCredentials(name, password) != null) {
            showMsg();
        } else {
            tryAgain();
        }
    }

    public void showMsg() {
        Alert success = new Alert("Succés", "", img2, AlertType.INFO);
        success.setImage(img2);
        userName.setString("");
        password.setString("");
        Display.getDisplay(projectMidlet).setCurrent(mainList);

    }

    public void tryAgain() {
        Alert error = new Alert("", "", imge, AlertType.ERROR);
        error.setImage(imge);
        userName.setString("");
        password.setString("");
        Display.getDisplay(projectMidlet).setCurrent(error, this);
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        String label = c.getLabel();
        if (label.equals("Cancel")) {
            destroyApp(true);
        } else if (label.equals("Login")) {
            validateUser(userName.getString(), password.getString());
        } else if (label.equals("Inscription")) {
            deleteAll();
            init_Signup();
        } else if (label.equals("Valider")) {
            if (userName.getString() != "" && password.getString() != "") {
                signupUser();
            }
        }
    }

    public void signupUser() {
        Thread th = new Thread(new Runnable() {
            public void run() {
                String url = "http://localhost/travelSmart_J2ME/User/addUser.php";
                try {
                    HttpConnection hc = (HttpConnection) Connector.open(url + "?username=" + userName.getString().trim() + "&password=" + password.getString().trim());
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    int ch;
                    String response = "";
                    while ((ch = dis.read()) != -1) {
                        response += ch;
                    }
                    if ("OK".equals(response)) {
                        Display.getDisplay(projectMidlet).setCurrent(new Login(projectMidlet, mainList));
                        //init();
                    } else {
                        init_Signup();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        th.start();
    }

}
