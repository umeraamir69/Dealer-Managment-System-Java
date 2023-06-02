import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InspectionTablePanel extends JPanel {
    private JTextField searchField;
    private JTable inspectionTable;
    private DefaultTableModel tableModel;

    private ArrayList<Inspection> inspections;

    public InspectionTablePanel(CarDealerManagementSystem data) {
        this.inspections = data.inspections;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#E6EEF3"));

        JPanel topPanel = createTopPanel();
        JScrollPane tableScrollPane = createTableScrollPane();

        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#E6EEF3"));

        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.decode("#E6EEF3"));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.decode("#E6EEF3"));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        topPanel.add(searchPanel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        // Add ActionListener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                searchInspections(searchTerm);
            }
        });

        // Add ActionListener to the edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = inspectionTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(topPanel, "Please select a row to edit.", "Edit Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get the selected inspection
                    Inspection selectedInspection = inspections.get(selectedRow);

                    // Create a dialog for editing
                    JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(topPanel), "Edit Inspection", true);
                    editDialog.setLayout(new BorderLayout());
                    editDialog.setSize(400, 300);
                    editDialog.setLocationRelativeTo(topPanel);

                    JPanel editPanel = new JPanel(new GridLayout(9, 2, 10, 10));
                    editPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                    // Create labels and text fields for each field in the inspection
                    JLabel inspectionIDLabel = new JLabel("Inspection ID:");
                    JTextField inspectionIDField = new JTextField(selectedInspection.getInspectionID());
                    JLabel registrationNumberLabel = new JLabel("Registration Number:");
                    JTextField registrationNumberField = new JTextField(selectedInspection.getRegistrationNumber());
                    // Repeat the above pattern for other fields

                    // Add labels and fields to the edit panel
                    editPanel.add(inspectionIDLabel);
                    editPanel.add(inspectionIDField);
                    editPanel.add(registrationNumberLabel);
                    editPanel.add(registrationNumberField);
                    // Repeat the above pattern for other fields

                    // Create a submit button
                    JButton submitButton = new JButton("Submit");
                    submitButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Get the updated values from the text fields
                            String updatedInspectionID = inspectionIDField.getText();
                            String updatedRegistrationNumber = registrationNumberField.getText();
                            // Repeat the above pattern for other fields

                            // Update the selected inspection
                            selectedInspection.setInspectionID(updatedInspectionID);
                            selectedInspection.setRegistrationNumber(updatedRegistrationNumber);
                            // Repeat the above pattern for other fields

                            // Update the table model
                            tableModel.setValueAt(updatedInspectionID, selectedRow, 0);
                            tableModel.setValueAt(updatedRegistrationNumber, selectedRow, 1);
                            // Repeat the above pattern for other fields

                            JOptionPane.showMessageDialog(topPanel, "Record updated successfully.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                            editDialog.dispose();
                        }
                    });

                    editDialog.add(editPanel, BorderLayout.CENTER);
                    editDialog.add(submitButton, BorderLayout.SOUTH);
                    editDialog.setVisible(true);
                }
            }
        });

        return topPanel;
    }


    private void searchInspections(String searchTerm) {
        DefaultTableModel model = (DefaultTableModel) inspectionTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        inspectionTable.setRowSorter(sorter);

        if (searchTerm.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
        }
    }


    private JScrollPane createTableScrollPane() {
        String[] columnNames = {
                "Inspection ID",
                "Registration Number",
                "Inspection Date",
                "Remarks",
                "Outer Condition",
                "Inner Condition",
                "Accident",
                "Owner Status",
                "Documents Status"
        };

        Object[][] data = new Object[inspections.size()][9];

        for (int i = 0; i < inspections.size(); i++) {
            Inspection inspection = inspections.get(i);
            data[i] = new Object[]{
                    inspection.getInspectionID(),
                    inspection.getRegistrationNumber(),
                    inspection.getInspectionDate(),
                    inspection.getRemarks(),
                    inspection.getOuterCondition(),
                    inspection.getInnerCondition(),
                    inspection.getAccident(),
                    inspection.getOwnerStatus(),
                    inspection.getDocumentsStatus()
            };
        }

        tableModel = new DefaultTableModel(data, columnNames);
        inspectionTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(inspectionTable);

        return tableScrollPane;
    }
}
