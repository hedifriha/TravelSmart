/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.handlers;

import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import travelsmart.entities.User;

/**
 *
 * @author ReZ_NoV
 */
public class UserHandler extends DefaultHandler {

    private Vector users;
    User[] usersBeta;
    String idTag = "close";
    String usernameTag = "close";
    String passwordTag = "close";
    public static User currentUser;
    
    
    public UserHandler() {
        users = new Vector();
    }

    public User[] getUser() {
        User[] usersAlpha = new User[users.size()];
        users.copyInto(usersAlpha);
        return usersAlpha;
    }

    

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("Utilisateur")) {
            currentUser = new User();
            //2ème methode pour parser les attributs
            currentUser.setIdUser(attributes.getValue("id"));
            currentUser.setUsername(attributes.getValue("username"));
            currentUser.setPassword(attributes.getValue("password"));
            /**
             * *
             */
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("username")) {
            usernameTag = "open";
        } else if (qName.equals("password")) {
            passwordTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Utilisateur")) {
            // we are no longer processing a <reg.../> tag

            users.addElement(currentUser);

            
        } else if (qName.equals("id")) {

            idTag = "close";
        } else if (qName.equals("username")) {

            usernameTag = "close";
        } else if (qName.equals("password")) {

            passwordTag = "close";
        }
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentUser != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentUser.setIdUser(id);

            } else if (usernameTag.equals("open")) {
                String username = new String(ch, start, length).trim();
                currentUser.setUsername(username);

            } else if (passwordTag.equals("open")) {
                String pwd = new String(ch, start, length).trim();
                currentUser.setPassword(pwd);

            }
        }
    }

    public User checkCredentials(String username, String pwd) {
        try {
            // this will handle our XML
            UserHandler userH = new UserHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/User/getUser.php?" + "username=" + username + "&password=" + pwd);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, userH);
            
            // display the result
            usersBeta = this.getUser();
            return currentUser;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
