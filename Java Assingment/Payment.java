import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Payment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField amounttf;
	private JTextField transferAccTf;
	private JTextField suffixTf;
	JComboBox selAcc_cb = new JComboBox();
	
	private int bCheque;
	private int bSaving;
	private int customerID;
	private int accNum;;
	Connection con = null;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Payment dialog = new Payment();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Payment(int customerID, int bCheque, int accNum,int bSaving) {
		setTitle("Make Payment");
		
		this.customerID = customerID;
		this.bCheque = bCheque;
		this.accNum = accNum;
		this.bSaving = bSaving;
		
		con = MysqlConnection.dbconnect();
		
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setUndecorated(true);
		{
			
			selAcc_cb.setFont(new Font("Tahoma", Font.PLAIN, 16));
			selAcc_cb.setModel(new DefaultComboBoxModel(new String[] {"Cheque", "Saving"}));
			selAcc_cb.setBounds(285, 72, 116, 21);
			contentPanel.add(selAcc_cb);
		}
		{
			JLabel selectAcc_lbl = new JLabel("select account:");
			selectAcc_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
			selectAcc_lbl.setBounds(158, 76, 108, 13);
			contentPanel.add(selectAcc_lbl);
		}
		{
			JLabel amountlbl = new JLabel("enter amount");
			amountlbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
			amountlbl.setBounds(158, 119, 108, 13);
			contentPanel.add(amountlbl);
		}
		{
			JLabel enterAcclbl = new JLabel("transfer acc no.");
			enterAcclbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
			enterAcclbl.setBounds(158, 158, 122, 13);
			contentPanel.add(enterAcclbl);
		}
		{
			amounttf = new JTextField();
			amounttf.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char ch = e.getKeyChar();
					if(!Character.isDigit(ch)|| (ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
					e.consume();
					}
				}
			});
			amounttf.setFont(new Font("Tahoma", Font.PLAIN, 16));
			amounttf.setBounds(285, 118, 116, 19);
			contentPanel.add(amounttf);
			amounttf.setColumns(10);
		}
		{
			transferAccTf = new JTextField("0");
			transferAccTf.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char ch = e.getKeyChar();
					if(!Character.isDigit(ch)|| (ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
					e.consume();
					}
				}
			});
			transferAccTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
			transferAccTf.setColumns(10);
			transferAccTf.setBounds(285, 157, 116, 19);
			contentPanel.add(transferAccTf);
		}
		{
			suffixTf = new JTextField("0");
			suffixTf.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char ch = e.getKeyChar();
					if(!Character.isDigit(ch)|| (ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
					e.consume();
					}
				}
			});
			suffixTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
			suffixTf.setColumns(10);
			suffixTf.setBounds(411, 157, 41, 19);
			contentPanel.add(suffixTf);
		}
		
		JLabel suffixlbl = new JLabel("suffix");
		suffixlbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		suffixlbl.setBounds(462, 158, 108, 13);
		contentPanel.add(suffixlbl);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 153, 0));
			panel.setBounds(106, 40, 562, 195);
			contentPanel.add(panel);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 0, 51));
			panel.setBounds(0, 0, 146, 441);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel selectAcc_lbl = new JLabel("Make");
				selectAcc_lbl.setForeground(new Color(255, 255, 255));
				selectAcc_lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
				selectAcc_lbl.setBounds(10, 64, 76, 13);
				panel.add(selectAcc_lbl);
			}
			{
				JLabel selectAcc_lbl = new JLabel("Payment");
				selectAcc_lbl.setForeground(Color.WHITE);
				selectAcc_lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
				selectAcc_lbl.setBounds(10, 87, 76, 13);
				panel.add(selectAcc_lbl);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton paymentbt = new JButton("Make Payment");
				paymentbt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String selection = selAcc_cb.getSelectedItem().toString();
						int amount = Integer.parseInt(amounttf.getText());
						int traAcc = Integer.parseInt(transferAccTf.getText());
						int traBal =0;
						String suffix = suffixTf.getText();
						
						
						
							if(amount>getbCheque()) {
								JOptionPane.showMessageDialog(null,"amount exceeded !!!");
							}
							else if(suffix.length()>=2 || !suffix.equals("1") || !suffix.equals("2"))
							{
								JOptionPane.showMessageDialog(null,"invalid suffix !!!");
							}
							else {
								
								try {
									PreparedStatement bal = (PreparedStatement)con.prepareStatement("select balance from account where accNo =? and accountID =?");
									bal.setInt(1,traAcc);
									bal.setInt(2, Integer.parseInt(suffix));
									ResultSet rs = bal.executeQuery();
									if(rs.next()) {
										traBal = rs.getInt("balance");
										PreparedStatement trans = (PreparedStatement)con.prepareStatement("update account set balance = ? where accNo = ? and accountID = ?");
										trans.setInt(1, amount+traBal);
										trans.setInt(2,traAcc);
										trans.setInt(3, Integer.parseInt(suffix));
										
										trans.executeUpdate();
										if(selection.equals("Cheque")) {
										PreparedStatement trans1 = (PreparedStatement)con.prepareStatement("update account set balance = ? where customerID = ? and accountID = 1");
										trans1.setInt(1, getbCheque()-amount);
										trans1.setInt(2, getCustomerID());
										
										trans1.executeUpdate();
										}
										else if(selection.equals("Saving")) {
											PreparedStatement trans2 = (PreparedStatement)con.prepareStatement("update account set balance = ? where customerID = ? and accountID = 2");
											trans2.setInt(1, getbSaving()-amount);
											trans2.setInt(2, getCustomerID());
											
											trans2.executeUpdate();
										}
										dispose();
										refreshDashboard();
										JOptionPane.showMessageDialog(null, "Payment made successfully");
									}
									else {
										JOptionPane.showMessageDialog(null,"inavalid account number");
									}
									
								
									
								
								
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						
					}
				});
				paymentbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
				paymentbt.setActionCommand("OK");
				buttonPane.add(paymentbt);
				getRootPane().setDefaultButton(paymentbt);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						refreshDashboard();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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
	 public void refreshDashboard() {
		 int customerID=0, cheque=0,accNum=0,saving=0;
			try {
				PreparedStatement acc = (PreparedStatement)con.prepareStatement("select customerID,balance,accNo from account where customerID = ? and accountID = 1");
				int id = getCustomerID();
				acc.setInt(1,id);
				ResultSet rs = acc.executeQuery();
				while(rs.next()) {
					customerID = rs.getInt("customerID");	
					cheque = rs.getInt("balance");
					accNum = rs.getInt("accNo");
				}
				PreparedStatement accSaving = (PreparedStatement)con.prepareStatement("select balance from account where customerID = ? and accountID = 2");
				accSaving.setInt(1, id);
				ResultSet rSaving = accSaving.executeQuery();
				while(rSaving.next()) {
					saving = rSaving.getInt("balance");
				}
				CustomerDash cd = new CustomerDash(customerID,cheque,accNum,saving);
				cd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cd.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 }
	
}
