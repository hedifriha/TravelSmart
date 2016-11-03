/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelSmart.CLASSES.entities;

/**
 *
 * @author reznov
 */
public class Message {
    private String from;
    private String subject;
    private String content;
    private int type;

    public Message(String from, String subject, String content, int type) {
        this.from = from;
        this.subject = subject;
        this.content = content;
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message{" + "from=" + from + ", subject=" + subject + ", content=" + content + ", type=" + type + '}';
    }
    
    
    
    
}
