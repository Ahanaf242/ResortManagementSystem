package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;

    public AdminGUI() {
        setTitle("Admin Login Panel");
        setSize(480, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JLabel title = new JLabel("Admin Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 12, 15));
        inputPanel.add(new JLabel("Admin ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                passwordField.setText("");
            }
        });

        getRootPane().setDefaultButton(loginButton);
    }

    private void login() {
        String id = idField.getText().trim();
        String password = String.valueOf(passwordField.getPassword());
        Admin admin = new Admin("Ahanaf", "584");

        if (admin.login(id, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            new AdminDashboardGUI().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Wrong ID or Password");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminGUI().setVisible(true);
            }
        });
    }
}
