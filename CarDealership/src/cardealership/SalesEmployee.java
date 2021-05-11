package cardealership;

/**
 * Sales Employee class represents the operations of Sales Employee and a Sales 
 * @author VISHNU VARDHAN
 *
 */
public class SalesEmployee {
	double discountPercentage = 0.1;

	public void handleCustomer(Customer cust, boolean finance, Vehicle[] vehicleList) {
		if (finance == true) {
			double loanAmount = vehicleList[0].getPrice() - cust.getcashOnHand();
			runCreditHistory(cust, loanAmount);
		} else if (vehicleList[0].getPrice() <= cust.getcashOnHand()) {
			processTransaction(cust, vehicleList[0]);
		} else {
			System.out.println("Customer " + cust.getName()
					+ " will need more money to purchase the following vehicle: " + vehicleList[0].toString());
		}
	}
	/**
	 * 
	 * @param cust
	 * @param loanAmount
	 */
	public void runCreditHistory(Customer cust, double loanAmount) {
		System.out.println("Run credit check on customer: " + cust.getName());
		System.out.println("The loan amount has been approved, for " + cust.getName());
	}

	/**
	 * 
	 * @param cust
	 * @param selectedCar
	 */
	public void processTransaction(Customer cust, Vehicle selectedCar) {
		System.out.println(
				"Customer " + cust.getName() + " has purchased the vehicle " + selectedCar.toString() + " with cash.");
	}
/**
 * 
 * @param v
 * @return
 */
	public double getDiscountedPrice(Vehicle v) {
		double dPrice = v.getPrice() * (1 - discountPercentage);
		return dPrice;

	}
/**
 * 
 * @param vehicleList
 * @param cash
 * @return
 */
	public Vehicle[] findCarsInBudjet(Vehicle[] vehicleList, double cash) {
		int count = 0;
		for (int i = 0; i < vehicleList.length; i++) {
			Vehicle v = vehicleList[i];

			if (getDiscountedPrice(v) <= cash) {
				count++;
			}
		}
		Vehicle[] vehicleListInBudjet = new Vehicle[count];
		int carIndex = 0;
		for (int i = 0; i < vehicleList.length; i++) {
			Vehicle v = vehicleList[i];

			if (getDiscountedPrice(v) <= cash) {

				vehicleListInBudjet[carIndex] = v;
				carIndex++;
			}
		}
		return vehicleListInBudjet;
	}

	
/**
 * 
 * @param cust1
 * @param selectedCar
 * @param financeOption
 * @param monthlyPmt
 * @param loanTerm
 */
	private void printSalesAgrement(Customer cust1, Vehicle selectedCar, boolean financeOption, double monthlyPmt, int loanTerm) {
		System.out.println("Vyshnavi Motors Pvt Ltd");
		System.out.println("180 Begumpet Road");
		System.out.println("Hyderabad, TN-483122\n");

		System.out.println("To");
		System.out.println(cust1.getName());
		System.out.println(cust1.getAddress() + "\n");
		System.out.println("                 Sales Agreement\n");
		System.out.println("This agreement is made with " + cust1.getName() + " to sell the following car on this day "
				+ "December 28 2020");
		System.out.println("Model: " + selectedCar.getBrand() + " " + selectedCar.getModel());
		if (financeOption) {
			System.out.println("Purchase price: Rs " + selectedCar.getPrice() + "\n");
			System.out.println("Mothly Payment : Rs " + monthlyPmt);
			System.out.println("Loan Term: " + loanTerm + " years");
		} else {
			System.out.println("Purchase price: Rs " + getDiscountedPrice(selectedCar) + "\n");
		}
		System.out.println("Thank you for giving the opportunity to serve you.\n");
		System.out.println("Yours sincerely\n\n\n");
		System.out.println(" Pavan Kalyan");
		System.out.println("Sales Manager");
	}
  

	/**
	 * prints sales agreement for cash option 
	 * @param cust1
	 * @param selectedCar
	 */
	public void printCashSalesAgrement(Customer cust1, Vehicle selectedCar) {
		printSalesAgrement(cust1, selectedCar, false, 0, 0);
	}
	
	/**
	 * prints sales aggrement for finance option 
	 * @param cust1
	 * @param selectedCar
	 * @param monthlyPmt
	 * @param loanTerm
	 */
	public void printFinanceSalesAgrement(Customer cust1, Vehicle selectedCar, double monthlyPmt, int loanTerm) {
		printSalesAgrement(cust1, selectedCar, true, monthlyPmt, loanTerm);
	}
}