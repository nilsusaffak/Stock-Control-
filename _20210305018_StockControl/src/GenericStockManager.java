import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericStockManager<T extends Product> implements StockManager<T> {
    private List<T> products = new ArrayList<>(); // Sıralı ürün listesi
    private Map<Integer, T> productMap = new HashMap<>(); // ID'ye göre ürün saklama

    @Override
    public void addProduct(T product) {
        products.add(product);
        productMap.put(product.getId(), product); // ID ile Map'e ekleme
        System.out.println("Product added: " + product.getName());
    }

    @Override
    public void updateProduct(int id, int newQuantity, double newPrice) {
        // Stream ve Lambda kullanarak güncelleme
        products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .ifPresent(product -> {
                    product.setQuantity(newQuantity);
                    product.setPrice(newPrice);
                    System.out.println("Product updated: " + product.getName());
                });
    }

    @Override
    public void listAllProducts() {
        // Lambda ile sıralama
        products.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        products.forEach(Product::displayProductInfo);

        // Lambda ile toplam miktar hesaplama
        int totalQuantity = products.stream()
                .mapToInt(Product::getQuantity)
                .sum();
        System.out.println("Total Quantity: " + totalQuantity);
    }

    public void deleteProduct(int id) {
        // Map ve List üzerinden silme işlemi
        T product = productMap.remove(id); // Map'ten silme
        if (product != null) {
            products.remove(product); // List'ten silme
            System.out.println("Product with ID " + id + " has been deleted.");
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public T findProductById(int id) {
        // ID ile Map üzerinden ürün bulma
        return productMap.get(id);
    }
}



