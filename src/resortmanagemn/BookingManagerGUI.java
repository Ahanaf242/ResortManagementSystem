package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BookingManagerGUI extends JFrame {


    private JTextField roomField;

    private JTextField phoneField;


    private JButton showButton;

    private JButton searchRoomButton;

    private JButton searchPhoneButton;

    private JButton deleteButton;


    private JTextArea displayArea;


    private BookingManager bookingManager;



    public BookingManagerGUI(){


        bookingManager = new BookingManager();



        setTitle("Booking Manager");


        setSize(750,550);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLocationRelativeTo(null);






        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(
                new BorderLayout(10,10)
        );








        JPanel inputPanel = new JPanel();


        inputPanel.setLayout(
                new GridLayout(2,2,15,15)
        );





        JLabel roomLabel =
                new JLabel("Room Number:");



        JLabel phoneLabel =
                new JLabel("Customer Phone:");





        roomField = new JTextField();


        phoneField = new JTextField();






        roomLabel.setFont(
                new Font("Arial",Font.BOLD,16)
        );


        phoneLabel.setFont(
                new Font("Arial",Font.BOLD,16)
        );





        inputPanel.add(roomLabel);

        inputPanel.add(roomField);


        inputPanel.add(phoneLabel);

        inputPanel.add(phoneField);







        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );








        displayArea = new JTextArea();


        displayArea.setFont(
                new Font("Arial",Font.PLAIN,16)
        );



        JScrollPane scroll =
                new JScrollPane(displayArea);




        mainPanel.add(
                scroll,
                BorderLayout.CENTER
        );









        JPanel buttonPanel = new JPanel();





        showButton =
                new JButton("Show Booking");



        searchRoomButton =
                new JButton("Search By Room");



        searchPhoneButton =
                new JButton("Search By Phone");



        deleteButton =
                new JButton("Delete Booking");







        showButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );


        searchRoomButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );


        searchPhoneButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );


        deleteButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );






        buttonPanel.add(showButton);


        buttonPanel.add(searchRoomButton);


        buttonPanel.add(searchPhoneButton);


        buttonPanel.add(deleteButton);






        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );






        add(mainPanel);









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









        searchRoomButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                int roomNumber =
                        Integer.parseInt(
                                roomField.getText()
                        );




                Booking booking =
                        bookingManager.findBookingByRoom(
                                roomNumber
                        );




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









        searchPhoneButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                Booking booking =
                        bookingManager.findBookingByCustomerPhone(
                                phoneField.getText()
                        );




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









        deleteButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                int roomNumber =
                        Integer.parseInt(
                                roomField.getText()
                        );





                if(
                        bookingManager.removeBookingByRoom(
                                roomNumber
                        )
                ){


                    JOptionPane.showMessageDialog(
                            null,
                            "Booking Deleted Successfully"
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
    BookingManagerGUI gui = new BookingManagerGUI();
    gui.setVisible(true);
}

}