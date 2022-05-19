package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Report1 extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnMakeReport;
    private JButton btnGoBack;

    public Report1() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 368, 209);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblStartHour = new JLabel("Start hour:");
        lblStartHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblStartHour.setBounds(66, 31, 75, 25);
        contentPane.add(lblStartHour);

        JLabel lblEndHour = new JLabel("End hour:");
        lblEndHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEndHour.setBounds(66, 69, 75, 25);
        contentPane.add(lblEndHour);

        textField = new JTextField();
        textField.setBounds(151, 33, 142, 25);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(151, 69, 142, 25);
        contentPane.add(textField_1);

        btnMakeReport = new JButton("Make report");
        btnMakeReport.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnMakeReport.setBounds(112, 124, 126, 38);
        contentPane.add(btnMakeReport);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(0, 151, 88, 21);
        contentPane.add(btnGoBack);
    }

    public String getStartHour() {
        return textField.getText();
    }

    public String getEndHour() {
        return textField_1.getText();
    }

    public void setBtnMakeReport(ActionListener buttonRep) {
        btnMakeReport.addActionListener(buttonRep);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }
}
