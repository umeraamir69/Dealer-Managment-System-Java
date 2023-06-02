import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class FinancePanel extends JPanel {
    private JLabel cashInHandLabel;
    private JLabel cashInBankLabel;
    private Finance finance;
    private JButton addButton;

    public FinancePanel(CarDealerManagementSystem data) {
        this.finance = data.finance;

        setLayout(new GridLayout(10, 2, 10, 10));
        setBackground(Color.decode("#27374D"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel cashInHandTextLabel = createLabel("Balance in Hand:");
        cashInHandLabel = createValueLabel(finance.getCashInHand());

        JLabel cashInBankTextLabel = createLabel("Balance in Bank:");
        cashInBankLabel = createValueLabel(finance.getCashInBank());

        addButton = createButton("Add Balance");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBalance();
            }
        });

        add(cashInHandTextLabel);
        add(cashInHandLabel);
        add(cashInBankTextLabel);
        add(cashInBankLabel);
        add(addButton);
    }

    public void updateFinanceStatus() {
        cashInHandLabel.setText(formatAmount(finance.getCashInHand()));
        cashInBankLabel.setText(formatAmount(finance.getCashInBank()));
    }

    public void addBalance() {
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
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter the amount to add to Cash in Hand:"));
            finance.recordTransaction(amount, true);
            updateFinanceStatus();
            System.out.println("Balance added to Cash in Hand: " + amount);
        } else if (choice == 1) {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter the amount to add to Cash in Bank:"));
            finance.recordTransaction(amount, false);
            updateFinanceStatus();
            System.out.println("Balance added to Cash in Bank: " + amount);
        }
    }


    // Utility methods for creating components with the desired color scheme

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        return label;
    }

    private JLabel createValueLabel(double amount) {
        JLabel label = new JLabel(formatAmount(amount));
        label.setForeground(Color.decode("#DDE6ED"));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#27374D"));
        button.setForeground(Color.decode("#DDE6ED"));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "$" + df.format(amount);
    }
}
