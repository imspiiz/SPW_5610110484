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

	public void updateGameUI(GReport reporter, int status){
		big.clearRect(0, 0, 400, 650);
		big.setColor(Color.WHITE);

		switch (status){
			case 0:	drawGame(reporter);
				break;
			case 1:	drawWin(reporter);
				break;
			case 2: drawLose(reporter);
				break;
		}

		repaint();
	}

	public void drawSprite(){
		for(Sprite s : sprites){
			s.paint(big);
		}
	}

	public void drawGame(GReport reporter){
		big.setFont(big.getFont().deriveFont(14F)); 
		big.drawString("HP", 10, 20);
		big.drawString("Level", 320, 40);
		big.drawString(String.format("%d", reporter.getLevel()), 360, 40);
		big.drawString(String.format("%07d", reporter.getScore()), 320, 20);
		drawSprite();
	}

	public void drawWin(GReport reporter){

		big.setFont(big.getFont().deriveFont(32F)); 
		big.drawString("W I N N E R", 100, 300);										
		big.setFont(big.getFont().deriveFont(18F)); 
		big.drawString(String.format("Your score: %d", reporter.getScore()), 110, 350);
	}

	public void drawLose(GReport reporter){

		big.setFont(big.getFont().deriveFont(32F)); 
		big.drawString("GAME OVER!", 100, 300);										
		big.setFont(big.getFont().deriveFont(18F)); 
		big.drawString(String.format("Your score: %d", reporter.getScore()), 130, 350);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
}
