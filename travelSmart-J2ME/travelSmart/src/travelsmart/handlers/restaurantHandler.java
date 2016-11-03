/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.handlers;

import travelsmart.entities.restaurant;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wassila
 */
public class restaurantHandler extends DefaultHandler{
    
    private Vector restaurants;
    String idRestaurantTag = "close";
    String nomRestaurantTag = "close";
    String categorieTag = "close";
    String descriptionTag = "close";
    String cheminTag = "close";

    
    public restaurantHandler() 
    {
        restaurants = new Vector();
    }
    
    public restaurant[] getRestaurants() {
        restaurant[] restaurant = new restaurant[restaurants.size()];
        restaurants.copyInto(restaurant);
        return restaurant;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private restaurant currentrestaurants ;
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("restaurant")) {
            currentrestaurants = new restaurant();
            //2ème methode pour parser les attribut
            currentrestaurants.setNomRestaurant(attributes.getValue("nomRestaurant"));
            currentrestaurants.setCategorie(attributes.getValue("categorie"));
            currentrestaurants.setDescription(attributes.getValue("description"));
            currentrestaurants.setChemin(attributes.getValue("chemin"));
            currentrestaurants.setLatitude(attributes.getValue("Latitude"));
            currentrestaurants.setLongitude(attributes.getValue("Longitude"));
            //currentrestaurants.setGrtAssistance(attributes.getValue("GrtAssistance"));
            /****/
            
        } 
         else if (qName.equals("nomRestaurant")) {
            nomRestaurantTag = "open";
        } else if (qName.equals("categorie")) {
            categorieTag = "open";
        }else if (qName.equals("description")) {
            descriptionTag = "open";
        }else if (qName.equals("chemin")) {
            cheminTag = "open";
        }
               
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("restaurant")) {
            // we are no longer processing a <reg.../> tag
            restaurants.addElement(currentrestaurants);
            currentrestaurants = null;
        
        } else if (qName.equals("nomRestaurant")) {
            nomRestaurantTag = "close";
        } else if (qName.equals("categorie")) {
            categorieTag = "close";
        }else if (qName.equals("description")) {
            descriptionTag = "close";
        }else if (qName.equals("chemin")) {
            cheminTag = "close";
        }
    }
     // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        // we're only interested in this inside a <phone.../> tag
        if (currentrestaurants != null) 
        {
            // don't forget to trim excess spaces from the ends of the string
            
                if (nomRestaurantTag.equals("open")) 
                {
                String nomRestaurant = new String(ch, start, length).trim();
                currentrestaurants.setNomRestaurant(nomRestaurant);
            } else
                    if (categorieTag.equals("open")) 
                    {
                String categorie = new String(ch, start, length).trim();
               currentrestaurants.setCategorie(categorie);
            }
                else
                    if (descriptionTag.equals("open")) 
                    {
                String description = new String(ch, start, length).trim();
               currentrestaurants.setDescription(description);
            }
             else
                    if (cheminTag.equals("open")) 
                    {
                String chemin = new String(ch, start, length).trim();
               currentrestaurants.setChemin(chemin);
            }
             
        }
    }
}
