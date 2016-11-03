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
public class aeroportHandler extends DefaultHandler{
    
    private Vector aeroports;
    String idaeroportTag = "close";
    String nomaeroportTag = "close";
    String nbrpisteTag = "close";
    String cheminTag = "close";
    String adresseTag = "close";
    String descriptionTag = "close";


    
    public aeroportHandler() 
    {
        aeroports = new Vector();
    }
    
    public aeroport[] getaeroports() {
        aeroport[] aeroport = new aeroport[aeroports.size()];
        aeroports.copyInto(aeroport);
        return aeroport;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private aeroport currentaeroports ;
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentaeroports = new aeroport();
            //2ème methode pour parser les attribut
            currentaeroports.setNomaeroport(attributes.getValue("nomaeroport"));
            currentaeroports.setNbrpiste(attributes.getValue("nbrpiste"));
            currentaeroports.setChemin(attributes.getValue("chemin"));
            currentaeroports.setAdresse(attributes.getValue("adresse"));
            currentaeroports.setDescription(attributes.getValue("description"));
           
            //currentrestaurants.setGrtAssistance(attributes.getValue("GrtAssistance"));
            /****/
            
        } 
         else if (qName.equals("nomaeroport")) {
            nomaeroportTag = "open";
        } else if (qName.equals("nbrpiste")) {
            nbrpisteTag = "open";
        }else if (qName.equals("chemin")) {
            cheminTag = "open";
        }else if (qName.equals("adresse")) {
            adresseTag = "open";
        }else if (qName.equals("description")) {
            descriptionTag = "open";
        }
               
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            aeroports.addElement(currentaeroports);
            currentaeroports = null;
        
        } else if (qName.equals("nomaeroport")) {
            nomaeroportTag = "close";
        } else if (qName.equals("nbrpiste")) {
            nbrpisteTag = "close";
        }else if (qName.equals("chemin")) {
            cheminTag = "close";
        }else if (qName.equals("adresse")) {
            adresseTag = "close";
        }else if (qName.equals("description")) {
            descriptionTag = "close";
        }
    }
     // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        // we're only interested in this inside a <phone.../> tag
        if (currentaeroports != null) 
        {
            // don't forget to trim excess spaces from the ends of the string
            
                if (nomaeroportTag.equals("open")) 
                {
                String nomaeroport = new String(ch, start, length).trim();
                currentaeroports.setNomaeroport(nomaeroport);
            } else
                    if (nbrpisteTag.equals("open")) 
                    {
                String nbrpiste = new String(ch, start, length).trim();
               currentaeroports.setNbrpiste(nbrpiste);
            }else
                    if (cheminTag.equals("open")) 
                    {
                String chemin = new String(ch, start, length).trim();
               currentaeroports.setChemin(chemin);
            }else
                    if (adresseTag.equals("open")) 
                    {
                String adresse = new String(ch, start, length).trim();
               currentaeroports.setAdresse(adresse);
            }else
                    if (descriptionTag.equals("open")) 
                    {
                String description = new String(ch, start, length).trim();
               currentaeroports.setDescription(description);
            }
                
             
        }
    }
}
