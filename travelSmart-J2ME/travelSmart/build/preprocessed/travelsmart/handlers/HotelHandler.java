/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.handlers;

import java.util.Vector;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import travelsmart.entities.Hotel;

/**
 *
 * @author Hadhoud
 */
public class HotelHandler extends DefaultHandler {
    Vector THotel;
    String idHotelTag = "close";
    String nomHotelTag = "close";
    String categorieTag = "close";
    String descriptionTag = "close";
    String cheminTag = "close";

    public HotelHandler() 
    {
        THotel = new Vector();
    }
    public Hotel[]  getHotel(){
         Hotel[] Hotel = new Hotel[THotel.size()];
                 THotel.copyInto(Hotel);

        return Hotel;
    }
    private Hotel currentHotel; 
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("hotel")) {
            currentHotel = new Hotel();
            //2ème methode pour parser les attribut
            currentHotel.setNomHotel(attributes.getValue("nomHotel"));
            currentHotel.setCategorie(attributes.getValue("categorie"));
            currentHotel.setDescription(attributes.getValue("description"));
            currentHotel.setLatitude(attributes.getValue("Latitude"));
            currentHotel.setLongitude(attributes.getValue("Longitude"));
            currentHotel.setChemin(attributes.getValue("chemin"));
            //currentrestaurants.setGrtAssistance(attributes.getValue("GrtAssistance"));
            /****/
            
        } 
         else if (qName.equals("nomHotel")) {
            nomHotelTag = "open";
        } else if (qName.equals("categorie")) {
            categorieTag = "open";
        }else if (qName.equals("description")) {
            descriptionTag = "open";
        }else if (qName.equals("chemin")) {
            cheminTag = "open";
       }
        //   else if (qName.equals("Latitude")) {
//            LatitudeTag = "open";
//        }else if (qName.equals("Langitude")) {
//            LangitudeTag = "open";
//        }
               
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("hotel")) {
            // we are no longer processing a <reg.../> tag
            THotel.addElement(currentHotel);
            currentHotel = null;
        
        } else if (qName.equals("nomhotel")) {
            nomHotelTag = "close";
        } else if (qName.equals("categorie")) {
            categorieTag = "close";
        }else if (qName.equals("description")) {
            descriptionTag = "close";
        }else if (qName.equals("chemin")) {
            cheminTag = "close";
        }      
//            else if (qName.equals("Latitude")) {
//            LatitudeTag = "close";
//        }else if (qName.equals("Langitude")) {
//            LangitudeTag = "close";
//        }
    }
     public void characters(char[] ch, int start, int length) throws SAXException 
    {
        // we're only interested in this inside a <phone.../> tag
        if (currentHotel != null) 
        {
            // don't forget to trim excess spaces from the ends of the string
            
                if (nomHotelTag.equals("open")) 
                {
                String nomhotel = new String(ch, start, length).trim();
                currentHotel.setNomHotel(nomhotel);
            } else
                    if (categorieTag.equals("open")) 
                    {
                String categorie = new String(ch, start, length).trim();
               currentHotel.setCategorie(categorie);
            }
                else
                    if (descriptionTag.equals("open")) 
                    {
                String description = new String(ch, start, length).trim();
               currentHotel.setDescription(description);
            }
             else
                    if (cheminTag.equals("open")) 
                    {
                String chemin = new String(ch, start, length).trim();
               currentHotel.setChemin(chemin);
            }
//                 else
//                    if (LangitudeTag.equals("open")) 
//                    {
//                String langitude = new String(ch, start, length).trim();
//               currentHotel.setLongitude(langitude);
//            }else
//                    if (LatitudeTag.equals("open")) 
//                    {
//                String latitude = new String(ch, start, length).trim();
//               currentHotel.setLatitude(latitude);
//            }
             
        }
    }
    
    
}
