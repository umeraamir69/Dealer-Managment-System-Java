import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewInspectionPanel extends JPanel {
    private JComboBox<String> inspectionComboBox;
    private JTextArea detailsTextArea;

    private ArrayList<Inspection> data;

    public ViewInspectionPanel(CarDealerManagementSystem data) {
        this.data = data.inspections;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#E6EEF3"));

        JPanel topPanel = createTopPanel();
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.decode("#E6EEF3"));

        JLabel inspectionLabel = new JLabel("Select Inspection:");
        inspectionComboBox = new JComboBox<>(getInspectionOptions());

        inspectionComboBox.addActionListener(e -> {
            String selectedInspection = (String) inspectionComboBox.getSelectedItem();
            displayInspectionDetails(selectedInspection);
        });

        topPanel.add(inspectionLabel);
        topPanel.add(inspectionComboBox);

        return topPanel;
    }

    private String[] getInspectionOptions() {
        String[] inspectionOptions = new String[data.size()];

        for (int i = 0; i < data.size(); i++) {
            Inspection inspection = data.get(i);
            inspectionOptions[i] = inspection.getInspectionID() + " - " + inspection.getRegistrationNumber();
        }

        return inspectionOptions;
    }

    private void displayInspectionDetails(String selectedInspection) {
        if (selectedInspection != null) {
            String[] parts = selectedInspection.split(" - ");
            String inspectionID = parts[0];
            String registrationNumber = parts[1];

            Inspection inspection = findInspection(inspectionID, registrationNumber);
            if (inspection != null) {
                StringBuilder detailsBuilder = new StringBuilder();
                detailsBuilder.append("Inspection ID: ").append(inspection.getInspectionID()).append("\n");
                detailsBuilder.append("Registration Number: ").append(inspection.getRegistrationNumber()).append("\n");
                detailsBuilder.append("Inspection Date: ").append(inspection.getInspectionDate()).append("\n");
                detailsBuilder.append("Remarks: ").append(inspection.getRemarks()).append("\n");
                detailsBuilder.append("Outer Condition: ").append(inspection.getOuterCondition()).append("\n");
                detailsBuilder.append("Inner Condition: ").append(inspection.getInnerCondition()).append("\n");
                detailsBuilder.append("Accident: ").append(inspection.getAccident()).append("\n");
                detailsBuilder.append("Owner Status: ").append(inspection.getOwnerStatus()).append("\n");
                detailsBuilder.append("Documents Status: ").append(inspection.getDocumentsStatus()).append("\n");

                detailsTextArea.setText(detailsBuilder.toString());
            }
        } else {
            detailsTextArea.setText("");
        }
    }

    private Inspection findInspection(String inspectionID, String registrationNumber) {
        for (Inspection inspection : data) {
            if (inspection.getInspectionID().equals(inspectionID) && inspection.getRegistrationNumber().equals(registrationNumber)) {
                return inspection;
            }
        }
        return null;
    }
}
