import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeTableGUI extends JPanel {
    private JLabel filterLabel;
    private JComboBox<String> filterComboBox;
    private JTextField searchField;
    private JButton deleteButton;
    private JButton editButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private List<Employee> employees;

    public EmployeeTableGUI(CarDealerManagementSystem data) {
        this.employees = data.employees;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#27374D"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        filterLabel = createLabel("Filter:");
        filterComboBox = createComboBox(new String[]{"All", "Active", "Blocked"});
        filterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterEmployees();
            }
        });

        searchField = createTextField();
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterEmployees();
            }
        });

        deleteButton = createButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        editButton = createButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editEmployee();
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
        employeeTable = new JTable(tableModel);
        employeeTable.setBackground(Color.decode("#DDE6ED"));
        employeeTable.setForeground(Color.decode("#27374D"));
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        populateTable();
    }

    private void populateTable() {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        tableModel.addColumn("Employee ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");
        tableModel.addColumn("Salary");
        tableModel.addColumn("Address");
        tableModel.addColumn("Username");
        tableModel.addColumn("Active");

        for (Employee employee : employees) {
            Object[] row = new Object[]{
                    employee.getEmployeeID(),
                    employee.getName(),
                    employee.getPosition(),
                    employee.getSalary(),
                    employee.getAddress(),
                    employee.getUsername(),
                    employee.isActive()
            };

            tableModel.addRow(row);
        }
    }

    private void filterEmployees() {
        String filter = (String) filterComboBox.getSelectedItem();
        String searchTerm = searchField.getText().toLowerCase();

        tableModel.setRowCount(0);

        for (Employee employee : employees) {
            boolean isActive = employee.isActive();
            if ((filter.equals("All") || (filter.equals("Active") && isActive) ||
                    (filter.equals("Blocked") && !isActive)) &&
                    (employee.getEmployeeID().toLowerCase().contains(searchTerm) ||
                            employee.getName().toLowerCase().contains(searchTerm) ||
                            employee.getPosition().toLowerCase().contains(searchTerm) ||
                            String.valueOf(employee.getSalary()).contains(searchTerm) ||
                            employee.getAddress().toLowerCase().contains(searchTerm) ||
                            employee.getUsername().toLowerCase().contains(searchTerm))) {
                Object[] row = new Object[]{
                        employee.getEmployeeID(),
                        employee.getName(),
                        employee.getPosition(),
                        employee.getSalary(),
                        employee.getAddress(),
                        employee.getUsername(),
                        employee.isActive()
                };

                tableModel.addRow(row);
            }
        }
    }

    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
            employees = employees.stream()
                    .filter(e -> !e.getEmployeeID().equals(employeeId))
                    .collect(Collectors.toList());
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow >= 0) {
            String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
            Employee selectedEmployee = employees.stream()
                    .filter(e -> e.getEmployeeID().equals(employeeId))
                    .findFirst()
                    .orElse(null);

            if (selectedEmployee != null) {
                EditEmployeeGUI editEmployeeGUI = new EditEmployeeGUI(selectedEmployee);
                editEmployeeGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        populateTable();
                    }
                });
                editEmployeeGUI.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.", "No Selection",
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





    public class EditEmployeeGUI extends JFrame {
        private JTextField nameField;
        private JTextField positionField;
        private JTextField salaryField;
        private JTextField addressField;
        private JTextField usernameField;
        private JCheckBox activeCheckBox;
        private JButton saveButton;
        private Employee employee;

        public EditEmployeeGUI(Employee employee) {
            this.employee = employee;

            setTitle("Edit Employee");
            setLayout(new GridLayout(7, 2, 10, 10));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setLocationRelativeTo(null);

            JLabel nameLabel = createLabel("Name:");
            nameField = createTextField(employee.getName());

            JLabel positionLabel = createLabel("Position:");
            positionField = createTextField(employee.getPosition());

            JLabel salaryLabel = createLabel("Salary:");
            salaryField = createTextField(String.valueOf(employee.getSalary()));

            JLabel addressLabel = createLabel("Address:");
            addressField = createTextField(employee.getAddress());

            JLabel usernameLabel = createLabel("Username:");
            usernameField = createTextField(employee.getUsername());

            JLabel activeLabel = createLabel("Active:");
            activeCheckBox = new JCheckBox();
            activeCheckBox.setSelected(employee.isActive());

            saveButton = createButton("Save");
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveEmployee();
                }
            });

            add(nameLabel);
            add(nameField);
            add(positionLabel);
            add(positionField);
            add(salaryLabel);
            add(salaryField);
            add(addressLabel);
            add(addressField);
            add(usernameLabel);
            add(usernameField);
            add(activeLabel);
            add(activeCheckBox);
            add(new JLabel()); // Empty label for spacing
            add(saveButton);

            pack();
        }

        private void saveEmployee() {
            String name = nameField.getText().trim();
            String position = positionField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            String address = addressField.getText().trim();
            String username = usernameField.getText().trim();
            boolean isActive = activeCheckBox.isSelected();

            // Update the employee object with the new values
            employee.setName(name);
            employee.setPosition(position);
            employee.setSalary(salary);
            employee.setAddress(address);
            employee.setUsername(username);
            employee.setActive(isActive);

            // Perform the save operation here, e.g., update the database

            JOptionPane.showMessageDialog(this, "Employee details saved successfully.", "Success",
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
