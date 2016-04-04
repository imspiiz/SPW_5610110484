import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Blackwindows {
	public static void main(String[] args){
		JFrame frame = new JFrame("Black window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.black);
	
		frame.setVisible(true);
		
	}
}
