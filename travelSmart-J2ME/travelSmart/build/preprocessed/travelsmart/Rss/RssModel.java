/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.Rss;

import javax.microedition.lcdui.Image;

/**
 *
 * @author Ines
 */
public class RssModel {
    private String title;
    private Image image;
    public RssModel(String Title,Image image) {
        title = Title;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
   
    public Image getImage() {
        return image;
    }
}
