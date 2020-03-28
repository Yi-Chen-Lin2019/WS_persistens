package Models;

import java.time.*;
import java.util.*;


public class RentOrder extends GenericOrder {
	private int customerId;
	private ArrayList<RentContract> rentContracts;
	public RentOrder(LocalDateTime deliveryDate, LocalDateTime orderDate, String deliveryAddress, boolean isDelivered, double amount, EmployeeRole employee, int customerId)
	{
		super(deliveryDate, orderDate, deliveryAddress, isDelivered, amount, employee, "Rent");
		this.customerId = customerId;
		rentContracts = new ArrayList<>();
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setRentContract(RentContract rentContract)
    {
        rentContracts.add(rentContract);
    }
    public void removeRentContract(RentContract rentContract)
    {
        rentContracts.remove(rentContract);
    }
    public ArrayList<RentContract> readRentContract()
    {
        return rentContracts;
    }
    public void setTotalPrice() {
    	double amount = super.getAmount();
    	if(rentContracts.size()!=0) {
    		for(RentContract r: rentContracts) {
    			amount += r.getRentOrderLine().getProduct().getPrice() * r.getRentOrderLine().getQuantity();
    		}
    	}
    	super.setAmount(amount);
    }	
}
