
public abstract class AbstractGridElem {
	protected int x;
	protected int y;
	public AbstractGridElem(){
		x = 0;
		y = 0;
	};
	public AbstractGridElem(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	};
	public int getY() {
		return y;
	};
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
