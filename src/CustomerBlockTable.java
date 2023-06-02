import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerBlockTable extends JPanel {
    private JLabel filterLabel;
    private JComboBox<String> filterComboBox;
    private JTextField searchField;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private List<Customer> customers;

    public CustomerBlockTable(CarDealerManagementSystem data) {
        this.customers = data.customers;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#27374D"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        filterLabel = createLabel("Filter:");
        filterComboBox = createComboBox(new String[]{"All", "Active", "Blocked"});
        filterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterCustomers();
            }
        });

        searchField = createTextField();
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterCustomers();
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.decode("#27374D"));
        topPanel.add(filterLabel);
        topPanel.add(filterComboBox);
        topPanel.add(searchField);

        tableModel = new DefaultTableModel();
        customerTable = new JTable(tableModel);
        customerTable.setBackground(Color.decode("#DDE6ED"));
        customerTable.setForeground(Color.decode("#27374D"));
        JScrollPane scrollPane = new JScrollPane(customerTable);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        populateTable();
    }

    public void populateTable() {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");
        tableModel.addColumn("Active");

        for (Customer customer : customers) {
            Object[] row = new Object[]{
                    customer.getCustomerID(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.isActive()
            };
            tableModel.addRow(row);
        }
    }
    private void filterCustomers() {
        String filter = (String) filterComboBox.getSelectedItem();
        String searchTerm = searchField.getText().toLowerCase();
        tableModel.setRowCount(0);
        for (Customer customer : customers) {
            boolean isActive = customer.isActive();
            if ((filter.equals("All") || (filter.equals("Active") && isActive) ||
                    (filter.equals("Blocked") && !isActive)) &&
                    (customer.getCustomerID().toLowerCase().contains(searchTerm) ||
                            customer.getName().toLowerCase().contains(searchTerm) ||
                            customer.getEmail().toLowerCase().contains(searchTerm) ||
                            customer.getPhoneNumber().toLowerCase().contains(searchTerm) ||
                            customer.getAddress().toLowerCase().contains(searchTerm))) {
                Object[] row = new Object[]{
                        customer.getCustomerID(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        customer.getAddress(),
                        customer.isActive()
                };
                tableModel.addRow(row);
            }
        }
    }
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        return label;
    }
    private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBackground(Color.decode("#DDE6ED"));
        comboBox.setForeground(Color.decode("#27374D"));
        return comboBox;
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
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
