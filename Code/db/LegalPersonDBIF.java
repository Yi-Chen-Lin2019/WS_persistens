package db;

import java.util.List;

import Models.*;


public interface LegalPersonDBIF {
	
	List<LegalPerson> findAll() throws DataAccessException;
	LegalPerson findById(String id) throws DataAccessException;
	List<LegalPerson> findByName(String name) throws DataAccessException;
	LegalPerson insert(LegalPerson person) throws DataAccessException;
	
	 
}
