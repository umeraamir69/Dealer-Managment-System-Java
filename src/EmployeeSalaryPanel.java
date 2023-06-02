import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeSalaryPanel extends JPanel {
    private JComboBox<String> employeeDropdown;
    private JButton recordSalaryButton;
    private JTable salaryTable;
    private DefaultTableModel tableModel;
    private ArrayList<Employee> employeeList;
    private JDialog dialog;
    public EmployeeSalaryPanel(CarDealerManagementSystem data) {
        this.employeeList =data.employees;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#27374D"));

        JPanel topPanel = createTopPanel();
        JPanel tablePanel = createTablePanel();

        add(topPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#27374D"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new BorderLayout());

        JPanel employeePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        employeePanel.setBackground(Color.decode("#27374D"));

        JLabel employeeLabel = new JLabel("Employee:");
        employeeLabel.setForeground(Color.decode("#DDE6ED"));
        employeeDropdown = createEmployeeDropdown();
        JLabel salaryLabel = new JLabel("Current Salary: N/A");
        salaryLabel.setForeground(Color.decode("#DDE6ED"));

        employeeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = employeeDropdown.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Employee selectedEmployee = employeeList.get(selectedIndex);
                    Double currentSalary = selectedEmployee.getSalary();

                    if (currentSalary != null) {
                        salaryLabel.setText("Current Salary: " + currentSalary);
                    } else {
                        salaryLabel.setText("Current Salary: N/A");
                    }
                }
            }
        });

        employeePanel.add(employeeLabel);
        employeePanel.add(employeeDropdown);
        employeePanel.add(salaryLabel);

        recordSalaryButton = createRecordSalaryButton();

        topPanel.add(employeePanel, BorderLayout.WEST);
        topPanel.add(recordSalaryButton, BorderLayout.EAST);

        return topPanel;
    }



    private JComboBox<String> createEmployeeDropdown() {
        JComboBox<String> dropdown = new JComboBox<>();
        dropdown.setBackground(Color.decode("#DDE6ED"));
        dropdown.setForeground(Color.decode("#27374D"));

        for (Employee employee : employeeList) {
            if (employee.isActive()) {
                dropdown.addItem(employee.getName() + " (" + employee.getEmployeeID() + ")");
            }
        }

        return dropdown;
    }

    private JButton createRecordSalaryButton() {
        JButton button = new JButton("Record Salary");
        button.setBackground(Color.decode("#27374D"));
        button.setForeground(Color.decode("#DDE6ED"));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRecordSalaryPopup();
            }
        });

        return button;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.decode("#27374D"));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Month,Year"};
        Object[][] rowData = {}; // Empty data for now

        tableModel = new DefaultTableModel(rowData, columnNames);
        salaryTable = new JTable(tableModel);
        salaryTable.setBackground(Color.decode("#DDE6ED"));
        salaryTable.setForeground(Color.decode("#27374D"));
        salaryTable.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(salaryTable);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private void showRecordSalaryPopup() {
        int selectedIndex = employeeDropdown.getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee selectedEmployee = employeeList.get(selectedIndex);

            JPanel popupPanel = new JPanel();
            popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
            popupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            popupPanel.setBackground(Color.decode("#DDE6ED"));

            JLabel monthLabel = new JLabel("Month:");
            JComboBox<String> monthDropdown = new JComboBox<>(new String[]{"January", "February", "March", "April",
                    "May", "June", "July", "August", "September", "October", "November", "December"});
            monthDropdown.setBackground(Color.decode("#DDE6ED"));

            JLabel yearLabel = new JLabel("Year:");
            JTextField yearField = new JTextField(10);
            yearField.setBackground(Color.decode("#DDE6ED"));
            yearField.setForeground(Color.decode("#27374D"));

            JButton submitButton = new JButton("Submit");
            submitButton.setBackground(Color.decode("#27374D"));
            submitButton.setForeground(Color.decode("#DDE6ED"));

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBackground(Color.decode("#27374D"));
            cancelButton.setForeground(Color.decode("#DDE6ED"));

            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String month = (String) monthDropdown.getSelectedItem();
                    String year = yearField.getText();
                    String salaryRecord = month + ", " + year;

                    if (!selectedEmployee.getSalaryReceived().contains(salaryRecord)) {
                        selectedEmployee.recordSalary(salaryRecord);
                        JOptionPane.showMessageDialog(EmployeeSalaryPanel.this,
                                "Salary recorded for " + selectedEmployee.getName() +
                                        " (" + selectedEmployee.getEmployeeID() + ") " +
                                        "for the month of " + month + ", " + year,
                                "Salary Recorded", JOptionPane.INFORMATION_MESSAGE);

                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(EmployeeSalaryPanel.this,
                                "Salary for " + selectedEmployee.getName() +
                                        " (" + selectedEmployee.getEmployeeID() + ") " +
                                        "for the month of " + month + ", " + year +
                                        " is already recorded.",
                                "Salary Already Recorded", JOptionPane.WARNING_MESSAGE);
                    }

                    dialog.dispose();
                }
            });


            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });

            popupPanel.add(monthLabel);
            popupPanel.add(monthDropdown);
            popupPanel.add(Box.createVerticalStrut(10));
            popupPanel.add(yearLabel);
            popupPanel.add(yearField);
            popupPanel.add(Box.createVerticalStrut(10));
            popupPanel.add(submitButton);
            popupPanel.add(cancelButton);

            dialog = new JDialog();
            dialog.setTitle("Record Salary");
            dialog.setContentPane(popupPanel);
            dialog.setModal(true);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);


        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing data

        int selectedIndex = employeeDropdown.getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee selectedEmployee = employeeList.get(selectedIndex);
            ArrayList<String> salaryReceived = selectedEmployee.getSalaryReceived();

            for (String salary : salaryReceived) {
                String[] rowData = {salary};
                tableModel.addRow(rowData);
            }
        }
    }
}
