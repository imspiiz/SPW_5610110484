import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.util.ArrayList;

public class GPanel extends JPanel {
	
	private BufferedImage bi;
    Graphics2D big;

    ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GPanel() {
		bi = new BufferedImage(400, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GReport reporter){
		big.clearRect(0, 0, 400, 650);
		big.setColor(Color.WHITE);
		big.drawString("HP", 10, 20);
		big.drawString(String.format("%07d", reporter.getScore()), 320, 20);

		for(Sprite s : sprites){
			s.paint(big);
		}

		repaint();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
}
