package Models;

import java.time.*;

public class RentContract {
	private LocalDateTime returnDate;
	private boolean isReturnerd;
	private RentOrderLine rentOrderLine;
	
	public RentContract()
	{
		
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturnerd() {
		return isReturnerd;
	}

	public void setReturnerd(boolean isReturnerd) {
		this.isReturnerd = isReturnerd;
	}
	
	public void setRentOrderLine(RentOrderLine rl) {
		this.rentOrderLine = rl;
	}
	public RentOrderLine getRentOrderLine() {
		return rentOrderLine;
	}
}
