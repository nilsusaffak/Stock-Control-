
public class ClothingProducts extends Product {


    public ClothingProducts(int id, String name, int quantity, double price, String brand) {   //constructor
        super(id, name, quantity, price, brand);
    }

    @Override
    public void displayProductInfo() {        //ürün bilgilerini görüntüle
        System.out.println("Clothing Product: " + getName() + ", Quantity: " + getQuantity() + ", Price: " + getPrice() + ", Brand: " + getBrand());
    }
}

