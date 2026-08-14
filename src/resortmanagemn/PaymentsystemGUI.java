package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class PaymentsystemGUI extends JFrame {


    private JTextField amountField;

    private JComboBox<String> methodBox;

    private JButton payButton;

    private JButton showButton;

    private JTextArea displayArea;


    private ArrayList<String> paymentList = new ArrayList<>();



    public PaymentsystemGUI(){


        setTitle("Payment System");


        setSize(800,600);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLocationRelativeTo(null);



        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(new BorderLayout(10,10));




        JPanel inputPanel = new JPanel();


        inputPanel.setLayout(new GridLayout(2,2,20,20));



        JLabel amountLabel = new JLabel("Payment Amount:");

        JLabel methodLabel = new JLabel("Payment Method:");



        amountLabel.setFont(
                new Font("Arial",Font.BOLD,16)
        );


        methodLabel.setFont(
                new Font("Arial",Font.BOLD,16)
        );



        amountField = new JTextField();



        String methods[] = {

            "Cash",
            "Card",
            "Mobile Banking"

        };



        methodBox = new JComboBox<>(methods);




        inputPanel.add(amountLabel);

        inputPanel.add(amountField);


        inputPanel.add(methodLabel);

        inputPanel.add(methodBox);




        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );






        displayArea = new JTextArea();


        displayArea.setFont(
                new Font("Arial",Font.PLAIN,16)
        );



        JScrollPane scroll = new JScrollPane(displayArea);



        mainPanel.add(
                scroll,
                BorderLayout.CENTER
        );







        JPanel buttonPanel = new JPanel();



        payButton = new JButton("Make Payment");


        showButton = new JButton("Show Payment");




        payButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );


        showButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );




        buttonPanel.add(payButton);


        buttonPanel.add(showButton);




        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );




        add(mainPanel);







        payButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                String amount = amountField.getText();


                String method =
                        methodBox.getSelectedItem().toString();




                paymentList.add(
                        amount + " Paid by " + method
                );




                JOptionPane.showMessageDialog(
                        null,
                        "Payment Successful"
                );



                amountField.setText("");



            }


        });








        showButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                displayArea.setText(
                        "Payment List\n\n"
                );



                for(String p : paymentList){



                    displayArea.append(
                            p + "\n\n"
                    );


                }



            }


        });




    }




    public static void main(String args[]) {


        PaymentsystemGUI gui = new PaymentsystemGUI();


        gui.setVisible(true);


    }



}