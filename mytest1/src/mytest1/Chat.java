package mytest1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;

public class Chat {

	private JFrame frame;
	private JTextField textField_1;
	public String username;
	public String atomo_poy_milas;
	public static String[][] Messages = new String[50][4];
	public int[][] id = new int[50][2];
	int seconds=0;
	public int i;
	public JTextArea chat = new JTextArea();
	
	Timer timerr = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			seconds++;
			MessagesReload();
		}
	};
	
	public void startTimer() {
		timerr.scheduleAtFixedRate(task,50,1000);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void Start(final String username,final String atomo_poy_milas) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat window = new Chat(username,atomo_poy_milas);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param atomo_poy_milas 
	 * @param username 
	 */
	
	
	public static void bubbleSort(String[][] messages,int[][] id,int i) {
	    boolean sorted = false;
	    int temp;
	    String tempString;
	    while(!sorted) {
	        sorted = true;
	        for (int k = 0; k < i-1; k++) {
	            if (id[k][0] > id[k+1][0]) {
	                temp = id[k][0];
	                id[k][0] = id[k+1][0];
	                id[k+1][0] = temp;
	                temp = id[k][1];
	                id[k][1] = id[k+1][1];
	                id[k+1][1] = temp;
	                
	                tempString=Messages[k][1];
	                Messages[k][1]=Messages[k+1][1];
	                Messages[k+1][1]=tempString;
	                tempString=Messages[k][2];
	                Messages[k][2]=Messages[k+1][2];
	                Messages[k+1][2]=tempString;
	                tempString=Messages[k][3];
	                Messages[k][3]=Messages[k+1][3];
	                Messages[k+1][3]=tempString;
	                tempString=Messages[k][0];
	                Messages[k][0]=Messages[k+1][0];
	                Messages[k+1][0]=tempString;
	                
	                sorted = false;
	            }
	        }
	    }
	}
	
	
	public Chat(String username, String atomo_poy_milas) {
		this.username=username;
		this.atomo_poy_milas=atomo_poy_milas;
		initialize();
		startTimer();
		
	}

	public void MysqlKwdikas(String query, String mynhma){
	      Connection con1 = null;
	      Statement st = null;
	      try{
	          con1 = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
	          st = con1.createStatement();
	          st.executeUpdate(query);
	          if(!(mynhma.equals(""))) {
	          JOptionPane.showMessageDialog(null,mynhma);
	          }
	      }catch(Exception ex){
	          JOptionPane.showMessageDialog(null,ex.getMessage());
	      }
	  }
	
	public void MessagesReload() {
		i=0;
		
		
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=con.createStatement();
		String sql="Select * from messages where name1='"+username+"' and name2='"+ atomo_poy_milas + "'";
		ResultSet rss=stmt.executeQuery(sql);
		while(rss.next()) {
			id[i][0]=rss.getInt(1);
			id[i][1]=1;
			Messages[i][0]=rss.getString(2);
			Messages[i][1]=rss.getString(3);
			Messages[i][2]=rss.getString(4);
			Messages[i][3]=rss.getString(5);
			i++;
		}
		con.close();
				
		}catch(Exception ee) {System.out.println(ee);}
		
		try {Class.forName("com.mysql.jdbc.Driver");
		Connection con2= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		Statement stmt=con2.createStatement();
		String sql2="Select * from messages where name2='"+username+"' and name1='"+ atomo_poy_milas + "'";
		ResultSet rss=stmt.executeQuery(sql2);
		while(rss.next()) {
			id[i][0]=rss.getInt(1);
			id[i][1]=2;
			Messages[i][0]=rss.getString(2);
			Messages[i][1]=rss.getString(3);
			Messages[i][2]=rss.getString(4);
			Messages[i][3]=rss.getString(5);
			i++;
		}
		con2.close();
				
		}catch(Exception ee) {System.out.println(ee);}
		

		
		bubbleSort(Messages,id,i);
		chat.setEditable(false);
		
		chat.setText("");
		
		for(int k=0; k<i; k++) {
			if(id[i][1]==1) {
			chat.append(Messages[k][1] + ": " + Messages[k][2] + " (" + Messages[k][3] + ")\n");
			}
			else {
			chat.append(Messages[k][0] + ": " + Messages[k][2] + " (" + Messages[k][3] + ")\n");
			}
		}
		

	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 567, 556);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Chat with : " + atomo_poy_milas);
		chat.setColumns(500);
		chat.setBounds(10, 56, 527, 370);
		frame.getContentPane().add(chat);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(10, 11, 527, 33);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 437, 439, 62);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		
		
		MessagesReload();
		
		

		
		
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try{
			             MysqlKwdikas("insert into messages (name1,name2,message) values('"+ username + "','"+ atomo_poy_milas+"','"+textField_1.getText()+"')","");
			         }
			         catch(Exception ex){}
			         }
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(460, 437, 77, 62);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel.setText("Συνομιλία με "+ atomo_poy_milas);
	}
}
