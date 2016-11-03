/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.handlers;

import travelsmart.entities.Guide;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Amri
 */
public class GuideHandler extends DefaultHandler{
    
    
    private Vector guides;
    String idTag = "close";
    String titreTag = "close";
    String destination1Tag = "close";
    String destination2Tag = "close";
    String destination3Tag = "close";
    String destination4Tag = "close";
    String destination5Tag = "close";
    String desc1Tag = "close";
    String desc2Tag = "close";
    String desc3Tag = "close";
    String desc4Tag = "close";
    String desc5Tag = "close";
    String cheminTag = "close";
    
    
    
    public GuideHandler() {
        guides = new Vector();
    }

    public Guide[] getGuide() {
        Guide[] guidess = new Guide[guides.size()];
        guides.copyInto(guidess);
        return guidess;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Guide currentGuide;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("guide")) {
            currentGuide = new Guide();
            //2ème methode pour parser les attributs
            /****/
            
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("titre")) {
            titreTag = "open";
        } else if (qName.equals("destination1")) {
            destination1Tag = "open";
        } else if (qName.equals("destination2")) {
            destination2Tag = "open";
        } else if (qName.equals("destination3")) {
            destination3Tag = "open";
        } else if (qName.equals("destination4")) {
            destination4Tag = "open";
        } else if (qName.equals("destination5")) {
            destination5Tag = "open";
        } else if (qName.equals("desc1")) {
            desc1Tag = "open";
        } else if (qName.equals("desc2")) {
            desc2Tag = "open";
        } else if (qName.equals("desc3")) {
            desc3Tag = "open";
        } else if (qName.equals("desc4")) {
            desc4Tag = "open";
        } else if (qName.equals("desc5")) {
            desc5Tag = "open";
        }  else if (qName.equals("chemin")) {
            cheminTag = "open";
        }
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("guide")) {
            // we are no longer processing a <reg.../> tag
            guides.addElement(currentGuide);
            currentGuide = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("titre")) {
            titreTag = "close";
        } else if (qName.equals("destination1")) {
            destination1Tag = "close";
        } else if (qName.equals("destination2")) {
            destination2Tag = "close";
        } else if (qName.equals("destination3")) {
            destination3Tag = "close";
        } else if (qName.equals("destination4")) {
            destination4Tag = "close";
        } else if (qName.equals("destination5")) {
            destination5Tag = "close";
        } else if (qName.equals("desc1")) {
            desc1Tag = "close";
        } else if (qName.equals("desc2")) {
            desc2Tag = "close";
        } else if (qName.equals("desc3")) {
            desc3Tag = "close";
        } else if (qName.equals("desc4")) {
            desc4Tag = "close";
        } else if (qName.equals("desc5")) {
            desc5Tag = "close";
        }  else if (qName.equals("chemin")) {
            cheminTag = "close";
        }
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentGuide != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                int id = Integer.parseInt(new String(ch, start, length).trim());
                currentGuide.setId(id);
            } else
                if (titreTag.equals("open")) {
                String titre = new String(ch, start, length).trim();
                currentGuide.setTitre(titre);
            } else
                    if (destination1Tag.equals("open")) {
                String destination1 = new String(ch, start, length).trim();
                currentGuide.setDestination1(destination1);
            } else
                    if (destination2Tag.equals("open")) {
                String destination2 = new String(ch, start, length).trim();
                currentGuide.setDestination2(destination2);
            } else
                    if (destination3Tag.equals("open")) {
                String destination3 = new String(ch, start, length).trim();
                currentGuide.setDestination3(destination3);
            } else
                    if (destination4Tag.equals("open")) {
                String destination4 = new String(ch, start, length).trim();
                currentGuide.setDestination4(destination4);
            } else
                    if (destination5Tag.equals("open")) {
                String destination5 = new String(ch, start, length).trim();
                currentGuide.setDestination5(destination5);
            }else
                    if (desc1Tag.equals("open")) {
                String desc1 = new String(ch, start, length).trim();
                currentGuide.setDesc1(desc1);
            }
            else
                    if (desc2Tag.equals("open")) {
                String desc2 = new String(ch, start, length).trim();
                currentGuide.setDesc2(desc2);
            }
            else
                    if (desc3Tag.equals("open")) {
                String desc3 = new String(ch, start, length).trim();
                currentGuide.setDesc3(desc3);
            }
            else
                    if (desc4Tag.equals("open")) {
                String desc4 = new String(ch, start, length).trim();
                currentGuide.setDesc4(desc4);
            }
            else
                    if (desc5Tag.equals("open")) {
                String desc5 = new String(ch, start, length).trim();
                currentGuide.setDesc5(desc5);
            }
            else
                    if (cheminTag.equals("open")) {
                String chemin = new String(ch, start, length).trim();
                currentGuide.setChemin(chemin);
            }
        }
    }
    
}
