import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {
	private int width;  // attributes of model
	private int height;
	private int imgWidth;
	private int imgHeight;
	private int X = 0;
	private int Y = 0;
	private Direction direct; 
	private int xIncr = 8;
	private int yIncr = 2;
	boolean north = false;//N or S, pos/neg y values
    boolean east = true;
	
	
	public Model(int wid, int high, int imgwid, int imghigh) { // sets up Model with dimensions from View
		width = wid;
		height = high;
		imgWidth = imgwid;
		imgHeight = imghigh;
		
	}
	public int collision() {
		boolean xb = (X + xIncr + (4*imgWidth/5) <= width);
		xb = xb && (X + xIncr + (imgWidth/4) >= 0);
		boolean yb = (Y + yIncr + imgHeight <= height);
		yb = yb && (Y + yIncr + (imgWidth/5) >= 0);
		if (!xb) {
			east =! east;
    		return -1;
		}
		if(!yb){
			north =! north;
    		return -2;
		}
		else {
			return 0;
		}
	}
	public void updateLocationAndDirection() {
		if (collision() == -1) {
			xIncr *= -1;
		}else if (collision() == -2) {
			yIncr *= -1;
		}
		X += xIncr;
		Y += yIncr;
		if(!north && east) {
			direct = Direction.SOUTHEAST;
		}else if(north && east) {
			direct = Direction.NORTHEAST;
		}else if(!north && !east) {
			direct = Direction.SOUTHWEST;
		}else if(north && !east) {
			direct = Direction.NORTHWEST;
		}else {
			direct = Direction.NORTH;
		}
	}
	//getters for X and Y
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public Direction getDirect() {
		return direct;
	}
}
