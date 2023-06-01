import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BlockEmployeePanel extends JPanel {
    private JComboBox<String> employeeDropdown;
    private JButton blockButton;
    private JButton unblockButton;
    private ArrayList<Employee> employeeList;

    private JLabel nameLabel;
    private CarDealerManagementSystem data;
    private EmployeeBlockTable blcklost;

    public BlockEmployeePanel(CarDealerManagementSystem data) {
        this.data = data;
        employeeList = data.employees;

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.decode("#27374D"));

        nameLabel = createLabel("Select Employee:");
        employeeDropdown = createDropdown();
        blockButton = createButton("Block");
        blockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blockEmployee();
                blcklost.populateTable();
            }
        });

        unblockButton = createButton("Unblock");
        unblockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unblockEmployee();
                blcklost.populateTable();
            }
        });

        add(nameLabel);
        add(employeeDropdown);
        add(blockButton);
        add(unblockButton);
        blcklost = new EmployeeBlockTable(data);
        add(blcklost);

    }



    private void blockEmployee() {
        String selectedEmployee = (String) employeeDropdown.getSelectedItem();
        for (Employee employee : employeeList) {
            if (employee.getName().equals(selectedEmployee)) {
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
                        employee.setActive(false);
                        // Perform the blocking operation here, e.g., update the database
                        JOptionPane.showMessageDialog(this, "Employee blocked successfully.", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Password does not match
                        JOptionPane.showMessageDialog(this, "Incorrect password. Employee not blocked.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
        }
    }


    private void unblockEmployee() {
        String selectedEmployee = (String) employeeDropdown.getSelectedItem();
        for (Employee employee : employeeList) {
            if (employee.getName().equals(selectedEmployee)) {
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
                        employee.setActive(true);
                        // Perform the unblocking operation here, e.g., update the database
                        JOptionPane.showMessageDialog(this, "Employee unblocked successfully.", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Password does not match
                        JOptionPane.showMessageDialog(this, "Incorrect password. Employee not unblocked.", "Error",
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

        for (Employee employee : employeeList) {
            dropdown.addItem(employee.getName());
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
