import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SalesPanel extends JPanel {
    private JTextField searchField;
    private JButton editButton;
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private ArrayList<Sale> sales;

    public SalesPanel(CarDealerManagementSystem data) {
        this.sales = data.sales;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#E6EEF3"));

        JPanel topPanel = createTopPanel();
        JScrollPane scrollPane = createTableScrollPane();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#E6EEF3"));

        // Search field
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 25));
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterTable(searchField.getText());
            }
        });

        topPanel.add(searchField, BorderLayout.WEST);

        // Edit button
        editButton = new JButton("Edit");
        editButton.setBackground(Color.decode("#27374D"));
        editButton.setForeground(Color.decode("#DDE6ED"));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editSelectedSale();
            }
        });

        topPanel.add(editButton, BorderLayout.EAST);

        return topPanel;
    }

    private JScrollPane createTableScrollPane() {
        String[] columnNames = {"Sales ID", "Car Name", "Employee Name", "Customer Name", "Price"};
        Object[][] rowData = new Object[sales.size()][5];

        for (int i = 0; i < sales.size(); i++) {
            Sale sale = sales.get(i);
            rowData[i][0] = sale.getSalesID();
            rowData[i][1] = sale.getCar().getMake();
            rowData[i][2] = sale.getEmployee().getName();
            rowData[i][3] = sale.getCustomer().getName();
            rowData[i][4] = sale.getPrice();
        }

        tableModel = new DefaultTableModel(rowData, columnNames);
        salesTable = new JTable(tableModel);
        salesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(salesTable);

        return scrollPane;
    }

    private void filterTable(String keyword) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        salesTable.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + keyword);
        sorter.setRowFilter(rowFilter);
    }

    private void editSelectedSale() {
        int selectedRow = salesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String salesID = (String) tableModel.getValueAt(selectedRow, 0);
            double price = (double) tableModel.getValueAt(selectedRow, 4);
            openEditPopup(salesID, price);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a sale to edit.", "No Sale Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void openEditPopup(String salesID, double currentPrice) {
        JFrame frame = new JFrame("Edit Sale Price");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout());

        JLabel priceLabel = new JLabel("Current Price: $" + currentPrice);
        panel.add(priceLabel, BorderLayout.NORTH);

        JTextField priceField = new JTextField();
        panel.add(priceField, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPriceStr = priceField.getText();
                try {
                    double newPrice = Double.parseDouble(newPriceStr);
                    updateSalePrice(salesID, newPrice);
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid price format. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(submitButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void updateSalePrice(String salesID, double newPrice) {
        for (Sale sale : sales) {
            if (sale.getSalesID().equals(salesID)) {
                sale.setPrice(newPrice);
                tableModel.setValueAt(newPrice, salesTable.getSelectedRow(), 4);
                JOptionPane.showMessageDialog(this, "Sale price updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }
}
