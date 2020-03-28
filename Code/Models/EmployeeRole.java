package Models;

public class EmployeeRole 
{
	private String SSN;
	private String title;
	private double salary;
	
	public EmployeeRole(String SSN, String title, double salary)
	{
		this.SSN = SSN;
		this.title = title;
		this.salary = salary;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
