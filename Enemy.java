import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Sprite{

	public static final int Y_DIE = 600;
	private boolean alive = true;
	int step = 12;
	
	public Enemy(int x, int y) {
		super(x, y, 30, 45);
		this.setImage("pic/enemy.png");
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
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
 