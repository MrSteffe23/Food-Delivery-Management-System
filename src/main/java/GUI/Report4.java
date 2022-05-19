package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Report4 extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JButton btnMakeReport;
    private JButton btnGoBack;

    public Report4() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 368, 209);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblday = new JLabel("Insert the day of the month");
        lblday.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblday.setBounds(10, 39, 215, 25);
        contentPane.add(lblday);

        textField = new JTextField();
        textField.setBounds(202, 41, 142, 25);
        contentPane.add(textField);
        textField.setColumns(10);

        btnMakeReport = new JButton("Make report");
        btnMakeReport.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnMakeReport.setBounds(109, 97, 126, 38);
        contentPane.add(btnMakeReport);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(0, 151, 88, 21);
        contentPane.add(btnGoBack);
    }

    public String getday() {
        return textField.getText();
    }

    public void setBtnMakeReport(ActionListener buttonRep) {
        btnMakeReport.addActionListener(buttonRep);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }
}
