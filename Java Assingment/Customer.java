import java.sql.Date;

public class Customer {
private int customerID;
 public int getCustomerID() {
	return customerID;
}
public void setCustomerID(int customerID) {
	this.customerID = customerID;
}
private String firstName;
 private String lastName;
 private int phone;
 private int dob;
 private String address;
 private String email;
 private String password;
 private String gender;
public String getGender() {
	return gender;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public int getPhone() {
	return phone;
}
public void setPhone(int phone) {
	this.phone = phone;
}
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
public int getDob() {
	return dob;
}
public Customer(String firstName, String lastName, int phone, int dob, String address, String email, String password,String Gender) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.dob = dob;
	this.address = address;
	this.email = email;
	this.password = password;
	this.gender = Gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
}
