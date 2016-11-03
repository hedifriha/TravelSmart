/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wassila
 */
public class compagnieHandler extends DefaultHandler{
    
    private Vector compagnies;
    String idcompagnieTag = "close";
    String nomcompagnieTag = "close";
    String typeTag = "close";


    
    public compagnieHandler() 
    {
        compagnies = new Vector();
    }
    
    public compagnie[] getcompagnies() {
        compagnie[] compagnie = new compagnie[compagnies.size()];
        compagnies.copyInto(compagnie);
        return compagnie;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private compagnie currentcompagnies ;
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentcompagnies = new compagnie();
            //2ème methode pour parser les attribut
            currentcompagnies.setNomcompagnie(attributes.getValue("nomcompagnie"));
            currentcompagnies.setType(attributes.getValue("type"));
           
            //currentrestaurants.setGrtAssistance(attributes.getValue("GrtAssistance"));
            /****/
            
        } 
         else if (qName.equals("nomcompagnie")) {
            nomcompagnieTag = "open";
        } else if (qName.equals("type")) {
            typeTag = "open";
        }
               
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            compagnies.addElement(currentcompagnies);
            currentcompagnies = null;
        
        } else if (qName.equals("nomcompagnie")) {
            nomcompagnieTag = "close";
        } else if (qName.equals("type")) {
            typeTag = "close";
        }
    }
     // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        // we're only interested in this inside a <phone.../> tag
        if (currentcompagnies != null) 
        {
            // don't forget to trim excess spaces from the ends of the string
            
                if (nomcompagnieTag.equals("open")) 
                {
                String nomcompagnie = new String(ch, start, length).trim();
                currentcompagnies.setNomcompagnie(nomcompagnie);
            } else
                    if (typeTag.equals("open")) 
                    {
                String type = new String(ch, start, length).trim();
               currentcompagnies.setType(type);
            }
                
             
        }
    }
}
