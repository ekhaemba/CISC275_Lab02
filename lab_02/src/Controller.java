import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class Controller{
	static List<Orc> orcList = new ArrayList<Orc>();
	
	public static void tick(int frameWidth, int frameHeight, int imgWidth, int imgHeight) {
			for(Orc thisOrc : orcList) {
				int nextx = thisOrc.getNextXLoc();
				int nexty = thisOrc.getNextYLoc();
				if(nextx < 0 || nextx + imgWidth > frameWidth) {
					thisOrc.setxComp(-thisOrc.getxComp());
				}
				if(nexty < 0 || nexty + imgHeight > frameHeight) {
					thisOrc.setyComp(-thisOrc.getyComp());
				}
				thisOrc.setX(thisOrc.getNextXLoc());
				thisOrc.setY(thisOrc.getNextYLoc());
			}
	}
	//Make frame, loop on repaint and wait
	public static void main(String[] args) {
	  	JFrame frame = new JFrame();
	  		orcList.add(new Orc(8,4));
	  		Animation Ani1 = new Animation(orcList);
	    	frame.getContentPane().add(Ani1);
	    	frame.setBackground(Color.gray);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(Ani1.getFrameWidth(), Ani1.getFrameHeight());
	    	frame.setVisible(true);
	    	//Initial State
	    	for(int i = 0; i < 1000; i++){
	    		frame.repaint();
	    		tick(Ani1.getFrameWidth(), Ani1.getFrameHeight(), Ani1.getimgWidth(), Ani1.getimgWidth());
	   		try {
	    			Thread.sleep(100);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	}
	}
}