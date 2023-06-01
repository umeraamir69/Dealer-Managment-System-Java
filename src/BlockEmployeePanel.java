import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BlockEmployeePanel extends JPanel {
    private JComboBox<String> employeeDropdown;
    private JButton blockedButton;
    private ArrayList<Employee> employeeList;

    public BlockEmployeePanel(CarDealerManagementSystem data) {
        employeeList = data.employees;

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBackground(Color.decode("#E8F1F2"));

        JLabel nameLabel = createLabel("Select Employee:");
        employeeDropdown = createDropdown();
        blockedButton = createButton("Blocked");
        blockedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blockEmployee();
            }
        });

        add(nameLabel);
        add(employeeDropdown);
        add(blockedButton);
    }

    private void blockEmployee() {
        String selectedEmployee = (String) employeeDropdown.getSelectedItem();
        for (Employee employee : employeeList) {
            if (employee.getName().equals(selectedEmployee)) {
                employee.setActive(false);
                break;
            }
        }

        // Perform the blocking operation here, e.g., update the database

        JOptionPane.showMessageDialog(this, "Employee blocked successfully.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
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
