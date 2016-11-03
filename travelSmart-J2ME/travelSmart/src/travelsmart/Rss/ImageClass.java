/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.Rss;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import travelsmart.MainMidlet;
import travelsmart.MainMidlet;

/**
 *
 * @author Ines
 */
public class ImageClass {

    private MainMidlet parentMidlet;
    private DataInputStream is = null;
    private Image img = null;
    private Display display;

    public Image getImage(MainMidlet parentMidlet, String imageurl) {
        this.parentMidlet = parentMidlet;
        display = Display.getDisplay(parentMidlet);
        try {
            HttpConnection c = (HttpConnection) Connector.open(imageurl);
            int len = (int) c.getLength();
            if (len > 0) {
                is = c.openDataInputStream();
                byte[] data = new byte[len];
                is.readFully(data);
                img = Image.createImage(data, 0, len);
                //img=createThumbnail(img);
            } else {
                showAlert("length is null");
            }
            is.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(e.getMessage());
        }
        return img;
    }

    private void showAlert(String err) {
        Alert a = new Alert("");
        a.setString(err);
        a.setTimeout(Alert.FOREVER);
        display.setCurrent(a);
    }
}
