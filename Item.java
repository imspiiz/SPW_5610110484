import java.awt.Graphics;
import java.awt.Color;

public class Item extends Sprite{

	public static final int Y_DIS = 650;
	private boolean alive = true;
	int step = 18;
	
	public Item(int x, int y) {
		super(x, y, 12, 12);
	}

	public void paint(Graphics g) {    
		g.setColor(Color.GREEN);
    	g.fillRect(x, y, width, height);   
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
 