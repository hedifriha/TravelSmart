/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.SETTINGS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reznov
 */
public class MyConnection {
    Properties properties;
    private String url;
    private String login;
    private String password;
    private Connection connection;
    private static MyConnection instance;
    
    private MyConnection(){
        if(connection==null)
        {
            properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("configuration.properties")));
            url = properties.getProperty("url");
            login = properties.getProperty("user");
            password = properties.getProperty("pwd");
            connection = DriverManager.getConnection(url,login, password);
            System.out.println("Connection Established with travelSmart");
        } catch (SQLException | IOException ex){
            //System.out.println("Connection not Established with travelSmart");
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public Connection getConnection() {
        return connection;
    }


    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
}
