import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class ReportGeneratorGUI extends JPanel {
    private JComboBox<String> saleDropdown;
    private JButton generateButton;

    private CarDealerManagementSystem data;

    public ReportGeneratorGUI(CarDealerManagementSystem data) {
        this.data = data;

        setSize(300, 150);
        setLayout(new FlowLayout());

        JLabel saleLabel = new JLabel("Select Sale:");
        saleDropdown = new JComboBox<>();
        for (Sale sale : data.sales) {
            saleDropdown.addItem(sale.getSalesID());
        }

        generateButton = new JButton("Generate Report");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSaleID = (String) saleDropdown.getSelectedItem();
                Sale selectedSale = findSaleByID(selectedSaleID);
                if (selectedSale != null) {
                    generateReport(selectedSale);
                }
            }
        });

        add(saleLabel);
        add(saleDropdown);
        add(generateButton);
    }

    private Sale findSaleByID(String saleID) {
        for (Sale sale : data.sales) {
            if (sale.getSalesID().equals(saleID)) {
                return sale;
            }
        }
        return null;
    }

    private void generateReport(Sale sale) {
        StringBuilder report = new StringBuilder();
        report.append("Sale ID: ").append(sale.getSalesID()).append("\n");

        // Car Details
        report.append("Car Make: ").append(sale.getCar().getMake()).append("\n");
        report.append("Car Model: ").append(sale.getCar().getModel()).append("\n");
        report.append("Car Registration Number: ").append(sale.getCar().getRegistrationNumber()).append("\n");

        // Customer Details
        report.append("Customer Name: ").append(sale.getCustomer().getName()).append("\n");
        report.append("Customer Contact Number: ").append(sale.getCustomer().getPhoneNumber()).append("\n");
        report.append("Customer Email: ").append(sale.getCustomer().getEmail()).append("\n");

        // Employee Details
        report.append("Employee Name: ").append(sale.getEmployee().getName()).append("\n");
        report.append("Employee Position: ").append(sale.getEmployee().getPosition()).append("\n");
        report.append("Employee Salary: ").append(sale.getEmployee().getSalary()).append("\n");

        JTextArea reportTextArea = new JTextArea(report.toString());
        reportTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JTextPane reportTextPane = new JTextPane();
        reportTextPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        reportTextPane.setText(report.toString());
        reportTextPane.setEditable(false);

        int option = JOptionPane.showOptionDialog(
                this,
                new JScrollPane(reportTextPane),
                "Report",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Print", "Cancel"},
                "Print"
        );

        if (option == 0) {
            printReport(reportTextPane);
        }
    }

    private void printReport(JTextPane textPane) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Report");

        Printable printable = textPane.getPrintable(null, null);
        job.setPrintable(printable);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
