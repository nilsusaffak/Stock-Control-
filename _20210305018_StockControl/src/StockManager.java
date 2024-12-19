// interface
interface StockManager<T extends Product> {
    void addProduct(T product);
    void updateProduct(int id, int newQuantity, double newPrice);
    void listAllProducts();
}



