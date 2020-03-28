package db;

import java.util.List;
import Models.*;

public interface ProductDBIF {
	List<ProductDescription> findAll() throws DataAccessException; 
	ProductDescription findByBarcode(String barcode) throws DataAccessException;
	List<ProductDescription> findByPName(String name) throws DataAccessException;
	ProductDescription insert(ProductDescription product) throws DataAccessException;
	  

}
