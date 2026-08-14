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
    private JButton addButton;
    private JButton showButton;
    private JButton searchButton;
    private JButton cancelButton;
    private JTextArea displayArea;
   private BookingManager bookingManager;

    public BookingGUI(){
        bookingManager = new BookingManager();

        setTitle("Booking Management");
   setSize(800,650);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(
                new BorderLayout(20,20)
        );





        JPanel inputPanel = new JPanel();


        inputPanel.setLayout(
                new GridLayout(8,4,20,20)
        );





        JLabel nameLabel =
                new JLabel("Customer Name:");

        JLabel nidLabel =
                new JLabel("National ID:");

        JLabel phoneLabel =
                new JLabel("Phone:");

        JLabel roomLabel =
                new JLabel("Room Number:");

        JLabel priceLabel =
                new JLabel("Room Price:");

        JLabel daysLabel =
                new JLabel("Days:");

        JLabel checkInLabel =
                new JLabel("Check In Date:");

        JLabel checkOutLabel =
                new JLabel("Check Out Date:");





        nameField = new JTextField();

        nidField = new JTextField();

        phoneField = new JTextField();

        roomField = new JTextField();

        priceField = new JTextField();

        daysField = new JTextField();

        checkInField = new JTextField();

        checkOutField = new JTextField();
     inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(nidLabel);
        inputPanel.add(nidField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(roomLabel);
        inputPanel.add(roomField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(daysLabel);
        inputPanel.add(daysField);
         inputPanel.add(checkInLabel);
        inputPanel.add(checkInField);
        inputPanel.add(checkOutLabel);
        inputPanel.add(checkOutField);

        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        displayArea = new JTextArea();

        displayArea.setFont(
                new Font("Arial",Font.PLAIN,20)
        );
       JScrollPane scroll =
                new JScrollPane(displayArea);
        mainPanel.add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel buttonPanel = new JPanel();

        addButton =
                new JButton("Add Booking");
        showButton =
                new JButton("Show Booking");
        searchButton =
                new JButton("Search Booking");
        cancelButton =
                new JButton("Cancel Booking");
        addButton.setFont(
                new Font("Arial",Font.BOLD,20)
        );

        showButton.setFont(
                new Font("Arial",Font.BOLD,20)
        );


        searchButton.setFont(
                new Font("Arial",Font.BOLD,20)
        );


        cancelButton.setFont(
                new Font("Arial",Font.BOLD,20)
        );

        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(mainPanel);


        addButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){

                
                try{

                    Customer customer =
                            new Customer(
                                    nameField.getText(),
                                    nidField.getText(),
                                    phoneField.getText()
                            );

                    
                    Room room =
                            new Room(
                                    Integer.parseInt(roomField.getText()),
                                    RoomType.DELUXE,
                                    Integer.parseInt(priceField.getText())
                            );



                    Booking booking =
                            new Booking(
                                    customer,
                                    room,
                                    Integer.parseInt(daysField.getText()),
                                    checkInField.getText(),
                                    checkOutField.getText()
                            );

                    
                    if(bookingManager.addBooking(booking)){


                        JOptionPane.showMessageDialog(
                                null,
                                "Booking Added Successfully"
                        );


                    }



                }


                catch(Exception ex){

                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage()
                    );


                }
           }


        });

        showButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
               displayArea.setText(
                        "Booking List\n\n"
                );



                for(Booking b :
                        bookingManager.getBookingList()){



                    displayArea.append(
                            b.toString()
                            +"\n\n"
                    );

                }

            }

        });

        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int roomNumber =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        "Enter Room Number"
                                )
                        );
                Booking booking =
                        bookingManager.findBookingByRoom(roomNumber);
                if(booking != null){


                    displayArea.setText(
                            booking.toString()
                    );


                }

                else{


                    JOptionPane.showMessageDialog(
                            null,
                            "Booking Not Found"
                    );


                }



            }


        });

        cancelButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
     int roomNumber =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        "Enter Room Number"
                                )
                        );

                if(bookingManager.removeBookingByRoom(roomNumber)){

                    JOptionPane.showMessageDialog(
                            null,
                            "Booking Cancelled"
                    );

                }

                else{


                    JOptionPane.showMessageDialog(
                            null,
                            "Booking Not Found"
                    );


                }

            }


        });

    }

public static void main(String args[])
{
    BookingGUI gui = new BookingGUI();
    gui.setVisible(true);
}

}
