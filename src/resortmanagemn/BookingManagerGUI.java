package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookingManagerGUI extends JFrame {
    private JTextField roomField;
    private JTextField phoneField;
    private JTextField daysField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextArea displayArea;
    private BookingManager bookingManager;

    public BookingManagerGUI() {
        this(ApplicationData.getBookingManager());
    }

    public BookingManagerGUI(BookingManager bookingManager) {
        this.bookingManager = bookingManager;

        setTitle("Booking Manager");
        setSize(900, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Booking Management", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 12, 10));
        roomField = addInput(inputPanel, "Room Number:");
        phoneField = addInput(inputPanel, "Customer Phone:");
        daysField = addInput(inputPanel, "New Days:");
        checkInField = addInput(inputPanel, "New Check In (yyyy-MM-dd):");
        checkOutField = addInput(inputPanel, "New Check Out (yyyy-MM-dd):");
        centerPanel.add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        centerPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        JButton createButton = new JButton("Create Booking");
        JButton showButton = new JButton("Show All");
        JButton searchRoomButton = new JButton("Search By Room");
        JButton searchPhoneButton = new JButton("Search By Phone");
        JButton updateButton = new JButton("Update Stay");
        JButton deleteButton = new JButton("Delete Booking");

        buttonPanel.add(createButton);
        buttonPanel.add(showButton);
        buttonPanel.add(searchRoomButton);
        buttonPanel.add(searchPhoneButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookingGUI(bookingManager).setVisible(true);
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllBookings();
            }
        });

        searchRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByRoom();
            }
        });

        searchPhoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByPhone();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBooking();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBooking();
            }
        });
    }

    private JTextField addInput(JPanel panel, String labelText) {
        panel.add(new JLabel(labelText));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void showAllBookings() {
        displayArea.setText("BOOKING LIST\n\n");
        if (bookingManager.getBookingList().isEmpty()) {
            displayArea.append("No bookings found.");
            return;
        }

        for (Booking booking : bookingManager.getBookingList()) {
            displayArea.append(booking.toString());
            displayArea.append("\n----------------------------------------\n");
        }
    }

    private void searchByRoom() {
        try {
            int roomNumber = Integer.parseInt(roomField.getText().trim());
            Booking booking = bookingManager.findBookingByRoom(roomNumber);
            showSearchResult(booking);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid room number.");
        }
    }

    private void searchByPhone() {
        if (phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a customer phone number.");
            return;
        }

        Booking booking = bookingManager.findBookingByCustomerPhone(
                phoneField.getText()
        );
        showSearchResult(booking);
    }

    private void showSearchResult(Booking booking) {
        if (booking == null) {
            JOptionPane.showMessageDialog(this, "Booking Not Found");
        } else {
            displayArea.setText(booking.toString());
            roomField.setText(String.valueOf(booking.getRoom().getRoomNumber()));
            daysField.setText(String.valueOf(booking.getDays()));
            checkInField.setText(booking.getCheckInDate());
            checkOutField.setText(booking.getCheckOutDate());
        }
    }

    private void updateBooking() {
        try {
            int roomNumber = Integer.parseInt(roomField.getText().trim());
            int days = Integer.parseInt(daysField.getText().trim());

            if (bookingManager.updateBooking(
                    roomNumber,
                    days,
                    checkInField.getText(),
                    checkOutField.getText())) {
                JOptionPane.showMessageDialog(this, "Booking Updated Successfully");
                showSearchResult(bookingManager.findBookingByRoom(roomNumber));
            } else {
                JOptionPane.showMessageDialog(this, "Booking Not Found");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Room number and days must be valid numbers.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void deleteBooking() {
        try {
            int roomNumber = Integer.parseInt(roomField.getText().trim());
            Booking booking = bookingManager.findBookingByRoom(roomNumber);

            if (booking == null) {
                JOptionPane.showMessageDialog(this, "Booking Not Found");
                return;
            }

            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Delete booking for room " + roomNumber + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION
                    && bookingManager.removeBookingByRoom(roomNumber)) {
                JOptionPane.showMessageDialog(this, "Booking Deleted Successfully");
                showAllBookings();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid room number.");
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookingManagerGUI().setVisible(true);
            }
        });
    }
}
