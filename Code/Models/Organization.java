package Models;

public class Organization extends LegalPerson{
    private int organizationID;
    private String orgnizationName;
	private String CVR;
	private CustomerRole customerRole;
	private SupplierRole supplierRole;
    

    public Organization(String phoneNumber, String email, String address, int organizationID, String organizationName, String CVR, CustomerRole customerRole, SupplierRole supplierRole)
    {
        super(phoneNumber, email, address);
        this.organizationID = organizationID;
        this.orgnizationName = organizationName;
        this.CVR = CVR;
        this.customerRole = customerRole;
        this.supplierRole = supplierRole;
    }


	public int getOrganizationID() {
		return organizationID;
	}


	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}


	public String getOrgnizationName() {
		return orgnizationName;
	}


	public void setOrgnizationName(String orgnizationName) {
		this.orgnizationName = orgnizationName;
	}


	public String getCVR() {
		return CVR;
	}


	public void setCVR(String cVR) {
		CVR = cVR;
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
