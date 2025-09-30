import java.io.*;
import java.util.*;

public class ClientList implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<Client> clients = new LinkedList<>();
  private static ClientList clientList;

  private ClientList() { }

  public static ClientList instance() {
    if (clientList == null) {
      clientList = new ClientList();
    }
    return clientList;
  }

  public boolean insertClient(Client client) {
    return clients.add(client);
  }

  public Iterator<Client> getClients() {
    return clients.iterator();
  }

  public Client search(String id) {
    for (Client c : clients) {
      if (c.getId().equals(id)) return c;
    }
    return null;
  }
}