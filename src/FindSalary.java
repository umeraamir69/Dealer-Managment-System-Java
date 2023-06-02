import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindSalary extends JPanel {
    private JComboBox<String> employeeDropdown;
    private JTable salaryTable;
    private JButton recordSalaryButton;
    private ArrayList<Employee> employees;

    public FindSalary(CarDealerManagementSystem data) {
        this.employees = data.employees;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#27374D"));

        JPanel topPanel = createTopPanel();
        JScrollPane tableScrollPane = createSalaryTable();

        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.decode("#27374D"));

        JLabel searchLabel = createLabel("Select Employee:");
        employeeDropdown = createEmployeeDropdown();
        employeeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSalaryTable();
                updateSalaryField();
            }
        });

        JLabel salaryLabel = createLabel("Current Salary:");
        JTextField salaryField = createSalaryField();

        topPanel.add(searchLabel);
        topPanel.add(employeeDropdown);
        topPanel.add(salaryLabel);
        topPanel.add(salaryField);

        return topPanel;
    }
    private JTextField createSalaryField() {
        JTextField salaryField = new JTextField(10);
        salaryField.setEditable(false);
        salaryField.setBackground(Color.decode("#DDE6ED"));
        salaryField.setForeground(Color.decode("#27374D"));
        salaryField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        salaryField.setFont(new Font("Arial", Font.PLAIN, 12));
        salaryField.setHorizontalAlignment(JTextField.CENTER);
        return salaryField;
    }

    private void updateSalaryField() {
        int selectedIndex = employeeDropdown.getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee selectedEmployee = employees.get(selectedIndex);
            double currentSalary = selectedEmployee.getSalary();

            JTextField salaryField = (JTextField) ((Container) employeeDropdown.getParent()).getComponent(3);
            salaryField.setText(String.valueOf(currentSalary));
        }
    }

    private JScrollPane createSalaryTable() {
        String[] columnNames = {"Month", "Year"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        salaryTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(salaryTable);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        return scrollPane;
    }

    private void showSalaryTable() {
        int selectedIndex = employeeDropdown.getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee selectedEmployee = employees.get(selectedIndex);

            DefaultTableModel model = (DefaultTableModel) salaryTable.getModel();
            model.setRowCount(0);

            for (String salaryReceived : selectedEmployee.getSalaryReceived()) {
                String[] salaryData = salaryReceived.split(",");
                model.addRow(salaryData);
            }
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        return label;
    }

    private JComboBox<String> createEmployeeDropdown() {
        JComboBox<String> dropdown = new JComboBox<>();
        dropdown.setBackground(Color.decode("#DDE6ED"));
        dropdown.setForeground(Color.decode("#27374D"));

        for (Employee employee : employees) {
            if (employee.isActive()) {
                String employeeInfo = employee.getName() + " (" + employee.getEmployeeID() + ")";
                dropdown.addItem(employeeInfo);
            }
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
