package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboardGUI extends JFrame {

    public AdminDashboardGUI() {
        setTitle("Admin Dashboard");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JLabel title = new JLabel("Resort Admin Dashboard", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        JButton createBookingButton = new JButton("Create Booking");
        JButton manageBookingButton = new JButton("Manage Bookings");
        JButton customerButton = new JButton("Customer Management");
        JButton roomButton = new JButton("Room Management");
        JButton paymentButton = new JButton("Payment System");
        JButton serviceButton = new JButton("Resort Service");
        JButton logoutButton = new JButton("Logout");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(createBookingButton);
        buttonPanel.add(manageBookingButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(roomButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(serviceButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        createBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookingGUI(ApplicationData.getBookingManager()).setVisible(true);
            }
        });

        manageBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookingManagerGUI(ApplicationData.getBookingManager()).setVisible(true);
            }
        });

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CustomerManagerGUI().setVisible(true);
            }
        });

        roomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RoomGUI().setVisible(true);
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PaymentsystemGUI().setVisible(true);
            }
        });

        serviceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ResortserviceGUI().setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminGUI().setVisible(true);
                dispose();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
