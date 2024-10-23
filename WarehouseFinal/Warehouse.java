public class Warehouse implements Serializable {

    private static Warehouse warehouse;
    private ProductList pList;
    private ClientList cList;

    // Private constructor
    private Warehouse() {
        pList = ProductList.instance();
        cList = ClientList.instance();
    }

    // Create a static warehouse instance
    public static Warehouse instance() {
        if(warehouse == null) {
            warehouse = new Warehouse();
        }
        return warehouse;
    }


    /**** Client Operations ****/

    // Add a client to the client list
    public boolean addClient(Client client) {
        return cList.insertClient(client);
    }

    // Remove a client from the client list
    public boolean removeClient(String clientId) {
        return cList.removeClient(clientId);
    }

    // Search for a client
    public Client searchForClient(String clientId) {
        return cList.searchClient(clientId);
    }

    // Get client list
    public Iterator getClients() {
        return cList.getClients();
    }


    /**** Product Operations ****/

    // Add a product to the product list
    public boolean addProduct(Product product) {
        return pList.insertProduct(product);
    }

    // Search for a product
    public Product searchForProduct(String productId) {
        return pList.searchProduct(productId);
    }

    // Get product list
    public Iterator getProducts() {
        return pList.getProducts();
    }


    /**** Warehouse Operations ****/

    // Add a product to a client's wishlist
    public boolean addToWishlist(String clientId, String productId, int quantity) {
        Client client = cList.searchClient(clientId);
        Product product = pList.searchProduct(productId);
        if(client == null || product == null) return false;
        product.setQuantity(quantity);
        return client.addToWishlist(product);
    }

    // Remove a product from a client's wishlist
    public boolean removeFromWishlist(String clientId, String productId) {
        Client client = cList.searchClient(clientId);
        if(client == null) return false;
        return client.removeFromWishlist(productId);
    }

    // Add client to a waitlist
    public boolean addToWaitlist(String clientId, String productId, int quantity) {
        Product product = pList.searchProduct(productId);
        if(product == null) return false;
        return product.addToWaitlist(clientId, quantity);
    }

    public boolean placeOrder(String clientId) {
        Client client = cList.searchClient(clientId);
        if(client == null) return false;
        Iterator wishlist = client.getWishlist();
        int totalCost;
        while(wishlist.hasNext()) {
            Product product = wishlist.next();
            totalCost += product.getCost() * product.getQuantity();
            Product listProduct = pList.searchProduct(product.getProductId());
            if(listProduct == null) return false;
            int quantity = listProduct.getQuantity() - product.getQuantity();
            if(quantity < 0) {
                listProduct.addToWaitlist(client.getClientId(), -1*quantity);
                listProduct.setQuantity(0);
            }
            else listProduct.setQuantity(quantity);
        }
        client.addCredit(totalCost);
    }

    public boolean receiveShipment(String productId, int quantity) {
        Product product = pList.searchProduct(productId);
        if(product == null) return false;
        Iterator waitlist = product.getWaitlist();
        Waitlist copy = new Waitlist();
        while(waitlist.hasNext()) {
            // Need waitlist code to continue
        }
    }
}