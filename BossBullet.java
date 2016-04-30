import java.awt.Graphics;
import java.awt.Color;

public class BossBullet extends Sprite{

	int step = 15;
	public static final int Y_DIS = 600;
	private boolean alive = true;

	public BossBullet(int x, int y) {
		super(x, y, 10, 30);
		this.setImage("pic/redbullet.png");
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
	}

	public void bbMove(){
		y += step;
		if(y > Y_DIS){
			alive = false;
		}
	}
	public void shoot(){
		this.alive = false;
	}
	
	public boolean isShoot(){
		return alive;
	}
}
