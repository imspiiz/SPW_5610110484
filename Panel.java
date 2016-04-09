import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private BufferedImage bi;
	private SpaceShip sp;	
    Graphics2D big;

	public Panel(SpaceShip sp) {
		this.sp = sp;
		bi = new BufferedImage(400, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void gUI(){
		big.clearRect(0, 0, 400, 650);
		sp.paint(big);
		
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}


}
