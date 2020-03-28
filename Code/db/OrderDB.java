package db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.*;


public class OrderDB implements OrderDBIF{
	private OrderDBIF orderDB;
	private static final String FIND_ALL_Q = 
			"SELECT GENERIC_ORDER.orderId, orderDate, deliveryDate, orderType, (StreetName + ' ' + StreetNo +' '+ PostCode +' '+ city + ' '+ country) AS address, isDelivered, amount, employeeId, Rent_order.customerId, SALE_ORDER.CustomerId, discountValue, isDeliveryDiscount, SUPPLY_ORDER.SupplierId  FROM (Generic_Order LEFT OUTER JOIN Address ON Address.AddressId = Generic_Order.DeliveryAddressId LEFT OUTER JOIN POSTCODE ON POSTCODE.PostalCode = ADDRESS.PostCode LEFT OUTER JOIN SALE_ORDER ON SALE_ORDER.OrderId = GENERIC_ORDER.OrderId LEFT OUTER JOIN RENT_ORDER ON RENT_ORDER.OrderId = GENERIC_ORDER.OrderId LEFT OUTER JOIN SUPPLY_ORDER ON SUPPLY_ORDER.OrderId = GENERIC_ORDER.OrderId LEFT OUTER JOIN DISCOUNT ON DISCOUNT.DiscountId = SALE_ORDER.DiscountId)";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " WHERE GENERIC_ORDER.orderId = ?";
	private PreparedStatement findAllOrder; 
	private PreparedStatement findById;
	
	public OrderDB() throws DataAccessException {
		init();
	}
	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllOrder = connection.prepareStatement(FIND_ALL_Q);
			findById = connection.prepareStatement(FIND_BY_ID_Q);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	
	@Override
	public List<GenericOrder> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllOrder.executeQuery();		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<GenericOrder> res = buildObjects(rs);
		return res;
	}

	@Override
	public GenericOrder findById(int id) throws DataAccessException {
		GenericOrder res = null;
		try {
			findById.setInt(1, id);
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
	private List<GenericOrder> buildObjects(ResultSet rs) throws DataAccessException {
		List<GenericOrder> res = new ArrayList<>();
		try {
			while (rs.next()) {
				GenericOrder currGenericOrder = buildObject(rs);
				res.add(currGenericOrder);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private GenericOrder buildObject(ResultSet rs) throws DataAccessException {
		try {
			if(rs.getString("orderType").equals("Rent")) {
				GenericOrder res = buildRent(rs);
				return res;
			}
			
			if(rs.getString("orderType").equals("Sale")) {
				GenericOrder res = buildSales(rs);
				return res;
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		System.out.print("Order doesn't exist");
		return null;
	}
	
	private GenericOrder buildRent(ResultSet rs) throws DataAccessException {
		RentOrder res = new RentOrder(null, null, null, false, 0, null, 0);
		try {
			//int orderId, LocalDateTime deliveryDate, LocalDateTime orderDate, String deliveryAddress, 
			//boolean isDelivered, double amount, EmployeeRole employee, int customerId
			res.setOrderID(rs.getInt("orderId")); 
			//orderDate
			Date date = rs.getDate("orderDate");
			Timestamp timestamp = new Timestamp(date.getTime());
			res.setOrderDate(timestamp.toLocalDateTime());
			//deliveryDate
			Date dateD = rs.getDate("deliveryDate");
			Timestamp timestampD = new Timestamp(dateD.getTime());
			res.setOrderDate(timestampD.toLocalDateTime());
			
			res.setDeliveryAddress(rs.getString("address"));
			
			if(rs.getInt("isDelivered") ==1) {
				res.setDelivered(true);
			}else {
				res.setDelivered(false);
			}
			
			res.setAmount(rs.getDouble("amount"));
			res.setCustomerId(rs.getInt("Rent_order.customerId"));
			LegalPersonDB l = new LegalPersonDB();
			res.setEmployee(((Person) l.findById("employeeId")).getEmployeeRole());	
			
			
		} catch (SQLException | DataAccessException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	/*
	private SupplyOrder buildSupply(ResultSet rs) throws DataAccessException{
		SupplyOrder res = new SupplyOrder();
		return res;
	}*/
	private SaleOrder buildSales(ResultSet rs) throws DataAccessException {
		SaleOrder res = new SaleOrder(null, null, null, false, 0, null, 0);
		try {
			//int orderId, LocalDateTime deliveryDate, LocalDateTime orderDate, String deliveryAddress, 
			//boolean isDelivered, double amount, EmployeeRole employee, int customerId
			res.setOrderID(rs.getInt("orderId")); 
			//orderDate
			Date date = rs.getDate("orderDate");
			Timestamp timestamp = new Timestamp(date.getTime());
			res.setOrderDate(timestamp.toLocalDateTime());
			//deliveryDate
			Date dateD = rs.getDate("deliveryDate");
			Timestamp timestampD = new Timestamp(dateD.getTime());
			res.setOrderDate(timestampD.toLocalDateTime());
			
			res.setDeliveryAddress(rs.getString("address"));
			
			if(rs.getInt("isDelivered") ==1) {
				res.setDelivered(true);
			}else {
				res.setDelivered(false);
			}
			
			res.setAmount(rs.getDouble("amount"));
			res.setCustomerId(rs.getInt("Rent_order.customerId"));
			LegalPersonDB l = new LegalPersonDB();
			res.setEmployee(((Person) l.findById("employeeId")).getEmployeeRole());	
			
			
		} catch (SQLException | DataAccessException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	@Override
	public GenericOrder insert(GenericOrder order) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
