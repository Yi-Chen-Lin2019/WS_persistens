package Models;

public class Clothing extends ProductDescription {
	
	private String size;
	private String colour;
	
	public Clothing(int barcode, String name, String productType, double price, double supplyPrice, String countryOfOrigin, String size, String colour)
	{
		super(barcode, name, productType, price, supplyPrice, countryOfOrigin);
		this.size = size;
		this.colour = colour;
		
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}
