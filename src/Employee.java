import java.util.ArrayList;

public class Employee extends User {
    private String employeeID;
    private String position;
    private double salary;
    private String address;
    private String username;
    private String password;
    private boolean active;
    private ArrayList<String> salaryReceived;

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public ArrayList<String> getSalaryReceived() {
        return salaryReceived;
    }

    public void addEmployee(String employeeID, String name, String position, double salary, String address , String user , String pass) {
        setEmployeeID(employeeID);
        setName(name);
        setPosition(position);
        setSalary(salary);
        setAddress(address);
        setActive(true);
        this.username   = user;
        this.password = pass;
        salaryReceived = new ArrayList<>();
    }

    public void updateEmployee(String position, double salary, String address) {
        setPosition(position);
        setSalary(salary);
        setAddress(address);
    }

    public void recordSalary(String month) {
        salaryReceived.add(month);
    }


    public void increaseSalary(double percentageIncrease) {
        double increaseAmount = salary * (percentageIncrease / 100);
        salary += increaseAmount;
    }

    public void getEmployeeDetails() {
        System.out.println( "Employee ID: " + employeeID + "\n" +
                "Name: " + getName() + "\n" +
                "Position: " + position + "\n" +
                "Salary: " + salary + "\n" +
                "Address: " + address + "\n" +
                "Active: " + active);
    }

    public Employee(String employeeID, String name, String position, double salary, String address, String user , String pass){
        addEmployee(employeeID , name , position , salary , address , user ,pass);
    }


    public void changePassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            password = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Invalid old password. Password change failed.");
        }
    }
}
