package BusinessLogic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private final int orderId;
    private final int clientId;
    private final Date orderDate;
    private final ArrayList<MenuItem> products;

    public Order(int orderId, int clientId, Date orderDate, ArrayList<MenuItem> products) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.products = products;
    }

    public Order(int orderId, int clientId, Date orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        products = new ArrayList<>();
    }

    public ArrayList<MenuItem> getProducts() {
        return products;
    }

    public int getPrice() {
        int sum = 0;
        for (MenuItem item : products)
            sum += item.getPrice();
        return sum;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    //Depends only on orderId, clientId, orderDate
    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderDate);
    }

    //Depends only on orderId, clientId, orderDate
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order that = (Order) o;
        return orderId == that.getOrderId() && clientId == that.getClientId() && Objects.equals(orderDate, that.getOrderDate());
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder result = new StringBuilder("Date: " + formatter.format(orderDate) + "\nOrder with id: " + orderId + "\nMade by client with id: " + clientId + "\nContains:\n");
        for (MenuItem item : products) {
            result.append(item);
        }
        int totalPrice = 0;
        for (MenuItem item : products)
            totalPrice += item.computePrice();
        return result + "Total price: " + totalPrice + "\n\n";
    }
}
