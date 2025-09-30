import java.util.*;
import java.io.*;

public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int SHOW_CLIENTS = 2;
    private static final int ADD_PRODUCT = 3;
    private static final int SHOW_PRODUCTS = 4;
    private static final int ADD_TO_WISHLIST = 5;
    private static final int SHOW_WISHLIST = 6;

    private UserInterface() {
        warehouse = Warehouse.instance();
    }

    public static UserInterface instance() {
        if (userInterface == null) {
            userInterface = new UserInterface();
        }
        return userInterface;
    }

    public String getToken(String prompt) {
        try {
            System.out.println(prompt);
            return reader.readLine();
        } catch (IOException e) {
            System.exit(0);
            return null;
        }
    }

    public int getCommand() {
        while (true) {
            try {
                System.out.println("Enter command: " + ADD_CLIENT + " to add client, " 
                + SHOW_CLIENTS + " to display clients, " 
                + ADD_PRODUCT + " to add product, " 
                + SHOW_PRODUCTS + " to display products "  
                + ADD_TO_WISHLIST + " to add a product to wishlist, "
                + SHOW_WISHLIST + " to display a client's wishlist, "
                + EXIT + " to exit");
                int value = Integer.parseInt(reader.readLine());
                if (value >= EXIT && value <= SHOW_WISHLIST) {
                    return value;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid number");
            }
        }
    }

    // Add client use case
    public void addClient() {
        String name = getToken("Enter client name:");
        String address = getToken("Enter client address:");
        Client result = warehouse.addClient(name, address);
        if (result == null) {
            System.out.println("Could not add client");
        } else {
            System.out.println("Added client: " + result);
        }
    }

    // Display all clients
    public void displayClients() {
        Iterator<Client> clients = warehouse.getClients();
        System.out.println("All clients:");
        while (clients.hasNext()) {
            Client client = clients.next();
            System.out.println(client);
        }
    }

    //ADD product to wishlist
    public void addProductToWishlist() {
    String clientId = getToken("Enter client ID:");
    String productId = getToken("Enter product ID:");
    int qty = Integer.parseInt(getToken("Enter quantity:"));
    boolean success = warehouse.addProductToClientWishlist(clientId, productId, qty);
    if (success) {
        System.out.println("Added product " + productId + " x" + qty + " to client " + clientId + "'s wishlist.");
    } else {
        System.out.println("Failed to add product. Check client ID and product ID.");
    }
}

    public void displayClientWishlist() {
    String clientId = getToken("Enter client ID:");
    List<WishlistItem> items = warehouse.getClientWishlist(clientId);
    if (items.isEmpty()) {
        System.out.println("No items found or invalid client ID.");
        return;
    }
    System.out.println("Wishlist for client " + clientId + ":");
    for (WishlistItem item : items) {
        System.out.println(item);
    }
    }

    public void addProduct() {
        String name = getToken("Enter product name:");
        double price = Double.parseDouble(getToken("Enter price:"));
        int quantity = Integer.parseInt(getToken("Enter quantity:"));
        Product result = warehouse.addProduct(name, price, quantity);
        if (result == null) {
            System.out.println("Could not add product");
        }         
        else {
            System.out.println("Added product: " + result);
        }
    }

    public void displayProducts() {
        Iterator<Product> products = warehouse.getProducts();
        System.out.println("All products:");
        while (products.hasNext()) {
            System.out.println(products.next());
        }
    }

    public void process() {
        int command;
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:
                    addClient();
                    break;
                case SHOW_CLIENTS:
                    displayClients();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case SHOW_PRODUCTS:
                    displayProducts();
                    break;
                case ADD_TO_WISHLIST:
                    addProductToWishlist();
                    break;
                case SHOW_WISHLIST:
                    displayClientWishlist();
                    break;
            }
        }
        System.out.println("Exiting...");
    }

    public static void main(String[] args) {
        UserInterface.instance().process();
    }
}