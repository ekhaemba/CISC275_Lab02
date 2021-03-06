//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
// Elton Mwale, William Ransom

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Animation extends JPanel {

	//Velocity enumerated type
	enum Velocity { UNDEF, NEGATIVE, ZERO, POSITIVE }
	static List<Orc> orcList = new ArrayList<Orc>();
    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][]pics;
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 4;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Map<String, Integer> direction = new HashMap<String, Integer>();
    String directionKey;
    Velocity xVel = Velocity.UNDEF;
    Velocity yVel = Velocity.UNDEF;
    
    //Sets the direction based off of the enumerated types
    /*private void setDirection() {
		if (xVel == Velocity.ZERO && yVel == Velocity.POSITIVE) {
			directionKey = "South";
		}
		if (xVel == Velocity.POSITIVE && yVel == Velocity.POSITIVE) {
			directionKey = "SouthEast";
		}
		if (xVel == Velocity.POSITIVE && yVel == Velocity.ZERO) {
			directionKey = "East";
		}
		if (xVel == Velocity.POSITIVE && yVel == Velocity.NEGATIVE) {
			directionKey = "NorthEast";
		}
		if (xVel == Velocity.ZERO && yVel == Velocity.NEGATIVE) {
			directionKey = "North";
		}
		if (xVel == Velocity.NEGATIVE && yVel == Velocity.NEGATIVE) {
			directionKey = "NorthWest";
		}
		if (xVel == Velocity.NEGATIVE && yVel == Velocity.ZERO) {
			directionKey = "West";
		}
		if (xVel == Velocity.NEGATIVE && yVel == Velocity.POSITIVE) {
			directionKey = "SouthWest";
		}
    }*/
    
    //Sets the initial velocity enum based on the velocity value given
    private Velocity setInitialVel(int vel) {
    		if(vel == 0) {
    			return Velocity.ZERO;
    		}
    		else if(vel < 0) {
    			return Velocity.NEGATIVE;
    		}
    		else {
    			return Velocity.POSITIVE;
    		}
    }
    
    //returns the next incr value dependent on the enum type
    private int getVelocityVal(Velocity v, int velVal) {
    		switch(v) {
			case POSITIVE:
				return velVal;
			case NEGATIVE:
				return -velVal;
			default:
				return 0;
    		}
    }
    
    //Sets the next velocity 
    private void setNextVelocity() {
    		if(xVel != Velocity.ZERO) {
	    		int next_x = xloc + getVelocityVal(xVel, xIncr);
		    	if ((next_x + imgWidth > frameWidth) || (next_x < 0)){
		    		xVel = xVel == Velocity.POSITIVE ? Velocity.NEGATIVE : Velocity.POSITIVE;
		    	}
	    	}
    		if(yVel != Velocity.ZERO) {
		    	int next_y = yloc + getVelocityVal(yVel, yIncr);
		    	if ((next_y + imgHeight > frameHeight) || (next_y < 0)){
		    		yVel = yVel == Velocity.POSITIVE  ? Velocity.NEGATIVE : Velocity.POSITIVE;
		    	}
	    	}
    }
    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
	    	picNum = (picNum + 1) % frameCount;
	    	for(Orc orc: orcList) {
	    		g.drawImage(pics[picNum][direction.get(orc.getDirection())], orc.getX(), orc.getY(), Color.gray, this);
	    	}
    }
    public static void tick() {
    		for(Orc thisOrc : orcList) {
    			int nextx = thisOrc.getNextXLoc();
    			int nexty = thisOrc.getNextYLoc();
    			System.out.println(thisOrc.getX()+ ","+thisOrc.getY());
    			if(nextx < 0 || nextx + imgHeight > frameWidth) {
    				thisOrc.setxComp(-thisOrc.getxComp());
    			}
    			if(nexty < 0 || nexty + imgHeight > frameWidth) {
    				thisOrc.setyComp(-thisOrc.getyComp());
    			}
    			thisOrc.setX(thisOrc.getNextXLoc());
    			thisOrc.setY(thisOrc.getNextYLoc());
    		}
    }
    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
      	JFrame frame = new JFrame();
	    	frame.getContentPane().add(new Animation());
	    	frame.setBackground(Color.gray);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(frameWidth, frameHeight);
	    	frame.setVisible(true);
	    	//Initial State
	    	orcList.add(new Orc(8,4));
	    	for(int i = 0; i < 1000; i++){
	    		frame.repaint();
	    		tick();
	   		try {
	    			Thread.sleep(100);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	}
    }

	//Constructor: get image, segment and store in array
    public Animation(){
    	direction.put("South", 0);
    	direction.put("SouthEast", 1);
    	direction.put("East", 2);
    	direction.put("NorthEast", 3);
    	direction.put("North", 4);
    	direction.put("NorthWest", 5);
    	direction.put("West", 6);
    	direction.put("SouthWest", 7);
    	String[] paths = {"images/orc/orc_forward_south.png", 
    			"images/orc/orc_forward_southeast.png", 
    			"images/orc/orc_forward_east.png",
    			"images/orc/orc_forward_northeast.png",
    			"images/orc/orc_forward_north.png",
    			"images/orc/orc_forward_northwest.png",
    			"images/orc/orc_forward_west.png",
    			"images/orc/orc_forward_southwest.png"};
    	pics = new BufferedImage[frameCount][8];
		for(int dir = 0; dir < 8; dir++) {
			BufferedImage img = createImage(paths[dir]);
			for(int i = 0; i < frameCount; i++) {
				pics[i][dir] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		}
    	}
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    //Read image from file and return
    private BufferedImage createImage(String path){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(path));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}
