package ControlLayer;

import Models.*;
import db.*;
import java.util.List;

public class OrderCtr {
	private LegalPersonCtr personCtr;
	private ProductCtr productCtr;
	private OrderDBIF orderDB;
	

	
	public OrderCtr() throws DataAccessException, db.DataAccessException {
		personCtr = new LegalPersonCtr();
		productCtr = new ProductCtr();
		orderDB = new OrderDB();
	}
	
	public GenericOrder insertOrder(GenericOrder order) throws DataAccessException {
		GenericOrder go = null;
		try {
			go = orderDB.insert(order);
		} catch (db.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return go;
	}
	
	public List<GenericOrder> findAll() throws DataAccessException, db.DataAccessException {
		List<GenericOrder> go = this.orderDB.findAll();
		return go;
	}
	
	public GenericOrder findById(int id) throws DataAccessException, db.DataAccessException {
		GenericOrder go = this.orderDB.findById(id);
		return go;
	}
}
