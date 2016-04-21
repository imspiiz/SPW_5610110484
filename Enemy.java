import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Sprite{

	public static final int Y_DIE = 650;
	private boolean alive = true;
	int step = 12;
	
	public Enemy(int x, int y) {
		super(x, y, 7, 10);
	}

	public void paint(Graphics g) {    
		g.setColor(Color.YELLOW);
    	g.fillRect(x, y, width, height);   
	}

	public void eMove(){
		y += step;

		if(y > Y_DIE){
			alive = false;
		}
	}

	public void dead(){
		this.alive = false;
	}

	public boolean isAlive(){
		return alive;
	}

}
 