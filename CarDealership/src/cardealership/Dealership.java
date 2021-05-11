package cardealership;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * 
 * @author VISHNU VARDHAN
 *
 */

public class Dealership {
	// public static final int LOAN_TERM = 10;
	static int loanTerm = 10;
	static double interestRate = 8.0;

	public static void main(String[] args) {
		Properties prop = readPropertiesFile("config.properties");
		loanTerm = Integer.parseInt(prop.getProperty("loanTerm"));
		interestRate = Double.parseDouble(prop.getProperty("interestRate"));

		Vehicle[] vehicleList = prepareInventory();
		SalesEmployee employee = new SalesEmployee();
		Scanner input = new Scanner(System.in);

		Customer cust = getCustomerInfo(input);

		boolean financeOption = getFinanceOption(input);
		if (financeOption) {
			financeOption = processFinanceOption(cust, input, employee);
		} else {
			processCashOption(cust, vehicleList, employee, input);
		}
		System.out.println("\n\n\npress a key to Exit!!!");
		input.next();
		
	}
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static Customer getCustomerInfo(Scanner input) {
		Customer cust = new Customer();
		String name;
		String addres;
		double cash;
		System.out.println("Welcome to Vyshnavi Motors Pvt Ltd");
		System.out.println("Enter your name:");
		name = input.next();
		System.out.println("Enter your address:");
		addres = input.next();
		System.out.println("Enter cash on hand:");
		cash = input.nextDouble();

		cust.setName(name);
		cust.setAddress(addres);
		cust.setcashOnHand(cash);
		return cust;
	}
	/**
	 * 
	 * @param cust
	 * @param vehicleList
	 * @param employee
	 * @param input
	 */
	public static void processCashOption(Customer cust, Vehicle[] vehicleList, SalesEmployee employee, Scanner input) {
		double cash = cust.getcashOnHand();
		Vehicle[] carListInBudjet = employee.findCarsInBudjet(vehicleList, cash);
		System.out.println("The following models are in your budget:");
		for (int i = 0; i < carListInBudjet.length; i++) {
			System.out.println(i + 1 + " " + carListInBudjet[i].getBrand() + " " + carListInBudjet[i].getModel());

		}
		System.out.println("Which model are you interested?");
		int carSelected;
		carSelected = input.nextInt();

		if (carSelected <= carListInBudjet.length && carSelected > 0) {
			System.out.println("Congratulations on buying the car. Here is the Sales Agreement:");
			Vehicle selectedCar = carListInBudjet[carSelected - 1];
			employee.printCashSalesAgrement(cust, selectedCar);
		} else {
			System.out.println("Invalid Option");
		}
	}

	/**
	 * 
	 * @param cust
	 * @param input
	 * @param employee
	 * @return
	 */
	public static boolean processFinanceOption(Customer cust, Scanner input, SalesEmployee employee) {
		boolean financeOption;
		financeOption = true;

		System.out.println("Enter the salary");
		int salary = input.nextInt();
		System.out.println("Enter month-1 balance amount:");
		int month1 = input.nextInt();
		System.out.println("Enter month-2 balance amount:");
		int month2 = input.nextInt();
		System.out.println("Enter month-3 balance amount:");
		int month3 = input.nextInt();

		cust.setSalary(salary);
		cust.setMonth1(month1);
		cust.setMonth2(month2);
		cust.setMonth3(month3);

		double credWorth = cust.getCreditWorthiness();

		ArrayList<Vehicle> allowedVehicles = new ArrayList<>();
		Vehicle[] inventory = prepareInventory();
		for (Vehicle v : inventory) {
			double monthlyPmt = getMonthlyPayment(v.getPrice());
			if (monthlyPmt <= credWorth) {
				allowedVehicles.add(v);
			}
		}
		if (allowedVehicles.size() <= 0) {
			System.out.println("Sorry There are no Vehicles in Your budjet. Please try again later. ");
		} else {
			Vehicle selectedVehicle = getSelectedVehicle(allowedVehicles, input);
			double monthlyPmt = getMonthlyPayment(selectedVehicle.getPrice());
			employee.printFinanceSalesAgrement(cust, selectedVehicle, monthlyPmt, loanTerm);
		}
		return financeOption;
	}
    /**
	 * 
	 * @param allowedVehicles
	 * @param input
	 * @return
	 */
	public static Vehicle getSelectedVehicle(ArrayList<Vehicle> allowedVehicles, Scanner input) {
		Vehicle sellVehicle = null;
		int c = 1;
		System.out.println("Here are the Vehicles in your budjet : ");
		for (Vehicle v : allowedVehicles) {
			System.out.println(c + " " + v.getBrand() + " " + v.getModel());
			c++;
		}
		String option;
		int idx;

		do {
			System.out.println("Enter the Option of a car form the List");
			option = input.next();
			idx = Integer.parseInt(option);
			idx = idx - 1;
		} while (!(idx < allowedVehicles.size()));
		sellVehicle = allowedVehicles.get(idx);
		return sellVehicle;
	}

	/**
	 * 
	 * @return
	 */
	public static Vehicle[] prepareInventory() {
		Vehicle[] vehicleList = new Vehicle[10];
		vehicleList[0] = new Vehicle("BMW", "X5", 590000);
		vehicleList[1] = new Vehicle("Audi", "A6", 540000);
		vehicleList[2] = new Vehicle("MG Hector", "MT Sharp", 170000);
		vehicleList[3] = new Vehicle("Maruthi", "i20", 190000);
		vehicleList[4] = new Vehicle("Maruthi", "Brezza", 110000);
		vehicleList[5] = new Vehicle("Ford", "Figo", 110000);
		vehicleList[6] = new Vehicle("Maruthi", "Swift", 800000);
		vehicleList[7] = new Vehicle("Maruthi", "i10", 150000);
		vehicleList[8] = new Vehicle("Hundai", "Kona", 250000);
		vehicleList[9] = new Vehicle("Kia", "carnival", 230000);

		return vehicleList;
	}
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static boolean getFinanceOption(Scanner input) {
		boolean financeOption = false;
		String option;
		do {
			System.out.println("Would you like to finance the car (y/n)?");
			option = input.next();
			financeOption = option.equalsIgnoreCase("y");
		} while (!(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")));

		return financeOption;
	}
	/**
	 * 
	 * @param loanPayment
	 * @return
	 */
	public static double getMonthlyPayment(double loanAmount) {
		double monthlyPmt = 0;

		double i = interestRate / 100 / 12;
		double n = loanTerm * 12;

		monthlyPmt = loanAmount * i * Math.pow((1 + i), n) / (Math.pow((1 + i), n) - 1);
		return monthlyPmt;

	}
	

	public static Properties readPropertiesFile(String fileName) {
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
}