package Models;

public abstract class ProductDescription {
	private int id;
	private int barcode;
	private String name;
	private String productType;
	private double price;
	private double supplyPrice;
	private String countryOfOrigin;
	
	public ProductDescription(int barcode, String name, String productType, double price, double supplyPrice, String countryOfOrigin) {
		this.barcode = barcode;
		this.name = name;
		this.productType = productType;
		this.price = price;
		this.supplyPrice = supplyPrice;
		this.countryOfOrigin = countryOfOrigin;
	} 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(double supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public String getCountrOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountrOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
		
}
