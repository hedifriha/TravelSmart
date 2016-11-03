/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.CLASSES.entities;

import java.util.Properties;

/**
 *
 * @author Wassila
 */
public class Email {

    final String username = "travelSmart2015@gmail.com";
    final String password = "travel2015";
    Properties props = System.getProperties();
    String host = "smtp.gmail.com";
  /*  
    //mail static
    private String mailAddressSender ;
    private String pwd ;
    private String mailAddressRecipient ; 
    private String mailObject ;
    private String mailSubject ;

    public String getMailAddressSender() {
        return mailAddressSender;
    }

    public void setMailAddressSender(String mailAddressSender) {
        this.mailAddressSender = mailAddressSender;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMailAddressRecipient() {
        return mailAddressRecipient;
    }

    public void setMailAddressRecipient(String mailAddressRecipient) {
        this.mailAddressRecipient = mailAddressRecipient;
    }

    public String getMailObject() {
        return mailObject;
    }

    public void setMailObject(String mailObject) {
        this.mailObject = mailObject;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }
   
*/
   //mail recovery

    public Email(){
        
        
        props.put("mail.smtp.starttls.enable","true");
        /* mail.smtp.ssl.trust is needed in script to avoid error "Could not convert socket to TLS"  */
        props.setProperty("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", "true");      
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
    
        System.out.println(props.getProperty("mail.smtp.password"));
    }

    public Properties getProps() {
        return props;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }
    

}
