/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import travelsmart.Rss.ParseThread;
import travelsmart.Rss.RssModel;
import travelsmart.libs.SendSMS;
import travelsmart.handlers.*;
import travelsmart.entities.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import travelsmart.Screens.SplashScreen;

/**
 * @author Amri
 */
public class MainMidlet extends MIDlet implements Runnable, CommandListener {

    // get Display
    private Display disp;
    // Forms
    private Form afficheGuideForm, ajoutGuideForm, modifGuideForm, accueilExp, formExp, loadingDialog, modifFormExp, f1Exp;

    // Commands
    private Command cmdExplorer, cmdExit, cmdBack_Guide, cmdAfficher_Guide, cmdMesGuides_Guide, cmdAjouter_Guide, cmdModifier_Guide, cmdSupprimer_Guide, cmdValider_Guide;

    // Lists
    private List mainList, listeTtList_Guide, mesGuidesList, lstExp;

    // TextFields
    TextField tfTitre_Guide, tfDest1_Guide, tfDesc1_Guide, tfDest2_Guide, tfDesc2_Guide, tfDest3_Guide, tfDesc3_Guide, tfDest4_Guide, tfDesc4_Guide, tfDest5_Guide, tfDesc5_Guide, tfChemin_Guide,
            tfTitre_modif_Guide, tfDest1_modif_Guide, tfDesc1_modif_Guide, tfDest2_modif_Guide, tfDesc2_modif_Guide, tfDest3_modif_Guide, tfDesc3_modif_Guide,
            tfDest4_modif_Guide, tfDesc4_modif_Guide, tfDest5_modif_Guide, tfDesc5_modif_Guide, tfChemin_modif_Guide;
    //Ajout
    TextField titreExp;
    TextField dest1Exp, dep1Exp, desc1Exp;
    TextField dest2Exp, dep2Exp, desc2Exp;
    TextField dest3Exp, dep3Exp, desc3Exp;

    //Modification
    TextField titreModifExp;
    TextField dest1ModifExp, dep1ModifExp, desc1ModifExp;
    TextField dest2ModifExp, dep2ModifExp, desc2ModifExp;
    TextField dest3ModifExp, dep3ModifExp, desc3ModifExp;

    Form form1Exp, form3Exp;

    Command cmdValiderExp, cmdBackExp, cmdDleteExp, cmdUpdExp, cmdshowExp, cmdNewExp;

    //Restaurant
    Form fResto, formResto, f1Resto, f2Resto, f3Resto;

    List lstResto, lstComResto;

    TextField tfUserResto, tfComResto;

    Command cmdParseResto, cmdBackResto, cmdMapResto, cmdComResto, cmdAffComResto, cmdSuppResto, cmdValiderResto;

    //Hotel
    Form fhotel, formhotel, loadingDialoghotel, mail, f1, f2, f3;

    List lsthotel, lstCom;

    TextField tMail, tPWD, tSujet, tMessage, tfUser, tfCom;

    Command cmdParsehotel, cmdmail, cmdBackhotel, cmdMaphotel, cmdCom, cmdAffCom, cmdSupp, EnvoiMail, cmdValider;

    Alert alertb, alert;

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb = new StringBuffer();
    String url;
    int ch;
    int idExpModif;
    String id;
    // SESSION INFO : User Id
//    int userId = 0;
    private Guide[] guides;
    private Guide[] mesGuides;
    int idGuide_modif = 0;

    private Experience[] experiences;

    private restaurant[] restaurants;
    private commantair[] commantResto;

    private Hotel h = new Hotel();
    private Hotel[] hotel;
    private commantair[] commant;

    /* ******* RSS ******* */
    private List rssList;
    private Vector rssItem;
    private Command m_backCommand;      // The back to header list command
    private Command Rss;      // The item form
    private RssModel model;

    //  Aer & Comp
    Command cmdBackcompagnies = new Command("Retour", Command.BACK, 0);
    compagnie[] compagnies;
    List lstcompagnies = new List("compagnies", List.IMPLICIT);

    Form ffac = new Form("Accueil");
    Form f = new Form("Accueil");
    Form formcompagnie = new Form("Infos compagnie");
//    Form loadingDialog = new Form("Patientez");
//    StringBuffer sb = new StringBuffer(); //memoire tempon ou on n a pas besoin de specifier la taille

    ////
    Command cmdA = new Command("Aeroports", Command.SCREEN, 0);
    Command cmdC = new Command("Compagnies", Command.SCREEN, 0);
    Command cmdParse1 = new Command("Aeroports", Command.SCREEN, 0);
    Command cmdBackaeroports = new Command("Retour", Command.BACK, 0);

    aeroport[] aeroports;
    List lstaeroports = new List("aeroports", List.IMPLICIT);
//    Form f1 = new Form("Accueil");
    Form formaeroports = new Form("Infos aeroports");
    Form loadingDialog1 = new Form("Patientez");
    StringBuffer sb1 = new StringBuffer(); //memoire tempon ou on n a pas besoin de specifier la taille

    public MainMidlet() {

        String[] stringElements = {"Gestion des Guides", "Gestion des Expériences", "Gestion des Hôtels", "Gestion des Restaurants", "Gestion des Aeroports & Compagnies"};
        mainList = new List("Accueil", List.IMPLICIT, stringElements, null);

        cmdExplorer = new Command("Explorer", Command.ITEM, 0);
        cmdmail = new Command("contacter nous", Command.SCREEN, 0);
        cmdExit = new Command("Quitter", Command.EXIT, 0);
        Rss = new Command("Rss", Command.ITEM, 0);

        mainList.addCommand(cmdExplorer);
        mainList.addCommand(cmdmail);
        mainList.addCommand(cmdExit);
        mainList.addCommand(Rss);
        mainList.setCommandListener(this);

        /**
         * * Guides **
         */
        // Lists
        listeTtList_Guide = new List("Liste Guides", List.IMPLICIT);
        mesGuidesList = new List("Mes Guides", List.IMPLICIT);

        // Forms
        afficheGuideForm = new Form("Guide");
        ajoutGuideForm = new Form("Ajouter Guide");
        modifGuideForm = new Form("Modifier");

        // Commands
        cmdBack_Guide = new Command("Retour", Command.BACK, 0);
        cmdAfficher_Guide = new Command("Afficher", Command.SCREEN, 0);
        cmdMesGuides_Guide = new Command("Mes Guides", Command.SCREEN, 0);
        cmdAjouter_Guide = new Command("Ajouter un Guide", Command.SCREEN, 0);
        cmdModifier_Guide = new Command("Modifier le Guide", Command.SCREEN, 0);
        cmdSupprimer_Guide = new Command("Supprimer le Guide", Command.ITEM, 0);
        cmdValider_Guide = new Command("Valider", Command.SCREEN, 0);

        /**
         * *************
         */
        tfTitre_Guide = new TextField("Titre", null, 100, TextField.PLAIN);
        tfDest1_Guide = new TextField("Destination 1", null, 100, TextField.PLAIN);
        tfDesc1_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest2_Guide = new TextField("Destination 2", null, 100, TextField.PLAIN);
        tfDesc2_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest3_Guide = new TextField("Destination 3", null, 100, TextField.PLAIN);
        tfDesc3_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest4_Guide = new TextField("Destination 4", null, 100, TextField.PLAIN);
        tfDesc4_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest5_Guide = new TextField("Destination 5", null, 100, TextField.PLAIN);
        tfDesc5_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfChemin_Guide = new TextField("Chemin", null, 100, TextField.PLAIN);
        /**
         * *************
         */

        /**
         * *************
         */
        tfTitre_modif_Guide = new TextField("Titre", null, 100, TextField.PLAIN);
        tfDest1_modif_Guide = new TextField("Destination 1", null, 100, TextField.PLAIN);
        tfDesc1_modif_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest2_modif_Guide = new TextField("Destination 2", null, 100, TextField.PLAIN);
        tfDesc2_modif_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest3_modif_Guide = new TextField("Destination 3", null, 100, TextField.PLAIN);
        tfDesc3_modif_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest4_modif_Guide = new TextField("Destination 4", null, 100, TextField.PLAIN);
        tfDesc4_modif_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfDest5_modif_Guide = new TextField("Destination 5", null, 100, TextField.PLAIN);
        tfDesc5_modif_Guide = new TextField("Description", null, 100, TextField.PLAIN);
        tfChemin_modif_Guide = new TextField("Chemin", null, 100, TextField.PLAIN);
        /**
         * *************
         */

        // Session
//        userId = 1;
        // Add Commands to List listeTtList_Guide
        listeTtList_Guide.addCommand(cmdBack_Guide);
        listeTtList_Guide.addCommand(cmdAfficher_Guide);
        listeTtList_Guide.addCommand(cmdMesGuides_Guide);
        listeTtList_Guide.setCommandListener(this);

        // Add Commands to List mesGuidesList
        mesGuidesList.addCommand(cmdBack_Guide);
        mesGuidesList.addCommand(cmdAfficher_Guide);
        mesGuidesList.addCommand(cmdAjouter_Guide);
        mesGuidesList.addCommand(cmdModifier_Guide);
        mesGuidesList.addCommand(cmdSupprimer_Guide);
        mesGuidesList.setCommandListener(this);

        // Add Commands to Form afficheGuideForm
        afficheGuideForm.addCommand(cmdBack_Guide);
        afficheGuideForm.setCommandListener(this);

        // Add Commands to Form ajoutGuide
        ajoutGuideForm.addCommand(cmdBack_Guide);
        ajoutGuideForm.addCommand(cmdValider_Guide);
        ajoutGuideForm.setCommandListener(this);

        // Add TextFields to Form ajoutGuide
        ajoutGuideForm.append(tfTitre_Guide);
        ajoutGuideForm.append(tfDest1_Guide);
        ajoutGuideForm.append(tfDesc1_Guide);
        ajoutGuideForm.append(tfDest2_Guide);
        ajoutGuideForm.append(tfDesc2_Guide);
        ajoutGuideForm.append(tfDest3_Guide);
        ajoutGuideForm.append(tfDesc3_Guide);
        ajoutGuideForm.append(tfDest4_Guide);
        ajoutGuideForm.append(tfDesc4_Guide);
        ajoutGuideForm.append(tfDest5_Guide);
        ajoutGuideForm.append(tfDesc5_Guide);
        ajoutGuideForm.append(tfChemin_Guide);

        // Add TextFields to Form modifGuide
        modifGuideForm.append(tfTitre_modif_Guide);
        modifGuideForm.append(tfDest1_modif_Guide);
        modifGuideForm.append(tfDesc1_modif_Guide);
        modifGuideForm.append(tfDest2_modif_Guide);
        modifGuideForm.append(tfDesc2_modif_Guide);
        modifGuideForm.append(tfDest3_modif_Guide);
        modifGuideForm.append(tfDesc3_modif_Guide);
        modifGuideForm.append(tfDest4_modif_Guide);
        modifGuideForm.append(tfDesc4_modif_Guide);
        modifGuideForm.append(tfDest5_modif_Guide);
        modifGuideForm.append(tfDesc5_modif_Guide);
        modifGuideForm.append(tfChemin_modif_Guide);

        // Add Commands to Form modifGuide
        modifGuideForm.addCommand(cmdBack_Guide);
        modifGuideForm.addCommand(cmdValider_Guide);
        modifGuideForm.setCommandListener(this);
        /**
         * * Guides **
         */

        /**
         * * Experiences **
         */
        f1Exp = new Form("Expérience");
        lstExp = new List("Experiences", List.IMPLICIT);
        accueilExp = new Form("Accueil");
        formExp = new Form("Infos Experience");
        loadingDialog = new Form("Please Wait");
        modifFormExp = new Form("Modification");

        form1Exp = new Form("Expérience");

        //Ajout
        titreExp = new TextField("Titre", null, 100, TextField.ANY);
        //////// DESTINATION 1
        dest1Exp = new TextField("Destination", null, 100, TextField.ANY);
        dep1Exp = new TextField("Depense", null, 100, TextField.ANY);
        desc1Exp = new TextField("Description", null, 255, TextField.ANY);
        //////// DESTINATION 2
        dest2Exp = new TextField("Destination", null, 100, TextField.ANY);
        dep2Exp = new TextField("Depense", null, 100, TextField.ANY);
        desc2Exp = new TextField("Description", null, 255, TextField.ANY);
        //////// DESTINATION 3
        dest3Exp = new TextField("Destination", null, 100, TextField.ANY);
        dep3Exp = new TextField("Depense", null, 100, TextField.ANY);
        desc3Exp = new TextField("Description", null, 255, TextField.ANY);

        //Modifcation
        titreModifExp = new TextField("Titre", null, 100, TextField.ANY);
        //////// DESTINATION 1
        dest1ModifExp = new TextField("Destination", null, 100, TextField.ANY);
        dep1ModifExp = new TextField("Depense", null, 100, TextField.ANY);
        desc1ModifExp = new TextField("Description", null, 255, TextField.ANY);
        //////// DESTINATION 2
        dest2ModifExp = new TextField("Destination", null, 100, TextField.ANY);
        dep2ModifExp = new TextField("Depense", null, 100, TextField.ANY);
        desc2ModifExp = new TextField("Description", null, 255, TextField.ANY);
        //////// DESTINATION 3
        dest3ModifExp = new TextField("Destination", null, 100, TextField.ANY);
        dep3ModifExp = new TextField("Depense", null, 100, TextField.ANY);
        desc3ModifExp = new TextField("Description", null, 255, TextField.ANY);

        cmdshowExp = new Command("Experiences", Command.SCREEN, 0);
        cmdNewExp = new Command("Nouveau", Command.SCREEN, 0);
        cmdValiderExp = new Command("Ajouter", Command.SCREEN, 0);
        cmdBackExp = new Command("Retour", Command.BACK, 0);
        cmdDleteExp = new Command("Supprimer", Command.ITEM, 0);
        cmdUpdExp = new Command("Modifier", Command.ITEM, 1);

        form3Exp = new Form("Erreur");

        //Affichage
        formExp.addCommand(cmdBackExp);
        formExp.addCommand(cmdDleteExp);
        formExp.addCommand(cmdUpdExp);
        formExp.setCommandListener(this);

        lstExp.addCommand(cmdNewExp);
        lstExp.addCommand(cmdBackExp);
        lstExp.setCommandListener(this);

        // Ajout
        form1Exp.append(titreExp);
        form1Exp.append(dest1Exp);
        form1Exp.append(dep1Exp);
        form1Exp.append(desc1Exp);

        form1Exp.append(dest2Exp);
        form1Exp.append(dep2Exp);
        form1Exp.append(desc2Exp);

        form1Exp.append(dest3Exp);
        form1Exp.append(dep3Exp);
        form1Exp.append(desc3Exp);

        form1Exp.addCommand(cmdValiderExp);
        form1Exp.addCommand(cmdBackExp);
        form1Exp.setCommandListener(this);

        //Modification
        modifFormExp.append(titreModifExp);
        modifFormExp.append(dest1ModifExp);
        modifFormExp.append(dep1ModifExp);
        modifFormExp.append(desc1ModifExp);

        modifFormExp.append(dest2ModifExp);
        modifFormExp.append(dep2ModifExp);
        modifFormExp.append(desc2ModifExp);

        modifFormExp.append(dest3ModifExp);
        modifFormExp.append(dep3ModifExp);
        modifFormExp.append(desc3ModifExp);

        modifFormExp.addCommand(cmdBackExp);
        modifFormExp.addCommand(cmdValiderExp);
        modifFormExp.setCommandListener(this);
        /**
         * * Experiences **
         */

        /**
         * * Restaurants **
         */
        lstResto = new List("restaurants", List.IMPLICIT);
        lstComResto = new List("Liste Commantaire", List.IMPLICIT);

        fResto = new Form("Accueil");
        formResto = new Form("Infos restaurants");
        f1Resto = new Form("Poster un commantaire");
        f2Resto = new Form("Welcome");
        f3Resto = new Form("Erreur");
        try {
            if (UserHandler.currentUser.equals(null)) {
                tfUserResto = new TextField("utilisateur", null, 100, TextField.ANY);
            } else {
                tfUserResto = new TextField("Utilisateur", UserHandler.currentUser.getUsername(), 100, TextField.ANY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tfComResto = new TextField("commantaire", null, 200, TextField.ANY);

        cmdValiderResto = new Command("valider", Command.SCREEN, 0);
        cmdParseResto = new Command("restaurants", Command.SCREEN, 0);
        cmdBackResto = new Command("Retour", Command.BACK, 0);
        cmdMapResto = new Command("GoogleMap", Command.SCREEN, 1);
        cmdComResto = new Command("Commanter", Command.SCREEN, 1);
        cmdAffComResto = new Command("Afficher Commantaire", Command.SCREEN, 1);
        cmdSuppResto = new Command("Supprimer", Command.EXIT, 1);

        //fResto.append("Cliquez Restaurant pour avoir la liste des restaurants");
        fResto.addCommand(cmdParseResto);
        fResto.setCommandListener(this);

        lstComResto.addCommand(cmdSuppResto);
        lstComResto.addCommand(cmdExplorer);
        lstResto.addCommand(cmdBackResto);
        lstComResto.setCommandListener(this);

        lstResto.setCommandListener(this);

        formResto.addCommand(cmdBackResto);
        formResto.addCommand(cmdMapResto);
        formResto.addCommand(cmdComResto);
        formResto.addCommand(cmdAffComResto);
        formResto.setCommandListener(this);

        f1Resto.append(tfComResto);
        f1Resto.addCommand(cmdValiderResto);
        f1Resto.setCommandListener(this);

        f2Resto.addCommand(cmdSuppResto);
        f2Resto.setCommandListener(this);

        /**
         * * Restaurants **
         */
        /**
         * * Hotels **
         */
        cmdParsehotel = new Command("Liste des Hotels", Command.SCREEN, 0);
        cmdBackhotel = new Command("Retour", Command.BACK, 0);
        cmdMaphotel = new Command("GoogleMap", Command.SCREEN, 1);
        cmdCom = new Command("Commanter", Command.SCREEN, 1);
        cmdAffCom = new Command("Afficher Commantaire", Command.SCREEN, 1);
        cmdSupp = new Command("Supprimer", Command.EXIT, 1);
        EnvoiMail = new Command("envoyer mail", Command.SCREEN, 1);
        cmdValider = new Command("valider", Command.SCREEN, 0);

        lsthotel = new List("hotel", List.IMPLICIT);
        lstCom = new List("Liste Commantaire", List.IMPLICIT);

        fhotel = new Form("Accueil");
        formhotel = new Form("Infos hotel");
        loadingDialoghotel = new Form("Patientez");
        mail = new Form("envoyer un mail");
        f1 = new Form("Poster un commantaire");
        f2 = new Form("Welcome");
        f3 = new Form("Erreur");

        tMail = new TextField("Mail", null, 50, TextField.ANY);
        tPWD = new TextField("mot de passe", null, 50, TextField.PASSWORD);
        tSujet = new TextField("Sujet", null, 50, TextField.ANY);
        tMessage = new TextField("Message", null, 50, TextField.ANY);
        try {
            if (UserHandler.currentUser.equals(null)) {
                tfUser = new TextField("utilisateur", null, 100, TextField.ANY);
            } else {
                tfUser = new TextField("Utilisateur", UserHandler.currentUser.getUsername(), 100, TextField.ANY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tfCom = new TextField("commantaire", null, 200, TextField.ANY);

        alertb = new Alert("Commantaire", "Votre commantaire est supprimÃ©", null, AlertType.ERROR);
        alert = new Alert("Mail", "Votre mail a ete envoyer", null, AlertType.CONFIRMATION);

        lsthotel.addCommand(cmdBackhotel);
        lsthotel.setCommandListener(this);

        formhotel.addCommand(cmdBackhotel);
        formhotel.addCommand(cmdMaphotel);
        formhotel.addCommand(cmdCom);
        formhotel.addCommand(cmdAffCom);
        formhotel.setCommandListener(this);

        f1.append(tfCom);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);

        lstCom.addCommand(cmdSupp);
        lstCom.setCommandListener(this);

        mail.append(tMail);
        mail.append(tPWD);
        mail.append(tSujet);
        mail.append(tMessage);
        mail.addCommand(EnvoiMail);
        mail.setCommandListener(this);

        /**
         * * Hotels **
         */
        /**
         * * RSS **
         */
        rssItem = new Vector();

        m_backCommand = new Command("Retour", Command.SCREEN, 1);

        rssList = new List("Flux Rss", List.IMPLICIT);
        rssList.addCommand(m_backCommand);

        rssList.setCommandListener(this);

        /**
         * * RSS **
         */
    }

    public void startApp() {
        lstcompagnies.addCommand(cmdBackcompagnies);
        lstaeroports.addCommand(cmdBackaeroports);
        ffac.append("choisir aeroports ou compagnies");
        ffac.addCommand(cmdC);
//        ff.setCommandListener(this);
        ffac.addCommand(cmdA);
        ffac.setCommandListener(this);
        f.append("Cliquez compagnie pour avoir la liste des compagnies");
//        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lstcompagnies.setCommandListener(this);
        formcompagnie.addCommand(cmdBackcompagnies);

        formcompagnie.setCommandListener(this);
        /////
        f1.append("Cliquez aeroports pour avoir la liste des aeroports");
//        f1.addCommand(cmdParse);
        f1.setCommandListener(this);
        lstaeroports.setCommandListener(this);
        formaeroports.addCommand(cmdBackcompagnies);
        formaeroports.setCommandListener(this);
        disp = Display.getDisplay(this);
        disp.setCurrent(mainList);

        try {
            disp.setCurrent(new SplashScreen(this, mainList));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        System.out.println(UserHandler.currentUser.toString());

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void run() {

    }

    public void commandAction(Command c, Displayable d) {

        if(c == cmdExplorer){
            disp.setCurrent(mainList);
        }
        if (d == mainList) {
            if (c == cmdExit) {
                destroyApp(true);
                notifyDestroyed();
            }

            if (c == List.SELECT_COMMAND || c == cmdExplorer) {
                int index = mainList.getSelectedIndex();
                if (mainList.getString(index).equals("Gestion des Guides")) {

                    showGuides();

                } else if (mainList.getString(index).equals("Gestion des Expériences")) {

                    showExperiences();

                } else if (mainList.getString(index).equals("Gestion des Hôtels")) {

                    showAllhotels();

                } else if (mainList.getString(index).equals("Gestion des Restaurants")) {

                    showAllResto();
                } else if (mainList.getString(index).equals("Gestion des Aeroports & Compagnies")) {
                    disp.setCurrent(ffac);
                }
            }

        }

        /**
         * * RSS **
         */
        if (c == Rss) {

            url = "http://localhost/travelSmart_J2ME/Experience/experiences.xml";

            ParseThread myThread = new ParseThread(this);
            //this will start the second thread
            myThread.getXMLFeed(url);
        }

        if (c == m_backCommand && d == rssList) {
            disp.setCurrent(mainList);
        }

        /**
         * * RSS **
         */
        /**
         * * Guides **
         */
        if (d == listeTtList_Guide) {
            if (c == cmdBack_Guide) {
                disp.setCurrent(mainList);
            }

            if (c == cmdMesGuides_Guide) {

                showMesGuides();
                disp.setCurrent(mesGuidesList);
            }

            if (c == List.SELECT_COMMAND || c == cmdAfficher_Guide) {
                afficheGuideForm.deleteAll();
                afficheGuideForm.append("Informations Guide: \n");
                afficheGuideForm.append(showGuide(listeTtList_Guide.getSelectedIndex()));
                disp.setCurrent(afficheGuideForm);
            }

        }
        if (d == afficheGuideForm) {
            if (c == cmdBack_Guide) {

                Thread th = new Thread(new Runnable() {

                    public void run() {
                        showGuides();
                    }
                });
                th.start();

            }

        }

        if (d == mesGuidesList) {
            if (c == cmdBack_Guide) {

                Thread th = new Thread(new Runnable() {

                    public void run() {
                        showGuides();
                    }
                });
                th.start();

            }
            if (c == List.SELECT_COMMAND || c == cmdAfficher_Guide) {
                afficheGuideForm.deleteAll();
                afficheGuideForm.append("Informations Guide: \n");
                afficheGuideForm.append(showMyGuide(listeTtList_Guide.getSelectedIndex()));
                disp.setCurrent(afficheGuideForm);
            }
            if (c == cmdSupprimer_Guide) {

                supprimerGuide(mesGuidesList.getSelectedIndex());
                mesGuidesList.deleteAll();
                showMesGuides();
                disp.setCurrent(mesGuidesList);
            }

            if (c == cmdAjouter_Guide) {
                disp.setCurrent(ajoutGuideForm);
            }

            if (c == cmdModifier_Guide) {

                idGuide_modif = chargerGuide(mesGuidesList.getSelectedIndex());
                disp.setCurrent(modifGuideForm);
            }
        }
        if (d == ajoutGuideForm) {
            if (c == cmdBack_Guide) {
                showMesGuides();
                disp.setCurrent(mesGuidesList);
            }
            if (c == cmdValider_Guide) {
                Thread th = new Thread(new Runnable() {

                    public void run() {
                        try {
                            ajouterGuide();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                th.start();

            }
        }
        if (d == modifGuideForm) {
            if (c == cmdBack_Guide) {
                showMesGuides();
                disp.setCurrent(mesGuidesList);
            }
            if (c == cmdValider_Guide) {
                try {

                    updateGuide(idGuide_modif);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }

        /**
         * * Guides **
         */
        /**
         * * Experiences **
         */
        if (d == lstExp) {
            if (c == List.SELECT_COMMAND) {
                formExp.deleteAll();
                formExp.append("Informations Experience: \n");
                formExp.append(showExperience(lstExp.getSelectedIndex()));
                disp.setCurrent(formExp);
            }
            if (c == cmdBackExp) {
                lstExp.deleteAll();
                disp.setCurrent(mainList);
            }
        }

        if (c == cmdBackExp && d == formExp) {
            formExp.deleteAll();
            disp.setCurrent(lstExp);
        }

        if (c == cmdNewExp) {
            disp.setCurrent(form1Exp);
        }

        if (c == cmdBackExp && d == form1Exp) {
            form1Exp.deleteAll();
            disp.setCurrent(lstExp);
        }

        if (c == cmdValiderExp) {
            Thread th = new Thread(new Runnable() {

                public void run() {
                    addExperience();
                }
            });
            th.start();
        }

        if (c == cmdDleteExp) {
            Thread th = new Thread(new Runnable() {

                public void run() {
                    deleteExperience();
                }
            });
            th.start();
        }
        if (c == cmdUpdExp) {

            idExpModif = getExperince(lstExp.getSelectedIndex());
            disp.setCurrent(modifFormExp);
        }

        if (d == modifFormExp) {
            if (c == cmdBackExp) {
                showExperiences();
                disp.setCurrent(lstExp);
            }
            if (c == cmdValiderExp) {
                updateExperience(idExpModif);

            }
        }

        /**
         * * Experiences **
         */
        /**
         * * Restaurants **
         */
        if (d == lstResto) {
            try {
            if (c == List.SELECT_COMMAND) {
                
                    formResto.append("Informations Restaurant: \n");

                    formResto.append(showrestaurants(lstResto.getSelectedIndex()));

                } 
            disp.setCurrent(formResto);
            }catch (IOException ex) {
                    ex.printStackTrace();
                }

                
            

            if (c == cmdBackResto) {
                lstResto.deleteAll();
                disp.setCurrent(mainList);
            }

        }

        if (c == cmdBackResto && d == formResto) {
            formResto.deleteAll();
            disp.setCurrent(lstResto);
        }

        if (c == cmdMapResto) {
            formResto.deleteAll();
            disp.setCurrent(new GoogleMapsMoveCanvas(this, d, restaurants[lstResto.getSelectedIndex()].getLatitude(), restaurants[lstResto.getSelectedIndex()].getLongitude()));
        }
        if (c == cmdComResto) {
            formResto.deleteAll();
            disp.setCurrent(f1Resto);
        }
        /////valider--------------------ajout Commantaire
        if (c == cmdValiderResto) {
            Thread th = new Thread(new Runnable() {
                public void run() {
                    url = "http://localhost/travelSmart_J2ME/Restaurant/ajout.php";
                    try {

                        hc = (HttpConnection) Connector.open(url + "?comment=" + tfComResto.getString().trim() + "&user=" + UserHandler.currentUser.getIdUser());
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sb.append((char) ch);
                        }
                        if ("OK".equals(sb.toString().trim())) {
                            showCommantairesResto();
                        } else {
                            disp.setCurrent(f3Resto);
                        }
                        sb = new StringBuffer();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });
            th.start();
        }

        if (c == cmdSuppResto) {
            Thread th = new Thread(new Runnable() {

                public void run() {
                    id = getId(lstComResto.getSelectedIndex());
                    url = "http://localhost/travelSmart_J2ME/Restaurant/supprimer.php";
                    try {
                        hc = (HttpConnection) Connector.open(url + "?id=" + id);
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sb.append((char) ch);
                        }
                        if ("OK".equals(sb.toString().trim())) {
                            lstComResto.deleteAll();
                            showCommantairesResto();
                        } else {
                            f3Resto.append("Erreur de suppression");
                            disp.setCurrent(f3Resto);
                        }
                        sb = new StringBuffer();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            th.start();
        }
        //--------------------------------------affichage Commantaire
        if (c == cmdAffComResto) {

            Thread thx = new Thread(new Runnable() {
                public void run() {
                    showCommantairesResto();
                }
            });
            thx.start();
        }
        /**
         * * Restaurants **
         */

        /**
         * * Hotels **
         */
        if (c == cmdmail) {
            disp.setCurrent(mail);

        }

        if (d == lsthotel) {
            try {
                if (c == List.SELECT_COMMAND) {
                    formhotel.append("Informations Hotel: \n");

                    formhotel.append(showHotel(lsthotel.getSelectedIndex()));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            disp.setCurrent(formhotel);

            if (c == cmdBackhotel) {
                lsthotel.deleteAll();
                disp.setCurrent(mainList);
            }

        }

        if (c == cmdBackhotel && d == formhotel) {
            formhotel.deleteAll();
            disp.setCurrent(lsthotel);
        }

        if (c == cmdMaphotel) {
            formhotel.deleteAll();
            disp.setCurrent(new GoogleMapsMoveCanvas(this, d, hotel[lsthotel.getSelectedIndex()].getLatitude(), hotel[lsthotel.getSelectedIndex()].getLongitude()));
            System.out.println("lag : " + h.getLongitude() + "lat : " + h.getLatitude());
        }
        if (c == cmdCom) {
            formhotel.deleteAll();
            disp.setCurrent(f1);
        }
        /////valider--------------------ajout Commantaire
        if (c == cmdValider) {
            Thread th = new Thread(new Runnable() {
                public void run() {
                    url = "http://localhost/travelSmart_J2ME/Hotel/ajout.php";
                    try {
                        hc = (HttpConnection) Connector.open(url + "?comment=" + tfCom.getString().trim() + "&user=" + UserHandler.currentUser.getIdUser());
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sb.append((char) ch);
                        }
                        if ("OK".equals(sb.toString().trim())) {
                            //disp.setCurrent(lstCom);
                            showCommantaires();
                        } else {
                            disp.setCurrent(f3);
                        }
                        sb = new StringBuffer();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });
            th.start();
        }

        if (c == cmdSupp) {
            Thread th = new Thread(new Runnable() {

                public void run() {
                    id = getIdCommentaire(lstCom.getSelectedIndex());
                    url = "http://localhost/travelSmart_J2ME/Hotel/supprimer.php";
                    try {
                        hc = (HttpConnection) Connector.open(url + "?id=" + id);
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sb.append((char) ch);
                        }
                        if ("OK".equals(sb.toString().trim())) {

                            disp.setCurrent(alertb, lstCom);

                            //alertb.setTimeout(Alert.FOREVER);
                            lstCom.deleteAll();
                            showCommantaires();

                        } else {
                            disp.setCurrent(f3);

                        }
                        sb = new StringBuffer();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            th.start();
        }
        //--------------------------------------affichage Commantaire
        if (c == cmdAffCom) {
            Thread th1 = new Thread(new Runnable() {
                public void run() {
                    try {

                        // ajout des composants au formulaire
                        // this will handle our XML
                        commantairHandler commantairHandler1 = new commantairHandler();
                        // get a parser object
                        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                        // get an InputStream from somewhere (could be HttpConnection, for example)
                        HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Hotel/getXmlCommantaire_Attributes.php");
                        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                        parser.parse(dis, commantairHandler1);
                        // display the result
                        commant = commantairHandler1.getCommantaire();

                        if (commant.length > 0) {
                            for (int i = 0; i < commant.length; i++) {
                                lstCom.append(commant[i].getComment() + " ", null);
                            }
                        }

                    } catch (ParserConfigurationException e) {
                        System.out.println("Exception:" + e.toString());

                    } catch (SAXException e) {
                        System.out.println("Exception:" + e.toString());
                    } catch (IOException e) {
                        System.out.println("Exception:" + e.toString());
                    }
                    disp.setCurrent(lstCom);

                }
            });
            th1.start();
        }
        if (c == EnvoiMail) {

            try {
                sendMail();
                alert = new Alert("mail envoyé avec succes", null, null, null);
                alert.setTimeout(Alert.FOREVER);
                alert.setType(AlertType.INFO);
                disp.setCurrent(alert, mainList);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        /**
         * * Hotels **
         */
        // Aer & Comp
        if (c == cmdA) {
//            disp.setCurrent(loadingDialog);
            Thread th1 = new Thread(new Runnable() {

                public void run() {
                    lstaeroports.deleteAll();
                    try {
            // ajout des composants au formulaire

                        // this will handle our XML
                        aeroportHandler aeroportHandler1 = new aeroportHandler();
                        // get a parser object
                        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                        // get an InputStream from somewhere (could be HttpConnection, for example)
                        HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Aer_Comp/getXmlPersons_Attributes1.php");
                        DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                        parser.parse(dis, aeroportHandler1);
                        // display the result
                        aeroports = aeroportHandler1.getaeroports();

                        if (aeroports.length > 0) {
                            for (int i = 0; i < aeroports.length; i++) {
                                lstaeroports.append(aeroports[i].getNomaeroport() + " ", null);
                                Image img = Image.createImage(
                                        (disp.isColor()) ? aeroports[i].getChemin() : aeroports[i].getChemin());
                                formaeroports.append(new ImageItem(null, img,
                                        ImageItem.LAYOUT_CENTER, null));
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Exception:" + e.toString());

                    }
                    disp.setCurrent(lstaeroports);

                }
            });
            th1.start();
        }
        if (c == cmdC) {
//            disp.setCurrent(loadingDialog);
            Thread th = new Thread(new Runnable() {

                public void run() {

                    showCompagnies();
                }
            });
            th.start();
        }

        /*  if (c == cmdParse)
         {
         disp.setCurrent(loadingDialog);
         Thread th = new Thread(this);
         th.start();
         }*/
        if (d == lstcompagnies) {
            if (c == List.SELECT_COMMAND) {
                formcompagnie.append("Informations compagnie: \n");
                formcompagnie.append(showcompagnies(lstcompagnies.getSelectedIndex()));

                disp.setCurrent(formcompagnie);
            }

            if (c == cmdBackcompagnies) {
                formcompagnie.deleteAll();
                disp.setCurrent(ffac);
            }
        }
        /////
       /* if (c == cmdParse1)
         {
         disp.setCurrent(loadingDialog);
         Thread th = new Thread(this);
         th.start();
         }*/
        if (d == lstaeroports) {
            if (c == List.SELECT_COMMAND) {
                formaeroports.append("Informations aeroports: \n");
                formaeroports.append(showaeroports(lstaeroports.getSelectedIndex()));
                System.out.println("yegfjbv: " + lstaeroports.getSelectedIndex());

                disp.setCurrent(formaeroports);
            }

            if (c == cmdBackaeroports) {
                formaeroports.deleteAll();
                disp.setCurrent(ffac);
            }
        }
        if (d == formaeroports) {
            if (c == cmdBackcompagnies) {
                formaeroports.deleteAll();
                disp.setCurrent(lstaeroports);
            }
        }
        if (d == formcompagnie) {
            if (c == cmdBackcompagnies) {
                formcompagnie.deleteAll();
                disp.setCurrent(lstcompagnies);
            }
        }

    }

    private String showGuide(int i) {
        String res = "";
        if (guides.length > 0) {
            sb.append("Titre: ");
            sb.append(guides[i].getTitre());
            sb.append("\n");
            sb.append("Destination 1: \n");
            sb.append(guides[i].getDestination1());
            sb.append("\nDescription: \n");
            sb.append(guides[i].getDesc1());
            sb.append("\n");
            sb.append("Destination 2: \n");
            sb.append(guides[i].getDestination2());
            sb.append("\n");
            sb.append("Description: \n");
            sb.append(guides[i].getDesc2());
            sb.append("\n");
            sb.append("Destination 3: \n");
            sb.append(guides[i].getDestination3());
            sb.append("\nDescription: \n");
            sb.append(guides[i].getDesc3());
            sb.append("\n");
            sb.append("Destination 4: \n");
            sb.append(guides[i].getDestination4());
            sb.append("\nDescription: \n");
            sb.append(guides[i].getDesc4());
            sb.append("\n");
            sb.append("Destination 5: \n");
            sb.append(guides[i].getDestination5());
            sb.append("\nDescription: \n");
            sb.append(guides[i].getDesc5());
            sb.append("\n");
            sb.append("Chemin: ");
            sb.append(guides[i].getChemin());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private void showGuides() {
        try {
            listeTtList_Guide.deleteAll();
            // this will handle our XML
            GuideHandler guidesHandler = new GuideHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Guide/getXmlGuides_Characters.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, guidesHandler);
            // display the result
            guides = guidesHandler.getGuide();

            if (guides.length > 0) {
                for (int i = 0; i < guides.length; i++) {
                    listeTtList_Guide.append(guides[i].getTitre(), null);

                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Exception:" + e.toString());
        } catch (SAXException e) {
            System.out.println("Exception:" + e.toString());
        } catch (IOException e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(listeTtList_Guide);
    }

    private String showMyGuide(int i) {
        String res = "";
        if (mesGuides.length > 0) {
            sb.append("Titre: ");
            sb.append(mesGuides[i].getTitre());
            sb.append("\n");
            sb.append("Destination 1: \n");
            sb.append(mesGuides[i].getDestination1());
            sb.append("\nDescription: \n");
            sb.append(mesGuides[i].getDesc1());
            sb.append("\n");
            sb.append("Destination 2: \n");
            sb.append(mesGuides[i].getDestination2());
            sb.append("\n");
            sb.append("Description: \n");
            sb.append(mesGuides[i].getDesc2());
            sb.append("\n");
            sb.append("Destination 3: \n");
            sb.append(mesGuides[i].getDestination3());
            sb.append("\nDescription: \n");
            sb.append(mesGuides[i].getDesc3());
            sb.append("\n");
            sb.append("Destination 4: \n");
            sb.append(mesGuides[i].getDestination4());
            sb.append("\nDescription: \n");
            sb.append(mesGuides[i].getDesc4());
            sb.append("\n");
            sb.append("Destination 5: \n");
            sb.append(mesGuides[i].getDestination5());
            sb.append("\nDescription: \n");
            sb.append(mesGuides[i].getDesc5());
            sb.append("\n");
            sb.append("Chemin: ");
            sb.append(mesGuides[i].getChemin());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public void showMesGuides() {
        try {

            mesGuidesList.deleteAll();
            // this will handle our XML
            GuideHandler guidesHandler = new GuideHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Guide/getXmlMesGuides.php?idUser=" + Integer.toString(UserHandler.currentUser.getIdUser()));
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());

            parser.parse(dis, guidesHandler);
            // display the result
            mesGuides = guidesHandler.getGuide();

            if (mesGuides.length > 0) {
                for (int i = 0; i < mesGuides.length; i++) {
                    mesGuidesList.append(mesGuides[i].getTitre(), null);

                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Exception:" + e.toString());
        } catch (SAXException e) {
            System.out.println("Exception:" + e.toString());
        } catch (IOException e) {
            System.out.println("Exception:" + e.toString());
        }

    }

    private void supprimerGuide(int i) {
        String url = "http://localhost/travelSmart_J2ME/Guide/supprimer_Guide.php";
        HttpConnection hc;
        DataInputStream dis;
        StringBuffer sb = new StringBuffer();
        int ch;
        try {
            hc = (HttpConnection) Connector.open(url + "?id=" + guides[i].getId());
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                disp.setCurrent(mesGuidesList);
            } else {
                disp.setCurrent(ajoutGuideForm);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void ajouterGuide() throws InterruptedException {

        try {
            String url = "http://localhost/travelSmart_J2ME/Guide/ajout_Guide.php";
            HttpConnection hc;
            DataInputStream dis;
            StringBuffer sb = new StringBuffer();
            int ch;

            hc = (HttpConnection) Connector.open(url + "?titre=" + tfTitre_Guide.getString().trim()
                    + "&destination1=" + tfDest1_Guide.getString().trim().replace(' ', '+')
                    + "&desc1=" + tfDesc1_Guide.getString().trim().replace(' ', '+')
                    + "&destination2=" + tfDest2_Guide.getString().trim().replace(' ', '+')
                    + "&desc2=" + tfDesc2_Guide.getString().trim().replace(' ', '+')
                    + "&destination3=" + tfDest3_Guide.getString().trim().replace(' ', '+')
                    + "&desc3=" + tfDesc3_Guide.getString().trim().replace(' ', '+')
                    + "&destination4=" + tfDest4_Guide.getString().trim().replace(' ', '+')
                    + "&desc4=" + tfDesc4_Guide.getString().trim().replace(' ', '+')
                    + "&destination5=" + tfDest5_Guide.getString().trim().replace(' ', '+')
                    + "&desc5=" + tfDesc5_Guide.getString().trim().replace(' ', '+')
                    + "&chemin=" + tfChemin_Guide.getString().trim().replace(' ', '+')
                    + "&id=" + Integer.toString(UserHandler.currentUser.getIdUser()));
            System.out.println("aaaa: " + url + "?titre=" + tfTitre_Guide.getString().trim()
                    + "&destination1=" + tfDest1_Guide.getString().trim().replace(' ', '+')
                    + "&desc1=" + tfDesc1_Guide.getString().trim().replace(' ', '+')
                    + "&destination2=" + tfDest2_Guide.getString().trim().replace(' ', '+')
                    + "&desc2=" + tfDesc2_Guide.getString().trim().replace(' ', '+')
                    + "&destination3=" + tfDest3_Guide.getString().trim().replace(' ', '+')
                    + "&desc3=" + tfDesc3_Guide.getString().trim().replace(' ', '+')
                    + "&destination4=" + tfDest4_Guide.getString().trim().replace(' ', '+')
                    + "&desc4=" + tfDesc4_Guide.getString().trim().replace(' ', '+')
                    + "&destination5=" + tfDest5_Guide.getString().trim().replace(' ', '+')
                    + "&desc5=" + tfDesc5_Guide.getString().trim().replace(' ', '+')
                    + "&chemin=" + tfChemin_Guide.getString().trim().replace(' ', '+')
                    + "&id=" + Integer.toString(UserHandler.currentUser.getIdUser()));
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                System.out.println("fazf");
                SendSMS s = new SendSMS(disp, "Le Guide a été créé ! Consulter la liste des Guides pour plus de détails.", "876543299");
                s.start();
                mesGuidesList.deleteAll();
                showMesGuides();
            } else {
                disp.setCurrent(new Alert("Création Guide", "Erreur Création Guide.", null, AlertType.ERROR));
                mesGuidesList.deleteAll();
                showMesGuides();
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int chargerGuide(int i) {

        tfTitre_modif_Guide.setString(guides[i].getTitre());
        tfDest1_modif_Guide.setString(guides[i].getDestination1());
        tfDesc1_modif_Guide.setString(guides[i].getDesc1());
        tfDest2_modif_Guide.setString(guides[i].getDestination2());
        tfDesc2_modif_Guide.setString(guides[i].getDesc2());
        tfDest3_modif_Guide.setString(guides[i].getDestination3());
        tfDesc3_modif_Guide.setString(guides[i].getDesc3());
        tfDest4_modif_Guide.setString(guides[i].getDestination4());
        tfDesc4_modif_Guide.setString(guides[i].getDesc4());
        tfDest5_modif_Guide.setString(guides[i].getDestination5());
        tfDesc5_modif_Guide.setString(guides[i].getDesc5());
        tfChemin_modif_Guide.setString(guides[i].getChemin());

        return guides[i].getId();
    }

    private void updateGuide(int id) throws InterruptedException {
        try {
            String url = "http://localhost/travelSmart_J2ME/Guide/modifier_Guide.php";
            HttpConnection hc;
            DataInputStream dis;
            StringBuffer sb = new StringBuffer();
            int ch;

            hc = (HttpConnection) Connector.open(url + "?titre=" + tfTitre_modif_Guide.getString().trim()
                    + "&destination1=" + tfDest1_modif_Guide.getString().trim().replace(' ', '+')
                    + "&desc1=" + tfDesc1_modif_Guide.getString().trim().replace(' ', '+')
                    + "&destination2=" + tfDest2_modif_Guide.getString().trim().replace(' ', '+')
                    + "&desc2=" + tfDesc2_modif_Guide.getString().trim().replace(' ', '+')
                    + "&destination3=" + tfDest3_modif_Guide.getString().trim().replace(' ', '+')
                    + "&desc3=" + tfDesc3_modif_Guide.getString().trim().replace(' ', '+')
                    + "&destination4=" + tfDest4_modif_Guide.getString().trim().replace(' ', '+')
                    + "&desc4=" + tfDesc4_modif_Guide.getString().trim().replace(' ', '+')
                    + "&destination5=" + tfDest5_modif_Guide.getString().trim().replace(' ', '+')
                    + "&desc5=" + tfDesc5_modif_Guide.getString().trim().replace(' ', '+')
                    + "&chemin=" + tfChemin_modif_Guide.getString().trim().replace(' ', '+')
                    + "&id=" + Integer.toString(id));
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {

                SendSMS s = new SendSMS(disp, "Le Guide a été modifié ! Consulter la liste des Guides pour plus de détails.", "876543299");
                s.start();
                mesGuidesList.deleteAll();
                showMesGuides();
            } else {
                mesGuidesList.deleteAll();
                disp.setCurrent(new Alert("Modification Guide", "Erreur Modification Guide.", null, AlertType.ERROR));

                showMesGuides();
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addExperience() {
        url = "http://localhost/travelSmart_J2ME/Experience/ajoutExp.php";
        try {
            hc = (HttpConnection) Connector.open(url + "?titre=" + titreExp.getString().trim()
                    + "&dest1=" + dest1Exp.getString().trim() + "&dep1=" + dep1Exp.getString().trim()
                    + "&desc1=" + desc1Exp.getString().trim()
                    + "&dest2=" + dest2Exp.getString().trim() + "&dep2=" + dep2Exp.getString().trim()
                    + "&desc2=" + desc2Exp.getString().trim()
                    + "&dest3=" + dest3Exp.getString().trim() + "&dep3=" + dep3Exp.getString().trim()
                    + "&desc3=" + desc3Exp.getString().trim());
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                lstExp.deleteAll();
                showExperiences();
            } else {
                form3Exp.append("Erreur d'ajout");
                disp.setCurrent(form3Exp);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteExperience() {
        url = "http://localhost/travelSmart_J2ME/Experience/suppressionExperience.php";
        id = getIdExp(lstExp.getSelectedIndex());
        try {
            hc = (HttpConnection) Connector.open(url + "?id=" + id);
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                lstExp.deleteAll();
                showExperiences();
            } else {
                form3Exp.append("Erreur de suppression");
                disp.setCurrent(form3Exp);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateExperience(int id) {
        url = "http://localhost/travelSmart_J2ME/Experience/modificationExp.php";
        try {
            hc = (HttpConnection) Connector.open(url + "?id=" + id + "&titre=" + titreModifExp.getString().trim()
                    + "&dest1=" + dest1ModifExp.getString().trim() + "&dep1=" + dep1ModifExp.getString().trim()
                    + "&desc1=" + desc1ModifExp.getString().trim()
                    + "&dest2=" + dest2ModifExp.getString().trim() + "&dep2=" + dep2ModifExp.getString().trim()
                    + "&desc2=" + desc2ModifExp.getString().trim()
                    + "&dest3=" + dest3ModifExp.getString().trim() + "&dep3=" + dep3ModifExp.getString().trim()
                    + "&desc3=" + desc3ModifExp.getString().trim());
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                lstExp.deleteAll();
                showExperiences();
            } else {
                form3Exp.append("Erreur de modification");
                disp.setCurrent(form3Exp);
            }
            sb = new StringBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showExperiences() {
        url = "http://localhost/travelSmart_J2ME/Experience/getXmlExperiences_Attributes.php";
        try {
            lstExp.deleteAll();
            // this will handle our XML
            ExperienceHandler experiencesHandler = new ExperienceHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open(url);
            dis = new DataInputStream(hc.openDataInputStream());

            parser.parse(dis, experiencesHandler);
            // display the result
            experiences = experiencesHandler.getExperience();

            if (experiences.length > 0) {
                for (int i = 0; i < experiences.length; i++) {
                    lstExp.append(experiences[i].getTitre(), null);
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Exception:" + e.toString());
        } catch (SAXException e) {
            System.out.println("Exception:" + e.toString());
        } catch (IOException e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lstExp);
    }

    private String showExperience(int i) {
        String res = "";
        System.out.println("evev: " + experiences.length);
        if (experiences.length > 0) {
            sb.append("* ");
            sb.append(experiences[i].getTitre());
            sb.append("\n\n");
            sb.append("* Destination 1:");
            sb.append("\n\n");
            sb.append("* ");
            sb.append(experiences[i].getDestination1());
            sb.append("\n");
            sb.append("* ");
            sb.append(experiences[i].getDepense1());
            sb.append("\n");
            sb.append("* ");
            sb.append(experiences[i].getDescription1());
            sb.append("\n\n");
            sb.append("* Destination 2:");
            sb.append("\n\n");
            sb.append("* ");
            sb.append(experiences[i].getDestination2());
            sb.append("\n");
            sb.append("* ");
            sb.append(experiences[i].getDepense2());
            sb.append("\n");
            sb.append("* ");
            sb.append(experiences[i].getDescription2());
            sb.append("\n\n");
            sb.append("* Destination 3:");
            sb.append("\n\n");
            sb.append("* ");
            sb.append(experiences[i].getDestination3());
            sb.append("\n");
            sb.append("* ");
            sb.append(experiences[i].getDepense3());
            sb.append("\n");
            sb.append("* ");
            sb.append(experiences[i].getDescription3());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String getIdExp(int i) {
        String res = "";
        if (experiences.length > 0) {
            sb.append(experiences[i].getId());
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private int getExperince(int i) {
        if (experiences.length > 0) {
            titreModifExp.setString(experiences[i].getTitre());
            dest1ModifExp.setString(experiences[i].getDestination1());
            dep1ModifExp.setString("" + experiences[i].getDepense1());
            desc1ModifExp.setString(experiences[i].getDescription1());
            dest2ModifExp.setString(experiences[i].getDestination2());
            dep2ModifExp.setString("" + experiences[i].getDepense2());
            desc2ModifExp.setString(experiences[i].getDescription2());
            dest3ModifExp.setString(experiences[i].getDestination3());
            dep3ModifExp.setString("" + experiences[i].getDepense3());
            desc3ModifExp.setString(experiences[i].getDescription3());
        }
        return experiences[i].getId();
    }

    private void showAllResto() {
        try {

            // ajout des composants au formulaire
            // this will handle our XML
            restaurantHandler restaurantHandler = new restaurantHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Restaurant/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, restaurantHandler);
            // display the result
            restaurants = restaurantHandler.getRestaurants();

            if (restaurants.length > 0) {
                for (int i = 0; i < restaurants.length; i++) {
                    lstResto.append(restaurants[i].getNomRestaurant(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());

        }
        disp.setCurrent(lstResto);
    }

    private String getId(int i) {
        String res = "";
        if (commantResto.length > 0) {
            sb.append(commantResto[i].getId());
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private void showCommantairesResto() {
        try { // this will handle our XML
            commantairHandler cHandler = new commantairHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Restaurant/getXmlCommantaire_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, cHandler);
            // display the result
            commantResto = cHandler.getCommantaire();

            if (commantResto.length > 0) {
                for (int i = 0; i < commantResto.length; i++) {
                    lstComResto.append(commantResto[i].getComment(), null);
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Exception:" + e.toString());
        } catch (SAXException e) {
            System.out.println("Exception:" + e.toString());
        } catch (IOException e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lstComResto);

    }

    private String showrestaurants(int i) throws IOException {
        String res = "";
        if (restaurants.length > 0) {
            sb.append("Nom : ");
            sb.append(restaurants[i].getNomRestaurant());
            sb.append("\n\n");

            sb.append("Categorie : ");
            sb.append(restaurants[i].getCategorie());
            sb.append("\n\n");

            sb.append("Description : ");
            sb.append(restaurants[i].getDescription());
            sb.append("\n\n");
            Image img = Image.createImage(
                    (disp.isColor()) ? restaurants[i].getChemin() : restaurants[i].getChemin());
            formhotel.append(new ImageItem(null, img,
                    ImageItem.LAYOUT_CENTER, null));
//            Image img = Image.createImage(restaurants[i].getChemin());
//            sb.append(restaurants[i].getChemin());
//            sb.append("\n\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private void showAllhotels() {
        try {

            // ajout des composants au formulaire
            // this will handle our XML
            HotelHandler hotelHandler = new HotelHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Hotel/getXmlHotel_Attributes.php");
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, hotelHandler);
            // display the result
            hotel = hotelHandler.getHotel();

            if (hotel.length > 0) {
                for (int i = 0; i < hotel.length; i++) {
                    lsthotel.append(hotel[i].getNomHotel(), null);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        disp.setCurrent(lsthotel);
        //     disp.setCurrent(new StatCanvas(hotel));
    }

    private String sendMail() throws IOException {

        String urlX = "http://localhost/travelSmart_J2ME/Mail/sendMail.php?pwd=" + tPWD.getString() + "&user=" + tMail.getString() + "&sub=" + tSujet.getString() + "&mes=" + tMessage.getString();
        HttpConnection hm = (HttpConnection) Connector.open(urlX);
        DataInputStream dis = new DataInputStream(hm.openDataInputStream());
        System.out.println("envoyé");
        return null;

    }

    private String showcomm(int i) {

        String res = "";
        if (commant.length > 0) {
            /* sb.append("* ");
             sb.append(assurance[i].getId());
             sb.append("\n");
            
             sb.append("* ");
             sb.append(assurance[i].getImage_id());
             sb.append("\n");
            
             */

            sb.append("Utilisateur : ");
            sb.append(commant[i].getUser());
            sb.append("\n\n");

            sb.append("Commentaire : ");
            sb.append(commant[i].getComment());
            sb.append("\n\n");

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String showHotel(int i) throws IOException {

        String res = "";
        if (hotel.length > 0) {
            sb.append("Nom : ");
            sb.append(hotel[i].getNomHotel());
            sb.append("\n\n");

            sb.append("Categorie : ");
            sb.append(hotel[i].getCategorie());
            sb.append("\n\n");

            sb.append("Description : ");
            sb.append(hotel[i].getDescription());
            sb.append("\n\n");

//            sb.append("* ");
//            sb.append(hotel[i].getChemin());
//            sb.append("\n");
            sb.append("Latitude : ");
            sb.append(hotel[i].getLatitude());
            sb.append("\n\n");
            sb.append("Longitude : ");
            sb.append(hotel[i].getLongitude());
            sb.append("\n\n");

            Image img = Image.createImage(
                    (disp.isColor()) ? hotel[i].getChemin() : hotel[i].getChemin());
            formhotel.append(new ImageItem(null, img,
                    ImageItem.LAYOUT_CENTER, null));
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private void showCommantaires() {
        try { // this will handle our XML
            commantairHandler cHandler = new commantairHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Hotel/getXmlCommantaire_Attributes.php");
            dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, cHandler);
            // display the result
            commantResto = cHandler.getCommantaire();

            if (commantResto.length > 0) {
                for (int i = 0; i < commantResto.length; i++) {
                    lstComResto.append(commantResto[i].getComment(), null);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Exception:" + e.toString());
        } catch (IOException e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lstComResto);

    }

    private String getIdCommentaire(int i) {
        String res = "";
        if (commant.length > 0) {
            sb.append(commant[i].getId());
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public void addItems(RssModel model) {
        rssItem.addElement(rssItem);

        rssList.append(model.getTitle(), model.getImage());

        disp.setCurrent(rssList);
    }

    public void DisplayError(Exception error) {
        Alert errorAlert = new Alert("Error", error.getMessage(), null, null);
        errorAlert.setTimeout(Alert.FOREVER);
        disp.setCurrent(errorAlert, rssList);
    }

    private String showcompagnies(int i) {
        String res = "";
        if (compagnies.length > 0) {
            /* sb.append("* ");
             sb.append(assurance[i].getId());
             sb.append("\n");
            
             sb.append("* ");
             sb.append(assurance[i].getImage_id());
             sb.append("\n");
            
             */
            sb.append("Nom Compagnie : ");
            sb.append(compagnies[i].getNomcompagnie());
            sb.append("\n\n");

            sb.append("Type : ");
            sb.append(compagnies[i].getType());
            sb.append("\n\n");

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String showaeroports(int i) {
        String res = "";
        if (aeroports.length > 0) {
            /* sb.append("* ");
             sb.append(assurance[i].getId());
             sb.append("\n");
            
             sb.append("* ");
             sb.append(assurance[i].getImage_id());
             sb.append("\n");
            
             */
            sb1.append("Nom : ");
            sb1.append(aeroports[i].getNomaeroport());
            sb1.append("\n\n");

            sb1.append("Nombre de Pistes : ");
            sb1.append(aeroports[i].getNbrpiste());
            sb1.append("\n\n");

//            sb1.append("* ");
//            sb1.append(aeroports[i].getChemin());
//            sb1.append("\n\n");
            sb1.append("Adresse : ");
            sb1.append(aeroports[i].getAdresse());
            sb1.append("\n\n");

            sb1.append("Description : ");
            sb1.append(aeroports[i].getDescription());
            sb1.append("\n\n");

        }
        res = sb1.toString();
        sb1 = new StringBuffer("");
        return res;
    }

    private void showCompagnies() {
        lstcompagnies.deleteAll();
        try {
            // ajout des composants au formulaire

            // this will handle our XML
            compagnieHandler compagnieHandler1 = new compagnieHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/travelSmart_J2ME/Aer_Comp/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, compagnieHandler1);
            // display the result
            compagnies = compagnieHandler1.getcompagnies();

            if (compagnies.length > 0) {
                for (int i = 0; i < compagnies.length; i++) {
                    lstcompagnies.append(compagnies[i].getNomcompagnie() + " ", null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());

        }
        disp.setCurrent(lstcompagnies);
    }

}
