public class Customer extends User {
    private String customerID;
    private String address;
    private String phoneNumber;
    private String email;
    private boolean active;

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void addCustomer(String customerID, String name, String address, String phoneNumber, String email) {
        setCustomerID(customerID);
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setActive(true);
    }

    public void updateCustomer( String name , String address, String phoneNumber, String email , boolean active) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setActive(active);
    }

    public void changeActive(boolean active) {
        setActive(active);
    }

    public String getCustomerDetails() {
        return "Customer ID: " + customerID + "\n" +
                "Name: " + getName() + "\n" +
                "Address: " + address + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Email: " + email + "\n" +
                "Active: " + active;
    }
}
