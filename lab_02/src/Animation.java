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
	private List<Orc> orcList;
    
    public int getFrameWidth() {
    	return frameWidth;
    }
    
    public int getFrameHeight() {
    	return frameHeight;
    }
    
    public int getimgWidth() {
    	return imgWidth;
    }
    
    public int getImgHeight() {
    	return imgHeight;
    }
    
    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
	    	picNum = (picNum + 1) % frameCount;
	    	for(Orc orc: orcList) {
	    		g.drawImage(pics[picNum][direction.get(orc.getDirection())], orc.getX(), orc.getY(), Color.gray, this);
	    	}
    }
    

	//Constructor: get image, segment and store in array
    public Animation(List<Orc> orcList){
    	this.orcList = orcList;
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
