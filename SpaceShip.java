import java.awt.Graphics;
import java.awt.Color;

public class SpaceShip{

	private int x;
	private int y;
	private int width;
	private int height;
	int step = 8;
	
	public SpaceShip(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void paint (Graphics g) {    
		g.setColor(Color.RED);
    	g.fillRect(x, y, width, height);   
	}

	public void moveX(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void moveY(int direction){
		y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 650 - height)
			y = 650 - height;
	}

}
 