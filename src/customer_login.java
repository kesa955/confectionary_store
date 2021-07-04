import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
public class customer_login {
    private static JLabel userlabel;
    private static JTextField usertext;
	
	public static void c_login() {
		JFrame frame = new JFrame();
        JPanel panel = new JPanel();
      
        frame.setSize(450,450);
        
      
        panel.setLayout(null);
        
        JLabel user = new JLabel("User Name");
        user.setBounds(10,20,80,25);
    	user.setBackground(Color.BLACK);
        panel.add(user);
        
        JTextField username = new JTextField(50);
    	username.setBounds(100, 20, 165, 25);
        
    	panel.add(username);
    	
        JLabel number = new JLabel("Phone Numer");
        number.setBounds(10,60,80,25);
        user.setBackground(Color.BLACK);
        panel.add(number);
        
        JTextField usernum = new JTextField(50);
    	usernum.setBounds(100, 60, 165, 25);
        
    	panel.add(usernum);
        
        String num = usernum.getText();
        System.out.println(usernum);
        String number1 = num.replace(" ", ""); // Remove spaces, sometimes people seperate different
                                         // parts of the number with them
        boolean valid = number1.matches("[0-9]{6,10}");
        
        System.out.println(valid);
        
        //Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        //Matcher matcher = pattern.matcher(number1);

        if (valid) {
            System.out.println("Phone Number Valid");
        } else {
            System.out.println("Phone Number must be in the form XXX-XXXXXXX");
        }
        
        JButton customer_Button = new JButton("Sign In");
        customer_Button.setBounds(100, 100, 165, 50);
        
        panel.add(customer_Button);
        
        customer_Button.addActionListener(e -> {
			   frame.dispose();
			});
     
    	
    	
    	frame.add(panel);
        frame.setVisible(true);
	}

}




/*
  item_name   item_price   item_quantity  

*/