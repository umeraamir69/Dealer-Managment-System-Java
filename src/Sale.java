import java.time.LocalDate;

public class Sale {
    private String salesID;
    private String customerID;
    private String carID;
    private String employeeID;
    private LocalDate saleDate;
    private double price;

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarID() {
        return carID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void sellCar(Car car, String customerID, String employeeID, LocalDate saleDate, double price) {
        car.setStatus(false);
        setCustomerID(customerID);
        setCarID(car.getCarID());
        setEmployeeID(employeeID);
        setSaleDate(saleDate);
        setPrice(price);
    }

    public void printSale() {
        System.out.println("Sales ID: " + salesID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Car ID: " + carID);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Sale Date: " + saleDate);
        System.out.println("Price: " + price);
    }
}
