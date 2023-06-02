import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashboardHomePanel extends JPanel {
    private JLabel carCountLabel;
    private JLabel employeeCountLabel;
    private JLabel customerCountLabel;
    private JLabel inspectionCountLabel;
    private JLabel financeLabel;

    public DashboardHomePanel(CarDealerManagementSystem data) {
        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        carCountLabel = new JLabel("Cars: " + data.cars.size());
        employeeCountLabel = new JLabel("Employees: " + data.employees.size());
        customerCountLabel = new JLabel("Customers: " + data.customers.size());
        inspectionCountLabel = new JLabel("Inspections: " + data.inspections.size());
        financeLabel = new JLabel("Finance: " + data.finance.getCashInBank()+data.finance.getCashInBank());

        // Set font and alignment for labels
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        carCountLabel.setFont(labelFont);
        employeeCountLabel.setFont(labelFont);
        customerCountLabel.setFont(labelFont);
        inspectionCountLabel.setFont(labelFont);
        financeLabel.setFont(labelFont);

        carCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        employeeCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        customerCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inspectionCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        financeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add labels to the panel
        add(carCountLabel);
        add(employeeCountLabel);
        add(customerCountLabel);
        add(inspectionCountLabel);
        add(financeLabel);
    }
}
