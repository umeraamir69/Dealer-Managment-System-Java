import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BlockCustomerPanel extends JPanel {
    private JComboBox<String> customerDropdown;
    private JButton blockButton;
    private JButton unblockButton;
    private ArrayList<Customer> customerList;

    private JLabel nameLabel;
    private CarDealerManagementSystem data;
    private CustomerBlockTable customerBlockTable;

    public BlockCustomerPanel(CarDealerManagementSystem data) {
        this.data = data;
        customerList = data.customers;

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.decode("#27374D"));

        nameLabel = createLabel("Select Customer:");
        customerDropdown = createDropdown();
        blockButton = createButton("Block");
        blockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blockCustomer();
                customerBlockTable.populateTable();
            }
        });

        unblockButton = createButton("Unblock");
        unblockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unblockCustomer();
                customerBlockTable.populateTable();
            }
        });

        add(nameLabel);
        add(customerDropdown);
        add(blockButton);
        add(unblockButton);
        customerBlockTable = new CustomerBlockTable(data);
        add(customerBlockTable);

    }

    private void blockCustomer() {
        String selectedCustomer = (String) customerDropdown.getSelectedItem();
        for (Customer customer : customerList) {
            if (customer.getName().equals(selectedCustomer)) {
                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(20);

                JPanel panel = new JPanel();
                panel.add(passwordLabel);
                panel.add(passwordField);

                int result = JOptionPane.showOptionDialog(
                        this,
                        panel,
                        "Enter Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (result == JOptionPane.OK_OPTION) {
                    char[] password = passwordField.getPassword();
                    String passwordString = new String(password);

                    if (passwordString.equals("admin")) {
                        customer.setActive(false);
                        // Perform the blocking operation here, e.g., update the database
                        JOptionPane.showMessageDialog(this, "Customer blocked successfully.", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Password does not match
                        JOptionPane.showMessageDialog(this, "Incorrect password. Customer not blocked.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
        }
    }

    private void unblockCustomer() {
        String selectedCustomer = (String) customerDropdown.getSelectedItem();
        for (Customer customer : customerList) {
            if (customer.getName().equals(selectedCustomer)) {
                JPanel panel = new JPanel();
                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(20);

                panel.add(passwordLabel);
                panel.add(passwordField);

                int result = JOptionPane.showOptionDialog(
                        this,
                        panel,
                        "Enter Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (result == JOptionPane.OK_OPTION) {
                    char[] password = passwordField.getPassword();
                    String passwordString = new String(password);

                    if (passwordString.equals("admin")) {
                        customer.setActive(true);
                        // Perform the unblocking operation here, e.g., update the database
                        JOptionPane.showMessageDialog(this, "Customer unblocked successfully.", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Password does not match
                        JOptionPane.showMessageDialog(this, "Incorrect password. Customer not unblocked.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
        }
    }

    // Utility methods for creating components with the desired color scheme

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#27374D"));
        return label;
    }

    private JComboBox<String> createDropdown() {
        JComboBox<String> dropdown = new JComboBox<>();
        dropdown.setBackground(Color.decode("#DDE6ED"));
        dropdown.setForeground(Color.decode("#27374D"));

        for (Customer customer : customerList) {
            dropdown.addItem(customer.getName());
        }
        return dropdown;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#27374D"));
        button.setForeground(Color.decode("#DDE6ED"));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
