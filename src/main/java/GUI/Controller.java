package GUI;

import BusinessLogic.*;
import DataAccess.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Controller {
    MainGUI logIn = new MainGUI();
    LogRegClientGUI logRegClient = new LogRegClientGUI();
    LogInAdministratorGUI logInAdministrator = new LogInAdministratorGUI();
    LogInEmployeeGUI logInEmployee = new LogInEmployeeGUI();
    AdministratorGUI administrator = new AdministratorGUI();
    Report1 report1 = new Report1();
    Report2 report2 = new Report2();
    Report3 report3 = new Report3();
    Report4 report4 = new Report4();
    AddGUI addGui = new AddGUI();
    ModifyGUI modifyGUI = new ModifyGUI();
    CreateComposeProductGUI createComposedGUI = new CreateComposeProductGUI();
    CompositeProduct compositeProduct = new CompositeProduct();
    ClientGUI client = new ClientGUI();
    OrderGUI orderGUI = new OrderGUI();
    ArrayList<MenuItem> orderList = new ArrayList<>();
    EmployeeGUI employee = new EmployeeGUI();
    DeliveryService deliveryService = new DeliveryService();
    CSVReader csvReader = new CSVReader();

    public Controller() {
        deliveryService.registerObserver(employee);
        deliveryService.initializeEmployeeOrdersTable();//initializing the order's table for the employee
        if (deliveryService.updateTable() != null)//here we populate the initial table for the administrator
            for (Object[] obj : deliveryService.updateTable()) {
                administrator.getModel().addRow(obj);
            }
        logIn.setVisible(true);
        logInEmployee.setEnabled(false);
        //------------------------------------------------------------------------------------Main GUI---------------------------------------
        logIn.administratorListener(
                (ActionEvent e) -> {
                    logInAdministrator.setVisible(true);
                }
        );
        logIn.clientListener(
                (ActionEvent e) -> {
                    logRegClient.setVisible(true);
                }
        );
        logIn.employeeListener(
                (ActionEvent e) -> {
                    logInEmployee.setEnabled(true);
                    logInEmployee.setVisible(true);
                }
        );
        //------------------------------------------------------------------------------------LogRegClient GUI---------------------------------------
        logRegClient.setBtnGoBackListener(
                (ActionEvent e) -> {
                    logRegClient.setVisible(false);
                }
        );
        logRegClient.comboBoxListener(
                (ActionEvent e) -> {
                    String item = logRegClient.getSelectedItem();
                    if (item.equals("Log In")) {
                        logRegClient.setUsername("");
                        logRegClient.setPassword("");
                        logRegClient.setConfirmPassword("");
                        logRegClient.setConfirmPasswordtextField((false));
                        logRegClient.setLog(true);
                        logRegClient.setReg(false);
                        logRegClient.setConfirmPasswordLabel(false);
                    } else {
                        logRegClient.setUsername("");
                        logRegClient.setPassword("");
                        logRegClient.setConfirmPassword("");
                        logRegClient.setConfirmPasswordtextField((true));
                        logRegClient.setLog(false);
                        logRegClient.setReg(true);
                        logRegClient.setConfirmPasswordLabel(true);
                    }
                }
        );
        logRegClient.LogInListener(
                (ActionEvent e) -> {
                    String username = logRegClient.getUsername();
                    String password = logRegClient.getPassword();
                    if (deliveryService.loginUser(username, password) == true) {
                        client.setVisible(true);
                        logRegClient.setVisible(false);
                    } else
                        JOptionPane.showMessageDialog(logRegClient, "Invalid client log in");
                }
        );
        logRegClient.RegisterListener(
                (ActionEvent e) -> {
                    String username = logRegClient.getUsername();
                    String password = logRegClient.getPassword();
                    String confirmPassword = logRegClient.getConfirmPassword();
                    if (confirmPassword.equals(password)) {
                        deliveryService.registerUser(username, password);
                        JOptionPane.showMessageDialog(logRegClient, "Successfully registration");
                    } else
                        JOptionPane.showMessageDialog(logRegClient, "Invalid password");
                }
        );
        //------------------------------------------------------------------------------------LogInAdministrator GUI---------------------------------------
        logInAdministrator.setBtnGoBackListener(
                (ActionEvent e) -> {
                    logInAdministrator.setVisible(false);
                }
        );
        logInAdministrator.LogInListener(
                (ActionEvent e) -> {
                    String username = logInAdministrator.getUsername();
                    String password = logInAdministrator.getPassword();
                    if (username.equals("admin") && password.equals("pass")) {
                        administrator.setVisible(true);
                        logInAdministrator.setVisible(false);
                    } else
                        JOptionPane.showMessageDialog(logInAdministrator, "Invalid administrator login");
                }
        );
        //------------------------------------------------------------------------------------LogInEmployee GUI---------------------------------------
        logInEmployee.setBtnGoBackListener(
                (ActionEvent e) -> {
                    logInEmployee.setVisible(false);
                }
        );
        logInEmployee.LogInListener(
                (ActionEvent e) -> {
                    String username = logInEmployee.getUsername();
                    String password = logInEmployee.getPassword();
                    if (username.equals(" ") && password.equals(" ")) {
                        employee.setVisible(true);
                        logInEmployee.setVisible(false);
                    } else
                        JOptionPane.showMessageDialog(logInEmployee, "Invalid employee login");
                }
        );
        //------------------------------------------------------------------------------------Administrator GUI---------------------------------------
        administrator.setBtnGoBackListener(
                (ActionListener) -> {
                    administrator.setVisible(false);
                }
        );
        administrator.importProductsListener(
                (ActionListener) -> {
                    int size = administrator.getModel().getRowCount();
                    //we clear the table
                    for (int i = 0; i < size; i++)
                        administrator.getModel().removeRow(0);
                    //we build the table
                    for (Object[] obj : deliveryService.importProducts()) {
                        administrator.getModel().addRow(obj);
                    }
                }
        );
        administrator.addListener(
                (ActionListener) -> {
                    addGui.setVisible(true);
                }
        );
        addGui.btnDoneListener(
                (ActionListener) -> {
                    try {
                        String title = addGui.getTitle();
                        double rating = Double.parseDouble(addGui.getRating());
                        int calories = Integer.parseInt(addGui.getCalories());
                        int protein = Integer.parseInt(addGui.getProtein());
                        int fat = Integer.parseInt(addGui.getFat());
                        int sodium = Integer.parseInt(addGui.getSodium());
                        int price = Integer.parseInt(addGui.getPrice());
                        if (rating < 0 || calories < 0 || protein < 0 || fat < 0 || sodium < 0 || price < 0)
                            throw new Exception();
                        MenuItem item = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                        if (deliveryService.addProduct(item) == true) {
                            administrator.getModel().addRow(
                                    new Object[]{
                                            title,
                                            rating,
                                            calories,
                                            protein,
                                            fat,
                                            sodium,
                                            price,
                                    }
                            );
                            addGui.setVisible(false);
                        } else
                            JOptionPane.showMessageDialog(addGui, "This name already exists");
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(addGui, "Invalid insert");
                    }
                }
        );
        addGui.setBtnGoBackListener(
                (ActionEvent e) -> {
                    addGui.setVisible(false);
                }
        );
        administrator.deleteListener(
                (ActionEvent e) -> {
                    if (administrator.getTable().getSelectedRow() != -1) {
                        deliveryService.deleteProduct(administrator.getTable().getSelectedRow());
                        administrator.getModel().removeRow(administrator.getTable().getSelectedRow());
                    } else
                        JOptionPane.showMessageDialog(administrator, "Select a Menu Item first");
                }
        );
        administrator.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (administrator.getSelectedRow() != -1) {
                int i = administrator.getSelectedRow();
                modifyGUI.setTitle((String) administrator.getModel().getValueAt(i, 0));
                modifyGUI.setRating((Double) administrator.getModel().getValueAt(i, 1));
                modifyGUI.setCalories((Integer) administrator.getModel().getValueAt(i, 2));
                modifyGUI.setProtein((Integer) administrator.getModel().getValueAt(i, 3));
                modifyGUI.setFat((Integer) administrator.getModel().getValueAt(i, 4));
                modifyGUI.setSodium((Integer) administrator.getModel().getValueAt(i, 5));
                modifyGUI.setPrice((Integer) administrator.getModel().getValueAt(i, 6));
            }
        });
        administrator.modifyListener(
                (ActionListener) -> {
                    if (administrator.getSelectedRow() != -1)
                        modifyGUI.setVisible(true);
                    else
                        JOptionPane.showMessageDialog(administrator, "Select a Menu Item first");
                }
        );
        modifyGUI.btnDoneListener(
                (ActionEvent e) -> {
                    try {
                        int i = administrator.getSelectedRow();
                        String title = modifyGUI.getTitle();
                        double rating = Double.parseDouble(modifyGUI.getRating());
                        int calories = Integer.parseInt(modifyGUI.getCalories());
                        int protein = Integer.parseInt(modifyGUI.getProtein());
                        int fat = Integer.parseInt(modifyGUI.getFat());
                        int sodium = Integer.parseInt(modifyGUI.getSodium());
                        int price = Integer.parseInt(modifyGUI.getPrice());
                        if (rating < 0 || calories < 0 || protein < 0 || fat < 0 || sodium < 0 || price < 0)
                            throw new Exception();
                        MenuItem item = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                        deliveryService.modifyProduct(item, i);
                        administrator.getModel().setValueAt(title, i, 0);
                        administrator.getModel().setValueAt(rating, i, 1);
                        administrator.getModel().setValueAt(calories, i, 2);
                        administrator.getModel().setValueAt(protein, i, 3);
                        administrator.getModel().setValueAt(fat, i, 4);
                        administrator.getModel().setValueAt(sodium, i, 5);
                        administrator.getModel().setValueAt(price, i, 6);
                        modifyGUI.setVisible(false);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(addGui, "Invalid data modifications");
                    }
                }
        );
        modifyGUI.setBtnGoBackListener(
                (ActionEvent e) -> {
                    modifyGUI.setVisible(false);
                }
        );
        administrator.setBtnCreateNewProduct(
                (ActionEvent e) -> {
                    compositeProduct = new CompositeProduct();
                    createComposedGUI.setVisible(true);
                }
        );
        createComposedGUI.setbtnAddToCart(
                (ActionEvent e) -> {
                    int i = administrator.getSelectedRow();
                    if (i != -1) {
                        String title = (String) administrator.getModel().getValueAt(i, 0);
                        double rating = (Double) administrator.getModel().getValueAt(i, 1);
                        int calories = (Integer) administrator.getModel().getValueAt(i, 2);
                        int protein = (Integer) administrator.getModel().getValueAt(i, 3);
                        int fat = (Integer) administrator.getModel().getValueAt(i, 4);
                        int sodium = (Integer) administrator.getModel().getValueAt(i, 5);
                        int price = (Integer) administrator.getModel().getValueAt(i, 6);
                        BaseProduct item = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                        compositeProduct.setTitle(createComposedGUI.getTitleField());
                        compositeProduct.addBaseProduct(item);
                        createComposedGUI.setTextArea(compositeProduct.toString());
                    } else
                        JOptionPane.showMessageDialog(logInEmployee, "Select a Menu Item");
                }
        );
        createComposedGUI.setbtnFinalize(
                (ActionEvent e) -> {
                    compositeProduct.setTitle(createComposedGUI.getTitleField());
                    if (deliveryService.createProduct(compositeProduct) == true) {
                        createComposedGUI.setText("");
                        createComposedGUI.setTextArea("");
                        createComposedGUI.setVisible(false);
                        int size = administrator.getModel().getRowCount();
                        //we clear the table
                        for (int i = 0; i < size; i++)
                            administrator.getModel().removeRow(0);
                        if (deliveryService.updateTable() != null)//here we update the table for the administrator
                            for (Object[] obj : deliveryService.updateTable()) {
                                administrator.getModel().addRow(obj);
                            }
                    } else
                        JOptionPane.showMessageDialog(logInEmployee, "This product name already exists");
                }
        );
        createComposedGUI.setBtnGoBackListener(
                (ActionEvent e) -> {
                    createComposedGUI.setVisible(false);
                }
        );
        administrator.comboBoxListener(
                (ActionEvent e) -> {
                    String item = administrator.getSelectedItem();
                    if (item.equals("time interval report")) {
                        report1.setVisible(true);
                    } else {
                        if (item.equals("products ordered report")) {
                            report2.setVisible(true);
                        } else {
                            if (item.equals("clients ordered report")) {
                                report3.setVisible(true);
                            } else {
                                report4.setVisible(true);
                            }
                        }
                    }
                }
        );
        report1.setBtnMakeReport(
                (ActionEvent e) -> {
                    try {
                        int start = Integer.parseInt(report1.getStartHour());
                        int end = Integer.parseInt(report1.getEndHour());
                        if(start < 0 || end < 0 || start > end || end > 23)
                            throw new Exception();
                        deliveryService.generateReportOne(start, end);
                        report1.setVisible(false);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(report1, "Invalid data input");
                    }
                }
        );
        report1.setBtnGoBackListener(
                (ActionEvent e) -> {
                    report1.setVisible(false);
                }
        );
        report2.setBtnMakeReport(
                (ActionEvent e) -> {
                    try {
                        int minim = Integer.parseInt(report2.getMinim());
                        if(minim<0)
                            throw new Exception();
                        deliveryService.generateReportTwo(minim);
                        report2.setVisible(false);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(report2, "Invalid data input");
                    }
                }
        );
        report2.setBtnGoBackListener(
                (ActionEvent e) -> {
                    report2.setVisible(false);
                }
        );
        report3.setBtnMakeReport(
                (ActionEvent e) -> {
                    try {
                        int minimOrders = Integer.parseInt(report3.getMinimOrdersMade());
                        int minimValue = Integer.parseInt(report3.getMinimValueOrder());
                        if(minimOrders<0 || minimValue<0)
                            throw new Exception();
                        deliveryService.generateReportThree(minimOrders, minimValue);
                        report3.setVisible(false);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(report3, "Invalid data input");
                    }
                }
        );
        report3.setBtnGoBackListener(
                (ActionEvent e) -> {
                    report3.setVisible(false);
                }
        );
        report4.setBtnMakeReport(
                (ActionEvent e) -> {
                    try {
                        int day = Integer.parseInt(report4.getday());
                        if(day<1 || day > 31)
                            throw new Exception();
                        deliveryService.generateReportFour(day);
                        report4.setVisible(false);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(report4, "Invalid data input");
                    }
                }
        );
        report4.setBtnGoBackListener(
                (ActionEvent e) -> {
                    report4.setVisible(false);
                }
        );
        //------------------------------------------------------------------------------------Client GUI---------------------------------------
        client.setBtnGoBackListener(
                (ActionEvent e) -> {
                    orderGUI.setVisible(false);
                    client.setVisible(false);
                }
        );
        client.comboBoxListener(
                (ActionEvent e) -> {
                    String item = client.getSelectedItem();
                    if (item.equals("View products")) {
                        client.changeTextField(false);
                        client.changeTextField1(false);
                        client.changeTextField2(false);
                        client.changeTextField3(false);
                        client.changeTextField4(false);
                        client.changeTextField5(false);
                        client.changeTextField6(false);
                        client.changeTextField7(false);
                        client.changeSeeTheResults(false);
                        int size = client.getModel().getRowCount();
                        //we clear the table
                        for (int i = 0; i < size; i++)
                            client.getModel().removeRow(0);
                        if (deliveryService.updateTable() != null)//here we populate the table for the client with the products from the Menu
                            for (Object[] obj : deliveryService.updateTable()) {
                                client.getModel().addRow(obj);
                            }
                    } else {
                        if (item.equals("Search")) {
                            client.changeTextField(true);
                            client.changeTextField1(true);
                            client.changeTextField2(true);
                            client.changeTextField3(true);
                            client.changeTextField4(true);
                            client.changeTextField5(true);
                            client.changeTextField6(true);
                            client.changeTextField7(true);
                            client.changeSeeTheResults(true);
                        } else {
                            client.changeTextField(false);
                            client.changeTextField1(false);
                            client.changeTextField2(false);
                            client.changeTextField3(false);
                            client.changeTextField4(false);
                            client.changeTextField5(false);
                            client.changeTextField6(false);
                            client.changeTextField7(false);
                            client.changeSeeTheResults(false);
                            orderGUI.setVisible(true);
                        }
                    }
                }
        );
        client.setTheResultListener(
                (ActionEvent e) -> {
                    ArrayList<MenuItem> items = deliveryService.getMenuItems();
                    try {
                        String title = client.getTitle();
                        if (!title.equals(""))
                            items = deliveryService.searchTitleProduct(items, title);
                    } catch (Exception exception) {
                    }
                    try {
                        double rating = Double.parseDouble(client.getRating());
                        items = deliveryService.searchRatingProduct(items, rating);
                    } catch (Exception exception) {
                    }
                    try {
                        int calories = Integer.parseInt(client.getCalories());
                        items = deliveryService.searchCaloriesProduct(items, calories);
                    } catch (Exception exception) {
                    }
                    try {
                        int protein = Integer.parseInt(client.getProtein());
                        items = deliveryService.searchProteinProduct(items, protein);
                    } catch (Exception exception) {
                    }
                    try {
                        int fat = Integer.parseInt(client.getFat());
                        items = deliveryService.searchFatProduct(items, fat);
                    } catch (Exception exception) {
                    }
                    try {
                        int sodium = Integer.parseInt(client.getSodium());
                        items = deliveryService.searchSodiumProduct(items, sodium);
                    } catch (Exception exception) {
                    }
                    try {
                        int price1 = Integer.parseInt(client.getPrice1());
                        int price2 = Integer.parseInt(client.getPrice2());
                        items = deliveryService.searchPriceProduct(items, price1, price2);
                    } catch (Exception exception) {
                    }
                    int size = client.getModel().getRowCount();
                    //we clear the table
                    for (int i = 0; i < size; i++)
                        client.getModel().removeRow(0);
                    if (deliveryService.updateSearchedTable(items) != null)
                        for (Object[] obj : deliveryService.updateSearchedTable(items)) {
                            client.getModel().addRow(obj);
                        }
                }
        );
        orderGUI.setbtnAddToOrder(
                (ActionEvent e) -> {
                    int i = client.getSelectedRow();
                    if (i != -1) {
                        String title = (String) client.getModel().getValueAt(i, 0);
                        double rating = (Double) client.getModel().getValueAt(i, 1);
                        int calories = (Integer) client.getModel().getValueAt(i, 2);
                        int protein = (Integer) client.getModel().getValueAt(i, 3);
                        int fat = (Integer) client.getModel().getValueAt(i, 4);
                        int sodium = (Integer) client.getModel().getValueAt(i, 5);
                        int price = (Integer) client.getModel().getValueAt(i, 6);
                        MenuItem item = deliveryService.getItem(title, rating, calories, protein, fat, sodium, price);
                        if (item != null) {
                            orderList.add(item);
                            orderGUI.setTextArea(orderList.toString());
                        }
                    } else
                        JOptionPane.showMessageDialog(logInEmployee, "Select a Menu Item");
                }
        );
        orderGUI.setbtnFinalize(
                (ActionEvent e) -> {
                    Date date = new Date();
                    deliveryService.createOrder(date, orderList);
                    orderGUI.setTextArea("");
                    orderGUI.setVisible(false);
                    deliveryService.notifyObservers(deliveryService.getOrder(date, orderList));
                    orderList = new ArrayList<>();
                }
        );
        orderGUI.setBtnGoBackListener(
                (ActionEvent e) -> {
                    orderGUI.setTextArea("");
                    orderList = new ArrayList<>();
                    orderGUI.setVisible(false);
                }
        );
        //------------------------------------------------------------------------------------Employee GUI---------------------------------------
        employee.setBtnGoBackListener(
                (ActionEvent e) -> {
                    employee.setVisible(false);
                }
        );
    }
}
