/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.LIBS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author reznov
 */
public class FileHandler {
    public String path="";
    public String text="";

    
    public static void saveText(String path, String text){
        try {
            String str = encodeData(text);
            File newTextFile = new File(path);
            if(!newTextFile.exists()){
                FileWriter fw = new FileWriter(newTextFile);
                fw.write(str.toString());
                fw.close();
            }
            else
            {
                if(deleteFile(path)){
                    FileWriter fw = new FileWriter(newTextFile);
                    fw.write(str.toString());
                    fw.close();
                }
            }
            

        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
    
    public static boolean deleteFile(String path){
        if(new File(path).delete())
            return true;
        
        return false;
    }
    
    public static boolean fileExists(String path){
        if(new File(path).exists())
            return true;
        return false;
    }
    
    public static String getText(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                return decodeData(sCurrentLine);
            }

        } catch (IOException e) {
                e.printStackTrace();
        } 
        return "";
    }
    
    public static String encodeData(String data){
      
        final byte[] encBytes = data.getBytes(StandardCharsets.UTF_8);
        
        return Base64.getEncoder().encodeToString(encBytes);
    }
    
    public static String decodeData(String data){
        return new String(Base64.getDecoder().decode(data));
    }
 
    
}
