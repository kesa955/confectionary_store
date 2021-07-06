import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class login extends owner_Login implements ActionListener {
    private static JButton owner_Button;
    private static JButton customer_Button;
    private static JFrame frame;
    //private static JButton customer_Button;
	public static void main(String[] args) {

		// TODO Auto-generated method stub
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setBounds(500, 200, 500, 150);
        frame.add(panel);
        
        
        //Owner Button
        owner_Button = new JButton("Login as Owner");
        owner_Button.setBounds(200, 50, 80, 25);
        panel.add(owner_Button);
        
        owner_Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		owner_Login obj1 = new owner_Login();
        		obj1.owner();
        	}
        });
        
        owner_Button.addActionListener(e -> {
			   frame.dispose();
		});
        
  
        //Customer Button
        customer_Button = new JButton("Login as Customer");
        customer_Button.setBounds(300, 100, 80, 25);
        panel.add(customer_Button);
        
        customer_Button.addActionListener(new ActionListener() {
        		 public void actionPerformed(ActionEvent e) {
        		      //your code here
        			 customer_login obj = new customer_login();
        			 obj.c_login();
        		    }
        });
        
        customer_Button.addActionListener(e -> {
			   frame.dispose();
			});
     
        frame.setVisible(true);
        
        
	}

}
