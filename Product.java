import java.util.*;
import java.lang.*;
import java.lang.reflect.Member;
import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private int stockQuantity;
    private String id;
    private Client boughtBy;
    private Calendar pusrchaseDate;
    private static int counter = 1;

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.id = "P" + counter++;
    }

    public boolean sell(Client client) {
        boughtBy = client;
        pusrchaseDate = new GregorianCalendar();
        pusrchaseDate.setTimeInMillis(System.currentTimeMillis());
        pusrchaseDate.add(Calendar.MONTH, 1);
        return true;
    }

    public String getPurchaseDate() {
        return (pusrchaseDate.getTime().toString());
    }

    public Client returnProduct() {
        if (boughtBy == null) {
            return null;
        } else {
            Client buyer = boughtBy;
            boughtBy = null;
            return buyer;

        }
    }

    public Client getBuyer() {
        return boughtBy;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Name: " + name + " | Price: " + price + " | stockQuantity: " + stockQuantity + " | ID: " + id;
    }
}
