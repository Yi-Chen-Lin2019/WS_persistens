package Models;

public class SalesOrderLine extends GenericOrderLine {
	private ProductDescription product;
	
	public SalesOrderLine(int quantity, ProductDescription product)
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
