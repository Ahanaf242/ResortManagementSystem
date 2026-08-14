package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ResortserviceGUI extends JFrame {


    private JTextField serviceNameField;

    private JTextField priceField;


    private JButton addButton;

    private JButton showButton;


    private JTextArea displayArea;



    private ArrayList<String> serviceList = new ArrayList<>();





    public ResortserviceGUI(){


        setTitle("Resort Service");


        setSize(850,650);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLocationRelativeTo(null);





        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(
                new BorderLayout(10,10)
        );






        JPanel inputPanel = new JPanel();


        inputPanel.setLayout(
                new GridLayout(4,4,40,40)
        );






        JLabel nameLabel = new JLabel(
                "Service Name:"
        );


        JLabel priceLabel = new JLabel(
                "Service Price:"
        );




        nameLabel.setFont(
                new Font("Arial",Font.BOLD,18)
        );


        priceLabel.setFont(
                new Font("Arial",Font.BOLD,18)
        );





        serviceNameField = new JTextField();


        priceField = new JTextField();






        inputPanel.add(nameLabel);

        inputPanel.add(serviceNameField);



        inputPanel.add(priceLabel);

        inputPanel.add(priceField);






        mainPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );







        displayArea = new JTextArea();


        displayArea.setFont(
                new Font("Arial",Font.PLAIN,18)
        );



        JScrollPane scroll = new JScrollPane(displayArea);




        mainPanel.add(
                scroll,
                BorderLayout.CENTER
        );









        JPanel buttonPanel = new JPanel();




        addButton = new JButton(
                "Add Service"
        );


        showButton = new JButton(
                "Show Service"
        );





        addButton.setFont(
                new Font("Arial",Font.BOLD,16)
        );


        showButton.setFont(
                new Font("Arial",Font.BOLD,16)
        );





        buttonPanel.add(addButton);


        buttonPanel.add(showButton);





        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );







        add(mainPanel);









        addButton.addActionListener(
                new ActionListener(){



            public void actionPerformed(ActionEvent e){



                String name =
                        serviceNameField.getText();


                String price =
                        priceField.getText();





                serviceList.add(
                        name + " : " + price
                );





                JOptionPane.showMessageDialog(
                        null,
                        "Service Added Successfully"
                );




                serviceNameField.setText("");

                priceField.setText("");



            }



        });









        showButton.addActionListener(
                new ActionListener(){



            public void actionPerformed(ActionEvent e){



                displayArea.setText(
                        "Service List\n\n"
                );





                for(String s : serviceList){



                    displayArea.append(
                            s + "\n\n"
                    );



                }





            }



        });





    }






    public static void main(String args[]){



        ResortserviceGUI gui =
                new ResortserviceGUI();



        gui.setVisible(true);



    }



}