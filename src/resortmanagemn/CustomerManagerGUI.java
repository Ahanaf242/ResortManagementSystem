package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerManagerGUI extends JFrame {
    private JTextField phoneField;
    private JTextArea displayArea;
    private CustomerManager customerManager;

    public CustomerManagerGUI() {
        customerManager = ApplicationData.getCustomerManager();

        setTitle("Customer Manager");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        inputPanel.add(new JLabel("Customer Phone:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Customer");
        JButton showButton = new JButton("Show Customers");
        JButton searchButton = new JButton("Search By Phone");
        JButton deleteButton = new JButton("Delete Customer");
        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CustomerGUI().setVisible(true);
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCustomers();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Customer customer = customerManager.findByPhone(phoneField.getText());
                if (customer == null) {
                    JOptionPane.showMessageDialog(CustomerManagerGUI.this,
                            "Customer Not Found");
                } else {
                    displayArea.setText(customer.toString());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });
    }

    private void showCustomers() {
        displayArea.setText("CUSTOMER LIST\n\n");
        if (customerManager.getCustomerList().isEmpty()) {
            displayArea.append("No customers found.");
            return;
        }
        for (Customer customer : customerManager.getCustomerList()) {
            displayArea.append(customer.toString() + "\n");
        }
    }

    private void deleteCustomer() {
        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a customer phone number.");
            return;
        }

        if (customerManager.deleteCustomer(phone)) {
            JOptionPane.showMessageDialog(this, "Customer Deleted Successfully");
            phoneField.setText("");
            showCustomers();
        } else {
            JOptionPane.showMessageDialog(this, "Customer Not Found");
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomerManagerGUI().setVisible(true);
            }
        });
    }
}
