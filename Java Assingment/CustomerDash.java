import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSeparator;




public class CustomerDash extends JFrame {
	
	private int bCheque;
	private int bSaving;
	private int customerID;
	private int accNum;;

	private JPanel CDcontentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CustomerDash frame = new CustomerDash();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection conn = MysqlConnection.dbconnect();
//	public CustomerDash() {
//		this.customerID = getCustomerID();
//		this.bCheque = getbCheque();
//		this.accNum = getAccNum();
//		this.bSaving = getbSaving();
//	}
	public CustomerDash(int customerID, int bCheque, int accNum,int bSaving) {
		
		
		this.customerID = customerID;
		this.bCheque = bCheque;
		this.accNum = accNum;
		this.bSaving = bSaving;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080,720);
		
		CDcontentPane = new JPanel();
		CDcontentPane.setBackground(new Color(255, 255, 255));
		CDcontentPane.setForeground(Color.WHITE);
		CDcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(CDcontentPane);
		CDcontentPane.setLayout(null);
		
		JButton btntranMoney = new JButton("Transfer Funds");
		btntranMoney.setBackground(new Color(255, 255, 255));
		btntranMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TranFundDialog t = new TranFundDialog(getCustomerID(),getbCheque(),getAccNum(),getbSaving());
				//t.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				t.setVisible(true);
				dispose();
			}
		});
		btntranMoney.setFont(new Font("Tahoma", Font.BOLD, 16));
		btntranMoney.setBounds(32, 82, 185, 38);
		CDcontentPane.add(btntranMoney);
		
		JLabel lbCid = new JLabel("CustomerID: ");
		lbCid.setForeground(new Color(255, 255, 255));
		lbCid.setBackground(new Color(255, 255, 255));
		lbCid.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lbCid.setBounds(43, 39, 81, 13);
		CDcontentPane.add(lbCid);
		
		JLabel lblNewLabel = new JLabel(Integer.toString(getCustomerID()));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(134, 39, 71, 13);
		CDcontentPane.add(lblNewLabel);
		
		JButton btnUpdateDetails = new JButton("Update details");
		btnUpdateDetails.setBackground(new Color(255, 255, 255));
		btnUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = ""; int phone =0; String email =""; String password ="";
				try {
					PreparedStatement updetail = (PreparedStatement)conn.prepareStatement("select phone, address, email, password from customer where customerID = ?");
				   updetail.setInt(1, customerID);
				   
				   Resultset rs = (Resultset) updetail.executeQuery();
				   while(((ResultSet) rs).next()) {
					   
					   phone = ((ResultSet) rs).getInt("phone");
					   address = ((ResultSet)rs).getString("address");
					   email = ((ResultSet) rs).getString("email");
					   password = ((ResultSet) rs).getString("password");
					   
				   }
				   
				   UpdateDetails up = new UpdateDetails(phone,address,email,password,customerID);
				   
				   up.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				   up.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnUpdateDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdateDetails.setBounds(31, 140, 186, 38);
		CDcontentPane.add(btnUpdateDetails);
		
		JButton btnMakePatment = new JButton("Make Payment");
		btnMakePatment.setBackground(new Color(255, 255, 255));
		btnMakePatment.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMakePatment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment p = new Payment(getCustomerID(),getbCheque(),getAccNum(),getbSaving());
				p.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				p.setVisible(true);
				dispose();
			}
		});
		btnMakePatment.setBounds(32, 198, 185, 38);
		CDcontentPane.add(btnMakePatment);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(258, 392, 33, -308);
		CDcontentPane.add(separator);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 153, 51));
		panel_2.setBounds(258, 49, 738, 311);
		CDcontentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblBalance = new JLabel("Balances");
		lblBalance.setBounds(497, 21, 133, 33);
		panel_2.add(lblBalance);
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblCheque = new JLabel(Integer.toString(getbCheque()));
		lblCheque.setBounds(497, 108, 72, 27);
		panel_2.add(lblCheque);
		lblCheque.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JLabel lblAccounts = new JLabel("Accounts");
		lblAccounts.setBounds(95, 22, 185, 31);
		panel_2.add(lblAccounts);
		lblAccounts.setForeground(new Color(0, 0, 0));
		lblAccounts.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblsaving = new JLabel(Integer.toString(getbSaving()));
		lblsaving.setBounds(497, 200, 94, 38);
		panel_2.add(lblsaving);
		lblsaving.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JLabel cBalance_tf = new JLabel("Cheque");
		cBalance_tf.setBounds(106, 108, 105, 27);
		panel_2.add(cBalance_tf);
		cBalance_tf.setBackground(Color.WHITE);
		cBalance_tf.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblcAccNum = new JLabel(Integer.toString(getAccNum())+"-1");
		lblcAccNum.setBounds(111, 78, 169, 20);
		panel_2.add(lblcAccNum);
		lblcAccNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel sBalance_tf = new JLabel("Saving");
		sBalance_tf.setBounds(106, 199, 105, 38);
		panel_2.add(sBalance_tf);
		sBalance_tf.setFont(new Font("Tahoma", Font.PLAIN, 26));
		sBalance_tf.setBackground(Color.WHITE);
		
		JLabel lblsAccNum = new JLabel(Integer.toString(getAccNum())+"-2");
		lblsAccNum.setBounds(106, 169, 169, 20);
		panel_2.add(lblsAccNum);
		lblsAccNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setForeground(new Color(0, 0, 102));
		panel.setBounds(0, 0, 313, 683);
		CDcontentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton(" Delete Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement del = (PreparedStatement)conn.prepareStatement("delete from account where customerID = ?");
					del.setInt(1, getCustomerID());
					del.executeUpdate();
					PreparedStatement del2 = (PreparedStatement)conn.prepareStatement("delete from customer where customerID = ?");
					del2.setInt(1, getCustomerID());
					del2.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(35, 255, 182, 38);
		panel.add(btnNewButton);
	}

	public int getbCheque() {
		return bCheque;
	}

	public void setbCheque(int bCheque) {
		this.bCheque = bCheque;
	}

	public int getbSaving() {
		return bSaving;
	}

	public void setbSaving(int bSaving) {
		this.bSaving = bSaving;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
}
