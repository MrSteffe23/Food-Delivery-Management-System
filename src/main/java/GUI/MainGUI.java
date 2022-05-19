package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel contentPane;
    private JButton btnAdministrator;
    private JButton btnClient;
    private JButton btnEmployee;

    public MainGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 404, 485);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("Select the user type to log in");
        lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblLogin.setBounds(71, 140, 271, 42);
        contentPane.add(lblLogin);

        btnClient = new JButton("Client");
        btnClient.setBounds(136, 286, 118, 42);
        contentPane.add(btnClient);

        JLabel lblFoodDelivery = new JLabel("Food Delivery");
        lblFoodDelivery.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblFoodDelivery.setBounds(96, 49, 236, 81);
        contentPane.add(lblFoodDelivery);

        btnAdministrator = new JButton("Administrator");
        btnAdministrator.setBounds(136, 213, 118, 42);
        contentPane.add(btnAdministrator);

        btnEmployee = new JButton("Employee");
        btnEmployee.setBounds(136, 358, 118, 42);
        contentPane.add(btnEmployee);
    }

    public void administratorListener(ActionListener administrator) {
        btnAdministrator.addActionListener(administrator);
    }

    public void clientListener(ActionListener client) {
        btnClient.addActionListener(client);
    }

    public void employeeListener(ActionListener employee) {
        btnEmployee.addActionListener(employee);
    }
}
