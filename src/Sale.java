import java.time.LocalDate;

public class Sale {
    private String salesID;

    public Customer getCustomer() {
        return customer;
    }

    private Customer customer;

    public Car getCar() {
        return car;
    }

    private Car car;

    public Employee getEmployee() {
        return employee;
    }

    private Employee employee;
    private LocalDate saleDate;
    private double price;

    public  Sale(String salesID , Car car, Customer customer, Employee employee, LocalDate saleDate, double price) {
        sellCar(salesID,car , customer , employee , saleDate , price);
    }
    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerID() {
        return customer.getCustomerID();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCarID() {
        return car.getCarID();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmployeeID() {
        return employee.getEmployeeID();
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

    public void sellCar(String salesID,Car car, Customer customer, Employee employee, LocalDate saleDate, double price) {
        setSalesID(salesID);
        car.setStatus(false);
        car.setPrice(price);
        setCustomer(customer);
        setCar(car);
        setEmployee(employee);
        setSaleDate(saleDate);
        setPrice(price);
    }

    public void printSale() {
        System.out.println("Sales ID: " + salesID);
        System.out.println("Customer: " + customer.getCustomerDetails());
    car.printCar();
 employee.getEmployeeDetails();
        System.out.println("Sale Date: " + saleDate);
        System.out.println("Price: " + price);
    }
}
