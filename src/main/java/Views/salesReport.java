package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

class MonthlySalesReport extends JFrame {

    private Map<String, MonthlyData> monthlyDataMap;
    private DefaultTableModel tableModel;
    private JTextArea reportTextArea;
    private JComboBox<String> monthComboBox;
    private JTable salesTable;

    public MonthlySalesReport() {
        monthlyDataMap = new HashMap<>();

        setTitle("Monthly Sales Report");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        monthComboBox = new JComboBox<>(getMonthNames());
        JButton generateButton = new JButton("Generate Report");

        tableModel = new DefaultTableModel();
        salesTable = new JTable(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(salesTable);
        reportTextArea = new JTextArea(5, 30);
        JScrollPane reportScrollPane = new JScrollPane(reportTextArea);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMonth = (String) monthComboBox.getSelectedItem();
                generateMonthlyReport(selectedMonth);
                String report = generateReportText(selectedMonth);
                reportTextArea.setText(report);
                displaySalesDetailsInTable(selectedMonth);
            }
        });

        panel.add(new JLabel("Select Month:"));
        panel.add(monthComboBox);
        panel.add(generateButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(reportScrollPane, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String[] getMonthNames() {
        return new DateFormatSymbols().getMonths();
    }

    private void generateMonthlyReport(String month) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/supplier?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String sql = "SELECT salesId, date, quantitySold, totalAmount, expenses FROM monthlyReport WHERE MONTH(date) = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, getMonthIndex(month));
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        monthlyDataMap.clear();
                        while (resultSet.next()) {
                            String salesId = resultSet.getString("salesId");
                            Date date = resultSet.getDate("date");
                            int quantitySold = resultSet.getInt("quantitySold");
                            double totalAmount = resultSet.getDouble("totalAmount");
                            double expenses = resultSet.getDouble("expenses");

                            MonthlyData monthlyData = monthlyDataMap.getOrDefault(salesId, new MonthlyData());
                            monthlyData.incrementCompletedOrders();
                            monthlyData.incrementTotalIncome(totalAmount);
                            monthlyData.incrementTotalExpenses(expenses);
                            monthlyDataMap.put(salesId, monthlyData);


                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private String generateReportText(String month) {
        int totalCompletedOrders = calculateTotalCompletedOrders();
        double totalIncome = calculateTotalIncome();
        double totalExpenses = calculateTotalExpenses();
        double totalProfit = totalIncome - totalExpenses;

        return "Sales Report for " + month + ":\n" +
                "Total Completed Orders: " + totalCompletedOrders + "\n" +
                "Total Income: $" + totalIncome + "\n" +
                "Total Expenses: $" + totalExpenses + "\n" +
                "Total Profit: $" + totalProfit;
    }

    private int calculateTotalCompletedOrders() {
        return monthlyDataMap.values().stream().mapToInt(MonthlyData::getCompletedOrders).sum();
    }

    private double calculateTotalIncome() {
        return monthlyDataMap.values().stream().mapToDouble(MonthlyData::getTotalIncome).sum();
    }

    private double calculateTotalExpenses() {
        return monthlyDataMap.values().stream().mapToDouble(MonthlyData::getTotalExpenses).sum();
    }

    private void displaySalesDetailsInTable(String month) {
        tableModel.setRowCount(0);

        for (Map.Entry<String, MonthlyData> entry : monthlyDataMap.entrySet()) {
            Vector<Object> row = new Vector<>();
            row.add(entry.getKey());
            row.add(entry.getValue().getCompletedOrders());
            row.add(entry.getValue().getTotalIncome());
            row.add(entry.getValue().getTotalExpenses());
            tableModel.addRow(row);
        }
    }

    private int getMonthIndex(String month) {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(month)) {
                return i + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MonthlySalesReport());
    }

    private static class MonthlyData {
        private int completedOrders;
        private double totalIncome;
        private double totalExpenses;

        public int getCompletedOrders() {
            return completedOrders;
        }

        public void incrementCompletedOrders() {
            completedOrders++;
        }

        public double getTotalIncome() {
            return totalIncome;
        }

        public void incrementTotalIncome(double amount) {
            totalIncome += amount;
        }

        public double getTotalExpenses() {
            return totalExpenses;
        }

        public void incrementTotalExpenses(double amount) {
            totalExpenses += amount;
        }
    }
}
