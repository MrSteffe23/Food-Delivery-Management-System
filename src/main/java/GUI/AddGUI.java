package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddGUI extends JFrame {
    private final JTextField textField;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_3;
    private final JTextField textField_4;
    private final JTextField textField_5;
    private final JTextField textField_6;
    private final JButton btnNewButton;
    private final JButton btnGoBackToMenu;

    public AddGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 446, 548);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductSpec = new JLabel("Add Product Specifications");
        lblProductSpec.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblProductSpec.setBounds(112, 66, 268, 39);
        contentPane.add(lblProductSpec);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitle.setBounds(70, 138, 62, 25);
        contentPane.add(lblTitle);

        JLabel lblRating = new JLabel("Rating:");
        lblRating.setHorizontalAlignment(SwingConstants.CENTER);
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRating.setBounds(70, 187, 62, 25);
        contentPane.add(lblRating);

        JLabel lblCalories = new JLabel("Calories:");
        lblCalories.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCalories.setBounds(70, 233, 62, 25);
        contentPane.add(lblCalories);

        JLabel lblProtein = new JLabel("Protein:");
        lblProtein.setHorizontalAlignment(SwingConstants.CENTER);
        lblProtein.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblProtein.setBounds(70, 277, 62, 25);
        contentPane.add(lblProtein);

        JLabel lblTitle_3_1 = new JLabel("Fat:");
        lblTitle_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitle_3_1.setBounds(70, 322, 62, 25);
        contentPane.add(lblTitle_3_1);

        JLabel lblTitle_3_2 = new JLabel("Sodium:");
        lblTitle_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle_3_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitle_3_2.setBounds(70, 365, 62, 25);
        contentPane.add(lblTitle_3_2);

        JLabel lblTitle_3_3 = new JLabel("Price:");
        lblTitle_3_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle_3_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitle_3_3.setBounds(70, 408, 62, 25);
        contentPane.add(lblTitle_3_3);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBounds(174, 136, 195, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(174, 185, 195, 30);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_2.setColumns(10);
        textField_2.setBounds(174, 231, 195, 30);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_3.setColumns(10);
        textField_3.setBounds(174, 277, 195, 30);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_4.setColumns(10);
        textField_4.setBounds(174, 320, 195, 30);
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_5.setColumns(10);
        textField_5.setBounds(174, 363, 195, 30);
        contentPane.add(textField_5);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_6.setColumns(10);
        textField_6.setBounds(174, 403, 195, 30);
        contentPane.add(textField_6);

        btnNewButton = new JButton("Done");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(151, 456, 121, 30);
        contentPane.add(btnNewButton);

        btnGoBackToMenu = new JButton("Go back");
        btnGoBackToMenu.setBounds(0, 490, 88, 21);
        contentPane.add(btnGoBackToMenu);
    }

    public String getTitle() {
        return textField.getText();
    }

    public String getRating() {
        return textField_1.getText();
    }

    public String getCalories() {
        return textField_2.getText();
    }

    public String getProtein() {
        return textField_3.getText();
    }

    public String getFat() {
        return textField_4.getText();
    }

    public String getSodium() {
        return textField_5.getText();
    }

    public String getPrice() {
        return textField_6.getText();
    }

    public void btnDoneListener(ActionListener buttonDone) {
        btnNewButton.addActionListener(buttonDone);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBackToMenu.addActionListener(buttonGoBack);
    }
}
