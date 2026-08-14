package resortmanagemn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JFrame {

    private JLabel idLabel;
    private JLabel passwordLabel;

    private JTextField idField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton clearButton;


    public AdminGUI() {


        setTitle("Admin Login Panel");

        setSize(800,600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);



        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(8,6,20,20));



        idLabel = new JLabel("Admin ID:");

        passwordLabel = new JLabel("Password:");



        idField = new JTextField();

        passwordField = new JPasswordField();



        loginButton = new JButton("Login");

        clearButton = new JButton("Clear");



        mainPanel.add(idLabel);

        mainPanel.add(idField);


        mainPanel.add(passwordLabel);

        mainPanel.add(passwordField);


        mainPanel.add(loginButton);

        mainPanel.add(clearButton);



        add(mainPanel);



        loginButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {


                String id = idField.getText();

                String password = String.valueOf(passwordField.getPassword());



                Admin admin = new Admin("Ahanaf", "584");



                if(admin.login(id, password)) {


                    JOptionPane.showMessageDialog(
                            null,
                            "Login Successful"
                    );


                }

                else {


                    JOptionPane.showMessageDialog(
                            null,
                            "Wrong ID or Password"
                    );


                }

            }

        });



        clearButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {


                idField.setText("");

                passwordField.setText("");

            }

        });


    }



    public static void main(String[] args) {


        AdminGUI gui = new AdminGUI();

        gui.setVisible(true);


    }


}