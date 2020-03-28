package Models;

public class Person extends LegalPerson 
{
    private int id;
    private String fName;
    private String lName;
    private EmployeeRole employeeRole;
    private CustomerRole customerRole;
    private SupplierRole supplierRole;

    public Person(String phoneNumber, String email, String address, String fName, String lName, EmployeeRole employeeRole, CustomerRole customerRole, SupplierRole supplierRole)
    {
        super(phoneNumber, email, address);
        this.fName = fName;
        this.lName = lName;
        this.employeeRole = employeeRole;
        this.customerRole = customerRole;
        this.supplierRole = supplierRole;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}

	public CustomerRole getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(CustomerRole customerRole) {
		this.customerRole = customerRole;
	}

	public SupplierRole getSupplierRole() {
		return supplierRole;
	}

	public void setSupplierRole(SupplierRole supplierRole) {
		this.supplierRole = supplierRole;
	}

}
