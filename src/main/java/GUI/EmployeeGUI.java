package GUI;

import BusinessLogic.Order;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeGUI extends JFrame implements Observer {
    private final JTable table;
    private final JButton btnGoBack;
    private JTextArea textArea;
    // JTable Header
    public static final String[] columns = {
            "Order ID", "Client ID", "Order Date"
    };
    // Create the table model
    private DefaultTableModel model = new DefaultTableModel(columns, 0);

    public EmployeeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 803, 630);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAdministratorInterface = new JLabel("Employee Interface");
        lblAdministratorInterface.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdministratorInterface.setBounds(300, 5, 224, 75);
        contentPane.add(lblAdministratorInterface);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(10, 572, 88, 21);
        contentPane.add(btnGoBack);

        table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 70, 769, 256);
        contentPane.add(scrollPane);
        table.setFillsViewportHeight(true);


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        JScrollPane scrollPane_1 = new JScrollPane(textArea);
        scrollPane_1.setBounds(10, 347, 769, 215);
        contentPane.add(scrollPane_1);

    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }

    public JTable getTable() {
        return table;
    }

    public void setTextArea(String s) {
        textArea.append(s);
    }

    @Override
    public void update(Order order) {
        //JOptionPane.showMessageDialog(this, "New order");
        setTextArea("-------------------------------------------------------------------------------------NEW ORDER-----------------------------------------------------------------------------------\n" + order);
        getModel().addRow(
                new Object[]{
                        order.getOrderId(),
                        order.getClientId(),
                        order.getOrderDate(),
                }
        );
    }
}
