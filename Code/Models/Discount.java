package Models;

public class Discount {
	
	private double discountValue;
	private boolean isDeliveryFree = false;

	public Discount() {
		
	}
	
	public double getDiscountValue() {
		return discountValue;
	}
	
	public boolean getIsDeliveryFree() {
		return isDeliveryFree;
	}
	
	public void setDiscountValue(double value) {
		this.discountValue = value;
	}
	
	public void setIsDeliveryFree(boolean isFree) {
		isDeliveryFree = isFree;
	}
}
