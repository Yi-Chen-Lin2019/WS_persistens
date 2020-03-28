package ControlLayer;

import Models.*;
import db.*;

import java.util.ArrayList;
import java.util.List;

public class ProductCtr {
	private ProductDBIF productDB;
	
	
	public ProductCtr() throws DataAccessException, db.DataAccessException {
		productDB = new ProductDB();
	}
	
	public ProductDescription insertProduct(ProductDescription product) throws DataAccessException {
		ProductDescription pd = null;
		try {
			pd = productDB.insert(product);
		} catch (db.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
	}
	
	public List<ProductDescription> findAll() throws DataAccessException, db.DataAccessException {
		List<ProductDescription> pd = productDB.findAll();
		System.out.println(pd.size());
		return pd;
	}
	
	public ProductDescription findByBarcode(String barcode) throws DataAccessException, db.DataAccessException {
		ProductDescription pd = this.productDB.findByBarcode(barcode);
		return pd;
	}
	
	public List<ProductDescription> findByPName(String name) throws DataAccessException, db.DataAccessException {
		List<ProductDescription> pd = this.productDB.findByPName(name);
		return pd;
	}
}
