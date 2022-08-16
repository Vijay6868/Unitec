import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateDetails extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_address;
	private JLabel lblphone;
	private JLabel lblAddress;
	private JLabel lblPassword;
	private JLabel lblemail;
	private JTextField tf_email;
	private JPasswordField tf_password;
	private String address, email, password;
	private int phone, customerID;
	Customer c;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

	
	

	public int getCustomerID() {
		return customerID;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterAccount dialog = new RegisterAccount();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	Connection con = null;
	private JTextField tfPhone;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblUpdate;
	private JLabel lblAddress_2;
	public UpdateDetails(int phone, String address, String email, String password, int customerID) {
		con = MysqlConnection.dbconnect();
		this.customerID = customerID;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.password = password;
		
		
		//System.out.println("connected to database");
		
		setTitle("update detail");
		setBounds(100, 100, 1080, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setUndecorated(true);
		
		tf_address = new JTextField(getAddress());
		tf_address.setBounds(360, 120, 285, 57);
		tf_address.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPanel.add(tf_address);
		tf_address.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(255, 120, 133, 24);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPanel.add(lblAddress);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 153, 0));
		panel_1.setBounds(160, 47, 842, 328);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		tfPhone = new JTextField(Integer.toString(getPhone()));
		tfPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!Character.isDigit(ch)|| (ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
				e.consume();
				}
			}
		});
		tfPhone.setBounds(203, 140, 285, 24);
		panel_1.add(tfPhone);
		tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tfPhone.setColumns(10);
		
		lblphone = new JLabel("Phone");
		lblphone.setBounds(94, 140, 221, 24);
		panel_1.add(lblphone);
		lblphone.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		tf_email = new JTextField(getEmail());
		tf_email.setBounds(203, 186, 285, 24);
		panel_1.add(tf_email);
		tf_email.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tf_email.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(94, 233, 100, 24);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		tf_password = new JPasswordField(getPassword());
		tf_password.setBounds(203, 234, 285, 24);
		panel_1.add(tf_password);
		tf_password.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		lblemail = new JLabel("Email");
		lblemail.setBounds(94, 186, 78, 24);
		panel_1.add(lblemail);
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 222, 681);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblUpdate = new JLabel("Update");
		lblUpdate.setForeground(new Color(255, 255, 255));
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUpdate.setBounds(10, 91, 133, 24);
		panel.add(lblUpdate);
		
		lblAddress_2 = new JLabel("Details");
		lblAddress_2.setForeground(Color.WHITE);
		lblAddress_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddress_2.setBounds(10, 120, 133, 24);
		panel.add(lblAddress_2);
		
	
	
	     
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton updatebt = new JButton("Update");
				updatebt.setFont(new Font("Tahoma", Font.PLAIN, 16));
				updatebt.addActionListener(new ActionListener() {
					

					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						try {
							PreparedStatement upinfo = (PreparedStatement)con.prepareStatement("update customer set phone = ? , address = ?, email = ?,password = ? where customerID = ?");
							upinfo.setInt(1,Integer.parseInt(tfPhone.getText()));
							upinfo.setString(2, tf_address.getText());
							upinfo.setString(3, tf_email.getText());
							upinfo.setString(4, tf_password.getText());
							upinfo.setInt(5, getCustomerID());
							
							upinfo.executeUpdate();
							
			
										dispose();
										refreshDashboard();
										JOptionPane.showMessageDialog(null, "Details updated !!!");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				updatebt.setActionCommand("Register");
				buttonPane.add(updatebt);
				getRootPane().setDefaultButton(updatebt);
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
