import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterAccount extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_firstName;
	private JTextField tflastName;
	private JTextField tf_phone;
	private JTextField tf_dob;
	private JTextField tf_address;
	private JLabel lblLastName;
	private JLabel lblphone;
	private JLabel lbldob;
	private JLabel lblAddress;
	private JLabel lblPassword;
	private JLabel lblemail;
	private JTextField tf_email;
	private JPasswordField tf_password;
	JComboBox comboBox ;
	Customer c;
	

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
	private JComboBox combo_gen;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblEnter;
	private JLabel lblForNew;
	private JLabel lblCustomer;
	private JLabel lblNewLabel;
	public RegisterAccount() {
		
		
		con = MysqlConnection.dbconnect();
		//System.out.println("connected to database");
		
		setTitle("Register New Customer");
		setBounds(100, 100, 1080, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setUndecorated(true);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(304, 108, 120, 24);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPanel.add(lblFirstName);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(304, 135, 120, 24);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPanel.add(lblLastName);
		
		lblphone = new JLabel("Phone");
		lblphone.setBounds(304, 164, 120, 24);
		lblphone.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPanel.add(lblphone);
		
		lbldob = new JLabel("D.O.B");
		lbldob.setBounds(304, 196, 65, 24);
		lbldob.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPanel.add(lbldob);
		
		JLabel lblgender = new JLabel("Gender");
		lblgender.setBounds(304, 226, 120, 24);
		lblgender.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPanel.add(lblgender);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 153, 0));
		panel_1.setBounds(192, 37, 770, 467);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		tf_address = new JTextField();
		tf_address.setBounds(278, 217, 359, 82);
		panel_1.add(tf_address);
		tf_address.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tf_address.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(110, 217, 144, 24);
		panel_1.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		{
			tf_firstName = new JTextField();
			tf_firstName.setBounds(278, 68, 359, 24);
			panel_1.add(tf_firstName);
			tf_firstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
			tf_firstName.setColumns(10);
		}
		
		tflastName = new JTextField();
		tflastName.setBounds(278, 97, 359, 24);
		panel_1.add(tflastName);
		tflastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tflastName.setColumns(10);
		
		tf_phone = new JTextField();
		tf_phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!Character.isDigit(ch)|| (ch==KeyEvent.VK_BACK_SPACE || ch==KeyEvent.VK_DELETE)){
				e.consume();
		}
			}
		});
		tf_phone.setBounds(278, 126, 359, 24);
		panel_1.add(tf_phone);
		tf_phone.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tf_phone.setColumns(10);
		
		tf_dob = new JTextField();
		tf_dob.setBounds(278, 155, 359, 24);
		panel_1.add(tf_dob);
		tf_dob.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tf_dob.setColumns(10);
		
		combo_gen = new JComboBox();
		combo_gen.setBounds(278, 185, 359, 24);
		panel_1.add(combo_gen);
		combo_gen.setFont(new Font("Tahoma", Font.PLAIN, 22));
		combo_gen.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		
		lblemail = new JLabel("Email");
		lblemail.setBounds(110, 352, 120, 24);
		panel_1.add(lblemail);
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(110, 386, 120, 24);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		tf_email = new JTextField();
		tf_email.setBounds(278, 352, 359, 24);
		panel_1.add(tf_email);
		tf_email.setFont(new Font("Tahoma", Font.PLAIN, 22));
		tf_email.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setBounds(278, 386, 359, 24);
		panel_1.add(tf_password);
		tf_password.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		lblNewLabel = new JLabel("(ddmmyy) ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(181, 159, 69, 24);
		panel_1.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 263, 644);
		panel.setBackground(new Color(0, 0, 51));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblEnter = new JLabel("Enter Details");
		lblEnter.setForeground(new Color(255, 255, 255));
		lblEnter.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnter.setBounds(10, 70, 166, 24);
		panel.add(lblEnter);
		
		lblForNew = new JLabel("For New");
		lblForNew.setForeground(new Color(255, 255, 255));
		lblForNew.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblForNew.setBounds(10, 104, 120, 24);
		panel.add(lblForNew);
		
		lblCustomer = new JLabel("Customer");
		lblCustomer.setForeground(new Color(255, 255, 255));
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCustomer.setBounds(10, 138, 120, 24);
		panel.add(lblCustomer);
		
	
	
	     
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Register");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.addActionListener(new ActionListener() {
					//private String item;
						
					public void actionPerformed(ActionEvent e) {
						String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
						Pattern pattern = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(tf_email.getText());
						if(tf_email.getText()==null || (tf_email.getText()).isEmpty() || matcher.find() ==true) {
							JOptionPane.showMessageDialog(null, "Invalid email !!!");
						}
						else if(checkString(tf_firstName.getText())){
							JOptionPane.showMessageDialog(null, "Invalid First name !!!");
						}
						else if(checkString(tflastName.getText())) {
							JOptionPane.showMessageDialog(null, "Invalid Last name !!!");
						}
						else if(tf_password.getText().length()<5) {
							JOptionPane.showMessageDialog(null, "password must be 6 charachter long !!!");
						}
						//else if()
						else {
						try {
							String gen = combo_gen.getSelectedItem().toString();
							 c = new Customer(tf_firstName.getText(),tflastName.getText(),Integer.parseInt(tf_phone.getText()),Integer.parseInt(tf_dob.getText()), tf_address.getText(),tf_email.getText(), tf_password.getText(),gen);

						
							PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into customer(firstName,lastName,phone,address,email,password,dob,Gender) values(?,?,?,?,?,?,?,?)");

							pst.setString(1, c.getFirstName());
							pst.setString(2, c.getLastName());
							pst.setInt(3, c.getPhone());
							pst.setString(4, c.getAddress());
							pst.setString(5,  c.getEmail());
							pst.setString(6, c.getPassword());
							pst.setInt(7, c.getDob());
							
							pst.setString(8, c.getGender());
							
							
										int accNo =0;
										pst.executeUpdate();	
										//JOptionPane.showMessageDialog(null,"registered !!!");
										PreparedStatement cid = (PreparedStatement) con.prepareStatement("select max(customerID) from customer");
										ResultSet rs = cid.executeQuery();
										while(rs.next()) {
											int customerID = rs.getInt("max(customerID)");
											JOptionPane.showMessageDialog(null,"Account is registered !!!\n your customer ID is "+customerID);
											c.setCustomerID(customerID);
										}
										PreparedStatement AcNum = (PreparedStatement)con.prepareStatement("select max(accNo) from account");
										ResultSet rs1 = AcNum.executeQuery();
										while(rs1.next()) {
											accNo = rs1.getInt("max(accNo)");
										}
										
										accNo = accNo+1;
										PreparedStatement accGen = extractede(c.getCustomerID(),1,accNo);
										
											accGen.executeUpdate();
											
											PreparedStatement accGen2 = extractede(c.getCustomerID(),2,accNo);
											accGen2.executeUpdate();
											
											
											
										dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
					public PreparedStatement extractede(int customerID, int accountID,  int accNo) throws SQLException {
						
						PreparedStatement accGen= (PreparedStatement) con.prepareStatement("insert into account(customerID,accountID,balance,accNo) values(?,?,?,?)");
						accGen.setInt(1, c.getCustomerID());
						accGen.setInt(2,accountID);
						accGen.setInt(3,0);
						accGen.setInt(4,accNo);
						return accGen;
					}
				});
				okButton.setActionCommand("Register");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public boolean checkString(String str) {
		if(str.equals(null) && !str.matches("^[a-zA-Z]*$") && str.length()<3) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
