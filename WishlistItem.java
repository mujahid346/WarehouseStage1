//CSCI 430 Project 1 Implementation 1
//Mausam Rayamajhi


import java.io.Serializable;

/** Simple value object for a row in the wishlist (product + quantity). */
public class WishlistItem implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String productId;
  private int qty;

  public WishlistItem(String productId, int qty) {
    if (productId == null || productId.isEmpty()) {
      throw new IllegalArgumentException("productId required");
    }
    if (qty <= 0) {
      throw new IllegalArgumentException("qty must be > 0");
    }
    this.productId = productId;
    this.qty = qty;
  }

  public String getProductId() { return productId; }
  public int getQty()          { return qty; }
  public void setQty(int qty)  {
    if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
    this.qty = qty;
  }

  @Override public String toString() {
    return "WishlistItem{productId=" + productId + ", qty=" + qty + "}";
  }
}
