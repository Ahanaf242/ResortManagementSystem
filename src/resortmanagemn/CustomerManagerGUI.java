package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomerManagerGUI extends JFrame {


    private JTextField phoneField;


    private JButton showButton;
    private JButton deleteButton;


    private JTextArea displayArea;


    private CustomerManager customerManager;



    public CustomerManagerGUI(){


        customerManager = new CustomerManager();



        setTitle("Customer Manager");

        setSize(700,500);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);




        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(
                new BorderLayout(10,10)
        );




        JPanel inputPanel = new JPanel();


        inputPanel.setLayout(
                new GridLayout(1,2,15,15)
        );




        JLabel phoneLabel =
                new JLabel("Customer Phone:");



        phoneField = new JTextField();




        phoneLabel.setFont(
                new Font("Arial",Font.BOLD,16)
        );




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
                new JButton("Show Customers");



        deleteButton =
                new JButton("Delete Customer");





        showButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );



        deleteButton.setFont(
                new Font("Arial",Font.BOLD,14)
        );





        buttonPanel.add(showButton);


        buttonPanel.add(deleteButton);





        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );






        add(mainPanel);








        showButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                displayArea.setText(
                        "Customer List\n\n"
                );



                for(Customer c :
                        customerManager.getCustomerList()){


                    displayArea.append(
                            c.toString()
                            +"\n\n"
                    );


                }



            }



        });








        deleteButton.addActionListener(new ActionListener(){



            public void actionPerformed(ActionEvent e){



                String phone =
                        phoneField.getText();




                boolean result =
                        customerManager.deleteCustomer(phone);




                if(result){


                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Deleted Successfully"
                    );


                }


                else{


                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Not Found"
                    );


                }



                phoneField.setText("");



            }



        });




    }

    public static void main(String args[]) {

        CustomerManagerGUI gui = new CustomerManagerGUI();

        gui.setVisible(true);

    }


}

