package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookingGUI extends JFrame {
    private JTextField nameField;
    private JTextField nidField;
    private JTextField phoneField;
    private JTextField roomField;
    private JTextField priceField;
    private JTextField daysField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JComboBox<String> typeBox;
    private JTextArea displayArea;
    private BookingManager bookingManager;

    public BookingGUI() {
        this(ApplicationData.getBookingManager());
    }

    public BookingGUI(BookingManager bookingManager) {
        this.bookingManager = bookingManager;

        setTitle("Create Booking");
        setSize(900, 680);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("New Resort Booking", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 12, 10));

        nameField = addInput(inputPanel, "Customer Name:");
        nidField = addInput(inputPanel, "National ID:");
        phoneField = addInput(inputPanel, "Phone (11 digits):");
        roomField = addInput(inputPanel, "Room Number:");

        inputPanel.add(new JLabel("Room Type:"));
        typeBox = new JComboBox<>(new String[]{
            "DELUXE", "SUPER_DELUXE", "KING_SIZE"
        });
        inputPanel.add(typeBox);

        priceField = addInput(inputPanel, "Room Price Per Day:");
        daysField = addInput(inputPanel, "Days:");
        checkInField = addInput(inputPanel, "Check In (yyyy-MM-dd):");
        checkOutField = addInput(inputPanel, "Check Out (yyyy-MM-dd):");

        centerPanel.add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        centerPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton addButton = new JButton("Add Booking");
        JButton showButton = new JButton("Show All");
        JButton searchButton = new JButton("Search");
        JButton cancelButton = new JButton("Cancel Booking");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBooking();
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllBookings();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBooking();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelBooking();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private JTextField addInput(JPanel panel, String labelText) {
        panel.add(new JLabel(labelText));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    private void addBooking() {
        try {
            validateCustomerInput();

            int roomNumber = Integer.parseInt(roomField.getText().trim());
            int roomPrice = Integer.parseInt(priceField.getText().trim());
            int days = Integer.parseInt(daysField.getText().trim());

            if (bookingManager.isRoomBooked(roomNumber)) {
                throw new IllegalStateException("This room is already booked.");
            }

            Customer customer = ApplicationData.getCustomerManager().addCustomer(
                    nameField.getText().trim(),
                    nidField.getText().trim(),
                    phoneField.getText().trim()
            );

            if (customer == null) {
                throw new IllegalArgumentException("Customer information is invalid.");
            }

            Room room = ApplicationData.findRoom(roomNumber);

            if (room == null) {
                room = new Room(
                        roomNumber,
                        RoomType.valueOf(typeBox.getSelectedItem().toString()),
                        roomPrice
                );
                ApplicationData.addRoom(room);
            } else {
                if (!room.isAvailable()) {
                    throw new IllegalStateException("This room is not available.");
                }
                if (room.getPrice() != roomPrice) {
                    throw new IllegalArgumentException(
                            "Entered price does not match the saved room price: "
                            + room.getPrice()
                    );
                }
            }

            Booking booking = new Booking(
                    customer,
                    room,
                    days,
                    checkInField.getText(),
                    checkOutField.getText()
            );

            if (bookingManager.addBooking(booking)) {
                JOptionPane.showMessageDialog(this, "Booking Added Successfully");
                displayArea.setText(booking.toString());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Booking could not be added.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Room number, price and days must be valid numbers.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void validateCustomerInput() {
        if (nameField.getText().trim().isEmpty()
                || nidField.getText().trim().isEmpty()
                || phoneField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name, NID and phone are required.");
        }

        String phone = phoneField.getText().trim();
        if (phone.length() != 11) {
            throw new IllegalArgumentException("Phone number must contain 11 digits.");
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                throw new IllegalArgumentException("Phone number must contain digits only.");
            }
        }
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

    private void searchBooking() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Room Number");
            if (input == null) {
                return;
            }

            Booking booking = bookingManager.findBookingByRoom(
                    Integer.parseInt(input.trim())
            );

            if (booking == null) {
                JOptionPane.showMessageDialog(this, "Booking Not Found");
            } else {
                displayArea.setText(booking.toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid room number.");
        }
    }

    private void cancelBooking() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Room Number");
            if (input == null) {
                return;
            }

            int roomNumber = Integer.parseInt(input.trim());
            Booking booking = bookingManager.findBookingByRoom(roomNumber);

            if (booking == null) {
                JOptionPane.showMessageDialog(this, "Booking Not Found");
                return;
            }

            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Cancel booking for room " + roomNumber + "?",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION
            );

            if (answer == JOptionPane.YES_OPTION
                    && bookingManager.removeBookingByRoom(roomNumber)) {
                JOptionPane.showMessageDialog(this, "Booking Cancelled");
                showAllBookings();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid room number.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        nidField.setText("");
        phoneField.setText("");
        roomField.setText("");
        priceField.setText("");
        daysField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
        typeBox.setSelectedIndex(0);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookingGUI().setVisible(true);
            }
        });
    }
}
