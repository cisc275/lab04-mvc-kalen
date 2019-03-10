
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JPanel {
	private int width;  // attributes of view
	private int height;
	private int imgWidth;
	private int imgHeight;
	private int X = 0;
	private int Y = 0;
	HashMap<Direction, BufferedImage> dir = new HashMap<Direction, BufferedImage>();
	static JFrame frame = new JFrame();
	private Direction direct;
	
	public View() { // sets up View with dimensions
		width = 800;
		height = 500;
		imgWidth = 165;
		imgHeight = 165;
		//create images out of files
    	BufferedImage img0 = createImage("src/images/orc_animation 2/orc_forward_north.png");//"orc_animation" is the folder i kept. it you used "images" or something that's the only problem
    	BufferedImage img1 = createImage("src/images/orc_animation 2/orc_forward_northeast.png");//just ctrl-f and replace these to get it running
    	BufferedImage img2 = createImage("src/images/orc_animation 2/orc_forward_east.png");
    	BufferedImage img3 = createImage("src/images/orc_animation 2/orc_forward_southeast.png");
    	BufferedImage img4 = createImage("src/images/orc_animation 2/orc_forward_south.png");
    	BufferedImage img5 = createImage("src/images/orc_animation 2/orc_forward_southwest.png");
    	BufferedImage img6 = createImage("src/images/orc_animation 2/orc_forward_west.png");
    	BufferedImage img7 = createImage("src/images/orc_animation 2/orc_forward_northwest.png");
    	//assemble them into one collection
    	dir.put(Direction.NORTH, img0);
    	dir.put(Direction.NORTHEAST, img1);
    	dir.put(Direction.EAST, img2);
    	dir.put(Direction.SOUTHEAST, img3);
    	dir.put(Direction.SOUTH, img4);
    	dir.put(Direction.SOUTHWEST, img5);
    	dir.put(Direction.WEST, img6);
    	dir.put(Direction.NORTHWEST, img7);
    	/*frame.getContentPane().add(new Controller());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setVisible(true);*/
	}
	public void paint(Graphics g) {
		g.drawImage(dir.get(direct), X, Y, Color.gray, (ImageObserver) this);
		
	}
	public void update(int x, int y, Direction d) {
		X = x;
		Y = y;
		direct = d;
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	
	
	// getters for each attribute
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getImageWidth() {
		return imgWidth;
	}
	public int getImageHeight() {
		return imgHeight;
	}
	
	
	private BufferedImage createImage(String toLoad){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(toLoad));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
	}
	public static void main(String[] args) {
    	Controller cont = new Controller();
    	
    	frame.getContentPane().add(new View());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setVisible(true);
    	cont.start();
    		
    	}
    }
}