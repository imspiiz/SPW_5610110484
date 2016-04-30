import java.awt.Graphics;
import java.awt.Color;

public class BossHealth extends Sprite {
	
	public BossHealth(int width) {
		super(32, 25, width, 10);
	}

	public void paint (Graphics g) {    
		g.setColor(Color.RED);
    	g.fillRect(x, y, width, height);   
	}

    public void dec_hp(){
        width -= 5;
    }

    public int getHP(){
    	return width;
    }

}
 