import java.util.*;
import java.lang.*;
import java.lang.reflect.Member;
import java.io.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private int stockQuantity;
    private int id;
    private Client boughtBy;
    private Calendar pusrchaseDate;
    private static int counter = 1;

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.id = counter++;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "name " + name + " price " + price + " stockQuantity " + stockQuantity + " id " + id;
    }
}
