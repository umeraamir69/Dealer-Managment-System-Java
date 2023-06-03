import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class SalesAnalyticsPanel extends JPanel {
    private ArrayList<Sale> sales;

    public SalesAnalyticsPanel(CarDealerManagementSystem data) {
        this.sales = data.sales;

        setLayout(new BorderLayout());

        // Create and add analytics components
        JPanel analyticsPanel = createAnalyticsPanel();
        add(analyticsPanel, BorderLayout.CENTER);
    }

    private JPanel createAnalyticsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Get current year and month
        YearMonth currentYearMonth = YearMonth.now();

        // Calculate sales for this month and last month
        int thisMonthSales = getSalesCountForMonth(currentYearMonth);
        YearMonth lastMonth = currentYearMonth.minusMonths(1);
        int lastMonthSales = getSalesCountForMonth(lastMonth);

        // Calculate percentage change
        double percentageChange = calculatePercentageChange(lastMonthSales, thisMonthSales);

        // Create labels and values for analytics
        JLabel thisMonthLabel = new JLabel("Sales This Month:");
        JLabel thisMonthValue = new JLabel(String.valueOf(thisMonthSales));
        JLabel lastMonthLabel = new JLabel("Sales Last Month:");
        JLabel lastMonthValue = new JLabel(String.valueOf(lastMonthSales));
        JLabel changeLabel = new JLabel("Percentage Change:");
        JLabel changeValue = new JLabel(String.format("%.2f%%", percentageChange));

        // Add labels and values to the panel
        panel.add(thisMonthLabel);
        panel.add(thisMonthValue);
        panel.add(lastMonthLabel);
        panel.add(lastMonthValue);
        panel.add(changeLabel);
        panel.add(changeValue);

        return panel;
    }

    private int getSalesCountForMonth(YearMonth yearMonth) {
        int count = 0;
        for (Sale sale : sales) {
            LocalDate sellingDate = sale.getSaleDate();
            YearMonth saleYearMonth = YearMonth.from(sellingDate);
            if (saleYearMonth.equals(yearMonth)) {
                count++;
            }
        }
        return count;
    }

    private double calculatePercentageChange(int previousValue, int currentValue) {
        if (previousValue == 0) {
            if (currentValue == 0) {
                return 0.0; // No change
            } else {
                return 100.0; // Infinite change (from 0 to non-zero)
            }
        } else {
            return ((double) (currentValue - previousValue) / previousValue) * 100;
        }
    }


}

