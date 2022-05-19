package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogRegClientGUI extends JFrame {
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton btnGoBackToMenu;
    private JPasswordField confirmPasswordtextField;
    private JComboBox comboBox;
    private JButton btnLogIn;
    private JButton btnRegister;
    private JLabel lblConfirmPassword;

    public LogRegClientGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 435, 348);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIntroduce = new JLabel("Introduce the Client data:");
        lblIntroduce.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblIntroduce.setBounds(97, 36, 236, 37);
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
        btnLogIn.setBounds(32, 214, 107, 43);
        contentPane.add(btnLogIn);

        btnRegister = new JButton("Register");
        btnRegister.setEnabled(false);
        btnRegister.setBounds(167, 214, 107, 43);
        contentPane.add(btnRegister);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(147, 103, 200, 19);
        contentPane.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(147, 147, 200, 19);
        contentPane.add(passwordField);

        comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Log In", "Register"}));
        comboBox.setBounds(296, 224, 103, 23);
        contentPane.add(comboBox);

        lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setEnabled(false);
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblConfirmPassword.setBounds(12, 170, 127, 23);
        contentPane.add(lblConfirmPassword);

        confirmPasswordtextField = new JPasswordField();
        confirmPasswordtextField.setEnabled(false);
        confirmPasswordtextField.setColumns(10);
        confirmPasswordtextField.setBounds(147, 176, 200, 19);
        contentPane.add(confirmPasswordtextField);

        btnGoBackToMenu = new JButton("Go back");
        btnGoBackToMenu.setBounds(0, 290, 88, 21);
        contentPane.add(btnGoBackToMenu);


    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public String getConfirmPassword() {
        return confirmPasswordtextField.getText();
    }

    public void setUsername(String string) {
        usernameTextField.setText(string);
    }

    public void setPassword(String string) {
        passwordField.setText(string);
    }

    public void setConfirmPassword(String string) {
        confirmPasswordtextField.setText(string);
    }

    public String getSelectedItem() {
        return (String) comboBox.getSelectedItem();
    }

    public void setReg(boolean value) {
        btnRegister.setEnabled((value));
    }

    public void setLog(boolean value) {
        btnLogIn.setEnabled((value));
    }

    public void setConfirmPasswordtextField(boolean value) {
        confirmPasswordtextField.setEnabled(value);
    }

    public void setConfirmPasswordLabel(boolean value) {
        lblConfirmPassword.setEnabled(value);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBackToMenu.addActionListener(buttonGoBack);
    }

    public void comboBoxListener(ActionListener cbox) {
        comboBox.addActionListener(cbox);
    }

    public void LogInListener(ActionListener buttonLogIn) {
        btnLogIn.addActionListener(buttonLogIn);
    }

    public void RegisterListener(ActionListener buttonReg) {
        btnRegister.addActionListener(buttonReg);
    }
}
