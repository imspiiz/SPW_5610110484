import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Sprite{
	int x;
	int y;
	int width;
	int height;
	
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	abstract public void paint(Graphics g);
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
