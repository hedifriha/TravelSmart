/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;



import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import travelSmart.CLASSES.entities.Email;
import travelSmart.CLASSES.entities.User;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author Wassila
 */
public class EmailDAO {

    //partie recoveryEmail
    private Component User_Connexion;
    private Connection connection;
    
public EmailDAO()
{
  this.connection = MyConnection.getInstance().getConnection();
    
}
    
    public void SendRecoveryEmail(User user, String Content ) throws Exception {

        Email email = new Email();
        String mail = user.getEmail();
        //Création  de la session
        Session session = Session.getInstance(email.getProps(),
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email.getUsername(), email.getPassword());
                    }
                });
        //Création du message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getUsername()));

        InternetAddress toAddress = new InternetAddress(user.getEmail());

        message.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);

        message.setSubject("Votre Identifiant sur TravelSmart");
        
        message.setText("Bonjour,\n "+"Vous avez recemment tenté de vous connecter sur TravelSmart\n"+
                "Malheureusement vous avez oublié votre mot de passe/nom d'utilisateur. \n"+
                "Nous sommes donc heureux de pouvoir vous adresser les informations qui vont vous permettre d'avoir à nouveau accès à votre compte\n\n"+Content);

        //Envoi du message
        Transport transport = session.getTransport("smtp");

        transport.connect(email.getHost(), email.getUsername(), email.getPassword());

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        
        

    }
    

    public void UserRecoveryPassword(User user) {
        
        String requete = "SELECT password FROM login where username = '" + user.getEmail()+ "';";
       
 try {
            try (PreparedStatement ps = connection.prepareStatement(requete)) {
                ResultSet res = ps.executeQuery(requete);
                
                if (res.next()) {
                    
                    try {
                        this.SendRecoveryEmail(user, res.getString(1));
                    } catch (Exception ex) {
                        Logger.getLogger("UserLostPwd").log(Level.SEVERE, null, ex);//UserLostPwd.class.getName()
                        System.out.println("Erreur lors de Connexion_Vérifiez votre @Mail " + user.getEmail() + " " + ex.getMessage());
                    }
                    
                    System.out.println("Mail recovery sent to : " + res.getString(1));
                    
                }
                //else { System.out.println("erreur requete");}
            
            }catch(SQLException ex){}
        } catch (Exception ex) {
            
            System.out.println("Erreur lors de Connexion_Vérifiez votre @Mail" + ex.getMessage());
        }

    }
    
    public void UserRecoveryUserName(User user) {
       
        System.out.println("id: " + user.getIdUser());
        String requete = "SELECT username FROM login where idUser = " + user.getIdUser()+ ";";

        try {
            try (PreparedStatement ps = connection.prepareStatement(requete)) {
                ResultSet res = ps.executeQuery(requete);
                
                if (res.next()) {
                    //user.setPassword(res.getString(1));
                    //SendRecoveryEmail sre = new SendRecoveryEmail();
                    System.out.println(res.getString(1));
                    try {
                        this.SendRecoveryEmail(user, res.getString(1));
                    } catch (Exception ex) {
                        Logger.getLogger("UserNameLost").log(Level.SEVERE, null, ex);
                        System.out.println("Erreur lors de Connexion_Vérifiez votre @Mail " + user.getEmail() + " " + ex.getMessage());
                    }
                    
                    System.out.println("Mail recovery sent to : " + res.getString(1));
                    
                }
                //else { System.out.println("erreur requete");}
            }
        } catch (SQLException ex) {
            
            System.out.println("Erreur lors de Connexion_Vérifiez votre @Mail" + ex.getMessage());
        }

    }
    
    
    
    //Mail Confirmation d'inscription
     public void SendConfirmationEmail(User user) throws Exception {

        Email email = new Email();
        String mail = user.getEmail();
        Session session = Session.getInstance(email.getProps(),
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email.getUsername(), email.getPassword());
                    }
                });
      
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getUsername()));
        InternetAddress toAddress = new InternetAddress(user.getEmail());

        message.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);

        message.setSubject("Bienvenue sur TravelSmart");
        
        message.setText("Bonjour,\n\n"+"Merci pour votre inscription sur travelSmart. Votre compte est créé et doit être activé avant que vous  puissiez l'utiliser.\nCordialement.");

        Transport transport = session.getTransport("smtp");

        transport.connect(email.getHost(), email.getUsername(), email.getPassword());

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }
       
    
    public void SendNewsletter() throws Exception {

        Email email = new Email();

        

        NewsletterDAO newsDAO = new NewsletterDAO();
        ArrayList<Object> list = new ArrayList<Object>();

        //list = newsDAO.GetNewsletter();
        ArrayList<String> listInscriptions = newsDAO.getListeInscriptions();
        /*for (int i = 1; i < list.size(); i++) {
            Content = list.get(i).toString() + "\n";
        }*/
        System.out.println(listInscriptions.toString());
        //Création de la session
        Session session = Session.getInstance(email.getProps(),
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email.getUsername(), email.getPassword());
                    }
                });
        //Création du message 
        for (int i = 0; i < listInscriptions.size(); i++) {
            String Content = "\nVisitez notre futur site!";
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getUsername()));

            InternetAddress toAddress = new InternetAddress(listInscriptions.get(i));

            message.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);

            message.setSubject("Quoi de neuf sur TravelSmart ?!!");

            message.setText("Bonjour  \n"
                    + "L'equipe TravelSmart est heureuse de pouvoir vous informer des nouveautés sur TravelSmart \n\n" + Content);
            //Envoi du message 
            Transport transport = session.getTransport("smtp");

            transport.connect(email.getHost(), email.getUsername(), email.getPassword());

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }

    }
}
