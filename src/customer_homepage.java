import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class customer_homepage {

	
	
	public void c_homepage() {
		JFrame frame = new JFrame();
    	JPanel panel = new JPanel();
    	frame.setSize(300,300);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JLabel user = new JLabel("This is customer homepage");
    	user.setBounds(10,20,80,25);
    	user.setBackground(Color.BLACK);
    	panel.add(user);
    	frame.add(panel);
    	
    	panel.setLayout(null);
    	
    	frame.setVisible(true);
	}

}
