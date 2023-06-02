import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SalesReportPanel extends JPanel {
    private JComboBox<String> customerDropdown;
    private JTable salesTable;

    private ArrayList<Sale> sales;
    private ArrayList<Customer> customers;

    public SalesReportPanel(CarDealerManagementSystem data) {
        this.sales = data.sales;
        this.customers = data.customers;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#27374D"));

        JLabel customerLabel = new JLabel("Select Customer:");
        customerDropdown = new JComboBox<>();

        for (Customer customer : customers) {
            customerDropdown.addItem(customer.getName());
        }

        customerDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCustomerName = (String) customerDropdown.getSelectedItem();
                Customer selectedCustomer = getCustomerByName(selectedCustomerName);
                generateSalesTable(selectedCustomer);
            }
        });

        topPanel.add(customerLabel);
        topPanel.add(customerDropdown);

        add(topPanel, BorderLayout.NORTH);

        salesTable = new JTable();
        salesTable.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(salesTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    private Customer getCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    private void generateSalesTable(Customer selectedCustomer) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Sale ID");
        model.addColumn("Product");
        model.addColumn("Price");

        for (Sale sale : sales) {
            if (sale.getCustomer().equals(selectedCustomer)) {
                model.addRow(new Object[]{sale.getSalesID(), sale.getCar().getDetails(), sale.getPrice()});
            }
        }

        salesTable.setModel(model);
    }
}
