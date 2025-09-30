import java.io.*;
import java.util.*;

public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;

  private static Warehouse warehouse;
  private ClientList clientList;
  private ProductList productList;
  private int clientIdCounter = 1;

  private Warehouse() {
    clientList = ClientList.instance();
    productList = ProductList.instance();
  }

  public static Warehouse instance() {
    if (warehouse == null) {
      warehouse = new Warehouse();
    }
    return warehouse;
  }

  // Add a new client
  public Client addClient(String name, String address) {
    Client client = new Client(name, address, clientIdCounter++);
    if (clientList.insertClient(client)) {
      return client;
    }
    return null;
  }

  // Add a new product
  public Product addProduct(String name, double price, int quantity) {
    Product product = new Product(name, price, quantity);
    if (productList.insertProduct(product)) {
      return product;
    }
    return null;
  }

  //Add product to wishlist
  public boolean addProductToClientWishlist(String clientId, String productId, int qty) {
    Client client = clientList.search(clientId);
    Product product = productList.search(productId);
    if (client != null && product != null) {
        client.getWishlist().addItem(productId, qty);
        return true;
    }
    return false;
}

  public List<WishlistItem> getClientWishlist(String clientId) {
    Client client = clientList.search(clientId);
    if (client == null) return Collections.emptyList();
      return client.getWishlist().getItems();
    }
  
  // Return all products
  public Iterator<Product> getProducts() {
    return productList.getProducts();
  }

  // Return all clients
  public Iterator<Client> getClients() {
    return clientList.getClients();
  }
}