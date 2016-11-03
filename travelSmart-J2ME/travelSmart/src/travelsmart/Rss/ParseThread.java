/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart.Rss;

import org.kxml2.io.*;
import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.Image;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
//import travelsmart.MainMidlet;
import travelsmart.MainMidlet;

/**
 *
 * @author Ines
 */
public class ParseThread {

    protected MainMidlet parentMidlet;
    private String imageurl;
    private Image image;
    private String readUrl;
    private String srcUrl;

    public ParseThread(MainMidlet parent) {
        parentMidlet = parent;
    }

    public void getXMLFeed(final String url) {
        Thread t = new Thread() {
            public void run() {
                HttpConnection myConnection = null;
                try {
                    myConnection = (HttpConnection) Connector.open(url);
                    InputStream stream = myConnection.openInputStream();
                    ParseXMLFeed(stream);
                } catch (Exception error) {
//                    parentMidlet.DisplayError(error);
                } finally {
                    try {
                        if (myConnection != null) {
                            myConnection.close();
                        }
                    } catch (IOException eroareInchidere) {
//                        parentMidlet.DisplayError(eroareInchidere);
                    }
                }
            }
        };
        t.start();
    }

    private void ParseXMLFeed(InputStream input) throws IOException, XmlPullParserException {
        System.out.println("ParseXMLFeed");
        Reader dataReader = new InputStreamReader(input);
        KXmlParser myParser = null;
        try {
            myParser = new KXmlParser();
        } catch (Exception e) {
            //System.out.println("hiiii" + e);
        }
        myParser.setInput(dataReader);

        myParser.nextTag();
        myParser.require(XmlPullParser.START_TAG, null, "rss");
        myParser.nextTag();
        myParser.require(XmlPullParser.START_TAG, null, "channel");
        myParser.nextTag();
        myParser.require(XmlPullParser.START_TAG, null, "title");
        while (myParser.getEventType() != XmlPullParser.END_DOCUMENT) {
            String name = myParser.getName();
            if (name.equals("channel")) {
                break;
            }
            if (name.equals("item")) {
                if (myParser.getEventType() != XmlPullParser.END_TAG) {
                    myParser.nextTag();
                    String title = myParser.nextText();
                    System.out.println("Title" + title);
                    myParser.nextTag();
                    String link = myParser.nextText();
                    System.out.println("Link" + link);
                    myParser.nextTag();
                    String pubDate = myParser.nextText();
                    myParser.nextTag();
                    String ptext = myParser.nextText();//(<p>some text</p>)
                    System.out.println("PTEXT>>>" + ptext);
                    //Image src Value from ptext  
                    if (ptext.indexOf("src") != -1) {
                        int pTagIndex = ptext.indexOf("p");
                        int srcIndex1 = ptext.indexOf("src", pTagIndex);
                        int httpIndex1 = ptext.indexOf("/", srcIndex1);
                        int quotesIndex1 = ptext.indexOf("\"", httpIndex1);
                        srcUrl = ptext.substring(httpIndex1, quotesIndex1);
                        System.out.println("srcUrl " + srcUrl);
                        boolean containsWhitespace = srcUrl.trim().indexOf(" ") != -1;
                        if (containsWhitespace == true) {
                            //displayDefaultImage();

                        } else {
                            //imageurl = "http://www...." + srcUrl;

                            ImageClass ic = new ImageClass();
                            image = ic.getImage(parentMidlet, srcUrl.trim());
                        }

                    } else {
                        System.out.println("Default");
                        //displayDefaultImage();
                    }
                    //check imagesrc contains any whitespace characters or not
                    RssModel model = new RssModel(title, image);
                    parentMidlet.addItems(model);
                }
            } else {
                myParser.skipSubTree();
            }
            myParser.nextTag();
        }
        input.close();
    }

//    private void displayDefaultImage() {
//        try {
//            image = Image.createImage("/res/logo.png");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}
