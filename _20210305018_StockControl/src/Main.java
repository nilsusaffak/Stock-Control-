import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Giriş işlemleri
        Login login = new Login();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (!login.authenticate(username, password)) {
            System.out.println("Invalid credentials. Access denied.");
            return;
        }

        System.out.println("Login successful!");

        // Elektronik ve gıda ürünleri için stok yöneticileri
        GenericStockManager<ElectronicProduct> electronicStockManager = new GenericStockManager<>();
        GenericStockManager<ClothingProducts> clothingStockManager = new GenericStockManager<>();

        // Stoktaki ürünler
        electronicStockManager.addProduct(new ElectronicProduct(1, "Laptop", 10, 1500.0, "Dell"));
        electronicStockManager.addProduct(new ElectronicProduct(2, "Phone", 20, 800.0, "Samsung"));
        clothingStockManager.addProduct(new ClothingProducts(3, "Sweater", 100, 100, "Zara"));
        clothingStockManager.addProduct(new ClothingProducts(4, "Pants", 200, 200, "Mango"));

        // Ana menü
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. List all products");
            System.out.println("2. List products by category");
            System.out.println("3. Update stock");
            System.out.println("4. Add new product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAll Products:");
                    electronicStockManager.listAllProducts();
                    clothingStockManager.listAllProducts();
                    break;
                case 2:
                    System.out.println("\nChoose a category:");
                    System.out.println("1. Electronics");
                    System.out.println("2. Clothing");
                    System.out.print("Enter your choice: ");
                    int categoryChoice = scanner.nextInt();

                    if (categoryChoice == 1) {
                        System.out.println("\nElectronic Products:");
                        electronicStockManager.listAllProducts();
                    } else if (categoryChoice == 2) {
                        System.out.println("\nClothing Products:");
                        clothingStockManager.listAllProducts();
                    } else {
                        System.out.println("Invalid category choice.");
                    }
                    break;
                case 3:
                    System.out.print("\nEnter product ID to update: ");
                    int id = scanner.nextInt();

                    // ID kontrolü: Pozitif olmalı
                    if (id <= 0) {
                        System.out.println("Invalid product ID. It must be a positive number.");
                        break;
                    }

                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();

                    // Miktar kontrolü: Pozitif olmalı
                    if (newQuantity <= 0) {
                        System.out.println("Invalid quantity. It must be a positive number.");
                        break;
                    }

                    // Fiyat kontrolü: Geçerli bir double değeri girilmeli
                    double newPrice = 0.0;
                    boolean validPrice = false;
                    while (!validPrice) {
                        System.out.print("Enter new price: ");
                        if (scanner.hasNextDouble()) {
                            newPrice = scanner.nextDouble();
                            if (newPrice > 0) {
                                validPrice = true;  // Fiyat geçerli
                            } else {
                                System.out.println("Price must be a positive number.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid number for price.");
                            scanner.next();  // Geçersiz girişi atla
                        }
                    }

                    // Güncelleme işlemleri
                    // Elektronik ürünleri için
                    if (productExistsInCategory(electronicStockManager, id)) {
                        electronicStockManager.updateProduct(id, newQuantity, newPrice);
                    }
                    // Giyim ürünleri için
                    if (productExistsInCategory(clothingStockManager, id)) {
                        clothingStockManager.updateProduct(id, newQuantity, newPrice);
                    }
                    break;
                case 4:
                    System.out.println("\nChoose product type to add:");
                    System.out.println("1. Electronics");
                    System.out.println("2. Clothing");
                    System.out.print("Enter your choice: ");
                    int productType = scanner.nextInt();

                    if (productType == 1) {
                        System.out.print("Enter product ID: ");
                        int eId = scanner.nextInt();
                        scanner.nextLine(); // Boşluğu temizle
                        System.out.print("Enter product name: ");
                        String eName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int eQuantity = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double ePrice = scanner.nextDouble();
                        scanner.nextLine(); // Boşluğu temizle
                        System.out.print("Enter brand: ");
                        String eBrand = scanner.nextLine();
                        ElectronicProduct newElectronic = new ElectronicProduct(eId, eName, eQuantity, ePrice, eBrand);
                        electronicStockManager.addProduct(newElectronic);
                    } else if (productType == 2) {
                        System.out.print("Enter product ID: ");
                        int cId = scanner.nextInt();
                        scanner.nextLine(); // Boşluğu temizle
                        System.out.print("Enter product name: ");
                        String cName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int cQuantity = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double cPrice = scanner.nextDouble();
                        scanner.nextLine(); // Boşluğu temizle
                        System.out.print("Enter brand: ");
                        String cBrand = scanner.nextLine();

                        ClothingProducts newClothing = new ClothingProducts(cId, cName, cQuantity, cPrice, cBrand);
                        clothingStockManager.addProduct(newClothing);
                    } else {
                        System.out.println("Invalid product type.");
                    }
                    break;
                case 5:
                    System.out.print("\nEnter product ID to delete: ");
                    int deleteId = scanner.nextInt();

                    // Elektronik ürünlerden silme
                    if (productExistsInCategory(electronicStockManager, deleteId)) {
                        electronicStockManager.deleteProduct(deleteId);
                    }

                    // Giyim ürünlerden silme
                    if (productExistsInCategory(clothingStockManager, deleteId)) {
                        clothingStockManager.deleteProduct(deleteId);
                    }

                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean productExistsInCategory(GenericStockManager<?> stockManager, int id) {
        if (stockManager.findProductById(id) != null) {
            return true;
        }
        return false;
    }
}



