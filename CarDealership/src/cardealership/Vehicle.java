package cardealership;
/**
 * 
 * @author VISHNU VARDHAN
 *
 */
public class Vehicle {
	private String brand;
	private String model;
	private int price;

	public Vehicle(String brand, String model, int price) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String n) {
		model = n;

	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		return "Vehicle [brand=" + brand + ", model=" + model + ", price=" + price + "]";

	}

}