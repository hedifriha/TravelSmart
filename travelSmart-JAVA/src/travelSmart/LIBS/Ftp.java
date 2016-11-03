/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.LIBS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javafx.concurrent.Task;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import travelSmart.CLASSES.entities.Media;

/**
 *
 * @author Amri
 */
public class Ftp {

    private final static String FTP_HOST = "31.170.160.81";
    private final static String FTP_USERNAME = "a7815860";
    private final static String FTP_PASSWORD = "H07174069m";
    
    public Ftp() {
    }
    
    
    
    public void uploadFile(ArrayList<Media> liste){
        
        Task<String> task = new Task<String>() {
        @Override
    protected String call() throws Exception {
           
       
            /****************************************/
        FTPClient client = new FTPClient();
        FileInputStream fis = null;
        File file = null;
try {
    client.connect(FTP_HOST);
    client.login(FTP_USERNAME, FTP_PASSWORD);
    client.enterLocalPassiveMode();
    System.out.println("status: " + client.getReplyString());
    //
    // Create an InputStream of the file to be uploaded
    //
    System.out.println("Upload en cours");
    if(client.changeWorkingDirectory("/public_html/media")){
        client.setFileType(FTP.BINARY_FILE_TYPE);
        for (Media media : liste) 
        {
            file = new File(media.getChemin());
            fis = new FileInputStream(file);
        //
        // Store file to server
        //

            client.storeFile(media.getNomFichier().substring(1, media.getNomFichier().length()), fis);
            System.out.println("reply: " + client.getReplyString());
            //if(client.getReplyCode()/100 != 2) System.out.println("hello");
            
        }
        System.out.println("Upload Terminé");
   }
    else{
    System.out.println("Erreur working dir");
    client.logout();
    client.disconnect();
    }
    
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
    try {
        if (fis != null) {
            fis.close();
        }
        client.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
         
            /****************************************/
            //Thread.sleep(5000);
            
        
    return null;
    }
    };
        new Thread(task).start();
    }
    
    
    public static void downloadAll() {
        FTPClient client = new FTPClient();

try {
     
    client.connect(FTP_HOST);
    client.login(FTP_USERNAME, FTP_PASSWORD);
    client.enterLocalPassiveMode();
    
    System.out.println("status: " + client.getStatus());
    
    //
    // Store file to server
    //
    if(client.changeWorkingDirectory("/public_html/media")){
        
    System.out.println("reply: " + client.getReplyString());
    client.setFileType(FTP.BINARY_FILE_TYPE);
    //get list of filenames
            FTPFile[] ftpFiles = client.listFiles();  
            
            if (ftpFiles != null && ftpFiles.length > 0) {
                //loop thru files
                for (FTPFile file : ftpFiles) {
                    if (!file.isFile()) {
                        continue;
                    }
                    
                    System.out.println("File is " + file.getName());
                    
                    
                    //get output stream
                    OutputStream output;
                    output = new FileOutputStream(Media.CURRENT_DIR + "/" +file.getName());
                    //get the file from the remote system
                    client.retrieveFile(file.getName(), output);
                    System.out.println("downloading: " + client.getReplyString());
                    //close output stream
                    output.close();
                    
                
    }
    }
    }
    else{
        System.out.println("Erreur working dir");
         
            }
    client.logout();
    client.disconnect();
    }
    
 catch (IOException e) {
    e.printStackTrace();
} finally {
    try {
        client.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }
}
