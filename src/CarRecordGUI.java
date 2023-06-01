import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarRecordGUI extends JPanel {
    private ArrayList<Car> carRecords;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField searchField;
    private JComboBox<String> sortComboBox;
    private CarDealerManagementSystem data;

    public CarRecordGUI(CarDealerManagementSystem data) {
        this.carRecords = data.cars;
        this.data = data;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create table model and table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Car ID");
        tableModel.addColumn("Make");
        tableModel.addColumn("Model");
        tableModel.addColumn("Variant");
        tableModel.addColumn("Price");
        tableModel.addColumn("Status");
        tableModel.addColumn("Image");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Color");
        tableModel.addColumn("Registration Number");
        table = new JTable(tableModel);

        populateTable(carRecords);
        // Create search panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                searchCarRecords();
            }

            public void removeUpdate(DocumentEvent e) {
                searchCarRecords();
            }

            public void changedUpdate(DocumentEvent e) {
                searchCarRecords();
            }
        });

        // Create sort combo box
        sortComboBox = new JComboBox<>();
        sortComboBox.addItem("All");
        sortComboBox.addItem("Available");
        sortComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCarRecords();
            }
        });

        // Create search panel components
        JPanel searchComponentPanel = new JPanel(new BorderLayout());
        searchComponentPanel.add(searchField, BorderLayout.CENTER);
        searchComponentPanel.add(sortComboBox, BorderLayout.EAST);

        // Add components to the search panel
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchComponentPanel, BorderLayout.CENTER);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Create edit button
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String carID = table.getValueAt(selectedRow, 0).toString();
                    Car selectedCar = getCarByID(carID);
                    if (selectedCar != null) {
                        openEditForm(selectedCar);
                    }
                } else {
                    JOptionPane.showMessageDialog(CarRecordGUI.this, "Please select a car to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String carID = table.getValueAt(selectedRow, 0).toString();
                    Car selectedCar = getCarByID(carID);
                    if (selectedCar != null) {
                        carRecords.remove(selectedCar);
                        populateTable(carRecords);
                    }
                } else {
                    JOptionPane.showMessageDialog(CarRecordGUI.this, "Please select a car to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the current panel
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private void populateTable(List<Car> cars) {
        clearTable();
        for (Car car : cars) {
            Object[] rowData = {car.getCarID(), car.getMake(), car.getModel(), car.getVariant(), car.getPrice(),
                    car.getStatus(), car.getImage(), car.getRating(), car.getColour(), car.getRegistrationNumber()};
            tableModel.addRow(rowData);
        }
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void searchCarRecords() {
        String searchText = searchField.getText().toLowerCase();
        String sortOption = sortComboBox.getSelectedItem().toString();

        List<Car> searchResults = new ArrayList<>();
        for (Car car : carRecords) {
            boolean matchesSearchText = car.getMake().toLowerCase().contains(searchText) ||
                    car.getModel().toLowerCase().contains(searchText) ||
                    car.getVariant().toLowerCase().contains(searchText) ||
                    car.getRegistrationNumber().toLowerCase().contains(searchText);

            boolean matchesSortOption;
            if (sortOption.equals("Available")) {
                matchesSortOption = car.getStatus();
            } else {
                matchesSortOption = true; // Show all cars
            }

            if (matchesSearchText && matchesSortOption) {
                searchResults.add(car);
            }
        }

        // Sort search results based on car ID
        searchResults.sort(Comparator.comparing(Car::getCarID));

        populateTable(searchResults);
    }

    private Car getCarByID(String carID) {
        for (Car car : carRecords) {
            if (car.getCarID().equals(carID)) {
                return car;
            }
        }
        return null;
    }

    private void openEditForm(Car car) {
        JDialog editFormDialog = new JDialog();
        editFormDialog.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel makeLabel = new JLabel("Make:");
        JTextField makeTextField = new JTextField(car.getMake());

        JLabel modelLabel = new JLabel("Model:");
        JTextField modelTextField = new JTextField(car.getModel());

        JLabel variantLabel = new JLabel("Variant:");
        JTextField variantTextField = new JTextField(car.getVariant());

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceTextField = new JTextField(String.valueOf(car.getPrice()));

        JLabel statusLabel = new JLabel("Status:");
        JCheckBox statusCheckBox = new JCheckBox();
        statusCheckBox.setSelected(car.getStatus());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String make = makeTextField.getText();
                String model = modelTextField.getText();
                String variant = variantTextField.getText();
                float price = Float.parseFloat(priceTextField.getText());
                boolean status = statusCheckBox.isSelected();

                // Update car object with new values
                car.setMake(make);
                car.setModel(model);
                car.setVariant(variant);
                car.setPrice(price);
                car.setStatus(status);

                // Update table
                int selectedRow = table.getSelectedRow();
                tableModel.setValueAt(make, selectedRow, 1);
                tableModel.setValueAt(model, selectedRow, 2);
                tableModel.setValueAt(variant, selectedRow, 3);
                tableModel.setValueAt(price, selectedRow, 4);
                tableModel.setValueAt(status, selectedRow, 5);

                // Close edit form dialog
                editFormDialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close edit form dialog
                editFormDialog.dispose();
            }
        });

        editFormDialog.add(makeLabel);
        editFormDialog.add(makeTextField);
        editFormDialog.add(modelLabel);
        editFormDialog.add(modelTextField);
        editFormDialog.add(variantLabel);
        editFormDialog.add(variantTextField);
        editFormDialog.add(priceLabel);
        editFormDialog.add(priceTextField);
        editFormDialog.add(statusLabel);
        editFormDialog.add(statusCheckBox);
        editFormDialog.add(updateButton);
        editFormDialog.add(cancelButton);

        editFormDialog.pack();
        editFormDialog.setLocationRelativeTo(this);
        editFormDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CarDealerManagementSystem data = new CarDealerManagementSystem();
                CarRecordGUI carRecordGUI = new CarRecordGUI(data);

                JFrame frame = new JFrame("Car Records");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(carRecordGUI);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
