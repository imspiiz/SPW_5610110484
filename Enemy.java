import java.awt.Graphics;
import java.awt.Color;

public class Enemy{

	private int x;
	private int y;

	int step = 12;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void paint (Graphics g) {    
		g.setColor(Color.YELLOW);
    	g.fillRect(x, y, 7, 10);   
	}

	public void proceed(){
		y += step;
	}

}
 