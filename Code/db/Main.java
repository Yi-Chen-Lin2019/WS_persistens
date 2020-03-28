package db;

import java.sql.Connection;

import ControlLayer.LegalPersonCtr;
import ControlLayer.ProductCtr;
import Models.*;

public class Main {

	public static void main(String[] args) {
		try {
			Connection con = DBConnection.getInstance().getConnection();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			ProductDB odb = new ProductDB();
			for(ProductDescription o: odb.findAll()) {
				System.out.println(o.getName());
				System.out.println(odb.findAll().size());
			}			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		try {
			ProductCtr c = new ProductCtr();
			System.out.println(c.findAll().size());
		} catch (ControlLayer.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			LegalPersonDB l = new LegalPersonDB();
			System.out.println(( l.findByName("John")));
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//phoneNumber, String email, String address, String id, String name, 
		//EmployeeRole employeeRole, CustomerRole customerRole, SupplierRole supplierRole
		
		try {
			LegalPersonCtr lpc = new LegalPersonCtr();
			lpc.insertLegalPerson(new Person("12345678", "abc@ucn.dk", "2", "2", "abc", null, null, null));
		} catch (ControlLayer.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		

	}

}
