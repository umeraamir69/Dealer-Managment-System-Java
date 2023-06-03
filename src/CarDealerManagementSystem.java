import javax.swing.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.*;
import java.util.Date;

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


        Employee employee1 = new Employee("1", "Umer Aamir", "Admin", 9999999 ,"57 RB Street 12", "admin" , "123");
        Employee employee2 = new Employee("2", "Ali", "Manager", 10000 ,"ABC CUI Lahore", "ali" , "ali");
        Employee employee3 = new Employee("3", "Anas Malick", "Manager", 10000 ,"ABC CUI Lahore", "FA21-BSE-126" , "126");
        Employee employee4 = new Employee("4", "Ashsh Malick", "Manager", 10000 ,"ABC CUI Lahore", "FA21-BSE-120" , "120");

        system.addEmployee(employee1);
        system.addEmployee(employee2);
        system.addEmployee(employee3);
        system.addEmployee(employee4);


        Customer customer1 = new Customer("CUS01", "John Smith", "RAZA LDA", "3359119222" ,"123@123.com");
        Car car1 = new Car("Toyota" , "2020" , "Fortuner G 2.7" ,"2" ,"LE-20-120" ,"White" ,9.7 ,"" ,true ,1200000);
        Sale s1 = new Sale("001" , car1 , customer1 , employee1 , new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()  ,1200000);
        Inspection ins1 = new Inspection("001","LRR 9104" , new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),"Excellent Condition" , 100 , 100 ,true , "First" , "Original");


        customer1.setActive(true);

        system.addCustomer(customer1);
        system.addCar(car1);
        system.addSale(s1);
        system.addInspection(ins1);

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


