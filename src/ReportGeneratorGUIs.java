import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReportGeneratorGUIs extends JPanel {
    private JComboBox<String> classDropdown;
    private JButton generateButton;
    private JTextArea reportTextArea;

    private CarDealerManagementSystem data;

    public ReportGeneratorGUIs(CarDealerManagementSystem data) {
        this.data = data;

        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel classLabel = new JLabel("Select Class:");
        classDropdown = new JComboBox<>();
        classDropdown.addItem("Car");
        classDropdown.addItem("Employee");
        classDropdown.addItem("Customer");
        classDropdown.addItem("Inspection");

        generateButton = new JButton("Generate Report");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classDropdown.getSelectedItem();
                generateReport(selectedClass);
            }
        });

        topPanel.add(classLabel);
        topPanel.add(classDropdown);
        topPanel.add(generateButton);

        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(reportTextArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void generateReport(String className) {
        reportTextArea.setText("");

        try {
            Class<?> selectedClass = Class.forName(className);
            Field[] fields = selectedClass.getDeclaredFields();

            // Get the list of objects for the selected class
            ArrayList<?> objects = getObjectList(selectedClass);

            if (objects.isEmpty()) {
                reportTextArea.setText("No objects found for the selected class.");
            } else {
                // Generate the report header
                StringBuilder report = new StringBuilder();
                report.append("Report for ").append(className).append(":\n\n");

                // Generate the table header
                StringBuilder tableHeader = new StringBuilder();
                for (Field field : fields) {
                    tableHeader.append(field.getName()).append("\t");
                }
                report.append(tableHeader.toString().trim()).append("\n");

                // Generate the table rows for each object
                for (Object object : objects) {
                    StringBuilder tableRow = new StringBuilder();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object value = field.get(object);
                        tableRow.append(value).append("\t");
                    }
                    report.append(tableRow.toString().trim()).append("\n");
                }

                reportTextArea.setText(report.toString());
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            reportTextArea.setText("Error generating report.");
        }
    }

    private ArrayList<?> getObjectList(Class<?> selectedClass) {
        ArrayList<?> objects = new ArrayList<>();
        if (selectedClass.equals(Car.class)) {
            objects = data.cars;
        } else if (selectedClass.equals(Employee.class)) {
            objects = data.employees;
        } else if (selectedClass.equals(Customer.class)) {
            objects = data.customers;
        } else if (selectedClass.equals(Inspection.class)) {
            objects = data.inspections;
        }
        return objects;
    }

}
