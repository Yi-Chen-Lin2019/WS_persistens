package Models;

public class GenericOrderLine {
	private int quantity;
	
	public GenericOrderLine(int quantity) 
	{
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
