import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SalesDetailsPanel extends JPanel {
    private JComboBox<String> salesDropdown;
    private JTextArea detailsTextArea;
    private ArrayList<Sale> sales;

    public SalesDetailsPanel(CarDealerManagementSystem data) {
        this.sales = data.sales;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#E6EEF3"));

        JPanel topPanel = createTopPanel();
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setBackground(Color.decode("#DDE6ED"));
        detailsTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(detailsTextArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#E6EEF3"));

        JLabel label = new JLabel("Select Sale: ");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        salesDropdown = new JComboBox<>();
        salesDropdown.setFont(new Font("Arial", Font.PLAIN, 14));

        for (Sale sale : sales) {
            String saleInfo = String.format("Sale ID: %s - Car: %s - Customer: %s",
                    sale.getSalesID(), sale.getCar().getMake(), sale.getCustomer().getName());
            salesDropdown.addItem(saleInfo);
        }

        salesDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = salesDropdown.getSelectedIndex();
                if (selectedIndex != -1) {
                    Sale selectedSale = sales.get(selectedIndex);
                    displaySaleDetails(selectedSale);
                }
            }
        });

        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.decode("#E6EEF3"));
        innerPanel.add(label);
        innerPanel.add(salesDropdown);

        topPanel.add(innerPanel, BorderLayout.NORTH);

        return topPanel;
    }

    private void displaySaleDetails(Sale sale) {
        StringBuilder details = new StringBuilder();

        details.append("Sale ID: ").append(sale.getSalesID()).append("\n");
        details.append("Car Details:\n");
        details.append(sale.getCar().getDetails()).append("\n");
        details.append("Customer Details:\n");
        details.append(sale.getCustomer().getDetails()).append("\n");
        details.append("Employee Details:\n");
        details.append(sale.getEmployee().getDetails()).append("\n");
        details.append("Sale Details:\n");
        details.append("Sale Date: ").append(sale.getSaleDate()).append("\n");
        details.append("Price: $").append(sale.getPrice()).append("\n");

        detailsTextArea.setText(details.toString());
    }
}
