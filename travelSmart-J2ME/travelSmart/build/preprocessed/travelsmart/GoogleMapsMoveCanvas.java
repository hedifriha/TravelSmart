/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelsmart;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import javax.microedition.lcdui.Command;

/**
 *
 * @author Wassila
 */
public class GoogleMapsMoveCanvas extends GoogleMapsTestCanvas implements GoogleStaticMapHandler
{
	GoogleMaps gMaps = null;
	GoogleStaticMap map = null;
        
        Command zoomInCommand, zoomOutCommand;
        float x , y ;
	
	public GoogleMapsMoveCanvas(MIDlet m, Displayable testListScreen, double Latitude, double Longitude)
	{
            	
		super(m, testListScreen);
                //-----------------------ZoomCommand----------------------------
                addCommand(zoomInCommand = new Command("Zoom in", Command.OK, 1));
		addCommand(zoomOutCommand = new Command("Zoom out", Command.OK, 2));
                //
		
		gMaps = new GoogleMaps();
		
		map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		
		map.setHandler(this);
		
		map.setCenter(new GoogleMapsCoordinates(Latitude, Longitude));
                
                /* tessssssssssssst
                map.setCenter(new GoogleMapsCoordinates(x, y));
                x = map.getLatitude();
                y = map.getLongitude();     
                */
                //--------------------------Marker------------------------------
                GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(Latitude, Longitude));
		redMarker.setColor(GoogleStaticMap.COLOR_RED);
		redMarker.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker.setLabel('E');
                
                map.addMarker(redMarker);
                
                //--------------------------Zoom--------------------------------
		
		map.setZoom(15);
		
		map.update();
	}
	
	protected void paint(Graphics g)
	{
		map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
	}
	public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage)
	{
		showError("map error: " + errorCode + ", " + errorMessage);
	}
	public void GoogleStaticMapUpdated(GoogleStaticMap map)
	{
		repaint();
	}
        //--------------------------------Move----------------------------------
	protected void keyPressed(int key)
	{
		int gameAction = getGameAction(key);
		
		if(gameAction == Canvas.UP || gameAction == Canvas.RIGHT || gameAction == Canvas.DOWN || gameAction == Canvas.LEFT)
		{
			map.move(gameAction);
		}
                
	}
        //--------------------------------Zoom----------------------------------
        public void commandAction(Command c, Displayable d)
	{
		super.commandAction(c, d);
		
		if(c == zoomInCommand)
			map.zoomIn();
		else if(c == zoomOutCommand)
			map.zoomOut();
	}

}
