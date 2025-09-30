//CSCI 430 Project 1 Implementation 1
//Mausam Rayamajhi

import java.io.Serializable;
import java.util.*;

/** Writing a per-product FIFO waitlist. */
public class Waitlist implements Serializable {
  private static final long serialVersionUID = 1L;

  private final Map<String, Deque<WaitlistItem>> byProduct = new HashMap<>();

  /** Adding a new request and returning the generated waitlist item id. */
  public String add(String productId, int qty, String clientId) {
    if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
    WaitlistItem wi = new WaitlistItem(productId, clientId, qty);
    byProduct.computeIfAbsent(productId, k -> new ArrayDeque<>()).addLast(wi);
    return wi.getItemId();
  }

  /** Removing a specific waitlist item by id and returning true if found. */
  public boolean remove(String itemId) {
    // Looping through all product queues
    for (Deque<WaitlistItem> q : byProduct.values()) {
      Iterator<WaitlistItem> it = q.iterator();
      while (it.hasNext()) {
        if (it.next().getItemId().equals(itemId)) {
          it.remove();
          return true;
        }
      }
    }
    return false;
  }

  /** Popping the next (oldest) waitlist item for a product, or null if none. */
  public WaitlistItem popNext(String productId) {
    Deque<WaitlistItem> q = byProduct.get(productId);
    return (q == null) ? null : q.pollFirst();
  }

  /** Getting all waitlist entries for a particular client across all products. */
  public List<WaitlistItem> getItemsByClient(String clientId) {
    List<WaitlistItem> out = new ArrayList<>();
    for (Deque<WaitlistItem> q : byProduct.values()) {
      for (WaitlistItem wi : q) if (wi.getClientId().equals(clientId)) out.add(wi);
    }
    return out;
  }

  /** Counting how many items are waiting for a given product. */
  public int countForProduct(String productId) {
    Deque<WaitlistItem> q = byProduct.get(productId);
    return (q == null) ? 0 : q.size();
  }

  @Override 
  public String toString() {
    // Printing the whole map structure for quick debugging
    return byProduct.toString();
  }
}
