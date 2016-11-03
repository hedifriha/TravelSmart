/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.handlers;

import travelsmart.entities.commantair;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wassila
 */
public class commantairHandler extends DefaultHandler{
    private Vector commantaire;

    String idTag = "close";
    String commentTag = "close";
    String userTag = "close";
    
     public commantairHandler() 
    {
        commantaire = new Vector();
    }
     public commantair[] getCommantaire() {
        commantair[] commant = new commantair[commantaire.size()];
        commantaire.copyInto(commant);
        return commant;
    }
      private commantair currentcommantaire ;
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("commantair")) {
            currentcommantaire = new commantair();
            //2ème methode pour parser les attribut
            currentcommantaire.setUser(attributes.getValue("user"));
            currentcommantaire.setComment(attributes.getValue("comment"));
            currentcommantaire.setId(attributes.getValue("id"));
            //currentrestaurants.setGrtAssistance(attributes.getValue("GrtAssistance"));
            /****/
            
          
        } else if (qName.equals("user") ){
            userTag = "open";
        } else if (qName.equals("comment")) {
            commentTag = "open";
        }else if (qName.equals("id") ){
            idTag = "open";
        } 
}
 public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("commantair")) {
            // we are no longer processing a <reg.../> tag
            commantaire.addElement(currentcommantaire);
            currentcommantaire = null;
        
        } else if (qName.equals("user")) {
            userTag = "close";
        } else if (qName.equals("comment")) {
            commentTag = "close";
        } else if (qName.equals("id")) {
            idTag = "close";
        }
    }
     // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        // we're only interested in this inside a <phone.../> tag
        if (currentcommantaire != null) 
        {
            // don't forget to trim excess spaces from the ends of the string
            
                if (userTag.equals("open")) 
                {
                String user = new String(ch, start, length).trim();
                currentcommantaire.setUser(user);
            } else
                    if (commentTag.equals("open")) 
                    {
                String comment = new String(ch, start, length).trim();
               currentcommantaire.setComment(comment);
            }else
                    if (idTag.equals("open")) 
                    {
                String idc = new String(ch, start, length).trim();
               currentcommantaire.setComment(idc);
            }
                
        }
    }
}

