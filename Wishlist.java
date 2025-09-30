//CSCI 430 Project 1 Implementation 1
//Mausam Rayamajhi


import java.io.Serializable;
import java.util.*;

/**
 * Writing  wishlist that maps productId - quantity,
 *  when adding the same product,applying sum-merge behavior.
 */
public class Wishlist implements Serializable {
  private static final long serialVersionUID = 1L;

  // Keeping insertion order so that printed output stays predictable
  private final Map<String, Integer> items = new LinkedHashMap<>();

  /** Adding (or merging) a product quantity, using SUM as merge policy. */
  public void addItem(String productId, int qty) {
    if (productId == null || productId.isEmpty()) {
      throw new IllegalArgumentException("productId required");
    }
    if (qty <= 0) {
      throw new IllegalArgumentException("qty must be > 0");
    }
    // Merging qty into existing value, summing when duplicate found
    items.merge(productId, qty, Integer::sum);
  }

  /** Changing the exact quantity for a product that already exists. */
  public void changeQty(String productId, int qty) {
    if (!items.containsKey(productId)) {
      throw new NoSuchElementException("No such product in wishlist: " + productId);
    }
    if (qty <= 0) {
      throw new IllegalArgumentException("qty must be > 0");
    }
    // Overwriting with the new quantity
    items.put(productId, qty);
  }

  /** Removing a product completely from the wishlist. */
  public void removeItem(String productId) {
    items.remove(productId);
  }

  /** Taking a snapshot copy of the items for safe external use. */
  public List<WishlistItem> getItems() {
    List<WishlistItem> out = new ArrayList<>(items.size());
    // Converting map entries into WishlistItem objects
    for (Map.Entry<String,Integer> e : items.entrySet()) {
      out.add(new WishlistItem(e.getKey(), e.getValue()));
    }
    return out;
  }

  // Providing simple utility methods
  public boolean hasProduct(String productId) { return items.containsKey(productId); }
  public int size()                          { return items.size(); }
  public void clear()                        { items.clear(); }

  @Override public String toString() {
    // Printing map view for quick debugging
    return items.toString();
  }
}
