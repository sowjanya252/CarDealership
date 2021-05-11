package cardealership;

public class Customer {
	private String name;
	private String address;
	private double cashOnHand;
	
	int salary;
	int month1,month2,month3;
	//double avgBal;
	
		
	public double getAvgBal() {
		double avgBal = (month1 + month2 + month3) / 3;
		return avgBal;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getMonth1() {
		return month1;
	}

	public void setMonth1(int month1) {
		this.month1 = month1;
	}

	public int getMonth2() {
		return month2;
	}

	public void setMonth2(int month2) {
		this.month2 = month2;
	}

	public int getMonth3() {
		return month3;
	}

	public void setMonth3(int month3) {
		this.month3 = month3;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String a) {
		address = a;
	}

	public double getcashOnHand() {
		return cashOnHand;
	}

	public void setcashOnHand(double c) {
		cashOnHand = c;
	}

	/**
	 * 
	 * @param vehicle
	 * @param employee
	 * @param finance
	 */
	public void purchaseCar(Vehicle[] vehicle, SalesEmployee employee, boolean finance) {
		employee.handleCustomer(this, finance, vehicle);
	}

	/**
	 * 
	 * @return
	 */
	public double getCreditWorthiness() {
		double credit = 0.4*getAvgBal();		
		return credit;		
	}
}