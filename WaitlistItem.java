//CSCI 430 Project 1 Implementation 1
//Mausam Rayamajhi


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/** waitlist request for a product by a client. */
public class WaitlistItem implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String itemId = UUID.randomUUID().toString();
  private final String productId;
  private final String clientId;
  private final int qty;
  private final Date requestedAt = new Date();

  public WaitlistItem(String productId, String clientId, int qty) {
    if (productId == null || productId.isEmpty()) throw new IllegalArgumentException("productId required");
    if (clientId == null || clientId.isEmpty())   throw new IllegalArgumentException("clientId required");
    if (qty <= 0)                                  throw new IllegalArgumentException("qty must be > 0");
    this.productId = productId;
    this.clientId = clientId;
    this.qty = qty;
  }

  public String getItemId()    { return itemId; }
  public String getProductId() { return productId; }
  public String getClientId()  { return clientId; }
  public int getQty()          { return qty; }
  public Date getRequestedAt() { return requestedAt; }

  @Override public String toString() {
    return "WaitlistItem{id=" + itemId + ", productId=" + productId +
           ", clientId=" + clientId + ", qty=" + qty + ", at=" + requestedAt + "}";
  }
}
