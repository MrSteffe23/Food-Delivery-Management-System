package GUI;

import DataAccess.CSVReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private final JTable table;
    private final JTextField textField;
    private final JTextField textField_1;
    private final JTextField textField_2;
    private final JTextField textField_3;
    private final JTextField textField_4;
    private final JTextField textField_5;
    private final JTextField textField_6;
    private final JTextField textField_7;
    private final JComboBox comboBox;
    private final JButton btnGoBack;
    private final JButton btnSeeTheResults;
    private final CSVReader columns = new CSVReader();

    // Create the table model
    private final DefaultTableModel model = new DefaultTableModel(columns.getColumns(), 0);

    public ClientGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 803, 630);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblClientInterface = new JLabel("Client Interface");
        lblClientInterface.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClientInterface.setBounds(327, 0, 151, 80);
        contentPane.add(lblClientInterface);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(10, 572, 88, 21);
        contentPane.add(btnGoBack);

        comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"View products", "Search", "Create Order"}));
        comboBox.setBounds(28, 127, 185, 39);
        contentPane.add(comboBox);

        JLabel lblSelectOp = new JLabel("Select the operation:");
        lblSelectOp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSelectOp.setBounds(51, 96, 144, 21);
        contentPane.add(lblSelectOp);

        textField = new JTextField();
        textField.setBounds(99, 212, 111, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setEnabled(false);

        JLabel lblKeyword = new JLabel("Keyword:");
        lblKeyword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKeyword.setBounds(28, 209, 70, 20);
        contentPane.add(lblKeyword);

        JLabel lblRating = new JLabel("Rating:");
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRating.setBounds(28, 239, 70, 21);
        contentPane.add(lblRating);

        JLabel lblNewLabel_1_2 = new JLabel("Calories:");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2.setBounds(28, 270, 70, 28);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblProteins = new JLabel("Proteins:");
        lblProteins.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProteins.setBounds(28, 308, 70, 29);
        contentPane.add(lblProteins);

        JLabel lblFats = new JLabel("Fats:");
        lblFats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFats.setBounds(28, 347, 70, 31);
        contentPane.add(lblFats);

        JLabel lblSodium = new JLabel("Sodium:");
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSodium.setBounds(28, 388, 70, 21);
        contentPane.add(lblSodium);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(99, 242, 111, 20);
        contentPane.add(textField_1);
        textField_1.setEnabled(false);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(99, 277, 111, 20);
        contentPane.add(textField_2);
        textField_2.setEnabled(false);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(99, 315, 111, 20);
        contentPane.add(textField_3);
        textField_3.setEnabled(false);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(99, 355, 111, 20);
        contentPane.add(textField_4);
        textField_4.setEnabled(false);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(99, 389, 111, 20);
        contentPane.add(textField_5);
        textField_5.setEnabled(false);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrice.setBounds(28, 425, 70, 21);
        contentPane.add(lblPrice);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(99, 428, 44, 20);
        contentPane.add(textField_6);
        textField_6.setEnabled(false);

        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(165, 428, 44, 20);
        contentPane.add(textField_7);
        textField_7.setEnabled(false);

        btnSeeTheResults = new JButton("See the results");
        btnSeeTheResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSeeTheResults.setBounds(53, 475, 131, 39);
        btnSeeTheResults.setEnabled(false);
        contentPane.add(btnSeeTheResults);

        table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(242, 90, 537, 493);
        contentPane.add(scrollPane);
        table.setFillsViewportHeight(true);

        JLabel lblNewLabel = new JLabel("--->");
        lblNewLabel.setBounds(142, 431, 31, 13);
        contentPane.add(lblNewLabel);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
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

    public String getPrice1() {
        return textField_6.getText();
    }

    public String getPrice2() {
        return textField_7.getText();
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void changeTextField(boolean value) {
        textField.setEnabled(value);
    }

    public void changeTextField1(boolean value) {
        textField_1.setEnabled(value);
    }

    public void changeTextField2(boolean value) {
        textField_2.setEnabled(value);
    }

    public void changeTextField3(boolean value) {
        textField_3.setEnabled(value);
    }

    public void changeTextField4(boolean value) {
        textField_4.setEnabled(value);
    }

    public void changeTextField5(boolean value) {
        textField_5.setEnabled(value);
    }

    public void changeTextField6(boolean value) {
        textField_6.setEnabled(value);
    }

    public void changeTextField7(boolean value) {
        textField_7.setEnabled(value);
    }

    public void changeSeeTheResults(boolean value) {
        btnSeeTheResults.setEnabled(value);

    }


    public JTable getTable() {
        return table;
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }

    public void setTheResultListener(ActionListener buttonresults) {
        btnSeeTheResults.addActionListener(buttonresults);
    }

    public void comboBoxListener(ActionListener cbox) {
        comboBox.addActionListener(cbox);
    }

    public String getSelectedItem() {
        return (String) comboBox.getSelectedItem();
    }
}
