package mytest1;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Label;

public class Friends {
	
	
	private JFrame frame;
	public String username;
	public String Aithmata[] = new String[100];
	public String Filoi[] = new String[100];
	public TextField textField_3_1 = new TextField();
	/**
	 * Launch the application.
	 */

	public static void main2(final String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Friends window2 = new Friends(username);
					window2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public void MysqlKwdikas(String query, String mynhma){
	      Connection con = null;
	      Statement st = null;
	      try{
	          con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
	          st = con.createStatement();
	          st.executeUpdate(query);
	          JOptionPane.showMessageDialog(null,mynhma);
	      }catch(Exception ex){
	          JOptionPane.showMessageDialog(null,ex.getMessage());
	      }
	  }
	
	public Friends(String username2) {
		this.username=username2;
		initialize();

		

	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 457);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Your Friends");
		
		JLabel lblNewLabel = new JLabel("Friends");
		lblNewLabel.setBounds(523, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Friends Requests");
		lblNewLabel_1.setBounds(78, 11, 95, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		final TextField textField_2 = new TextField();
		textField_2.setBounds(229, 31, 129, 22);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Accept");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//checkarei ean ontws exeis aithma filias apo ton tade xrhsth//
				int elegxos = 0;
				
				
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from friends where user_add='"+textField_2.getText()+"' and user_accept='"+username+"'";
				ResultSet rs=stmt.executeQuery(sql);
				if((rs.next())) {
					System.out.println(rs.getString(1));
					if(rs.getString(4).equals("1")) {
						elegxos=1;
						JOptionPane.showMessageDialog(null,"έχεις ήδη αποδεχτεί το αίτημα φιλίας.");
					}
				con.close();
				}
				else{
					elegxos=1;
					JOptionPane.showMessageDialog(null,"Δεν υπάρχει τετοιο αίτημα φιλίας.");
				}
				}catch(Exception ee) {System.out.println(ee);}
				
				
				
				//kanei apodoxh filias//
				if(elegxos==0) {
				try{
		             MysqlKwdikas("update friends set dexthke = 1 where user_accept ='"+username+"' and user_add = '"+textField_2.getText()+"'","Αποδέχτηκες το αίτημα φιλίας");
		         }
		         catch(Exception ex){}
				//telos apodoxhs aithmatos//
				
				}
				}
			
		});
		btnNewButton.setBounds(364, 30, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		final TextField textField_3 = new TextField();
		textField_3.setBounds(229, 59, 129, 22);
		frame.getContentPane().add(textField_3);
		
		JButton btnNewButton_1 = new JButton("Chat");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int elegxos=0;
				
				
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from friends where user_add='"+username+"' and user_accept='"+textField_3.getText()+"'";
				ResultSet rss=stmt.executeQuery(sql);
				while(rss.next())  
					if(rss.getString(4).equals("1")){
						elegxos=1;
				}
				con.close();
						
				}catch(Exception ee) {System.out.println(ee);}	
				
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from friends where user_accept='"+username+"' and user_add='"+textField_3.getText()+"'";
				ResultSet rss=stmt.executeQuery(sql);
				while(rss.next())  
					if(rss.getString(4).equals("1")){
						elegxos=1;
				}
				con.close();
						
				}catch(Exception ee) {System.out.println(ee);}
				if(elegxos==0) {
					JOptionPane.showMessageDialog(null,"Δεν είστε φίλοι.");
				}
				else {
					Chat.Start(username,textField_3.getText());
				}
			}
		});
		btnNewButton_1.setBounds(364, 58, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		final JTextArea friend_requests = new JTextArea();
		friend_requests.setEditable(false);
		friend_requests.setColumns(10);
		friend_requests.setBounds(23, 34, 200, 361);
		frame.getContentPane().add(friend_requests);
		
		final JTextArea friends = new JTextArea();
		friends.setEditable(false);
		friends.setColumns(10);
		friends.setBounds(455, 31, 179, 364);
		frame.getContentPane().add(friends);
		
		
		textField_3_1.setBounds(229, 88, 129, 22);
		frame.getContentPane().add(textField_3_1);
		
		JButton Refreshb = new JButton("Refresh");
		Refreshb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//arxika diagrafei ta onomata ta opoia hdh einai grammena//
				friend_requests.setText("");
				friends.setText("");
				//telos diagrafwn
				int aithmata_c=0;
				int filoi=0;
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from friends where user_accept='"+username+"'";
				Aithmata[aithmata_c]=username;
				ResultSet rss=stmt.executeQuery(sql);
				while(rss.next())  
				if(rss.getString(4).equals("0")) {
					friend_requests.append(rss.getString(2)+"\n");  
				}
				else if(rss.getString(4).equals("1")){
					friends.append(rss.getString(2)+"\n");
				}
				con.close();
						
				}catch(Exception ee) {System.out.println(ee);}
				
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from friends where user_add='"+username+"'";
				Aithmata[aithmata_c]=username;
				ResultSet rss=stmt.executeQuery(sql);
				while(rss.next())  
					if(rss.getString(4).equals("1")){
					friends.append(rss.getString(3)+"\n");
				}
				con.close();
						
				}catch(Exception ee) {System.out.println(ee);}
				//enhmerwsh//
				
			}
			
			
		});
		Refreshb.setBounds(282, 131, 89, 23);
		frame.getContentPane().add(Refreshb);
		
		JButton btnNewButton_1_1 = new JButton("Add");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int elegxos=0;
				//checkarei an exei ginei hdh etoima
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
	
		JButton btnNewButton = new JButton("Friends");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Friends.main2(username);
			}
		});
		btnNewButton.setBounds(623, 384, 89, 23);
		frame.getContentPane().add(btnNewButton);
					String sql="Select * from friends where user_add='"+username+"' and user_accept ='"+textField_3_1.getText()+"'";
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next()) {
					
					JOptionPane.showMessageDialog(null, "έχεις στείλει ήδη αίτημα σε αυτόν τον χρήστη ");
					elegxos=1;
					}
				con.close();
				}catch(Exception ee) {System.out.println(ee);}
				
				//telos checkarismatos gia to ean exei ginei hdh aithma
				
				//checkarei an o xrhsths yparxei
				
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from users where username='"+textField_3_1.getText()+"'";
				ResultSet rs=stmt.executeQuery(sql);
				if(!(rs.next())) {
					JOptionPane.showMessageDialog(null, "αυτός ο χρήστης δεν υπάρχει ");
					elegxos=1;
				}
				con.close();
				}catch(Exception ee) {System.out.println(ee);}
				//telos checkarismatos gia to ean o xrhsths yparxei//
				
				//checkarei ean kanei aithma ston eauto tou//
				if(textField_2.getText().equals(username)) {
					JOptionPane.showMessageDialog(null, "Δεν μπορείς να στείλεις αίτημα στον εαυτό σου. ");
					elegxos=1;
				}
				//
				
				
				
				if(elegxos==0) {
				//efoswn den exei ginei hdh etoima, kai efoswn yparxei o xrhsths, stelnei aithma//
				try{
		             MysqlKwdikas("insert into friends (user_add,user_accept,dexthke) values('"+ username + "','"+ textField_3_1.getText()+"','0')","το αίτημα στάλθηκε");
		         }
		         catch(Exception ex){}
				}
			}
		});
		btnNewButton_1_1.setBounds(364, 87, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		
		

		
		//prosthiki aithmatwn filias kai filwn//
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=con.createStatement();
		String sql="Select * from friends where user_accept='"+username+"'";
		ResultSet rss=stmt.executeQuery(sql);
		while(rss.next())  
		if(rss.getString(4).equals("0")) {
			friend_requests.append(rss.getString(2) + "\n");  
		}
		else if(rss.getString(4).equals("1")){
			friends.append(rss.getString(2) + "\n");
		}
		con.close();
				
		}catch(Exception ee) {System.out.println(ee);}		

		
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=con.createStatement();
		String sql="Select * from friends where user_add='"+username+"'";
		ResultSet rss=stmt.executeQuery(sql);
		while(rss.next())  
			if(rss.getString(4).equals("1")){
			friends.append(rss.getString(3) + "\n");
		}
		con.close();
				
		}catch(Exception ee) {System.out.println(ee);}		
		//telos checkarismatos gia to an einai filoi h oxi//
		
		

	}
}
