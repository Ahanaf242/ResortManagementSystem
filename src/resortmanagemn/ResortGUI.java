package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResortGUI extends JFrame {

    private JButton adminButton;
    private JButton customerButton;
    private JButton roomButton;
    private JButton bookingButton;
    private JButton paymentButton;
    private JButton serviceButton;

    public ResortGUI() {

        setTitle("Resort Management System");

        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "Resort Management System",
                JLabel.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(6, 1, 10, 10)
        );

        adminButton = new JButton(
                "Admin Login"
        );

        customerButton = new JButton(
                "Customer Management"
        );

        roomButton = new JButton(
                "Room Management"
        );

        bookingButton = new JButton(
                "Booking Management"
        );

        paymentButton = new JButton(
                "Payment System"
        );

        serviceButton = new JButton(
                "Resort Service"
        );

        buttonPanel.add(adminButton);

        buttonPanel.add(customerButton);

        buttonPanel.add(roomButton);

        buttonPanel.add(bookingButton);

        buttonPanel.add(paymentButton);

        buttonPanel.add(serviceButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        adminButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                AdminGUI gui = new AdminGUI();

                gui.setVisible(true);

            }

        });

        customerButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                CustomerGUI gui = new CustomerGUI();

                gui.setVisible(true);

            }

        });

        roomButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                RoomGUI gui = new RoomGUI();

                gui.setVisible(true);

            }

        });

        bookingButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                BookingManagerGUI gui = new BookingManagerGUI();

                gui.setVisible(true);

            }

        });

        paymentButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                PaymentsystemGUI gui = new PaymentsystemGUI();

                gui.setVisible(true);

            }

        });

        serviceButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ResortserviceGUI gui = new ResortserviceGUI();

                gui.setVisible(true);

            }

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ResortGUI().setVisible(true);
            }
        });
    }
}
