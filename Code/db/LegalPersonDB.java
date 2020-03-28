package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Models.*;

public class LegalPersonDB implements LegalPersonDBIF {
	private LegalPersonDB legalPersonDB;
	private static final String FIND_ALL_Q = 
			"SELECT Legal_Person.id, personType, fname, lname, title, salary, phoneNumber, email, (StreetName + ' ' + StreetNo +' '+ PostCode +' '+ city + ' '+ country) AS address, companyName, CVRno, SSN, supplyType, CustomerId FROM (Legal_Person LEFT OUTER JOIN Person ON Legal_Person.Id = Person.Id LEFT OUTER JOIN Organization ON Legal_Person.Id = Organization.Id LEFT OUTER JOIN Address ON Address.AddressId = Legal_Person.Id LEFT OUTER JOIN POSTCODE ON POSTCODE.PostalCode = ADDRESS.PostCode LEFT OUTER JOIN EMPLOYEE_ROLE ON EMPLOYEE_ROLE.PersonId = LEGAL_PERSON.Id LEFT OUTER JOIN CUSTOMER_ROLE ON CUSTOMER_ROLE.LegalPersonId = LEGAL_PERSON.Id LEFT OUTER JOIN SUPPLIER_ROLE ON SUPPLIER_ROLE.LegalPersonId = LEGAL_PERSON.Id)";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " WHERE Legal_Person.id = ?";
	private static final String FIND_BY_NAME_Q = FIND_ALL_Q + " WHERE fname = ?";
	private PreparedStatement findAllPerson; 
	private PreparedStatement findById;
	private PreparedStatement findByName;
	//insert part
	private static final String INSERT_Q = "insert into Legal_Person (phoneNumber, email, personType, addressId) values (?, ?, ?, ?)";
	private PreparedStatement insertPS;
	private static final String INSERT_PERSON_Q = "INSERT INTO PERSON (id, fname, lname) VALUES (?,?,?)";
	private PreparedStatement insertP;
	
	public LegalPersonDB() throws DataAccessException {
		init();
	}
	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllPerson = connection.prepareStatement(FIND_ALL_Q);
			findById = connection.prepareStatement(FIND_BY_ID_Q);
			findByName = connection.prepareStatement(FIND_BY_NAME_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	@Override
	public List<LegalPerson> findAll() throws DataAccessException {		
		ResultSet rs;
		try {
			rs = this.findAllPerson.executeQuery();		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<LegalPerson> res = buildObjects(rs);
		return res;
	}

	@Override
	public LegalPerson findById(String id) throws DataAccessException {
		LegalPerson res = null;
		try {
			findById.setString(1, id);
			ResultSet rs = findById.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}
		
	@Override
	public List<LegalPerson> findByName(String name) throws DataAccessException {
		List<LegalPerson> res = null;
		try {
			findByName.setString(1, "%" + name + "%");
			if (name != null && name.length() == 1) {
				findByName.setString(2, name);
			} else {
				findByName.setString(2, "");
			}
			findByName.setString(3, "%" + name + "%");
			ResultSet rs = findByName.executeQuery();
			res = buildObjects(rs);
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}
	private List<LegalPerson> buildObjects(ResultSet rs) throws DataAccessException {
		List<LegalPerson> res = new ArrayList<>();
		try {
			while (rs.next()) {
				LegalPerson currLegalPerson = buildObject(rs);
				res.add(currLegalPerson);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private LegalPerson buildObject(ResultSet rs) throws DataAccessException {
		try {
			if(rs.getString("personType").equals("Person")) {
				LegalPerson res = buildPerson(rs);
				return res;
			} 
			if (rs.getString("personType").equals("Organization")){
				LegalPerson res = buildOrganization(rs);
				return res;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Person buildPerson(ResultSet rs) throws DataAccessException {
		Person res = new Person(null, null, null, null ,null, null, null, null);
		try {
			//String phoneNumber, String email, String address, String fName, String lName, 
			//EmployeeRole employeeRole, CustomerRole customerRole, SupplierRole supplierRole
			res.setPhoneNumber(rs.getString("phoneNumber"));
			res.setEmail(rs.getString("email"));
			res.setAddress(rs.getString("address"));
			//person
			res.setId(rs.getInt("id"));
			res.setfName(rs.getString("fname"));
			res.setlName(rs.getString("lname"));
			
			//String SSN, String title, double salary
			EmployeeRole e = new EmployeeRole(rs.getString("ssn"), rs.getString("title"), rs.getDouble("salary"));
			res.setEmployeeRole(e);
			
			//customerID
			CustomerRole c = new CustomerRole(rs.getString("customerId"));
			res.setCustomerRole(c);
			
			//String supplyType
			SupplierRole s = new SupplierRole(rs.getString("supplyType"));
			res.setSupplierRole(s);
			
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	private Organization buildOrganization(ResultSet rs) throws DataAccessException {
		//String organizationID, String organizationName, String CVR, 
		//CustomerRole customerRole, SupplierRole supplierRole
		Organization res = new Organization(null, null, null, 0, null, null, null, null);
		try {
			res.setPhoneNumber(rs.getString("phoneNumber"));
			res.setEmail(rs.getString("email"));
			res.setAddress(rs.getString("address"));
			//organization
			res.setOrganizationID(rs.getInt("id"));
			res.setOrgnizationName(rs.getString("CompanyName"));
			res.setCVR(rs.getString("CVRno"));
			
			//customerID
			CustomerRole c = new CustomerRole(rs.getString("customerId"));
			res.setCustomerRole(c);
			
			//String supplyType
			SupplierRole s = new SupplierRole(rs.getString("supplyType"));		
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	@Override
	public LegalPerson insert(LegalPerson person) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
		
				
	

	

}
