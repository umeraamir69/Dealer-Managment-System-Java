import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployeeGUI extends JPanel {
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel positionLabel;
    private JLabel salaryLabel;
    private JLabel addressLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel activeLabel;

    private JTextField idField;
    private JTextField nameField;
    private JComboBox<String> positionComboBox;
    private JTextField salaryField;
    private JTextField addressField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox activeCheckBox;

    private JButton submitButton;
    private JButton cancelButton;
    private CarDealerManagementSystem data;

    public AddEmployeeGUI(CarDealerManagementSystem data) {
        this.data = data;
        setLayout(new GridLayout(9, 2, 10, 10));
        setBackground(Color.decode("#27374D"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel btn = new JPanel();

        idLabel = createLabel("Employee ID:");
        idField = createTextField();

        nameLabel = createLabel("Employee Name:");
        nameField = createTextField();

        positionLabel = createLabel("Position:");
        positionComboBox = createComboBox(new String[]{"Manager", "Worker", "Head"});

        salaryLabel = createLabel("Salary:");
        salaryField = createTextField();

        addressLabel = createLabel("Address:");
        addressField = createTextField();

        usernameLabel = createLabel("Username:");
        usernameField = createTextField();

        passwordLabel = createLabel("Password:");
        passwordField = createPasswordField();

        activeLabel = createLabel("Active:");
        activeCheckBox = new JCheckBox();

        submitButton = createButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    displayEmployeeData();
                } else {
                    showWarningDialog("Please fill in all the fields.");
                }
            }
        });

        cancelButton = createButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(positionLabel);
        add(positionComboBox);
        add(salaryLabel);
        add(salaryField);
        add(addressLabel);
        add(addressField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(activeLabel);
        add(activeCheckBox);
        add(submitButton);
        add(cancelButton);


        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setForeground(Color.decode("#DDE6ED"));
        textField.setBackground(Color.decode("#526D82"));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return textField;
    }

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setForeground(Color.decode("#DDE6ED"));
        comboBox.setBackground(Color.decode("#526D82"));
        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        comboBox.setMaximumRowCount(3); // Display 3 items at a time
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT); // Align dropdown to the left
        return comboBox;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(Color.decode("#DDE6ED"));
        passwordField.setBackground(Color.decode("#526D82"));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return passwordField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.decode("#DDE6ED"));
        button.setBackground(Color.RED);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); // Add white border
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private void resetFields() {
        idField.setText("");
        nameField.setText("");
        positionComboBox.setSelectedIndex(0);
        salaryField.setText("");
        addressField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        activeCheckBox.setSelected(false);
    }


    private boolean validateFields() {
        return !idField.getText().isEmpty() &&
                !nameField.getText().isEmpty() &&
                !salaryField.getText().isEmpty() &&
                !addressField.getText().isEmpty() &&
                !usernameField.getText().isEmpty() &&
                passwordField.getPassword().length > 0;
    }

    private void displayEmployeeData() {
        String id = idField.getText();
        String name = nameField.getText();
        String position = (String) positionComboBox.getSelectedItem();
        double salary = Double.parseDouble(salaryField.getText());
        String address = addressField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean active = activeCheckBox.isSelected();

        boolean user = false;
         for (Employee employee : data.employees){
             if(employee.getUsername().equals(username)){
                 user = true;
                 break;
             }
         }

          if(!user){
            data.employees.add(new Employee(id,name , position , salary,address,username , password));
            resetFields();
            for (Employee employees : data.employees){
                employees.getEmployeeDetails();
            }
        }
          else{
              showWarningDialog("Username Already Exsists.");
          }



    }

    private void showWarningDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
