import javax.swing.*;
import java.util.ArrayList;

public class CarDealerManagementSystem {
    public ArrayList<Car> cars;
    public ArrayList<Customer> customers;
    public ArrayList<Employee> employees;
    public ArrayList<Inspection> inspections;
    public ArrayList<Sale> sales;
    public ArrayList<Receipt> receipts;
    public Finance finance;
    public Employee currentobj;

    public CarDealerManagementSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        inspections = new ArrayList<>();
        sales = new ArrayList<>();
        receipts = new ArrayList<>();
        finance = new Finance();
    }

    // Add Car to the system
    public void addCar(Car car) {
        cars.add(car);
    }

    // Add Customer to the system
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Add Employee to the system
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Add Inspection to the system
    public void addInspection(Inspection inspection) {
        inspections.add(inspection);
    }

    // Add Sale to the system
    public void addSale(Sale sale) {
        sales.add(sale);
    }

    // Add Receipt to the system
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    // Add Finance transaction to the system
    public void addFinanceTransaction(double amount, boolean isCashInHand) {
        finance.recordTransaction(amount, isCashInHand);
    }

    public void logout (){
         this.employees = null;
    }

    // Other methods for managing the system

    public Finance getFinance() {
        return finance;
    }
    public void setLogin(Employee emp) {
        this.currentobj = emp;
    }

    // ...


    public static void main(String[] args) {
        CarDealerManagementSystem system = new CarDealerManagementSystem();
        Employee employee1 = new Employee("EmpID1", "John Smith", "Position1", 5000.0 ,"57 RB Street 12", "admin" , "123");
        system.addEmployee(employee1);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                system.addCar(new Car("Toyota" , "2022" , "Grandy" ,"1" , "LE-10-2012" , "Red" , 7.0 , "https://picsum.photos/200" , false ,12000000));
                LoginForm login = new LoginForm(system);
                login.setVisible(true);
            }
    });
}
}


