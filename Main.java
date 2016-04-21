import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main{

	public static void main(String[] args){
		
		JFrame frame = new JFrame("Space War I");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());

		SpaceShip v = new SpaceShip(180, 550, 40, 40);
		GPanel gp = new GPanel();
		GEngine eg = new GEngine(gp, v);
		frame.addKeyListener(eg);

		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		eg.start();
	}	

	
}


