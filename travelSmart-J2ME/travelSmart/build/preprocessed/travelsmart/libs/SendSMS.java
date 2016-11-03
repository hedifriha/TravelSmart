/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.libs;

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author Amri
 */
public class SendSMS extends Thread {

private String receiver;
private String receivedMsg;
private Display disp;
private boolean bool = false;
private boolean notsent;

public SendSMS(Display disp, String msg, String number) {
    this.disp = disp;
    this.receiver = number;
    this.receivedMsg = msg;
}

public void run() {
    while (!bool) {
        SendMessage();
    }
}

/**
 * Send the mesage using WMA api.
 */
private void SendMessage() { 
    String s = "sms://" + receiver;
    send(s);
}

private void send(String url) {
    MessageConnection messageconnection = null;
    try {
        messageconnection = (MessageConnection) Connector.open(url);
        TextMessage textmessage = (TextMessage) messageconnection.newMessage(MessageConnection.TEXT_MESSAGE);
        textmessage.setAddress(url);
        textmessage.setPayloadText(receivedMsg);
        messageconnection.send(textmessage);
    } catch (Exception throwable) {
        notsent = true;
        disp.setCurrent(new Alert("Info SMS","Message non Envoyé",null,AlertType.ERROR));
        bool = true;
        try {
            messageconnection.close();
        } catch (Exception e) {
        }
    }

    if (messageconnection != null) {
        try {
            messageconnection.close();
            if (!notsent) {
                
//             disp.setCurrent(new Alert("Info SMS","Message Envoyé",null,AlertType.INFO));
          
            }
            bool = true;
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    }
  }
}