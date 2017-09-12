
public class Orc extends AbstractGridElem{
	protected int xComp;
	protected int yComp;
	public Orc(int xComp, int yComp){
		super();
		this.xComp = xComp;
		this.yComp = yComp;
	}
	public Orc(int x, int y, int xComp, int yComp){
		super(x,y);
		this.xComp = xComp;
		this.yComp = yComp;
	}
	public int getxComp() {
		return xComp;
	}
	public void setxComp(int xComp) {
		this.xComp = xComp;
	}
	public int getyComp() {
		return yComp;
	}
	public void setyComp(int yComp) {
		this.yComp = yComp;
	}
	
	public int getNextXLoc() {
		return x + xComp;
	}
	public int getNextYLoc() {
		return y + yComp;
	}
	
	private String getStringYComp() {
		if(yComp < 0)
			return "North";
		else
			return "South";
	}
	
	private String getStringXComp() {
		if(xComp < 0)
			return "West";
		else if(xComp == 0)
			return "";
		else
			return "East";
	}
	public String getDirection() {
		return getStringYComp() + getStringXComp();
	}
	
}
