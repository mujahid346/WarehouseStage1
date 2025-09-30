import java.util.*;
import java.lang.*;
import java.io.*;

public class ProductList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List ProductList = new LinkedList();
    private static ProductList products;

    private ProductList() {
    }

    public static ProductList instance() {
        if (products == null) {
            return (products = new ProductList());
        } else {
            return products;
        }
    }

    public Product search(String id) {
    for (Object obj : ProductList) {
        Product p = (Product) obj;
        if (p.getId().equals(id)) return p;
    }
    return null;
}


    public boolean insertProduct(Product product) {
        ProductList.add(product);
        return true;
    }

    public Iterator<Product> getProducts() {
        return ProductList.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(products);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (products != null) {
                return;
            } else {
                input.defaultReadObject();
                if (products == null) {
                    products = (ProductList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            System.out.println("in Catalog readObject \n" + ioe);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public String toString() {
        return ProductList.toString();
    }
}
