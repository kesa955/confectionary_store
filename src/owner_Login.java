import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class owner_Login implements ActionListener {
	protected static JButton owner_Button;
	private JLabel userlabel,passwordlabel;
	private JTextField usertext;
	private JPasswordField passwordtext;
	protected static JFrame frame; 
	ResultSet rs;
	Connection connection;
	Statement st;

	protected void owner() {

		// TODO Auto-generated method stub
		frame = new JFrame();
    	JPanel panel = new JPanel();
    	frame.setSize(300,300);
    
    	
    	frame.add(panel);
    	frame.setBounds(500, 200, 300, 250);
    	panel.setLayout(null);
    	
    	userlabel = new JLabel("User");
    	userlabel.setBounds(10,20,80,25);
    	userlabel.setBackground(Color.BLACK);
    	panel.add(userlabel);
    	
    	usertext = new JTextField(20);
    	usertext.setBounds(100, 20, 165, 25);
    	panel.add(usertext);
    	
    	passwordlabel = new JLabel("Password");
    	passwordlabel.setBounds(10,60,80,25);
    	passwordlabel.setBackground(Color.BLACK);
    	panel.add(passwordlabel);
    	
        passwordtext = new JPasswordField(20);
    	passwordtext.setBounds(100, 60, 165, 25);
    	panel.add(passwordtext);
    	
    	owner_Button = new JButton("Login");
    	owner_Button.setBounds(100, 100, 80, 25);
    	
    	
		
		
    	owner_Button.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String username = usertext.getText();
				String password = passwordtext.getText();
		    	
							if(usertext.getText().length() == 0 || passwordtext.getText().length() == 0 ){
							    JOptionPane.showMessageDialog(frame,"Enter the empty field");
							}
							else {
								try {

								Class.forName("com.mysql.jdbc.Driver");
								connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
										"root", "password@123");
								st = connection.createStatement();
								String sql = "select * from credentials where name='"+username+"' and password='"+password+"' ";
								
								rs = st.executeQuery(sql);
								
								if(rs.next()) {
									//dispose();
									System.out.println("Existing owner");
									owner_homepage obj = new owner_homepage();
									obj.o_homepage();
								}
								
								else {
								//JOptionPane.showMessageDialog(panel, this, "username and password are invalid", 0, null);
							    JOptionPane.showMessageDialog(panel, "Invalid username or password");
								}
							}	
								catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  
							
							
							
						} 
    	}

			
    	}
			);
    	
    	panel.add(owner_Button);
    	
    	
    	
    	frame.setVisible(true);
    	
    	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
