import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main{

	public static void main(String[] args){
		
		JFrame frame = new JFrame("Black window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());

		SpaceShip v = new SpaceShip(180, 550, 40, 40);
		Panel gp = new Panel(v);
		gp.gUI();

		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
	}	

	
}


