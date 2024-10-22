import java.io.Serializable;
import java.util.*;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clientId;
    private String clientName;
    private static final String CLIENT_STRING = "C";
    private Wishlist wishlist;

    // Constructor to create a Client object with a name
    public Client(String clientName) {
        this.clientName = clientName;
        this.clientId = CLIENT_STRING + ClientIdServer.instance().getId(); // Generating unique client ID
        this.wishlist = new Wishlist();
    }

    // Getter for client name
    public String getClientName() {
        return clientName;
    }

    // Getter for client ID
    public String getClientId() {
        return clientId;
    }

    // Setter for client name
    public void setClientName(String newClientName) {
        this.clientName = newClientName;
    }

    // Equals method to compare clients by ID
    public boolean equals(String id) {
        return this.clientId.equals(id);
    }

    @Override
    public String toString() {
        return "Client Name: " + clientName + ", Client ID: " + clientId;
    }

    public boolean addToWishlist(Product product) {
        return wishlist.insertProduct(product);
    }

    public boolean removeFromWishlist(String productId) {
        return wishlist.removeProduct(productId);
    }

    public Iterator getWishlist() {
        return wishlist.getProducts();
    }
}