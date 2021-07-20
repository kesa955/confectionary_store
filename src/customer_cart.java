import java.awt.Color;
    	import java.awt.Font;
    	import java.awt.event.ActionEvent;
    	import java.awt.event.ActionListener;
    	import java.awt.event.MouseAdapter;
    	import java.awt.event.MouseEvent;
    	import java.sql.Connection;
    	import java.sql.DriverManager;
    	import java.sql.PreparedStatement;
    	import java.sql.ResultSet;
    	import java.sql.SQLException;
    	import java.sql.Statement;

    	import javax.swing.*;
    	import javax.swing.table.DefaultTableModel;
    	import javax.swing.table.TableRowSorter;

    	import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class customer_cart extends customer_homepage {
    public void view_cart() {
    	customer_homepage obj = new customer_homepage();
    	String number = obj.number;
    	System.out.println(number);
    			JFrame frame1 = new JFrame();
    	    	JTable table = new JTable();
    	    	JPanel panel = new JPanel();
    	    	
    	    	JButton total = new JButton("Total");
    	    	
    	        frame1.add(panel); 
    	        
    	    	// create a table model and set a Column Identifiers to this model 
    	        Object[] columns = {"Item Name","Item Price","Quantity"};
    	        DefaultTableModel model = new DefaultTableModel();
    	        model.setColumnIdentifiers(columns);
    	        
    	        //setting model to the table
    	        table.setModel(model);
    	        frame1.setTitle("Cart");

    	        table.setBackground(Color.WHITE);
    	        table.setForeground(Color.BLACK);
    	        Font font = new Font("",1,22);
    	        table.setFont(font);
    	        table.setRowHeight(30);
    	        table.setBounds(100, 100, 10000, 10000);
    	        
    	        JButton Back = new JButton("Back");
    	    	
    	    	JLabel item_name_Label = new JLabel("Item Name: ");
    	    	JLabel item_price_Label = new JLabel("Item Price: ");
    	    	JLabel item_quantity_Label = new JLabel("Item Quantity: ");
    	    	
    	    	
    	    	JTextField item_name_field = new JTextField();
    	    	JTextField item_price_field = new JTextField();
    	    	JTextField item_quantity_field = new JTextField();
    	    	
    	    	
    	    	JButton btn_update = new JButton("Update");
    	    	JButton btn_delete = new JButton("Delete");
    	    	
    	    	JButton clear = new JButton("Clear");
    	    	
    	    	
    	    	clear.setBounds(140, 360, 100, 25);
    	    	
    	    	item_name_field.setBounds(120,250, 150, 25);
    	    	item_price_field.setBounds(120,280, 150, 25);
    	    	item_quantity_field.setBounds(120,310, 150, 25);
    	    	
    	    
    	    	item_name_Label.setBounds(20,250, 100, 25);
    	    	item_price_Label.setBounds(20,280, 100, 25);
    	    	item_quantity_Label.setBounds(20,310, 100, 25);
    	    	
    	    	
    	    	btn_update.setBounds(350, 265, 100, 25);
    	    	btn_delete.setBounds(350, 310, 100, 25);
    	    	total.setBounds(550, 310, 100, 25);
    	    	Back.setBounds(750,310,100,25);
    	    	
    	    
    	    	frame1.add(item_name_field);
    	    	frame1.add(item_price_field);
    	    	frame1.add(item_quantity_field);
    	    	
    	    	
    	    	frame1.add(item_name_Label);
    	    	frame1.add(item_price_Label);
    	    	frame1.add(item_quantity_Label);
    	    	frame1.add(Back);
    	    	
    	    	JScrollPane pane = new JScrollPane(table);
    	    	pane.setBounds(0, 0, 880, 200);
    	    	
    	    	frame1.add(clear);
    	    	
    	    	frame1.add(pane);
    	    	
    	    	frame1.add(total);
    	    	frame1.add(btn_update);
    	    	frame1.add(btn_delete);

    	    	Object[] row = new Object[4];
    	    	
    	    	// create an array of objects to set the row data
    	        
    	        
    	        try {
    				Class.forName("com.mysql.jdbc.Driver");
    				Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
    						"root", "password@123");
    				Statement st = connection.createStatement(); 
    				PreparedStatement pst = connection.prepareStatement("select * from cart where phone='"+number+"'");
    	            ResultSet rs = pst.executeQuery();
    	            
    	            while(rs.next()) {
    	            	
    	                
    	                String name = rs.getString("item_name");
    	                String price = rs.getString("item_price");
    	                String quantity = rs.getString("item_quantity");
    	                
    	                model.addRow(new Object[]{ name, price, quantity});
    	                
    	            }
    	        }
    	        catch (ClassNotFoundException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    	     
    	        
    	        item_name_field.setEditable(false);
    	    	item_price_field.setEditable(false);
    	    	//JTextField item_quantity_field 
    	        
    	        // button deleting of row
    	        btn_delete.addActionListener(new ActionListener(){

    	            public void actionPerformed(ActionEvent e) {
    	            
    	                // i = the index of the selected row
    	                int i = table.getSelectedRow();
    	                
    	                try {
    	            		Class.forName("com.mysql.jdbc.Driver");
    						Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
    								"root", "password@123");
    	            	   Statement st = connection.createStatement();
    	                if(i >= 0){
    	                    // remove a row from jtable
    	                	String val = item_name_field.getText();
    	                    try {
    	                    	model.removeRow(i);
    	                    	st.executeUpdate("update menu set item_quantity = item_quantity+'"+item_quantity_field.getText()+"' where item_name='"+val+"'");
   	    	                 
    							st.executeUpdate("delete from cart where item_name='"+val+"' and phone='"+number+"'");
    							 item_name_field.setText("");
    			    	            item_price_field.setText("");
    			    	            item_quantity_field.setText("");
    						} catch (SQLException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
    	                }
    	                else{
    	                	JOptionPane.showMessageDialog(frame1, "Select a cell to delete the row");
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
    	        });
    	        
    	        // get selected row data From table to textfields 
    	        table.addMouseListener(new MouseAdapter(){
    	        	
    	        	public void mouseClicked(MouseEvent e){
    	            
    	                // i = the index of the selected row
    	                int i = table.getSelectedRow();
    	            
    	                
    	                item_name_field.setText(model.getValueAt(i, 0).toString());
    	                item_price_field.setText(model.getValueAt(i, 1).toString());
    	                item_quantity_field.setText(model.getValueAt(i, 2).toString());
    	            
    	            }
    	        });
    	        
    	        // button update row
    	        btn_update.addActionListener(new ActionListener(){

    	            public void actionPerformed(ActionEvent e) {
    	             
    	                // i = the index of the selected row
    	                int i = table.getSelectedRow();
    	                try {
    	            		Class.forName("com.mysql.jdbc.Driver");
    						Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
    								"root", "password@123");
    	            	   Statement st = connection.createStatement();
    	                if(i >= 0) 
    	                {  

    	                   
    	                   
    	                   String val2 = (String) table.getValueAt(i, 0);
       	               	String val3 = (String) table.getValueAt(i, 1);
       	               	String val4 = (String) table.getValueAt(i, 2);
       	               	System.out.println(val2 + " "+val3 + " "+val4);
       	               	
    	                   int field_val = Integer.parseInt(item_quantity_field.getText());
    	                   ResultSet rs3 = st.executeQuery("select * from cart where item_name='"+val2+"' and phone='"+number+"'");
    	                   rs3.next();
    	                   int cart_val = Integer.parseInt(rs3.getString("item_quantity"));
    	                   
    	                   ResultSet rs4 = st.executeQuery("select * from menu where item_name = '"+val2+"'");
    	                  	rs4.next();
    	                  	int menu_val = rs4.getInt("item_quantity");
    	                  	
    	                
    	                   
    	                   if(field_val>menu_val) {
    	                	   JOptionPane.showMessageDialog(frame1, "We are running out of stock!! Please choose another quantity");
    	                   }
    	                   else {
    	                	   
    	                	   model.setValueAt(item_name_field.getText(), i, 0);
        	                   model.setValueAt(item_price_field.getText(), i, 1);
        	                   model.setValueAt(item_quantity_field.getText(), i, 2);
    	                 if(Integer.parseInt(item_quantity_field.getText())>cart_val) {
    	                	 st.executeUpdate("update menu set item_quantity = item_quantity - ('"+item_quantity_field.getText()+"' - '"+cart_val+"') where item_name='"+val2+"'");
    	                 }
    	                 else {
    	                	 st.executeUpdate("update menu set item_quantity = item_quantity + ( '"+cart_val+"' - '"+item_quantity_field.getText()+"') where item_name='"+val2+"'");
    	    	                }
    	                 st.executeUpdate("update cart set item_quantity = '"+item_quantity_field.getText()+"' where item_name='"+val2+"'");
    	                 
    	                   }
    	                }
    	                else{
    	                    JOptionPane.showMessageDialog(frame1, "Select a cell to update");
    	                }
    	                }
    	                catch (ClassNotFoundException e1) {
    						e1.printStackTrace();
    					}
    	                catch (SQLException e1) {
    						e1.printStackTrace();
    					}
    	            }
    	        });
    	        
    	        
    	        JLabel note = new JLabel("NOTE: To Update or Delete the cells you need to select them first ");
    	        note.setBounds(10, 410, 700, 25);
    	        
    	        total.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
									"root", "password@123");
							Statement st = connection.createStatement();
							ResultSet rs5 = st.executeQuery("select * from cart where phone='"+number+"'");
							int total_amt = 0;
							if(rs5.first()) {
								rs5.previous();
							while(rs5.next()) {
								
								total_amt = total_amt + Integer.parseInt(rs5.getString("item_price"))* Integer.parseInt(rs5.getString("item_quantity"));
							  }
							JOptionPane.showMessageDialog(frame1, "Your total amount is " + total_amt);
							}
							else {
								JOptionPane.showMessageDialog(frame1, "Your Cart is empty");
							}
						} catch (ClassNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	            	   
					}
    	        }
    	        );
    	
    	    	
    	        clear.addActionListener(new ActionListener(){

    	            public void actionPerformed(ActionEvent e) {          	
    	           
    	            item_name_field.setText("");
    	            item_price_field.setText("");
    	            item_quantity_field.setText("");
    	            }
    	            
    	            });
    	 
    	        Back.addActionListener(new ActionListener(){

    	            public void actionPerformed(ActionEvent e) {          	
    	                  customer_homepage obj2 = new customer_homepage();
    	                  obj2.menu();
    	            }
    	            
	            });
	 
    	  
    	        frame1.add(note);
    	    	frame1.setLayout(null);
    	    	
    	    	frame1.setSize(900,500);
    	    	frame1.setLocationRelativeTo(null);
    	    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    	frame1.setVisible(true);
    	        
    		}
    		 
}
        
        

    

