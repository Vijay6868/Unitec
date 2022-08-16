import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField tfCustomerID;
	private int bCheque;
	private int bSaving;
	private int customerID;
	private int accountID;
	static Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws SQLException{
		// connection
		
		conn = MysqlConnection.dbconnect();
		// connection end
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 0));
		panel.setBounds(267, 55, 742, 333);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btLogin = new JButton("Search");
		btLogin.setBackground(new Color(255, 255, 255));
		btLogin.setBounds(532, 52, 138, 35);
		panel.add(btLogin);
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int customerID=0, cheque=0,accNum=0,saving=0;
				try {
					PreparedStatement acc = (PreparedStatement)conn.prepareStatement("select customerID,balance,accNo from account where customerID = ? and accountID = 1");
					int id = Integer.parseInt(tfCustomerID.getText());
					acc.setInt(1,id);
					ResultSet rs = acc.executeQuery();
					if(rs.next()) {
						customerID = rs.getInt("customerID");	
						cheque = rs.getInt("balance");
						accNum = rs.getInt("accNo");
						PreparedStatement accSaving = (PreparedStatement)conn.prepareStatement("select balance from account where customerID = ? and accountID = 2");
						accSaving.setInt(1, id);
						ResultSet rSaving = accSaving.executeQuery();
						while(rSaving.next()) {
							saving = rSaving.getInt("balance");
							CustomerDash cd = new CustomerDash(customerID,cheque,accNum,saving);
							cd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							cd.setVisible(true);
					}
					
					}
					else {
						JOptionPane.showMessageDialog(null, "no cusomter found with customerID: "+tfCustomerID.getText());
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btLogin.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		tfCustomerID = new JTextField();
		tfCustomerID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!Character.isDigit(ch)|| (ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
				e.consume();
				}
			}
		});
		tfCustomerID.setBounds(328, 53, 181, 31);
		panel.add(tfCustomerID);
		tfCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tfCustomerID.setBackground(new Color(255, 255, 255));
		tfCustomerID.setColumns(10);
		
		JLabel lbCustomerID = new JLabel("CusotmerID:");
		lbCustomerID.setBounds(130, 48, 172, 42);
		panel.add(lbCustomerID);
		lbCustomerID.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 0, 324, 683);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnregister = new JButton("Register New Customer");
		btnregister.setBackground(new Color(255, 255, 255));
		btnregister.setBounds(23, 110, 223, 41);
		panel_1.add(btnregister);
		btnregister.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(new Color(255, 255, 255));
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAdmin.setBounds(75, 58, 107, 42);
		panel_1.add(lblAdmin);
		btnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterAccount reg = new RegisterAccount();
				reg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				reg.setVisible(true);
			}
		});
	}
}
