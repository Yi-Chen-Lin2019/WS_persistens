package Models;

public class CustomerRole {
	private String customerID;
	
	public CustomerRole(String customerID)
	{
		this.customerID = customerID;
	}
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}
