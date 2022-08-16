import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class TranFundDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField amountTf;
	@SuppressWarnings("rawtypes")
	JComboBox fromCB = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox toCB = new JComboBox();
	
	private int bCheque;
	private int bSaving;
	private int customerID;
	private int accNum;;
	
	Connection con = null;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TranFundDialog(int customerID, int bCheque, int accNum,int bSaving) {
		this.customerID = customerID;
		this.bCheque = bCheque;
		this.accNum = accNum;
		this.bSaving = bSaving;
		con = MysqlConnection.dbconnect();
		setTitle("Tranfer Funds");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setUndecorated(true);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 153, 0));
			panel.setBounds(127, 25, 523, 175);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblchooseFrom = new JLabel("From:\r\n");
				lblchooseFrom.setBounds(90, 39, 86, 21);
				panel.add(lblchooseFrom);
				lblchooseFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
			{
				JLabel lblChooseto = new JLabel("To:");
				lblChooseto.setBounds(90, 70, 40, 21);
				panel.add(lblChooseto);
				lblChooseto.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
			{
				fromCB.setBounds(156, 70, 146, 21);
				panel.add(fromCB);
				
				fromCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
				fromCB.setModel(new DefaultComboBoxModel(new String[] {"Cheque","Saving"}));
			}
			{
				toCB.setBounds(156, 39, 146, 21);
				panel.add(toCB);
				
				toCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
				toCB.setModel(new DefaultComboBoxModel(new String[] {"Cheque","Saving"}));
			}
			{
				JLabel lblEnterAamount = new JLabel("Enter $");
				lblEnterAamount.setBounds(90, 102, 58, 21);
				panel.add(lblEnterAamount);
				lblEnterAamount.setFont(new Font("Tahoma", Font.PLAIN, 16));
			}
			{
				amountTf = new JTextField("0");
				amountTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
				amountTf.setBounds(156, 101, 146, 22);
				panel.add(amountTf);
				amountTf.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 0, 51));
			panel.setBounds(0, 0, 178, 441);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblTransfer = new JLabel("Transfer");
				lblTransfer.setForeground(new Color(255, 255, 255));
				lblTransfer.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblTransfer.setBounds(10, 54, 86, 21);
				panel.add(lblTransfer);
			}
			{
				JLabel lblFunds = new JLabel("Funds");
				lblFunds.setForeground(Color.WHITE);
				lblFunds.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblFunds.setBounds(10, 74, 86, 21);
				panel.add(lblFunds);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Transfer");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String from =  fromCB.getSelectedItem().toString();
						String to = toCB.getSelectedItem().toString();
						int amount = Integer.parseInt(amountTf.getText());
						if(from==to && to==from) {
							JOptionPane.showMessageDialog(null,"invalid account selections !!!");
						}
						else if(amount<=0) {
							JOptionPane.showMessageDialog(null,"invalid amount !!!");
						}
						else if( from=="Cheque" && to == "Saving") {
							if(amount> getbCheque()) {
								JOptionPane.showMessageDialog(null,"amount exceeded !!!");
							}
							else {
							try {
								PreparedStatement trans = (PreparedStatement)con.prepareStatement("update account set balance = ? where customerID = ? and accountID = 2");
								trans.setInt(1, amount+getbSaving());
								trans.setInt(2, getCustomerID());
								
								trans.executeUpdate();
								PreparedStatement trans1 = (PreparedStatement)con.prepareStatement("update account set balance = ? where customerID = ? and accountID = 1");
								trans1.setInt(1, getbCheque()-amount);
								trans1.setInt(2, getCustomerID());
								
								trans1.executeUpdate();
								
							dispose();
							refreshDashboard();
							JOptionPane.showMessageDialog(null, "Funds Transferred !!!");
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}
						}
						else if(from.equals("Saving") && to.equals("Cheque")) {
							
							if(amount>getbSaving()) {
								JOptionPane.showMessageDialog(null,"amount exceeded");
							}
							else {
							
							try {
								PreparedStatement trans3 = (PreparedStatement)con.prepareStatement("update account set balance = ? where customerID = ? and accountID = 1");
								trans3.setInt(1, amount+getbCheque());
								trans3.setInt(2, getCustomerID());
								trans3.executeUpdate();
								PreparedStatement trans4 = (PreparedStatement)con.prepareStatement("update account set balance = ? where customerID = ? and accountID = 2");
								trans4.setInt(1, getbSaving()-amount);
								trans4.setInt(2, getCustomerID());
								
								trans4.executeUpdate();
								dispose();
								refreshDashboard();
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						}
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setActionCommand("Transfer");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
