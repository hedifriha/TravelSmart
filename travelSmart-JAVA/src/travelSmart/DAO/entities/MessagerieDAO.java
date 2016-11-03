/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.DAO.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import travelSmart.CLASSES.entities.Message;
import travelSmart.DAO.interfaces.IMessagerieDAO;
import travelSmart.SETTINGS.MyConnection;

/**
 *
 * @author reznov
 */
public class MessagerieDAO implements IMessagerieDAO{
    private Connection connection;
    public MessagerieDAO() {
        this.connection = MyConnection.getInstance().getConnection();
    }
    
    @Override
    public boolean sendMessage(Message m) {
        String query = "insert into messagerie values (NULL,?,?,?,0)";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps2.setString(1, m.getFrom());
            ps2.setString(2, m.getSubject());
            ps2.setString(3, m.getContent());
            ps2.executeUpdate();
            
            return true;
        }catch (SQLException ex) {
           // Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList<Message> getMessages(int type) {
        ArrayList<Message> ListeMessages = new ArrayList<Message>();
        String query = "select * from messagerie where type=" + type + ";";       
        try {
            PreparedStatement pSt = connection.prepareStatement(query);
            ResultSet rs = pSt.executeQuery(query);

            while (rs.next()) {
                Message m = new Message(rs.getString("de"), rs.getString("subject"), rs.getString("content"), rs.getInt("type"));
                ListeMessages.add(m);
                //return ListeHotel;
            }
        } catch (SQLException ex) {
            System.out.println("base vide!!");
        }
        return ListeMessages;
    }

    @Override
    public int countMessages(int type) {
        String query = "select count(*) as nbrMessages from messagerie where type="+ type +";";
        int nombre = 0;
        try{
            PreparedStatement prep = connection.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                nombre = result.getInt("nbrMessages");
            }
        } catch (SQLException ex) {
            return nombre;
        }
        return nombre;
    }

    @Override
    public void readMessage(String content) {
        String query = "update messagerie set type=1 where content=?";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps2.setString(1, content);
            ps2.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteMessage(String content){
        String query = "delete from messagerie where content=?";
        try{
            PreparedStatement ps2 = connection.prepareStatement(query);
            ps2.setString(1, content);
            ps2.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
