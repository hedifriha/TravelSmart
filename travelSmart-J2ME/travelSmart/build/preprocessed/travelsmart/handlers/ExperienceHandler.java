/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.handlers;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import travelsmart.entities.Experience;

/**
 *
 * @author Ines
 */
public class ExperienceHandler extends DefaultHandler {

    private Vector experiences;
    String idTag = "close";
    String titreTag = "close";
    String dest1Tag = "close";
    String dep1Tag = "close";
    String desc1Tag = "close";
    String dest2Tag = "close";
    String dep2Tag = "close";
    String desc2Tag = "close";
    String dest3Tag = "close";
    String dep3Tag = "close";
    String desc3Tag = "close";

    public ExperienceHandler() {
        experiences = new Vector();
    }

    public Experience[] getExperience() {
        Experience[] exp = new Experience[experiences.size()];
        experiences.copyInto(exp);
        return exp;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Experience currentExperience;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("experience")) {
            currentExperience = new Experience();
            //2ème methode pour parser les attributs
            currentExperience.setId(attributes.getValue("id"));
            currentExperience.setTitre(attributes.getValue("titre"));
            currentExperience.setDestination1(attributes.getValue("destination1"));
            currentExperience.setDepense1(attributes.getValue("dep1"));
            currentExperience.setDescription1(attributes.getValue("desc1"));
            currentExperience.setDestination2(attributes.getValue("destination2"));
            currentExperience.setDepense2(attributes.getValue("dep2"));
            currentExperience.setDescription2(attributes.getValue("desc2"));
            currentExperience.setDestination3(attributes.getValue("destination3"));
            currentExperience.setDepense3(attributes.getValue("dep3"));
            currentExperience.setDescription3(attributes.getValue("desc3"));
            /**
             * *
             */

        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("titre")) {
            titreTag = "open";
        } else if (qName.equals("destination1")) {
            dest1Tag = "open";
        } else if (qName.equals("dep1")) {
            dep1Tag = "open";
        } else if (qName.equals("desc1")) {
            desc1Tag = "open";
        }else if (qName.equals("destination2")) {
            dest2Tag = "open";
        } else if (qName.equals("dep2")) {
            dep2Tag = "open";
        } else if (qName.equals("desc2")) {
            desc2Tag = "open";
        }else if (qName.equals("destination3")) {
            dest3Tag = "open";
        } else if (qName.equals("dep3")) {
            dep3Tag = "open";
        } else if (qName.equals("desc3")) {
            desc3Tag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("experience")) {
            // we are no longer processing a <reg.../> tag
            experiences.addElement(currentExperience);
            currentExperience = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("titre")) {
            titreTag = "close";
        } else if (qName.equals("destination1")) {
            dest1Tag = "close";
        } else if (qName.equals("dep1")) {
            dep1Tag = "close";
        } else if (qName.equals("desc1")) {
            desc1Tag = "close";
        }else if (qName.equals("destination2")) {
            dest2Tag = "close";
        } else if (qName.equals("dep2")) {
            dep2Tag = "close";
        } else if (qName.equals("desc2")) {
            desc2Tag = "close";
        }else if (qName.equals("destination1")) {
            dest3Tag = "close";
        } else if (qName.equals("dep3")) {
            dep3Tag = "close";
        } else if (qName.equals("desc3")) {
            desc3Tag = "close";
        }
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentExperience != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentExperience.setId(id);
            } else if (titreTag.equals("open")) {
                String titre = new String(ch, start, length).trim();
                currentExperience.setTitre(titre);
            } else if (dest1Tag.equals("open")) {
                String dest = new String(ch, start, length).trim();
                currentExperience.setDestination1(dest);
            } else if (dep1Tag.equals("open")) {
                String dep = new String(ch, start, length).trim();
                currentExperience.setDepense1(dep);
            } else if (desc1Tag.equals("open")) {
                String desc = new String(ch, start, length).trim();
                currentExperience.setDescription1(desc);
            }else if (dest2Tag.equals("open")) {
                String dest = new String(ch, start, length).trim();
                currentExperience.setDestination2(dest);
            } else if (dep2Tag.equals("open")) {
                String dep = new String(ch, start, length).trim();
                currentExperience.setDepense2(dep);
            } else if (desc2Tag.equals("open")) {
                String desc = new String(ch, start, length).trim();
                currentExperience.setDescription2(desc);
            }else if (dest3Tag.equals("open")) {
                String dest = new String(ch, start, length).trim();
                currentExperience.setDestination3(dest);
            } else if (dep3Tag.equals("open")) {
                String dep = new String(ch, start, length).trim();
                currentExperience.setDepense3(dep);
            } else if (desc3Tag.equals("open")) {
                String desc = new String(ch, start, length).trim();
                currentExperience.setDescription3(desc);
            }
        }
    }
}
