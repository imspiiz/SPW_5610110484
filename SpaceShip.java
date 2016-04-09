import java.awt.Graphics;
import java.awt.Color;

public class SpaceShip{

	private int x;
	private int y;
	private int width;
	private int height;

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

}
 