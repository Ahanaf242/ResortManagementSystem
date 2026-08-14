package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomerGUI extends JFrame {


    private JTextField nameField;
    private JTextField nidField;
    private JTextField phoneField;


    private JButton addButton;
    private JButton searchButton;


    private JTextArea displayArea;


    private CustomerManager customerManager;



    public CustomerGUI(){


        customerManager = new CustomerManager();


        setTitle("Customer Management");

        setSize(700,500);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);



        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(
                new BorderLayout(10,10)
        );



        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(
                new GridLayout(3,2,15,15)
        );



        JLabel nameLabel =
                new JLabel("Customer Name:");

        JLabel nidLabel =
                new JLabel("National ID:");

        JLabel phoneLabel =
                new JLabel("Phone Number:");



        nameField = new JTextField();

        nidField = new JTextField();

        phoneField = new JTextField();



        inputPanel.add(nameLabel);

        inputPanel.add(nameField);


        inputPanel.add(nidLabel);

        inputPanel.add(nidField);


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



        addButton =
                new JButton("Add Customer");


        searchButton =
                new JButton("Search Customer");



        buttonPanel.add(addButton);

        buttonPanel.add(searchButton);



        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );



        add(mainPanel);





        addButton.addActionListener(new ActionListener(){


            public void actionPerformed(ActionEvent e){


                String name =
                        nameField.getText();


                String nid =
                        nidField.getText();


                String phone =
                        phoneField.getText();




                Customer customer =
                        customerManager.addCustomer(
                                name,
                                nid,
                                phone
                        );



                if(customer != null){


                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Added Successfully"
                    );


                    nameField.setText("");

                    nidField.setText("");

                    phoneField.setText("");


                }


                else{


                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Not Added"
                    );


                }


            }


        });







        searchButton.addActionListener(new ActionListener(){


            public void actionPerformed(ActionEvent e){


                String nid =
                        JOptionPane.showInputDialog(
                                "Enter National ID"
                        );



                Customer customer =
                        customerManager.findByNID(nid);



                if(customer != null){


                    displayArea.setText(
                            customer.toString()
                    );


                }


                else{


                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Not Found"
                    );


                }


            }


        });



    }
public static void main(String args[]) {

    CustomerGUI gui = new CustomerGUI();

    gui.setVisible(true);

}
}