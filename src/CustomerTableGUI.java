import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerTableGUI extends JPanel {
    private JLabel filterLabel;
    private JComboBox<String> filterComboBox;
    private JTextField searchField;
    private JButton deleteButton;
    private JButton editButton;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private List<Customer> customers;

    public CustomerTableGUI(CarDealerManagementSystem data) {
        this.customers = data.customers;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#27374D"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        filterLabel = createLabel("Filter:");
        filterComboBox = createComboBox(new String[]{"All", "Active", "Blocked"});
        filterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterCustomers();
            }
        });

        searchField = createTextField();
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterCustomers();
            }
        });

        deleteButton = createButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        editButton = createButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCustomer();
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.decode("#27374D"));
        topPanel.add(filterLabel);
        topPanel.add(filterComboBox);
        topPanel.add(searchField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.decode("#27374D"));
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        tableModel = new DefaultTableModel();
        customerTable = new JTable(tableModel);
        customerTable.setBackground(Color.decode("#DDE6ED"));
        customerTable.setForeground(Color.decode("#27374D"));
        JScrollPane scrollPane = new JScrollPane(customerTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        populateTable();
    }

    private void populateTable() {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");
        tableModel.addColumn("Active");

        for (Customer customer : customers) {
            Object[] row = new Object[]{
                    customer.getCustomerID(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.isActive()
            };

            tableModel.addRow(row);
        }
    }

    private void filterCustomers() {
        String filter = (String) filterComboBox.getSelectedItem();
        String searchTerm = searchField.getText().toLowerCase();

        tableModel.setRowCount(0);

        for (Customer customer : customers) {
            boolean isActive = customer.isActive();
            if ((filter.equals("All") || (filter.equals("Active") && isActive) ||
                    (filter.equals("Blocked") && !isActive)) &&
                    (customer.getCustomerID().toLowerCase().contains(searchTerm) ||
                            customer.getName().toLowerCase().contains(searchTerm) ||
                            customer.getEmail().toLowerCase().contains(searchTerm) ||
                            customer.getPhoneNumber().toLowerCase().contains(searchTerm) ||
                            customer.getAddress().toLowerCase().contains(searchTerm))) {
                Object[] row = new Object[]{
                        customer.getCustomerID(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        customer.getAddress(),
                        customer.isActive()
                };

                tableModel.addRow(row);
            }
        }
    }

    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String customerId = (String) customerTable.getValueAt(selectedRow, 0);
            System.out.println("Selected customer ID: " + customerId);

            customers = customers.stream()
                    .filter(c -> !c.getCustomerID().equals(customerId))
                    .collect(Collectors.toList());

            // Verify the customer IDs after deletion


            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }


    private void editCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow >= 0) {
            String customerId = (String) customerTable.getValueAt(selectedRow, 0);
            Customer selectedCustomer = customers.stream()
                    .filter(c -> c.getCustomerID().equals(customerId))
                    .findFirst()
                    .orElse(null);

            if (selectedCustomer != null) {
                EditCustomerGUI editCustomerGUI = new EditCustomerGUI(selectedCustomer);
                editCustomerGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        populateTable();
                    }
                });
                editCustomerGUI.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a customer to edit.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Utility methods for creating components with the desired color scheme

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        return label;
    }

    private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBackground(Color.decode("#DDE6ED"));
        comboBox.setForeground(Color.decode("#27374D"));
        return comboBox;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setBackground(Color.decode("#DDE6ED"));
        textField.setForeground(Color.decode("#27374D"));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#27374D"));
        button.setForeground(Color.decode("#DDE6ED"));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }


    public class EditCustomerGUI extends JFrame {
        private JTextField nameField;
        private JTextField emailField;
        private JTextField phoneNumberField;
        private JTextField addressField;
        private JCheckBox activeCheckBox;
        private JButton saveButton;
        private Customer customer;

        public EditCustomerGUI(Customer customer) {
            this.customer = customer;

            setTitle("Edit Customer");
            setLayout(new GridLayout(6, 2, 10, 10));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);

            JLabel nameLabel = createLabel("Name:");
            nameField = createTextField(customer.getName());

            JLabel emailLabel = createLabel("Email:");
            emailField = createTextField(customer.getEmail());

            JLabel phoneNumberLabel = createLabel("Phone Number:");
            phoneNumberField = createTextField(customer.getPhoneNumber());

            JLabel addressLabel = createLabel("Address:");
            addressField = createTextField(customer.getAddress());

            JLabel activeLabel = createLabel("Active:");
            activeCheckBox = new JCheckBox();
            activeCheckBox.setSelected(customer.isActive());

            saveButton = createButton("Save");
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveCustomer();
                }
            });

            add(nameLabel);
            add(nameField);
            add(emailLabel);
            add(emailField);
            add(phoneNumberLabel);
            add(phoneNumberField);
            add(addressLabel);
            add(addressField);
            add(activeLabel);
            add(activeCheckBox);
            add(new JLabel()); // Empty label for spacing
            add(saveButton);

            pack();
        }

        private void saveCustomer() {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();
            String address = addressField.getText().trim();
            boolean isActive = activeCheckBox.isSelected();

            // Update the customer object with the new values
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);
            customer.setActive(isActive);

            // Perform the save operation here, e.g., update the database

            JOptionPane.showMessageDialog(this, "Customer details saved successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

        // Utility methods for creating components with the desired color scheme

        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setForeground(Color.decode("#27374D"));
            return label;
        }

        private JTextField createTextField(String text) {
            JTextField textField = new JTextField(text);
            textField.setBackground(Color.decode("#DDE6ED"));
            textField.setForeground(Color.decode("#27374D"));
            return textField;
        }

        private JButton createButton(String text) {
            JButton button = new JButton(text);
            button.setBackground(Color.decode("#27374D"));
            button.setForeground(Color.decode("#DDE6ED"));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return button;
        }


    }

}
