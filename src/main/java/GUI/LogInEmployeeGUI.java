package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInEmployeeGUI extends JFrame {
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton btnGoBackToMenu;
    private JButton btnLogIn;

    public LogInEmployeeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 435, 348);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIntroduce = new JLabel("Introduce the Employee data:");
        lblIntroduce.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblIntroduce.setBounds(71, 38, 302, 37);
        contentPane.add(lblIntroduce);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername.setBounds(67, 99, 85, 23);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(67, 143, 85, 23);
        contentPane.add(lblPassword);

        btnLogIn = new JButton("Log In");
        btnLogIn.setBounds(158, 205, 107, 43);
        contentPane.add(btnLogIn);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(147, 103, 200, 19);
        contentPane.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordTextField = new JPasswordField();
        passwordTextField.setColumns(10);
        passwordTextField.setBounds(147, 147, 200, 19);
        contentPane.add(passwordTextField);

        btnGoBackToMenu = new JButton("Go back");
        btnGoBackToMenu.setBounds(0, 290, 88, 21);
        contentPane.add(btnGoBackToMenu);


    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }

    public void LogInListener(ActionListener buttonLogIn) {
        btnLogIn.addActionListener(buttonLogIn);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBackToMenu.addActionListener(buttonGoBack);
    }
}
