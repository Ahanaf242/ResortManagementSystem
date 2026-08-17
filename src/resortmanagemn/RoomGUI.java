package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RoomGUI extends JFrame {
    private JTextField roomNumberField;
    private JTextField priceField;
    private JComboBox<String> typeBox;
    private JTextArea displayArea;
    private List<Room> roomList;

    public RoomGUI() {
        setTitle("Room Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        roomList = ApplicationData.getRoomList();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        inputPanel.add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        inputPanel.add(roomNumberField);
        inputPanel.add(new JLabel("Room Type:"));
        typeBox = new JComboBox<>(new String[]{
            "DELUXE", "SUPER_DELUXE", "KING_SIZE"
        });
        inputPanel.add(typeBox);
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Room");
        JButton showButton = new JButton("Show Rooms");
        JButton bookButton = new JButton("Book Room");
        JButton freeButton = new JButton("Free Room");
        JButton maintenanceButton = new JButton("Maintenance");
        JButton endMaintenanceButton = new JButton("End Maintenance");
        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(freeButton);
        buttonPanel.add(maintenanceButton);
        buttonPanel.add(endMaintenanceButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRooms();
            }
        });

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeRoomStatus("book");
            }
        });

        freeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeRoomStatus("free");
            }
        });

        maintenanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeRoomStatus("maintenance");
            }
        });

        endMaintenanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeRoomStatus("available");
            }
        });
    }

    private void addRoom() {
        try {
            int roomNumber = Integer.parseInt(roomNumberField.getText().trim());
            int price = Integer.parseInt(priceField.getText().trim());
            Room room = new Room(
                    roomNumber,
                    RoomType.valueOf(typeBox.getSelectedItem().toString()),
                    price
            );

            if (ApplicationData.addRoom(room)) {
                JOptionPane.showMessageDialog(this, "Room Added");
                roomNumberField.setText("");
                priceField.setText("");
                showRooms();
            } else {
                JOptionPane.showMessageDialog(this, "Room number already exists.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Room number and price must be valid numbers.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void showRooms() {
        displayArea.setText("ROOM LIST\n\n");
        if (roomList.isEmpty()) {
            displayArea.append("No rooms found.");
            return;
        }
        for (Room room : roomList) {
            displayArea.append(room.toString() + "\n");
        }
    }

    private void changeRoomStatus(String action) {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Room Number");
            if (input == null) {
                return;
            }

            Room room = ApplicationData.findRoom(Integer.parseInt(input.trim()));
            if (room == null) {
                JOptionPane.showMessageDialog(this, "Room Not Found");
                return;
            }

            boolean changed;
            String successMessage;

            if (action.equals("book")) {
                changed = room.book();
                successMessage = "Room Booked";
            } else if (action.equals("free")) {
                if (ApplicationData.getBookingManager().isRoomBooked(room.getRoomNumber())) {
                    JOptionPane.showMessageDialog(this,
                            "Cancel the active booking before freeing this room.");
                    return;
                }
                changed = room.free();
                successMessage = "Room Free";
            } else if (action.equals("maintenance")) {
                changed = room.setMaintenance();
                successMessage = "Maintenance Set";
            } else {
                changed = room.endMaintenance();
                successMessage = "Maintenance Ended";
            }

            JOptionPane.showMessageDialog(this,
                    changed ? successMessage : "Room status cannot be changed.");
            showRooms();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid room number.");
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RoomGUI().setVisible(true);
            }
        });
    }
}
