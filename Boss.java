import java.awt.Graphics;
import java.awt.Color;

public class Boss extends Sprite{

	private boolean alive = true;
	private boolean changeStep = true;
	private int left_limit = 10;
	private int right_limit = 280;

	int step = 1;
	
	public Boss(int x, int y) {
		super(x, y, 120, 90);
		this.setImage("pic/boss.png");
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.image, x, y, width, height, null);
	}

	public void bossMove(){
		if(changeStep){
			x += step;
			if(x == right_limit)
				changeStep = false;
		}
		else{
			x -= step;
			if(x == left_limit)
				changeStep = true;
		}
		
	}

	public void dead(){
		this.alive = false;
	}

	public boolean isAlive(){
		return alive;
	}

}
 