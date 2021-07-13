
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

public class customer_homepage implements ActionListener{
	DefaultTableModel model;
	JTable table;
	JFrame frame1;
	protected static String name;
    protected static String number;
    protected static int val;
    
	public void menu() {
		// TODO Auto-generated method stub
		
		if (val==1) {
			    JOptionPane.showMessageDialog(frame1, "Existing Customer");
		}
		else if(val==0) {
		      JOptionPane.showMessageDialog(frame1, "New Customer");	
		}
		
		JFrame frame1 = new JFrame();
    	table = new JTable();
    	JPanel panel = new JPanel();
    	
    	JTextField jtf = new JTextField(500);
    	JLabel search1 = new JLabel("Search: ");
    	JButton search2 = new JButton("Find");
    	
        frame1.add(panel); 
        
    	// create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Item Id","Item Name","Item Price","Quantity"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        //setting model to the table
        table.setModel(model);
        frame1.setTitle("Menu");

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        table.setBounds(100, 100, 10000, 10000);
        
    	JLabel id_Label = new JLabel("Item Id: ");
    	JLabel item_name_Label = new JLabel("Item Name: ");
    	JLabel item_price_Label = new JLabel("Item Price: ");
    	JLabel item_quantity_Label = new JLabel("Item Quantity: ");
    	
    	JTextField id_field = new JTextField();
    	JTextField item_name_field = new JTextField();
    	JTextField item_price_field = new JTextField();
    	JTextField item_quantity_field = new JTextField();
    	
    	
    	JButton btn_update = new JButton("Add Item To Cart");
    
    	
    	id_field.setBounds(120,220, 150, 25);
    	item_name_field.setBounds(120,250, 150, 25);
    	item_price_field.setBounds(120,280, 150, 25);
    	item_quantity_field.setBounds(120,310, 150, 25);
    	
    	id_Label.setBounds(20,220, 100, 25);
    	item_name_Label.setBounds(20,250, 100, 25);
    	item_price_Label.setBounds(20,280, 100, 25);
    	item_quantity_Label.setBounds(20,310, 100, 25);
    	
    	
    	btn_update.setBounds(350, 265, 100, 25);
    
    	
    	frame1.add(id_field);
    	frame1.add(item_name_field);
    	frame1.add(item_price_field);
    	frame1.add(item_quantity_field);
    	
    	frame1.add(id_Label);
    	frame1.add(item_name_Label);
    	frame1.add(item_price_Label);
    	frame1.add(item_quantity_Label);
    	
    	JScrollPane pane = new JScrollPane(table);
    	pane.setBounds(0, 0, 880, 200);
    	
    	frame1.add(pane);
    	frame1.add(btn_update);

    	Object[] row = new Object[4];
    	
    	// create an array of objects to set the row data
        
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
					"root", "password@123");
			Statement st = connection.createStatement(); 
			PreparedStatement pst = connection.prepareStatement("select * from menu");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
            	
                String id= rs.getString("id");
                String name = rs.getString("item_name");
                String price = rs.getString("item_price");
                String quantity = rs.getString("item_quantity");
                
                model.addRow(new Object[]{id, name, price, quantity});
                
            }
        }
        catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
    	
    	
       
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        	
        	public void mouseClicked(MouseEvent e){
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
            
                id_field.setText(model.getValueAt(i, 0).toString());
                item_name_field.setText(model.getValueAt(i, 1).toString());
                item_price_field.setText(model.getValueAt(i, 2).toString());
                item_quantity_field.setText(model.getValueAt(i, 3).toString());
            
            }
        });
        
        
        // button update row
        btn_update.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
             
            	int v=0;
                // i = the index of the selected row
                int i = table.getSelectedRow();
                try {
            		Class.forName("com.mysql.jdbc.Driver");
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false",
							"root", "password@123");
            	   Statement st = connection.createStatement();
                if(i >= 0) 
                {  
                	ResultSet rs = st.executeQuery("select * from menu where id = '"+id_field.getText()+"'");
                	rs.next();
                	int a = rs.getInt("item_quantity");
                	int b = Integer.parseInt(item_quantity_field.getText());
                	String x = String.valueOf(a-b);
                   model.setValueAt(id_field.getText(), i, 0);
                   model.setValueAt(item_name_field.getText(), i, 1);
                   model.setValueAt(item_price_field.getText(), i, 2);
                   model.setValueAt(x, i, 3);

                String val1 = (String) table.getValueAt(i, 0); 
               	String val2 = (String) table.getValueAt(i, 1);
               	String val3 = (String) table.getValueAt(i, 2);
               	String val4 = (String) table.getValueAt(i, 3);
               	
               	ResultSet rs1 = st.executeQuery("select * from cart where phone = '"+number+"'");
               	if(rs1.next()) {
               		while(rs1.next()) {
               		System.out.println(rs1.getString("item_name"));
               		System.out.println(val2);
               		System.out.println(number);
               		if (rs1.getString("item_name").equals(val2) && rs1.getString("phone").equals(number)) {
               			System.out.println("Hi");
               			String sql2 = "update cart set item_quantity= item_quantity + '"+b+"' where phone='"+number+"'";
                       	st.executeUpdate(sql2);
               		}
               		else {
               			System.out.println(val1+" " +val2 + " "+val3 + " "+val4);
                       	String sql1 = "insert into cart values('"+number+"','"+val2+"','"+val3+"','"+item_quantity_field.getText()+"')";
                       	st.executeUpdate(sql1);
               		}
               		}
               	}
               	else {
               		String sql1 = "insert into cart values('"+number+"','"+val2+"','"+val3+"','"+item_quantity_field.getText()+"')";
                   	st.executeUpdate(sql1);
               	}
               	
               	
               	/*
               	System.out.println(val1+" " +val2 + " "+val3 + " "+val4);
               	String sql1 = "insert into cart values('"+val1+"','"+val2+"','"+val3+"','"+val4+"')";
               	st.executeUpdate(sql1);
            	*/
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
        
        
        
  
       
   

    	frame1.setLayout(null);
    	
    	frame1.setSize(900,500);
    	frame1.setLocationRelativeTo(null);
    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame1.setVisible(true);
        
	}
	 
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}

}

