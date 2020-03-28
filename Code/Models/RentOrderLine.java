package Models;

public class RentOrderLine extends GenericOrderLine {
	private ProductDescription product;
	
	public RentOrderLine(int quantity, ProductDescription product)
	{
		super(quantity);
		this.product = product;
		
	}

	public ProductDescription getProduct() {
		return product;
	}

	public void setProduct(ProductDescription product) {
		this.product = product;
	}
}
