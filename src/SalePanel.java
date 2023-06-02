import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SalePanel extends JPanel {
    private JTextField saleIdField;
    private  CarDealerManagementSystem data;
    private JTextField customerIdField;
    private JTextField employeeIdField;
    private JTextField carIdField;
    private JTextField priceField;
    private JButton recordButton;
    private JButton selectCustomerButton;
    private JButton selectCarButton;
    private JLabel selectedCustomerLabel;
    private JLabel selectedCarLabel;

    private ArrayList<Customer> customers;
    private ArrayList<Car> cars;
    private Employee employee;

    public SalePanel(CarDealerManagementSystem data) {
        this.data = data;
        this.customers = data.customers;
        this.cars = data.cars;
        this.employee = data.currentobj;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#E6EEF3"));

        JPanel formPanel = createFormPanel();
        JPanel detailsPanel = createDetailsPanel();

        add(formPanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.decode("#E6EEF3"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel saleIdLabel = new JLabel("Sale ID:");
        formPanel.add(saleIdLabel, gbc);

        gbc.gridx++;
        saleIdField = new JTextField(10);
        formPanel.add(saleIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel customerIdLabel = new JLabel("Customer ID:");
        formPanel.add(customerIdLabel, gbc);

        gbc.gridx++;
        customerIdField = new JTextField(10);
        formPanel.add(customerIdField, gbc);

        gbc.gridx++;
        selectCustomerButton = new JButton("Select Customer");
        selectCustomerButton.setBackground(Color.decode("#27374D"));
        selectCustomerButton.setForeground(Color.decode("#DDE6ED"));
        selectCustomerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCustomerTable();
            }
        });
        formPanel.add(selectCustomerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel employeeIdLabel = new JLabel("Employee ID:");
        formPanel.add(employeeIdLabel, gbc);

        gbc.gridx++;
        employeeIdField = new JTextField(10);
        employeeIdField.setText(employee.getEmployeeID());
        employeeIdField.setEditable(false);
        formPanel.add(employeeIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel carIdLabel = new JLabel("Car ID:");
        formPanel.add(carIdLabel, gbc);

        gbc.gridx++;
        carIdField = new JTextField(10);
        formPanel.add(carIdField, gbc);

        gbc.gridx++;
        selectCarButton = new JButton("Select Car");
        selectCarButton.setBackground(Color.decode("#27374D"));
        selectCarButton.setForeground(Color.decode("#DDE6ED"));
        selectCarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCarTable();
            }
        });
        formPanel.add(selectCarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel priceLabel = new JLabel("Price:");
        formPanel.add(priceLabel, gbc);

        gbc.gridx++;
        priceField = new JTextField(10);
        formPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        recordButton = new JButton("Record");
        recordButton.setBackground(Color.decode("#1B5E20"));
        recordButton.setForeground(Color.decode("#DDE6ED"));
        recordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recordSale();
            }
        });
        formPanel.add(recordButton, gbc);

        return formPanel;
    }

    private JPanel createDetailsPanel() {
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.decode("#E6EEF3"));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Sale Details"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel selectedCarLabelTitle = new JLabel("Selected Car:");
        detailsPanel.add(selectedCarLabelTitle, gbc);

        gbc.gridx++;
        selectedCarLabel = new JLabel();
        detailsPanel.add(selectedCarLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel selectedCustomerLabelTitle = new JLabel("Selected Customer:");
        detailsPanel.add(selectedCustomerLabelTitle, gbc);

        gbc.gridx++;
        selectedCustomerLabel = new JLabel();
        detailsPanel.add(selectedCustomerLabel, gbc);

        return detailsPanel;
    }

    private void showCustomerTable() {
        String[] columnNames = {"Customer ID", "Name", "Email" ,"Phone"};
        String[][] data = new String[customers.size()][4];

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if(customer.isActive()){
            data[i][0] = customer.getCustomerID();
            data[i][1] = customer.getName();
            data[i][2] = customer.getEmail();
            data[i][3] = customer.getPhoneNumber()
            ;
        }
        }

        JTable table = new JTable(data, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(this, scrollPane, "Select Customer", JOptionPane.PLAIN_MESSAGE);

        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Customer selectedCustomer = customers.get(selectedRow);
            customerIdField.setText(selectedCustomer.getCustomerID());
            selectedCustomerLabel.setText("ID: " + selectedCustomer.getCustomerID() + ", Name: " + selectedCustomer.getName() + ", Email: " + selectedCustomer.getEmail());
        }
    }

    private void showCarTable() {
        String[] columnNames = {"Car ID", "Make", "Model" , "Variant" , "Avalible"};
        String[][] data = new String[cars.size()][5];

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getStatus()) {
                data[i][0] = car.getCarID();
                data[i][1] = car.getMake();
                data[i][2] = car.getModel();
                data[i][3] = car.getVariant();
                data[i][4] = car.getStatus() ? "Sold" : "Avalible";
            }
        }

        JTable table = new JTable(data, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(this, scrollPane, "Select Car", JOptionPane.PLAIN_MESSAGE);

        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Car selectedCar = cars.get(selectedRow);
            carIdField.setText(selectedCar.getCarID());
            selectedCarLabel.setText("ID: " + selectedCar.getCarID() + ", Make: " + selectedCar.getMake() + ", Model: " + selectedCar.getModel());
            priceField.setText(Double.toString(selectedCar.getPrice()));
        }
    }

    private void recordSale() {
        String saleId = saleIdField.getText();
        String customerId = customerIdField.getText();
        String employeeId = employeeIdField.getText();
        String carId = carIdField.getText();
        double price = Double.parseDouble(priceField.getText());

        Customer customer = getCustomerById(customerId);
        Car car = getCarById(carId);

        if (customer != null && car != null) {
            customer.getCustomerDetails();
            car.printCar();
            employee.getEmployeeDetails();
            data.addSale(new Sale(saleId , car , customer , employee , new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() ,price));

        }
    }

    private Customer getCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private Car getCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarID().equals(carId)) {
                return car;
            }
        }
        return null;
    }
}
