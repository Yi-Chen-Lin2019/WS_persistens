package Models;

public class Equipment extends ProductDescription {
	private String type;
	private String description;
	
	public Equipment(int barcode, String name, String productType, double price, double supplyPrice, String countryOfOrigin, String type, String description) 
	{
		super(barcode, name, productType, price, supplyPrice, countryOfOrigin);
		this.setType(type);
		this.setDescription(description);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
