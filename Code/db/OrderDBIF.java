package db;

import java.util.List;

import Models.GenericOrder;

public interface OrderDBIF {
	List<GenericOrder> findAll() throws DataAccessException; 
	GenericOrder findById(int id) throws DataAccessException;
	GenericOrder insert(GenericOrder order) throws DataAccessException;

}
