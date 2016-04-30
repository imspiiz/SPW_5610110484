import java.awt.Graphics;
import java.awt.Color;

public class Bullet extends Sprite{

	int step = 22;
	public static final int Y_DIS = 15;
	private boolean alive = true;

	public Bullet(int x, int y) {
		super(x, y, 10, 30);
		this.setImage("pic/bluebullet.png");
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
	}

	public void bMove(){
		y -= step;
		if(y < Y_DIS){
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
