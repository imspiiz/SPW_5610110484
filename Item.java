import java.awt.Graphics;
import java.awt.Color;

public class Item extends Sprite{

	public static final int Y_DIS = 650;
	private boolean alive = true;
	int step = 18;
	
	public Item(int x, int y) {
		super(x, y, 25, 25);
		this.setImage("pic/health.png");
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
	}

	public void itmMove(){
		y += step;

		if(y > Y_DIS){
			alive = false;
		}
	}

	public void getItem(){
		this.alive = false;
	}

	public boolean isGet(){
		return alive;
	}

}
 