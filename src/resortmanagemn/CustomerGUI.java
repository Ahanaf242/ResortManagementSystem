package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerGUI extends JFrame {
    private JTextField nameField;
    private JTextField nidField;
    private JTextField phoneField;
    private JTextArea displayArea;
    private CustomerManager customerManager;

    public CustomerGUI() {
        customerManager = ApplicationData.getCustomerManager();

        setTitle("Customer Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        inputPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("National ID:"));
        nidField = new JTextField();
        inputPanel.add(nidField);
        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Customer");
        JButton searchButton = new JButton("Search Customer");
        JButton showButton = new JButton("Show All");
        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCustomers();
            }
        });
    }

    private void addCustomer() {
        String nid = nidField.getText().trim();
        Customer oldCustomer = customerManager.findByNID(nid);
        Customer customer = customerManager.addCustomer(
                nameField.getText(),
                nid,
                phoneField.getText()
        );

        if (customer == null) {
            JOptionPane.showMessageDialog(this,
                    "Enter a name, NID and a valid 11-digit phone number.");
        } else if (oldCustomer != null) {
            JOptionPane.showMessageDialog(this,
                    "A customer with this National ID already exists.");
            displayArea.setText(oldCustomer.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Customer Added Successfully");
            displayArea.setText(customer.toString());
            nameField.setText("");
            nidField.setText("");
            phoneField.setText("");
        }
    }

    private void searchCustomer() {
        String nid = JOptionPane.showInputDialog(this, "Enter National ID");
        if (nid == null) {
            return;
        }

        Customer customer = customerManager.findByNID(nid);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Customer Not Found");
        } else {
            displayArea.setText(customer.toString());
        }
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

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomerGUI().setVisible(true);
            }
        });
    }
}
