import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm extends JFrame {
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    public CarDealerManagementSystem data;

    private ArrayList<Employee> employees;

    public LoginForm(CarDealerManagementSystem data) {
        this.data = data;


        // Set JFrame properties
        setTitle("Car Dealer Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#DDE6ED"));
        setLayout(null);

        // Create and configure components
        titleLabel = new JLabel("Car Dealer Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.decode("#27374D"));
        titleLabel.setBounds(50, 20, 300, 30);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 70, 80, 20);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 20);

        usernameField = new JTextField();
        usernameField.setBounds(140, 70, 150, 20);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 100, 150, 20);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 140, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check login credentials
                if (checkLoginCredentials(username, password)) {
                    dispose();  // Close the login form
                    Dashboard dashboard = new Dashboard(data);
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setBounds(200, 140, 80, 25);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        });

        // Add components to the JFrame
        add(titleLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(loginButton);
        add(exitButton);

        // Set JFrame size and center on the screen
        setSize(450, 220);
        setLocationRelativeTo(null);
    }

    private boolean checkLoginCredentials(String username, String password) {
        // Check the username and password with the stored credentials in the employee list
        for (Employee employee : data.employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                data.login = employee.getName();
                return true;  // Match found, login successful
            }
        }
        return false;  // No match found, login failed
    }


}
