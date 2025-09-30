import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String id;
  private static final String CLIENT_STRING = "C";
  private Wishlist wishlist = new Wishlist();

  public Client(String name, String address, int idNum) {
    this.name = name;
    this.address = address;
    id = CLIENT_STRING + idNum;
    wishlist = new Wishlist();
  }

  public String getName() { return name; }
  public String getAddress() { return address;}
  public String getId() { return id;}
  public Wishlist getWishlist() { return wishlist; }

  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }

@Override
  public String toString() {
    String string = "Client Name: " + name + " | Address: " + address + " | ID: " + id;
    return string;
  }
}

