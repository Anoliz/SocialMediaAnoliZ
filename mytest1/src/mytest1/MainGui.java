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

public class MainGui {

	private JFrame frame;
	public String username;
	private JTextArea textField;

	/**
	 * Launch the application.
	 */
	
	//timer gia na checkarei ta status kathe defterolepto
	int seconds=0;
	Timer timerr = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			seconds++;
			checkstatus();
		}
	};
	
	public void startTimer() {
		timerr.scheduleAtFixedRate(task,50,1000);
	}
	//telos_timer
	
	
	public static void main2(final String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui(username);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
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
	
	
	/**
	 * Create the application.
	 * @param username2 
	 */
	public MainGui(String username2) {
		this.username = username2;
		initialize();
		
		startTimer();
		
	}
	
	
	public void checkstatus() {
		
		textField.setText("");
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=con.createStatement();
		String sql="Select * from posts where username='"+username+"'";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())  
		textField.append("ο Χρήστης " + rs.getString(1) + " έγραψε " + rs.getString(2) + " στις " +  rs.getString(3) + "\n");  
		con.close();
		
		}catch(Exception ee) {System.out.println(ee);}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 728, 733, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(username);
		
		textField = new JTextArea();
		textField.setEditable(false);
		textField.setBounds(10, 75, 704, 223);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Your Posts");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 46));
		lblNewLabel.setBounds(10, 0, 265, 74);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Add new post:");
		lblNewLabel_1.setBounds(10, 309, 74, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		final TextField textField_1 = new TextField();
		textField_1.setBounds(10, 329, 173, 22);
		frame.getContentPane().add(textField_1);
		
		
		
		JButton Post = new JButton("Post");
		Post.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		             MysqlKwdikas("insert into posts (username,post) values('"+ username + "','"+ textField_1.getText()+"')","το Post έγινε");
		         }
		         catch(Exception ex){}

		         }


		});
		Post.setBounds(186, 328, 89, 23);
		frame.getContentPane().add(Post);
		
		JButton btnNewButton_2 = new JButton("Friends");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Friends.main2(username);
			}
		});
		btnNewButton_2.setBounds(628, 329, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
}
