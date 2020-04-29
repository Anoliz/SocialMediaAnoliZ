package mytest1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GuiTest {

	private JFrame frame;
	private JTextField txtLogin;
	private JTextField txtPassword;
	protected Object frame1;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainFrame();
	}

	/**
	 * Create the application.
	 */
	
	public static void MainFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTest window = new GuiTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GuiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 227, 284);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login");
		
		txtLogin = new JTextField();
		txtLogin.setText("anoli");
		txtLogin.setBounds(58, 42, 86, 20);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("re");
		txtPassword.setBounds(58, 90, 86, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from users where username='"+txtLogin.getText()+"' and password='" + txtPassword.getText() + "'";
				String username = txtLogin.getText();
				ResultSet rs=stmt.executeQuery(sql);
				if(rs.next()) {
					frame.dispose();
					MainGui.main2(username);
					}
				
				else {
					JOptionPane.showMessageDialog(null, "Λάθος στοιχεία.");
				}
				con.close();
				
				}catch(Exception ee) {System.out.println(ee);}
			}
		});
		btnNewButton.setBounds(58, 173, 86, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Register.main2();
			}
		});
		btnNewButton_1.setBounds(58, 208, 86, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(58, 24, 72, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(58, 73, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
