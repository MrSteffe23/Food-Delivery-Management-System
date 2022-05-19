package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateComposeProductGUI extends JFrame {
    private JPanel contentPane;
    private JTextField TileTextField;
    private JButton btnFinalize;
    private JButton btnAddToCart;
    private JTextArea textArea;
    private JButton btnGoBack;

    public CreateComposeProductGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 531, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbltitle = new JLabel("Create Composed Product");
        lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbltitle.setBounds(137, 23, 251, 40);
        contentPane.add(lbltitle);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle.setBounds(33, 89, 88, 27);
        contentPane.add(lblTitle);

        TileTextField = new JTextField();
        TileTextField.setBounds(81, 91, 220, 27);
        contentPane.add(TileTextField);
        TileTextField.setColumns(10);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 131, 497, 183);
        contentPane.add(scrollPane);

        btnFinalize = new JButton("Finalize");
        btnFinalize.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnFinalize.setBounds(304, 329, 95, 21);
        contentPane.add(btnFinalize);

        btnAddToCart = new JButton("Add to Cart");
        btnAddToCart.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAddToCart.setBounds(111, 329, 120, 21);
        contentPane.add(btnAddToCart);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(0, 362, 88, 21);
        contentPane.add(btnGoBack);
    }

    public void setbtnFinalize(ActionListener buttonFinalize) {
        btnFinalize.addActionListener(buttonFinalize);
    }

    public void setbtnAddToCart(ActionListener buttonAddToCart) {
        btnAddToCart.addActionListener(buttonAddToCart);
    }

    public void setTextArea(String s) {
        textArea.setText(s);
    }

    public void setText(String s) {
        TileTextField.setText(s);
    }

    public String getTitleField() {
        return TileTextField.getText();
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }
}
