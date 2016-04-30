import java.awt.Graphics;
import java.awt.Color;

public class Health extends Sprite {
	
	public Health(int width) {
		super(32, 11, width, 10);
	}

	public void paint (Graphics g) {    
		g.setColor(Color.BLUE);
    	g.fillRect(x, y, width, height);   
	}

    public void dec_hp(){
        width -= 10;
    }

    public void inc_hp(){
    	if(width != 200)
        	width += 20;
    }

    public int getHP(){
    	return width;
    }

}
 