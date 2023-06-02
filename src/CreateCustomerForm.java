import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateCustomerForm extends JPanel {
    private JTextField customerIdField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JCheckBox activeCheckBox;
    private JButton submitButton;
    private JButton cancelButton;
    private ArrayList<Customer> customerList;

    private  CarDealerManagementSystem data;

    public CreateCustomerForm(CarDealerManagementSystem data) {
        this.data = data;
        this.customerList = data.customers;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#E8F1F2"));

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBackground(Color.decode("#E8F1F2"));

        JLabel customerIdLabel = createLabel("Customer ID:");
        customerIdField = createTextField();
        JLabel nameLabel = createLabel("Name:");
        nameField = createTextField();
        JLabel addressLabel = createLabel("Address:");
        addressField = createTextField();
        JLabel phoneNumberLabel = createLabel("Phone Number:");
        phoneNumberField = createTextField();
        JLabel emailLabel = createLabel("Email:");
        emailField = createTextField();
        JLabel activeLabel = createLabel("Active:");
        activeCheckBox = createCheckBox();

        formPanel.add(customerIdLabel);
        formPanel.add(customerIdField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(phoneNumberLabel);
        formPanel.add(phoneNumberField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(activeLabel);
        formPanel.add(activeCheckBox);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.decode("#E8F1F2"));

        submitButton = createButton("Submit");
        cancelButton = createButton("Cancel");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = customerIdField.getText();
                String name = nameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                boolean isActive = activeCheckBox.isSelected();

                if (customerId.isEmpty() || name.isEmpty() || address.isEmpty() ||
                        phoneNumber.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateCustomerForm.this, "Please enter all values.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Check if email already exists
                    boolean emailExists = false;
                    for (Customer customer : customerList) {
                        if (customer.getEmail().equals(email) || customer.getPhoneNumber().equals(phoneNumber)) {
                            emailExists = true;
                            break;
                        }
                    }
                    if (emailExists) {
                        JOptionPane.showMessageDialog(CreateCustomerForm.this, "Email & Phone already exists.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        data.addCustomer(new Customer(customerId , name , address , phoneNumber , email));
                        JOptionPane.showMessageDialog(CreateCustomerForm.this, "Customer created", "Error",
                                JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the JPanel when Cancel button is pressed
                SwingUtilities.getWindowAncestor(CreateCustomerForm.this).dispose();
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private void reset(){
        customerIdField.setText("");
        nameField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        activeCheckBox.setSelected(false);
    }
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#27374D"));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(Color.decode("#DDE6ED"));
        textField.setForeground(Color.decode("#27374D"));
        return textField;
    }

    private JCheckBox createCheckBox() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBackground(Color.decode("#E8F1F2"));
        checkBox.setForeground(Color.decode("#27374D"));
        return checkBox;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#27374D"));
        button.setForeground(Color.decode("#DDE6ED"));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
