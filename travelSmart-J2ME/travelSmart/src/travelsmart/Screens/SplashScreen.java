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
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import travelsmart.entities.globalVars;
import travelsmart.MainMidlet;

/**
 * A simple splash screen.
 */
public class SplashScreen extends Canvas implements Runnable {

    private Image mImage;
    private MainMidlet projectMIDlet;
    public List mainList;
    public Form form;
    Player player;

    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press, a pointer
     * press, or a timeout
     *
     * @param projectMIDlet instance of MIDlet
     */
    public SplashScreen(MainMidlet projectMIDlet, List d) {
        this.projectMIDlet = projectMIDlet;
        this.mainList = d;
        try {
            mImage = Image.createImage("/res/splash.png");
            Thread t = new Thread(this);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

    /**
     * Paints the image centered on the screen.
     */
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(0x000000);
        g.fillRect(0, 0, width, height);
        g.drawImage(mImage, width / 2, height / 2,
                Graphics.HCENTER | Graphics.VCENTER);
    }

    /**
     * Dismisses the splash screen with a key press or a pointer press
     */
    public void dismiss() throws Exception {
        if (isShown()) {
            Display.getDisplay(projectMIDlet).setCurrent(new Login(projectMIDlet, mainList));
        }
    }

    /**
     * Default timeout with thread
     */
    public void run() {
        
        try {
            loadPlayer();
            Thread.sleep(3000);//set for 3 seconds
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            
            Display.getDisplay(projectMIDlet).setCurrent(new Login(projectMIDlet, mainList));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A key release event triggers the dismiss() method to be called.
     */
    public void keyReleased(int keyCode) {
        try {
            dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A pointer release event triggers the dismiss() method to be called.
     */
    public void pointerReleased(int x, int y) {
        try {
            dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadPlayer() throws Exception {
        player = Manager.createPlayer(getClass().getResourceAsStream("/res/splash.mp3"), "audio/mp3");
        player.realize();
        player.start();
        globalVars.idUser = 5;
        //globalVars.test  = "hellorrr" ; //SESSION LIKE
    }
}