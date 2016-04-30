import java.awt.Graphics;
import java.awt.Color;

public class SpaceShip extends Sprite{

	int step = 15;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setImage("pic/spaceship.png");
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
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
 