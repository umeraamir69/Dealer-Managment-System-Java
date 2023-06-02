import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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

        return topPanel;
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
