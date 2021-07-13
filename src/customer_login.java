import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
public class customer_login extends customer_homepage {
    private static JLabel userlabel;
    private static JTextField usertext;
	protected static String user_name;
	protected static String user_no;
	protected static JFrame frame;
	public static void c_login() {
		frame = new JFrame();
        JPanel panel = new JPanel();
      
        frame.setBounds(600,200, 350, 250);;
        
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
        
    	JButton customer_Button = new JButton("Sign In");
        customer_Button.setBounds(130, 100, 110, 30);
          
        panel.add(customer_Button);
        frame.add(panel);

    	/*
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
        */

        customer_Button.addActionListener(e -> {
        	Connection connection;
        	user_name = username.getText();
          	user_no = usernum.getText();
             
          	if(user_name.equals("") || user_no.equals("")) {
	          		JOptionPane.showMessageDialog(frame, "Enter the empty fields");
	          	}
          	else if(user_no.length()!=10) {
          		JOptionPane.showMessageDialog(frame, "Enter a valid phone number");
          	}
          	else {           	
        	try {
        		int count=0;
        		 
				Class.forName("com.mysql.jdbc.Driver");
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
						"root", "password@123");
				Statement st = connection.createStatement();
				
				PreparedStatement pst = connection.prepareStatement("select * from customer");
	            ResultSet rs = pst.executeQuery(); 
	            
	            while(rs.next()) {
	            	String num1 = rs.getString("phone");
	            	
	            	if (num1.equals(user_no)) {
	            		customer_homepage obj1 = new customer_homepage();
	    				obj1.name = user_name;
	    				obj1.number = user_no;
	    				count=1;
	    				obj1.val = 1;
	    				obj1.menu();
	    				
	            	}
	            	
	            }
	            if (count==0){
	            		String sql = "insert into customer values('"+user_name+"','"+user_no+"') ";
	    				st.executeUpdate(sql);
	    				customer_homepage obj1 = new customer_homepage();
	    				obj1.name = user_name;
	    				obj1.number = user_no;
	    				obj1.val = 0;
	    				obj1.menu();	
	            }
				
			}
        	catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          	}
          	
			   
			});

        frame.setVisible(true);
	}

}

/*
  item_name   item_price   item_quantity  

*/