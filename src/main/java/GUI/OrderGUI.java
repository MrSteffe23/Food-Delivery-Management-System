package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderGUI extends JFrame {
    private JPanel contentPane;
    private JButton btnFinalize;
    private JButton btnAddToOrder;
    private JTextArea textArea;
    private JButton btnGoBack;

    public OrderGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 531, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbltitle = new JLabel("Create Order");
        lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbltitle.setBounds(206, 23, 147, 40);
        contentPane.add(lbltitle);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 73, 497, 235);
        contentPane.add(scrollPane);

        btnFinalize = new JButton("Finalize");
        btnFinalize.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnFinalize.setBounds(304, 329, 95, 21);
        contentPane.add(btnFinalize);

        btnAddToOrder = new JButton("Add to Order");
        btnAddToOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAddToOrder.setBounds(97, 329, 147, 21);
        contentPane.add(btnAddToOrder);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(0, 362, 88, 21);
        contentPane.add(btnGoBack);
    }

    public void setbtnFinalize(ActionListener buttonFinalize) {
        btnFinalize.addActionListener(buttonFinalize);
    }

    public void setbtnAddToOrder(ActionListener buttonAddToCart) {
        btnAddToOrder.addActionListener(buttonAddToCart);
    }

    public void setTextArea(String s) {
        textArea.setText(s);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }
}
