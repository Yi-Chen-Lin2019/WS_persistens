package ControlLayer;
import db.*;

import Models.*;
import Models.LegalPerson;
import db.*;
import java.util.List;

public class LegalPersonCtr {
	private LegalPersonDBIF legalPersonDB;
	
	
	public LegalPersonCtr() throws DataAccessException, db.DataAccessException {
		try {
			legalPersonDB = new LegalPersonDB();
		} catch (db.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LegalPerson insertLegalPerson(LegalPerson person) throws DataAccessException, db.DataAccessException {
		LegalPerson lp = legalPersonDB.insert(person);
		return lp;
	}
	
	public List<LegalPerson> findAll() throws DataAccessException, db.DataAccessException {
		List<LegalPerson> lp = this.legalPersonDB.findAll();
		return lp;
	}
	
	public LegalPerson findById(String id) throws DataAccessException, db.DataAccessException {
		LegalPerson lp = this.legalPersonDB.findById(id);
		return lp;
	}
	
	public List<LegalPerson> findByName(String name) throws DataAccessException, db.DataAccessException {
		List<LegalPerson> lp = this.legalPersonDB.findByName(name);
		return lp;
	}
}
