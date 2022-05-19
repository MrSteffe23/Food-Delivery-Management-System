package BusinessLogic;

import DataAccess.CSVReader;
import DataAccess.FileWriter;
import DataAccess.Serializer;

import GUI.Observer;
import Model.User;

import java.io.IOException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

/**
 * Class which implements the main actions for administrator and client
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {
    private final ArrayList<Observer> observerList;
    private ArrayList<User> usersList = new ArrayList<>();
    private int clientId;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<>();
    private final Serializer serializer;

    /**
     * The constructor of this class, it deserializes the data loaded in files
     */
    public DeliveryService() {
        assert wellFormed();
        serializer = new Serializer();
        loadDataFromFile("users.txt");
        loadDataFromFile("menuItems.txt");
        loadDataFromFile("orders.txt");

        observerList = new ArrayList<>();
        assert wellFormed();
    }

    /**
     * @return true if the list of items isn't empty, false otherwise
     */
    protected boolean wellFormed() {
        return menuItems != null;
    }

    /**
     * deserializing the data
     *
     * @param path the path file from which we want to extract the data
     */
    private void loadDataFromFile(String path) {
        assert wellFormed();
        assert path != null;
        Object objects = serializer.deserialize(path);
        if (path.equals("users.txt") && objects != null)
            usersList = (ArrayList<User>) objects;
        if (path.equals("menuItems.txt") && objects != null)
            menuItems = (ArrayList<MenuItem>) objects;
        if (path.equals("orders.txt") && objects != null)
            orders = (HashMap<Order, ArrayList<MenuItem>>) objects;
        assert wellFormed();
    }

    /**
     * serializing the data
     *
     * @param object object which contains the data to be serialized
     * @param path   the path file where we want to load the data
     */
    private void loadDataInFile(Object object, String path) {
        assert wellFormed();
        assert object != null && path != null;
        serializer.serialize(object, path);
        assert wellFormed();
    }

    /**
     * implements the action importProducts for the administrator
     *
     * @return a list of products
     */
    @Override
    public ArrayList<Object[]> importProducts() {
        assert wellFormed();
        CSVReader csvReader = new CSVReader();
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            for (MenuItem item : csvReader.extractFromCSV()) {
                if (item != null && menuItems != null && !menuItems.contains(item))//we add only those products that are not in the menu already
                {
                    menuItems.add(item);//we add the baseProduct in the menu
                    BaseProduct product = (BaseProduct) item;
                    list.add(
                            new Object[]{
                                    product.getTitle(),
                                    product.getRating(),
                                    product.getCalories(),
                                    product.getProtein(),
                                    product.getFat(),
                                    product.getSodium(),
                                    product.getPrice(),
                            }
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadDataInFile(menuItems, "menuItems.txt");//we update the MenuItems
        assert wellFormed();
        return updateTable();
    }

    /**
     * method used to update the initial table with products from the interface
     *
     * @return a list of products/items
     */
    public ArrayList<Object[]> updateTable() {
        assert wellFormed();
        ArrayList<Object[]> list = new ArrayList<>();
        if (menuItems != null)
            for (MenuItem item : menuItems) {
                if (item != null)
                    list.add(
                            new Object[]{
                                    item.getTitle(),
                                    item.getRating(),
                                    item.getCalories(),
                                    item.getProtein(),
                                    item.getFat(),
                                    item.getSodium(),
                                    item.getPrice(),
                            }
                    );
            }
        assert wellFormed();
        return list;
    }

    /**
     * Adding a new item in the list of items from the menu
     *
     * @param item the product to be added
     * @return true if we added successfully the item, false otherwise
     */
    @Override
    public boolean addProduct(MenuItem item) {
        assert wellFormed();
        int size = menuItems.size();
        for (MenuItem items : menuItems) {
            if (items.getTitle().equals(item.getTitle()))
                return false;
        }
        menuItems.add(item);
        loadDataInFile(menuItems, "menuItems.txt");
        assert size >= menuItems.size();
        assert wellFormed();
        return true;
    }

    /**
     * deletes a product from the list of products
     *
     * @param index the index of a product to be deleted
     */
    @Override
    public void deleteProduct(int index) {
        assert wellFormed();
        assert index >= 0 && index < menuItems.size();
        int size = menuItems.size();
        menuItems.remove(index);
        loadDataInFile(menuItems, "menuItems.txt");
        assert size == menuItems.size() - 1;
        assert wellFormed();
    }

    /**
     * modifies some attributes of a product
     *
     * @param item  a product with new attributes
     * @param index the index of the product I want to modify
     */
    @Override
    public void modifyProduct(MenuItem item, int index) {
        assert wellFormed();
        assert item != null && index >= 0 && index < menuItems.size();
        int size = menuItems.size();
        MenuItem updateItem = menuItems.get(index);
        updateItem.setTitle(item.getTitle());
        updateItem.setCalories(item.getCalories());
        updateItem.setFat(item.getFat());
        updateItem.setPrice(item.getPrice());
        updateItem.setRating(item.getRating());
        updateItem.setSodium(item.getSodium());
        updateItem.setProtein(item.getProtein());
        loadDataInFile(menuItems, "menuItems.txt");
        assert size == menuItems.size();
        assert wellFormed();
    }

    /**
     * create a new product and tries to add it to the list
     *
     * @param compositeProduct a new Composite product which will
     * @return true if a new product was added tot the list, or false otherwise
     */
    @Override
    public boolean createProduct(CompositeProduct compositeProduct) {
        assert wellFormed();
        int size = menuItems.size();
        for (MenuItem items : menuItems) {
            if (items != null && items.getTitle().equals(compositeProduct.getTitle()))
                return false;
        }
        menuItems.add(compositeProduct);
        loadDataInFile(menuItems, "menuItems.txt");
        assert size == menuItems.size() + 1;
        assert wellFormed();
        return true;
    }

    /**
     * generates a report with the orders performed between a given start hour and a given end hour regardless the date
     *
     * @param start start hour
     * @param end   end hour
     */
    @Override
    public void generateReportOne(int start, int end) {
        assert wellFormed();
        assert start > 0 && end > 0 && start < end;
        ArrayList<Order> searched = (ArrayList<Order>) orders.keySet().stream()
                .filter(
                        order -> order.getOrderDate().getHours() >= start && order.getOrderDate().getHours() <= end
                ).collect(Collectors.toList());
        StringBuilder message = new StringBuilder(" ");
        for (Order order : searched) {
            message.append(order);
        }
        FileWriter fileWriter = new FileWriter();
        fileWriter.fileWrite("report1.pdf", message.toString());
        assert wellFormed();
    }

    public static <T> Predicate<T> distinct(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * generates a report with the products ordered more than a specified number of times so far
     *
     * @param minim minimum times a Product has been ordered so far
     */
    @Override
    public void generateReportTwo(int minim) {
        assert wellFormed();
        assert minim >= 0;
        Collection<ArrayList<MenuItem>> items = orders.values();
        ArrayList<MenuItem> flatList = new ArrayList<>();
        for (ArrayList<MenuItem> arraylist : items)
            flatList.addAll(arraylist);
        ArrayList<MenuItem> searched = (ArrayList<MenuItem>) flatList.stream()
                .filter(
                        menuItem -> Collections.frequency(flatList, menuItem) >= minim
                )
                .filter(distinct(MenuItem::getTitle))
                .collect(Collectors.toList());
        StringBuilder message = new StringBuilder(" ");
        for (MenuItem item : searched)
            message.append(item);
        FileWriter fileWriter = new FileWriter();
        fileWriter.fileWrite("report2.pdf", message.toString());
        assert wellFormed();
    }

    /**
     * generates a report with the clients that have ordered more than a specified number of times so far and the value of the order was higher than a specified amount
     *
     * @param minimOrders    minimum number of orders made by a client
     * @param miniOrderValue minimum value of an order made by a client
     */
    @Override
    public void generateReportThree(int minimOrders, int miniOrderValue) {
        assert wellFormed();
        assert minimOrders >= 0 && miniOrderValue >= 0;
        ArrayList<User> searched = (ArrayList<User>) usersList.stream()
                .filter(
                        user -> {
                            return orders.keySet().stream()
                                    .filter(
                                            order -> {
                                                return order.getClientId() == usersList.indexOf(user);
                                            }
                                    )
                                    .count() >= minimOrders;
                        }
                )
                .filter(
                        user -> {
                            return orders.keySet().stream()
                                    .filter(
                                            order -> {
                                                return order.getClientId() == usersList.indexOf(user);
                                            }
                                    )
                                    .count() ==
                                    orders.keySet().stream()
                                            .filter(
                                                    order -> {
                                                        return order.getClientId() == usersList.indexOf(user) && order.getPrice() >= miniOrderValue;
                                                    }
                                            )
                                            .count();
                        }
                ).collect(Collectors.toList());
        StringBuilder message = new StringBuilder(" ");
        for (User user : searched)
            message.append(user).append("\n");
        FileWriter fileWriter = new FileWriter();
        fileWriter.fileWrite("report3.pdf", message.toString());
        assert wellFormed();
    }

    /**
     * generates a report with the products ordered within a specified day with the number of times they have been ordered
     *
     * @param day the day of the month
     */
    @Override
    public void generateReportFour(int day) {
        assert wellFormed();
        assert day >= 1 && day <= 31;
        ArrayList<MenuItem> items = new ArrayList<>();
        orders.keySet().stream()
                .filter(
                        order -> order.getOrderDate().getDate() == day
                )
                .map(order -> orders.get(order))
                .forEach(items::addAll);
        //System.out.println(items);
        Map<MenuItem, Long> map
                = items.stream().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        //we make here a Set to iterate a single time through every MenuItem
        Set<MenuItem> unics = new HashSet<>(items);
        StringBuilder message = new StringBuilder(" ");
        for (MenuItem item : unics) {
            message.append(item).append("TIMES: ").append(map.get(item)).append("\n");
        }
        FileWriter fileWriter = new FileWriter();
        fileWriter.fileWrite("report4.pdf", message.toString());
        assert wellFormed();
    }

    /**
     * transforms a list of products into a list of objects
     *
     * @param items a list of products
     * @return a list of objects, where an object contains the attributes of an item
     */
    public ArrayList<Object[]> updateSearchedTable(ArrayList<MenuItem> items) {
        assert wellFormed();
        ArrayList<Object[]> list = new ArrayList<>();
        if (items != null)
            //System.out.println(items);
            for (MenuItem item : items) {
                assert item != null;
                list.add(
                        new Object[]{
                                item.getTitle(),
                                item.getRating(),
                                item.getCalories(),
                                item.getProtein(),
                                item.getFat(),
                                item.getSodium(),
                                item.getPrice(),
                        }
                );
            }
        assert wellFormed();
        return list;
    }

    /**
     * returns the list of items in the menu
     *
     * @return the list of products
     */
    public ArrayList<MenuItem> getMenuItems() {
        assert wellFormed();
        return menuItems;
    }

    /**
     * searches for products with the specified title
     *
     * @param menuItems the list of products which need to be searched
     * @param title     the title of a product
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchTitleProduct(ArrayList<MenuItem> menuItems, String title) {
        assert wellFormed();
        assert title != null;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> menuItem.getTitle().equals(title)
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * searches for products with the specified rating
     *
     * @param menuItems the list of products which need to be searched
     * @param rating    the rating of a product
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchRatingProduct(ArrayList<MenuItem> menuItems, double rating) {
        assert wellFormed();
        assert rating > 0;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> abs(menuItem.getRating() - rating) < 1e-3
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * searches for products with the specified calories
     *
     * @param menuItems the list of products which need to be searched
     * @param calories  the calories of a product
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchCaloriesProduct(ArrayList<MenuItem> menuItems, int calories) {
        assert wellFormed();
        assert calories > 0;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> menuItem.getCalories() == calories
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * searches for products with the specified protein
     *
     * @param menuItems the list of products which need to be searched
     * @param protein   the protein of a product
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchProteinProduct(ArrayList<MenuItem> menuItems, int protein) {
        assert wellFormed();
        assert protein > 0;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> menuItem.getProtein() == protein
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * searches for products with the specified fat
     *
     * @param menuItems the list of products which need to be searched
     * @param fat       the fat of a product
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchFatProduct(ArrayList<MenuItem> menuItems, int fat) {
        assert wellFormed();
        assert fat > 0;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> menuItem.getFat() == fat
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * searches for products with the specified sodium
     *
     * @param menuItems the list of products which need to be searched
     * @param sodium    the sodium of a product
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchSodiumProduct(ArrayList<MenuItem> menuItems, int sodium) {
        assert wellFormed();
        assert sodium > 0;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> menuItem.getSodium() == sodium
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * searches for products with the specified price between a range
     *
     * @param menuItems the list of products which need to be searched
     * @param one       the left limit for the price
     * @param two       the right limit for the price
     * @return a shorter array of products
     */
    @Override
    public ArrayList<MenuItem> searchPriceProduct(ArrayList<MenuItem> menuItems, int one, int two) {
        assert wellFormed();
        assert one > 0 && two > 0 && one <= two;
        int size = menuItems.size();
        List<MenuItem> searched = menuItems.stream()
                .filter(
                        menuItem -> menuItem.getPrice() >= one && menuItem.getPrice() <= two
                )
                .collect(Collectors.toList());
        assert wellFormed();
        assert size >= menuItems.size();
        return (ArrayList<MenuItem>) searched;
    }

    /**
     * creates a new order giving a date and list of products
     *
     * @param date  the date an order has been created
     * @param items an array of products
     */
    @Override
    public void createOrder(Date date, ArrayList<MenuItem> items) {
        assert wellFormed();
        assert date != null;
        int size = orders.size();//size indicates the orderId
        Order order = new Order(size, clientId, date, items);
        FileWriter fileWriter = new FileWriter();
        fileWriter.makeBill(order);
        orders.put(order, order.getProducts());
        loadDataInFile(orders, "orders.txt");//we update the orders
        System.out.println(orders);
        assert wellFormed();
    }

    /**
     * method used for initializing the table in the employee's interface
     */
    public void initializeEmployeeOrdersTable() {
        assert wellFormed();
        ArrayList<Order> ordersToFill = (ArrayList<Order>) orders.keySet().stream().collect(Collectors.toList());
        for (Order order : ordersToFill)
            notifyObservers(order);
        assert wellFormed();
    }

    /**
     * @param o an observer
     */
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    /**
     * creates and returns a new order giving a date and list of products
     *
     * @param date  the date an order has been created
     * @param items an array of products
     * @return a new order
     */
    public Order getOrder(Date date, ArrayList<MenuItem> items) {
        int size = orders.size();//size indicates the orderId
        return new Order(size, clientId, date, items);
    }

    /**
     * @param order an order
     */
    @Override
    public void notifyObservers(Order order) {
        for (Observer obs : observerList) {
            obs.update(order);
        }
    }

    /**
     * register a user for the application
     *
     * @param username username for client
     * @param password password for client
     */
    public void registerUser(String username, String password) {
        usersList.add(new User(username, password));
        loadDataInFile(usersList, "users.txt");
    }

    /**
     * verifies if a client is registered in the system
     *
     * @param username username for client
     * @param password password for client
     * @return true if the client exists, false otherwise
     */
    public boolean loginUser(String username, String password) {
        User user = new User(username, password);
        for (User users : usersList)
            if (users.equals(user)) {
                clientId = usersList.indexOf(users);
                return true;
            }
        return false;
    }

    /**
     * creates and returns a new item for the menu
     *
     * @param title    the title of a product
     * @param rating   the rating of a product
     * @param calories the calories of a product
     * @param protein  the protein of a product
     * @param fat      the fat of a product
     * @param sodium   the sodium of a product
     * @param price    the price of a product
     * @return a new item
     */
    public MenuItem getItem(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        assert wellFormed();
        for (MenuItem item : menuItems) {
            if (item != null && item.getTitle().equals(title) && item.getRating() - rating < 1e-3 && item.getCalories() == calories && item.getProtein() == protein && item.getFat() == fat && item.getSodium() == sodium && item.getPrice() == price)
                return item;
        }
        assert wellFormed();
        return null;
    }
}
