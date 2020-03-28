package Models;

public class GunReplica extends ProductDescription{
	private double calibre;
	private String material;
	
	public GunReplica(int barcode, String name, String productType, double price, double supplyPrice, String countryOfOrigin, double calibre, String material) 
	{
		super(barcode, name, productType, price, supplyPrice, countryOfOrigin);
		this.setCalibre(calibre);
		this.setMaterial(material);
	}

	public double getCalibre() {
		return calibre;
	}

	public void setCalibre(double calibre) {
		this.calibre = calibre;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

}
