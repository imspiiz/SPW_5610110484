

import java.awt.Graphics;
import java.awt.Color;

public class Health extends Sprite {
	
	public Health(int width) {
		super(32, 11, width, 10);
	}

	public void paint (Graphics g) {    
		g.setColor(Color.RED);
    	g.fillRect(x, y, width, height);   
	}

    public void dec_hp(){
        width -= 20;
    }

    public void inc_hp(){
    	if(width != 100)
        	width += 20;
    }

    public int getHP(){
    	return width;
    }

}
 