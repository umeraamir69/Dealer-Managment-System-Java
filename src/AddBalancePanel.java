import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBalancePanel extends JPanel {
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton addButton;
    private Finance finance;

    public AddBalancePanel(CarDealerManagementSystem data) {
        this.finance = data.finance;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.decode("#27374D"));

        amountLabel = createLabel("Amount:");
        amountField = createTextField();
        addButton = createButton("Add Balance");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBalance();
            }
        });

        add(amountLabel);
        add(amountField);
        add(addButton);
    }

    private void addBalance() {
        String amountText = amountField.getText();
        if (!amountText.isEmpty()) {
            double amount = Double.parseDouble(amountText);
            String[] options = {"Cash in Hand", "Cash in Bank"};
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Select where to add balance:",
                    "Add Balance",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                finance.recordTransaction(amount, true);
                System.out.println("Balance added to Cash in Hand: " + amount);
                JOptionPane.showMessageDialog(this, "Added $" + amount + " to Cash in Hand.", "Balance Added",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == 1) {
                finance.recordTransaction(amount, false);
                System.out.println("Balance added to Cash in Bank: " + amount);
                JOptionPane.showMessageDialog(this, "Added $" + amount + " to Cash in Bank.", "Balance Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            amountField.setText("");
        }
    }


    // Utility methods for creating components with the desired color scheme

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(10);
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
