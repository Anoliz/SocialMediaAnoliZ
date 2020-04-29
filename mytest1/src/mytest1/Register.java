package mytest1;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;


public class Register {





	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
///////////
	 public void MysqlKwdikas(String query,String message){
	      Connection con = null;
	      Statement st = null;
	      try{
	          con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
	          st = con.createStatement();
	          st.executeUpdate(query);
	    	  //ektypwnei to mynhma oti dhmiourghthike o logariasmoss
	          JOptionPane.showMessageDialog(null,message);
	          //telos mynhmatos
	      }catch(Exception ex){
	          JOptionPane.showMessageDialog(null,ex.getMessage());
	      }
	  }
//////////	 
	 
	
	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 245, 222);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Register");
		
		final TextField textField = new TextField();
		textField.setBounds(21, 47, 186, 22);
		frame.getContentPane().add(textField);
		
		final TextField textField_1 = new TextField();
		textField_1.setBounds(21, 110, 186, 22);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int elegxos=0;//arxikopoihsh me 0, dhladh o logariasmos pou paei na eggraftei, den yparxei//
				
				//edw checkarei ean yparxei hdh o logariasmos pou zhththike na ginei eggrafh (1)
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from users where username='"+textField.getText()+"'";
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next()) {
					
					JOptionPane.showMessageDialog(null, "Ο Χρήστης  " +textField.getText() + " υπάρχει ήδη ");
					elegxos=1;
					}
				con.close();
				}catch(Exception ee) {System.out.println(ee);}
				if(elegxos==0) {
				//telos tou checkarismatos (1)
				
				//edw kanei thn eggrafh
				try{
		             MysqlKwdikas("insert into users (username,password) values('"+textField.getText() + "','"+textField_1.getText()+"')", "Ο Λογαριασμός Δημιουργήθηκε επιτυχώς");
		             //efoswn dhmiourgithike o logariasmos, diagrafete to parathiro kai paei pisw sto login
		             GuiTest.MainFrame();
		             frame.dispose();
				}
		         catch(Exception ex){}
		         }
			}
			//eggrafh telos//
		     });
		    


		        
		        
		btnNewButton.setBounds(66, 153, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(21, 27, 64, 14);
		frame.getContentPane().add(lblNewLabel);
		
		Label label = new Label("Password");
		label.setForeground(SystemColor.desktop);
		label.setBackground(SystemColor.control);
		label.setBounds(21, 82, 62, 22);
		frame.getContentPane().add(label);
	}
}
