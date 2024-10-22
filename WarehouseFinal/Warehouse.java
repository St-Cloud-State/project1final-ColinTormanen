public class Warehouse implements Serializable {

    private static Warehouse warehouse;
    private ProductList pList;
    private ClientList cList;

    private Warehouse() {
        pList = ProductList.instance();
        cList = ClientList.instance();
    }

    public static Warehouse instance() {
        if(warehouse == null) {
            warehouse = new Warehouse();
        }
        return warehouse;
    }

    public boolean addClient(Client client) {
        return cList.insertClient(client);
    }

    public boolean removeClient(String clientId) {
        return cList.removeClient(clientId);
    }

    public boolean addProduct(Product product) {
        return pList.insertProduct(product);
    }

    public boolean searchForProduct(

    public Client searchForClient(String clientId) {
        return cList.searchClient(clientId);
    }

    public boolean addToWishlist(String clientId, Product product) {
        Client client = cList.searchClient(clientId);
        if(client == null) 
    }
}