//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {

    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    int xloc = 0;
    int yloc = 0;
    int xIncr = 8;
    int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    int direction;

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
	    	picNum = (picNum + 1) % frameCount;
	    	
	    	if ((xloc + xIncr + imgWidth > frameWidth) || (xloc + xIncr + imgWidth/2 < 0)){
	    		xIncr = -xIncr;
	    	}
	    	if ((yloc + yIncr + imgHeight > frameHeight) || (yloc + yIncr + imgHeight/2 < 0)){
	    		yIncr = -yIncr;
	    	}
	    	
	    	if (xIncr > 0 && yIncr > 0) {
	    		direction = 0;
	    	}
	    	else if(xIncr < 0 && yIncr > 0) {
	    		direction = 1;
	    	}
	    	else if(xIncr > 0 && yIncr < 0) {
	    		direction = 2;
	    	}
	    	else if(xIncr < 0 && yIncr < 0) {
	    		direction = 3;
	    	}
		g.drawImage(pics[direction][picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);

    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
	//Be sure that animation picture direction matches what is happening on screen.
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
	    	JFrame frame = new JFrame();
	    	frame.getContentPane().add(new Animation());
	    	frame.setBackground(Color.gray);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(frameWidth, frameHeight);
	    	frame.setVisible(true);
	    	for(int i = 0; i < 1000; i++){
	    		frame.repaint();
	    		try {
	    			Thread.sleep(100);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	}
    }

    //Constructor: get image, segment and store in array
    public Animation(){
    	String[] paths = {"images/orcs/orc_forward_southeast.png", 
    			"images/orcs/orc_forward_southwest.png", 
    			"images/orcs/orc_forward_northeast.png",
    			"images/orcs/orc_forward_northwest.png",
    			"images/orcs/orc_forward_south.png",
    			"images/orcs/orc_forward_north.png",
    			"images/orcs/orc_forward_east.png",
    			"images/orcs/orc_forward_west.png"};
    	
    	pics = new BufferedImage[8][10];
    	for(int j = 0; j < pics.length; j++) {
    		BufferedImage img = createImage(paths[j]);
	    	for(int i = 0; i < frameCount; i++)
	    		pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
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
