package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RoomGUI extends JFrame {

    private JTextField roomNumberField;
    private JTextField priceField;

    private JComboBox typeBox;

    private JButton addButton;
    private JButton showButton;
    private JButton bookButton;
    private JButton freeButton;
    private JButton maintenanceButton;
    private JButton endMaintenanceButton;

    private JTextArea displayArea;

    private ArrayList<Room> roomList;

    public RoomGUI() {

        setTitle("Room Management");

        setSize(800, 650);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        roomList = new ArrayList<>();

        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new GridLayout(4, 2, 20, 20));

        JLabel roomLabel = new JLabel("Room Number:");

        JLabel priceLabel = new JLabel("Price:");

        JLabel typeLabel = new JLabel("Room Type:");

        roomNumberField = new JTextField();

        priceField = new JTextField();

        String types[] = {
            "DELUXE",
            "SUPER_DELUXE",
            "KING_SIZE"
        };

        typeBox = new JComboBox(types);

        addButton = new JButton("Add Room");

        showButton = new JButton("Show Rooms");

        inputPanel.add(roomLabel);

        inputPanel.add(roomNumberField);

        inputPanel.add(typeLabel);

        inputPanel.add(typeBox);

        inputPanel.add(priceLabel);

        inputPanel.add(priceField);

        inputPanel.add(addButton);

        inputPanel.add(showButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();

        JScrollPane scroll = new JScrollPane(displayArea);

        add(scroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        bookButton = new JButton("Book Room");

        freeButton = new JButton("Free Room");

        maintenanceButton = new JButton("Maintenance");

        endMaintenanceButton = new JButton("End Maintenance");

        bookButton.setFont(new Font("Arial", Font.BOLD, 14));

        freeButton.setFont(new Font("Arial", Font.BOLD, 14));

        maintenanceButton.setFont(new Font("Arial", Font.BOLD, 14));

        endMaintenanceButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(bookButton);

        buttonPanel.add(freeButton);

        buttonPanel.add(maintenanceButton);

        buttonPanel.add(endMaintenanceButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int roomNumber
                        = Integer.parseInt(roomNumberField.getText());

                int price
                        = Integer.parseInt(priceField.getText());

                RoomType type
                        = RoomType.valueOf(
                                typeBox.getSelectedItem().toString()
                        );

                Room room
                        = new Room(roomNumber, type, price);

                roomList.add(room);

                JOptionPane.showMessageDialog(
                        null,
                        "Room Added"
                );

            }

        });

        showButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                displayArea.setText("");

                for (Room r : roomList) {

                    displayArea.append(
                            r.toString() + "\n"
                    );

                }

            }

        });

        bookButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String number
                        = JOptionPane.showInputDialog(
                                "Enter Room Number"
                        );

                for (Room r : roomList) {

                    if (r.getRoomNumber() == Integer.parseInt(number)) {

                        if (r.book()) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Room Booked"
                            );

                        } else {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Room Not Available"
                            );

                        }

                    }

                }

            }

        });

        freeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String number
                        = JOptionPane.showInputDialog(
                                "Enter Room Number"
                        );

                for (Room r : roomList) {

                    if (r.getRoomNumber() == Integer.parseInt(number)) {

                        r.free();

                        JOptionPane.showMessageDialog(
                                null,
                                "Room Free"
                        );

                    }

                }

            }

        });

        maintenanceButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String number
                        = JOptionPane.showInputDialog(
                                "Enter Room Number"
                        );

                for (Room r : roomList) {

                    if (r.getRoomNumber() == Integer.parseInt(number)) {

                        r.setMaintenance();

                        JOptionPane.showMessageDialog(
                                null,
                                "Maintenance Set"
                        );

                    }

                }

            }

        });

        endMaintenanceButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String number
                        = JOptionPane.showInputDialog(
                                "Enter Room Number"
                        );

                for (Room r : roomList) {

                    if (r.getRoomNumber() == Integer.parseInt(number)) {

                        r.endMaintenance();

                        JOptionPane.showMessageDialog(
                                null,
                                "Maintenance End"
                        );

                    }

                }

            }

        });

    }

    public static void main(String args[]) {

        RoomGUI gui = new RoomGUI();

        gui.setVisible(true);

    }

}
