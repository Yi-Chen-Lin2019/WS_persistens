package Models;

import java.time.*;
import java.util.*;


	public class SaleOrder extends GenericOrder {
		private int customerId;
		private ArrayList<SalesOrderLine> saleOrderLines;
		private Discount discount;
		
		public SaleOrder(LocalDateTime deliveryDate, LocalDateTime orderDate, String deliveryAddress, boolean isDelivered, double amount, EmployeeRole employee, int customerId)
		{
			super(deliveryDate, orderDate, deliveryAddress, isDelivered, amount, employee, "Sale");
			this.customerId = customerId;
			saleOrderLines = new ArrayList<>();
		}
		
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		public void setSalesOrderLine(SalesOrderLine SaleOrderLine)
	    {
	        saleOrderLines.add(SaleOrderLine);
	    }
	    public void removeSalesOrderLine(SalesOrderLine SalesOrderLine)
	    {
	        saleOrderLines.remove(SalesOrderLine);
	    }
	    public ArrayList<SalesOrderLine> readSalesOrderLine()
	    {
	        return saleOrderLines;
	    }
	    public void setTotalPriceForPerson() {
	    	double amount = super.getAmount();
	    	if(saleOrderLines.size()!=0) {
	    		for(SalesOrderLine r: saleOrderLines) {
	    			amount += r.getProduct().getPrice() * r.getQuantity();	   
	    		}
	    	if(amount < 2500) {
    			amount += amount +45;	    				
    		}
	    }
	    	super.setAmount(amount);
	    }
	    public void setTotalPriceForOrganization() {
	    	double amount = super.getAmount();
	    	if(saleOrderLines.size()!=0) {
	    		for(SalesOrderLine r: saleOrderLines) {
	    			amount += r.getProduct().getPrice() * r.getQuantity();	   
	    			
	    		}
	    	if(amount > 1500) {
    			amount += amount * discount.getDiscountValue();	    				
    		}	
	    	super.setAmount(amount);
	    }
	    }
	}

