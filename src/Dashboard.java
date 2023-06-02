import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    private JPanel contentPane;
    private CarDealerManagementSystem data;

    public Dashboard(CarDealerManagementSystem data) {
        this.data = data;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
//        setSize(800 , 900);


        // Create top navigation bar
        JPanel topNavBar = createTopNavigationBar();
        // Create side nav bar
        JPanel sideNavBar = createSideNavBar();
        JPanel user = loginlbl();
        // Create main content pane
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(topNavBar, BorderLayout.NORTH);
        contentPane.add(sideNavBar, BorderLayout.WEST);
        contentPane.add(user, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private JPanel loginlbl() {
        JLabel loginLabel = new JLabel("Login as " + data.currentobj.getName());
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginPanel.setBackground(Color.decode("#27374D"));
        loginPanel.add(loginLabel);
        return loginPanel;
    }

    private JPanel createTopNavigationBar() {
        JPanel topNavBar = new JPanel(new BorderLayout());
        topNavBar.setBackground(Color.decode("#27374D"));

        JLabel systemLabel = new JLabel("Car Dealer Management System");
        systemLabel.setForeground(Color.WHITE);
        systemLabel.setFont(new Font("Arial", Font.BOLD, 20));
        systemLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.decode("#27374D"));
        menuBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add functionality to menu items (copy, cut, paste, exit)

        fileMenu.add(copyItem);
        fileMenu.add(cutItem);
        fileMenu.add(pasteItem);
        fileMenu.add(exitItem);

        JMenu accountMenu = new JMenu("Account");
        accountMenu.setForeground(Color.WHITE);
        JMenuItem changePasswordItem = new JMenuItem("Change Password");
        JMenuItem editDetailsItem = new JMenuItem("Edit Details");

        // Add functionality to menu items (change password, edit details)

        accountMenu.add(changePasswordItem);
        accountMenu.add(editDetailsItem);

        menuBar.add(fileMenu);
        menuBar.add(accountMenu);

        topNavBar.add(systemLabel, BorderLayout.EAST);
        topNavBar.add(menuBar, BorderLayout.WEST);

        return topNavBar;
    }


    private JPanel createSideNavBar() {
        JPanel sideNavBar = new JPanel();
        sideNavBar.setBackground(Color.decode("#526D82"));
        sideNavBar.setPreferredSize(new Dimension(200, getHeight()));
        sideNavBar.setLayout(new GridLayout(19, 1));

        // Car dropdown
        JButton carDropdown = createDropdownButton("Car", "/car_icon.png",
                new String[]{"Add Car", "View Car", "Edit & Delete Car"});
        sideNavBar.add(carDropdown);

        // Employee dropdown
        JButton employeeDropdown = createDropdownButton("Employee", "/employee_icon.png",
                new String[]{"Add Employee", "Edit Employee", "Delete Employee", "Find Employee", "Block Employee"});
        sideNavBar.add(employeeDropdown);

        // Customer dropdown
        JButton customerDropdown = createDropdownButton("Customer", "/customer_icon.png",
                new String[]{"Add Customer", "Find Customer", "Edit Customer", "Delete Customer", "Block Customer", "Get Customer History"});
        sideNavBar.add(customerDropdown);

        // Finance dropdown
        JButton financeDropdown = createDropdownButton("Finance", "/finance_icon.png",
                new String[]{"View Balance", "View Bank Balance", "Add Record"});
        sideNavBar.add(financeDropdown);

        // Sale dropdown
        JButton saleDropdown = createDropdownButton("Sale", "/sale_icon.png",
                new String[]{"Add Sale", "View Sale", "Search Sale", "Edit Sale"});
        sideNavBar.add(saleDropdown);

        // Inspection dropdown
        JButton inspectionDropdown = createDropdownButton("Inspection", "/inspection_icon.png",
                new String[]{"Add Inspection",  "View Inspection",
                        "Edit & Delete Inspection", "Find Inspection", });
        sideNavBar.add(inspectionDropdown);

        // Payroll dropdown
        JButton payrollDropdown = createDropdownButton("Payroll", "/payroll_icon.png",
                new String[]{"Give Pay", "Check History", "Search Pay"});
        sideNavBar.add(payrollDropdown);

        // Receipt dropdown
        JButton receiptDropdown = createDropdownButton("Receipt", "/receipt_icon.png",
                new String[]{"Generate Receipt", "Search Receipt"});
        sideNavBar.add(receiptDropdown);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.RED);
        logoutButton.setBorderPainted(false);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform logout action
                // Close the dashboard window
             setVisible(false);
               LoginForm lgfmr = new LoginForm(data);
               lgfmr.setVisible(true);

                // Add your logout code here
            }
        });
        sideNavBar.add(logoutButton);

        return sideNavBar;
    }

    private JButton createDropdownButton(String text, String iconPath, String[] menuItems) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode("#526D82"));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPopupMenu popupMenu = new JPopupMenu();
        for (String menuItem : menuItems) {
            JMenuItem item = new JMenuItem(menuItem);
            if (menuItem.equals("Add Car")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new CarForm(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("View Car")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new ViewCarPanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Edit & Delete Car")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new CarRecordGUI(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Add Employee")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new AddEmployeeGUI(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Edit Employee")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new EmployeeTableGUI(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Find Employee")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new EmployeeTableGUI(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Delete Employee")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new EmployeeTableDel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Block Employee")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new BlockEmployeePanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("Add Customer")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new CreateCustomerForm(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("Find Customer") ||menuItem.equals("Edit Customer") || menuItem.equals("Delete Customer") ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new CustomerTableGUI(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("Block Customer")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new BlockEmployeePanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("View Balance")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new FinancePanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }
            else if (menuItem.equals("Add Record")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new AddBalancePanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }            else if (menuItem.equals("Give Pay")) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new EmployeeSalaryPanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }
            else if (menuItem.equals("Search Pay") ||menuItem.equals("Check History")  ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new FindSalary(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            } else if (menuItem.equals("Add Sale")  ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new SalePanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("Search Sale") || menuItem.equals("Edit Sale")  ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new SalesPanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("View Sale") ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new SalesDetailsPanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("Add Inspection") ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new InspectionForm(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("View Inspection") ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new ViewInspectionPanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }else if (menuItem.equals("Edit & Delete Inspection") || menuItem.equals("Find Inspection")  ) {
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ResetPane();
                        contentPane.add(new InspectionTablePanel(data), BorderLayout.CENTER);
                        contentPane.revalidate();
                        contentPane.repaint();
                    }
                });
            }





            popupMenu.add(item);
        }
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(button, 0, button.getHeight());
            }
        });


        return button;
    }


    public void ResetPane() {
        contentPane.removeAll();


        JPanel topNavBar = createTopNavigationBar();
        // Create side nav bar
        JPanel sideNavBar = createSideNavBar();
        JPanel user = loginlbl();

        contentPane.add(topNavBar, BorderLayout.NORTH);
        contentPane.add(sideNavBar, BorderLayout.WEST);
        contentPane.add(user, BorderLayout.SOUTH);
    }

}