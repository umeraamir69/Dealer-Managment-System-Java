import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class InspectionForm extends JPanel {
    private JTextField inspectionIDField;
    private JTextField registrationNumberField;
    private JSlider outerConditionSlider;
    private JSlider innerConditionSlider;
    private JCheckBox accidentCheckBox;
    private JComboBox<String> ownerStatusComboBox;
    private JComboBox<String> documentsStatusComboBox;
    private JTextArea remarksTextArea;
    private CarDealerManagementSystem data;
    private JLabel outerConditionValueLabel;
    private JLabel innerConditionValueLabel;

    public InspectionForm(CarDealerManagementSystem data) {
        this.data = data;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#E6EEF3"));

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        formPanel.setBackground(Color.decode("#E6EEF3"));

        JLabel inspectionIDLabel = new JLabel("Inspection ID:");
        inspectionIDField = new JTextField();

        JLabel registrationNumberLabel = new JLabel("Registration Number:");
        registrationNumberField = new JTextField();

        JLabel inspectionDateLabel = new JLabel("Inspection Date:");
        JLabel inspectionDateValue = new JLabel(LocalDate.now().toString());

        JLabel remarksLabel = new JLabel("Remarks:");
        remarksTextArea = new JTextArea();
        JScrollPane remarksScrollPane = new JScrollPane(remarksTextArea);

        JLabel outerConditionLabel = new JLabel("Outer Condition:");
        outerConditionSlider = new JSlider(0, 100);
        outerConditionValueLabel = new JLabel("0");

        JLabel innerConditionLabel = new JLabel("Inner Condition:");
        innerConditionSlider = new JSlider(0, 100);
        innerConditionValueLabel = new JLabel("0");

        JLabel accidentLabel = new JLabel("Accident:");
        accidentCheckBox = new JCheckBox();

        JLabel ownerStatusLabel = new JLabel("Owner Status:");
        String[] ownerStatusOptions = {"First", "Second", "Third", "Third+"};
        ownerStatusComboBox = new JComboBox<>(ownerStatusOptions);

        JLabel documentsStatusLabel = new JLabel("Documents Status:");
        String[] documentsStatusOptions = {"Original", "Duplicate", "Lost", "Damaged"};
        documentsStatusComboBox = new JComboBox<>(documentsStatusOptions);

        formPanel.add(inspectionIDLabel);
        formPanel.add(inspectionIDField);
        formPanel.add(registrationNumberLabel);
        formPanel.add(registrationNumberField);
        formPanel.add(inspectionDateLabel);
        formPanel.add(inspectionDateValue);
        formPanel.add(remarksLabel);
        formPanel.add(remarksScrollPane);
        formPanel.add(outerConditionLabel);
        formPanel.add(outerConditionSlider);
        formPanel.add(new JLabel());
        formPanel.add(outerConditionValueLabel);
        formPanel.add(innerConditionLabel);
        formPanel.add(innerConditionSlider);
        formPanel.add(new JLabel());
        formPanel.add(innerConditionValueLabel);
        formPanel.add(accidentLabel);
        formPanel.add(accidentCheckBox);
        formPanel.add(ownerStatusLabel);
        formPanel.add(ownerStatusComboBox);
        formPanel.add(documentsStatusLabel);
        formPanel.add(documentsStatusComboBox);

        // Slider value change listeners
        outerConditionSlider.addChangeListener(e -> {
            int value = outerConditionSlider.getValue();
            outerConditionValueLabel.setText(Integer.toString(value));
        });

        innerConditionSlider.addChangeListener(e -> {
            int value = innerConditionSlider.getValue();
            innerConditionValueLabel.setText(Integer.toString(value));
        });

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#E6EEF3"));

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.decode("#27374D"));
        submitButton.setForeground(Color.decode("#DDE6ED"));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    submitForm();
                } else {
                    JOptionPane.showMessageDialog(InspectionForm.this, "Please fill in all fields.", "Incomplete Form", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.decode("#27374D"));
        cancelButton.setForeground(Color.decode("#DDE6ED"));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disposeForm();
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private boolean validateFields() {
        return !inspectionIDField.getText().isEmpty()
                && !registrationNumberField.getText().isEmpty();
    }

    private void submitForm() {
        String inspectionID = inspectionIDField.getText();
        String registrationNumber = registrationNumberField.getText();
        LocalDate inspectionDate = LocalDate.now();
        String remarks = remarksTextArea.getText();
        float outerCondition = outerConditionSlider.getValue();
        float innerCondition = innerConditionSlider.getValue();
        boolean accident = accidentCheckBox.isSelected();
        String ownerStatus = (String) ownerStatusComboBox.getSelectedItem();
        String documentsStatus = (String) documentsStatusComboBox.getSelectedItem();

        data.addInspection(new Inspection(inspectionID ,registrationNumber ,inspectionDate ,remarks ,outerCondition,innerCondition,accident,ownerStatus,documentsStatus));
        JOptionPane.showMessageDialog(this, "Inspection recorded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        resetFields();
    }

    private void disposeForm() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    private void resetFields() {
        inspectionIDField.setText("");
        registrationNumberField.setText("");
        remarksTextArea.setText("");
        outerConditionSlider.setValue(0);
        innerConditionSlider.setValue(0);
        accidentCheckBox.setSelected(false);
        ownerStatusComboBox.setSelectedIndex(0);
        documentsStatusComboBox.setSelectedIndex(0);
    }

}

