//CSCI 430 Project 1 Implementation 1
//Mausam Rayamajhi


import java.io.Serializable;

/** Simple value object for a row in the wishlist (product + quantity). */
public class WishlistItem implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String productId;
  private int qty;
  private String productName;

  public WishlistItem(String productId, int qty) {
    Product product = ProductList.instance().search(productId);
    if (product == null) throw new IllegalArgumentException("Invalid productId");

    this.productId = productId;
    this.productName = product.getName();
    this.qty = qty;
}

  public String getProductId() { return productId; }
  public int getQty()          { return qty; }
  public void setQty(int qty)  {
    if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
    this.qty = qty;
  }

  @Override public String toString() {
    return "WishlistItem{Product Name: " + productName + " | ProductId: " + productId + " | Qty: " + qty + "}";
  }
}
