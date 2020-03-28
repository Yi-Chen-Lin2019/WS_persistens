package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.*;

public class ProductDB implements ProductDBIF{
	private ProductDB productDB;
	private static final String FIND_ALL_Q = 
			"SELECT productBarcode, productName, productPrice, productSupplyPrice, productCOO, productType, calibre, material, size, colour, type, description FROM (Product LEFT OUTER JOIN CLOTHING ON CLOTHING.ProductId = PRODUCT.ProductId LEFT OUTER JOIN GUN_REPLICA ON GUN_REPLICA.ProductId = PRODUCT.ProductId LEFT OUTER JOIN EQUIPMENT ON EQUIPMENT.ProductId = PRODUCT.ProductId)";
	private static final String FIND_BY_BARCODE_Q = FIND_ALL_Q + " where productBarcode = ?";
	private static final String FIND_BY_PNAME_Q = FIND_ALL_Q + " where productName = ?";
	private PreparedStatement findAllProduct; 
	private PreparedStatement findByBarcode;
	private PreparedStatement findByPName;
	private PreparedStatement insertProduct;
	private static final String INSERT_PRODUCT_Q = 
			"INSERT INTO PRODUCT values (?, ?, ?, ?, ?, ?)";
	private PreparedStatement insertGunReplica;
	private PreparedStatement insertEquipment;
	private PreparedStatement insertClothing;
	private static final String INSERT_GUNREPLICA_Q = "INSERT INTO GUN_REPLICA VALUES (?,?,?)";
	private static final String INSERT_EQUIPMENT_Q = "INSERT INTO EQUIPMENT VALUES (?,?,?)";
	private static final String INSERT_CLOTHING_Q = "INSERT INTO CLOTHING VALUES (?,?,?)";
	
	public ProductDB() throws DataAccessException {
		init();
	}
	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			findAllProduct = connection.prepareStatement(FIND_ALL_Q);
			findByBarcode = connection.prepareStatement(FIND_BY_BARCODE_Q);
			findByPName = connection.prepareStatement(FIND_BY_PNAME_Q);
			insertProduct = connection.prepareStatement(INSERT_PRODUCT_Q, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	@Override
	public List<ProductDescription> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllProduct.executeQuery();		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<ProductDescription> res = buildObjects(rs);
		return res;
	}
	@Override
	public ProductDescription findByBarcode(String barcode) throws DataAccessException {
		ProductDescription res = null;
		try {
			findByBarcode.setString(1, barcode);
			ResultSet rs = findByBarcode.executeQuery();
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
	public List<ProductDescription> findByPName(String name) throws DataAccessException {
		List<ProductDescription> res = new ArrayList<>(0);
		try {
			findByPName.setString(1, "%" + name + "%");
			if (name != null && name.length() == 1) {
				findByPName.setString(2, name);
			} else {
				findByPName.setString(2, "");
			}
			findByPName.setString(3, "%" + name + "%");
			ResultSet rs = findByPName.executeQuery();
			res = buildObjects(rs);
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}
	private List<ProductDescription> buildObjects(ResultSet rs) throws DataAccessException {
		List<ProductDescription> res = new ArrayList<>();
		try {
			while (rs.next()) {
				ProductDescription currProduct = buildObject(rs);
				res.add(currProduct);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private ProductDescription buildObject(ResultSet rs) throws DataAccessException {
		try {
			if(rs.getString("productType").equals("Clothing")) {
				ProductDescription res = buildCloth(rs);
				return res;
			}
			
			if(rs.getString("productType").equals("Guns")) {
				ProductDescription res = buildGun(rs);
				return res;
			}
			
			if(rs.getString("productType").equals("Equipment")) {
				ProductDescription res = buildEquipment(rs);
				return res;
			}
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		System.out.print("Order doesn't exist");
		return null;
	}
	
	private Clothing buildCloth(ResultSet rs) throws DataAccessException {
		Clothing res = new Clothing(0, null, null, 0, 0, null, null, null);
		try {
			//String barcode, String name, double price, double supplyPrice, 
			//String countryOfOrigin, String size, String colour
			res.setBarcode(rs.getInt("productBarcode"));
			res.setName(rs.getString("productName"));
			res.setProductType("productType");
			res.setPrice(rs.getDouble("productPrice"));
			res.setSupplyPrice(rs.getDouble("productSupplyPrice"));
			res.setCountrOfOrigin(rs.getString("productCOO"));
			
			res.setSize(rs.getString("size"));
			res.setColour(rs.getString("colour"));
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	private GunReplica buildGun(ResultSet rs) throws DataAccessException {
		GunReplica res = new GunReplica(0, null, null, 0, 0, null, 0, null);
		try {
			//String barcode, String name, double price, double supplyPrice, 
			//String countryOfOrigin, double calibre, String material
			res.setBarcode(rs.getInt("productBarcode"));
			res.setName(rs.getString("productName"));
			res.setProductType("productType");
			res.setPrice(rs.getDouble("productPrice"));
			res.setSupplyPrice(rs.getDouble("productSupplyPrice"));
			res.setCountrOfOrigin(rs.getString("productCOO"));
			
			res.setCalibre(rs.getDouble("calibre")); 
			res.setMaterial(rs.getString("material"));
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	private Equipment buildEquipment(ResultSet rs) throws DataAccessException {
		Equipment res = new Equipment(0, null, null, 0, 0, null, null, null);
		try {
			//String barcode, String name, String productType, double price, 
			//double supplyPrice, String countryOfOrigin, String type, String description
			res.setBarcode(rs.getInt("productBarcode"));
			res.setName(rs.getString("productName"));
			res.setProductType("productType");
			res.setPrice(rs.getDouble("productPrice"));
			res.setSupplyPrice(rs.getDouble("productSupplyPrice"));
			res.setCountrOfOrigin(rs.getString("productCOO"));
			
			res.setType(rs.getString("type"));
			res.setDescription(rs.getString("description"));
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	public ProductDescription insert(ProductDescription product) throws DataAccessException {
		// 
				try {
					insertProduct.setInt(1, product.getBarcode());
					insertProduct.setString(2, product.getName());
					insertProduct.setDouble(3, product.getPrice());
					insertProduct.setDouble(4, product.getSupplyPrice());
					insertProduct.setString(5, product.getCountrOfOrigin());
					insertProduct.setString(6, product.getProductType());
					
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
				}
				try {
					insertProduct.executeUpdate();
					
					ResultSet keys = insertProduct.getGeneratedKeys();
					if (keys.next()) {
						product.setId(keys.getInt(1));
				    }
							if (product instanceof GunReplica)
							{
								insertGunReplica(product);
							}
							else if (product instanceof Equipment)
							{
								insertEquipment(product);
							}
							else if (product instanceof Clothing)
							{
								insertClothing(product);
							}
							
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
				}

				return product;
	}
	private ProductDescription insertClothing(ProductDescription product) throws DataAccessException {
		//size, colour, productId
				try {
					insertClothing = DBConnection.getInstance().getConnection().prepareStatement(INSERT_CLOTHING_Q);
					insertClothing.setString(1, ((Clothing) product).getSize());
					insertClothing.setString(2, ((Clothing) product).getColour());
					insertClothing.setInt(3, ((Clothing) product).getId());

				} catch (SQLException e) {
					// e.printStackTrace();
					throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
				}
				try {
					insertClothing.executeUpdate();
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
				}

				return product;
		
	}
	private ProductDescription insertEquipment(ProductDescription product) throws DataAccessException {
		//type, description, productId
		try {
			insertEquipment = DBConnection.getInstance().getConnection().prepareStatement(INSERT_EQUIPMENT_Q);
			insertEquipment.setString(1, ((Equipment) product).getType());
			insertEquipment.setString(2, ((Equipment) product).getDescription());
			insertEquipment.setInt(3, ((Equipment) product).getId());

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		try {
			insertEquipment.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
		}

		return product;
		
	}
	private ProductDescription insertGunReplica(ProductDescription product) throws DataAccessException {
		//calibre, material, productId
				try {
					insertGunReplica = DBConnection.getInstance().getConnection().prepareStatement(INSERT_GUNREPLICA_Q);
					double calibre = ((GunReplica) product).getCalibre();
					insertGunReplica.setString(1, (String.valueOf(calibre)));
					insertGunReplica.setString(2, ((GunReplica) product).getMaterial());
					insertGunReplica.setInt(3, ((GunReplica) product).getId());

				} catch (SQLException e) {
					// e.printStackTrace();
					throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
				}
				try {
					insertGunReplica.executeUpdate();
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
				}

				return product;
				
	}
	
	
	
	
}
	
	
	
	

