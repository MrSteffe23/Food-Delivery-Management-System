package GUI;

import DataAccess.CSVReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorGUI extends JFrame {
    private final JTable table;
    private final JButton btnAdd;
    private final JButton btnModify;
    private final JButton btnDelete;
    private final JButton btnGoBack;
    private final JButton btnCreateNewProduct;
    private final JButton btnImportProducts;
    private final JComboBox comboBox;
    private final CSVReader columns = new CSVReader();

    // Create the table model
    private final DefaultTableModel model = new DefaultTableModel(columns.getColumns(), 0);

    public AdministratorGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 803, 630);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAdministratorInterface = new JLabel("Administrator Interface");
        lblAdministratorInterface.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdministratorInterface.setBounds(300, 5, 224, 75);
        contentPane.add(lblAdministratorInterface);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAdd.setBounds(56, 178, 126, 44);
        contentPane.add(btnAdd);

        btnModify = new JButton("Modify");
        btnModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnModify.setBounds(56, 245, 126, 44);
        contentPane.add(btnModify);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDelete.setBounds(56, 314, 126, 44);
        contentPane.add(btnDelete);

        btnCreateNewProduct = new JButton("Create New Product");
        btnCreateNewProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCreateNewProduct.setBounds(30, 388, 185, 44);
        contentPane.add(btnCreateNewProduct);

        btnGoBack = new JButton("Go back");
        btnGoBack.setBounds(10, 572, 88, 21);
        contentPane.add(btnGoBack);

        btnImportProducts = new JButton("Import products");
        btnImportProducts.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnImportProducts.setBounds(37, 113, 160, 44);
        contentPane.add(btnImportProducts);

        comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"time interval report", "products ordered report", "clients ordered report", "products ordered in a day report"}));
        comboBox.setBounds(30, 461, 185, 39);
        contentPane.add(comboBox);

        table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(242, 90, 537, 493);
        contentPane.add(scrollPane);
        table.setFillsViewportHeight(true);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void importProductsListener(ActionListener buttonimport) {
        btnImportProducts.addActionListener(buttonimport);
    }

    public void addListener(ActionListener buttonadd) {
        btnAdd.addActionListener(buttonadd);
    }

    public void deleteListener(ActionListener buttondelete) {
        btnDelete.addActionListener(buttondelete);
    }

    public void modifyListener(ActionListener buttonmodify) {
        btnModify.addActionListener(buttonmodify);
    }

    public void setBtnCreateNewProduct(ActionListener buttoncreate) {
        btnCreateNewProduct.addActionListener(buttoncreate);
    }

    public void setBtnGoBackListener(ActionListener buttonGoBack) {
        btnGoBack.addActionListener(buttonGoBack);
    }

    public JTable getTable() {
        return table;
    }

    public void comboBoxListener(ActionListener cbox) {
        comboBox.addActionListener(cbox);
    }

    public String getSelectedItem() {
        return (String) comboBox.getSelectedItem();
    }
}
