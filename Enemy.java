import java.awt.Graphics;
import java.awt.Color;

public class Enemy{

	private int x;
	private int y;
	public static final int Y_DIE = 650;
	private boolean alive = true;
	int step = 12;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void paint (Graphics g) {    
		g.setColor(Color.YELLOW);
    	g.fillRect(x, y, 7, 10);   
	}

	public void eMove(){
		y += step;

		if(y > Y_DIE){
			alive = false;
		}
	}

	public boolean isAlive(){
		return alive;
	}

}
 