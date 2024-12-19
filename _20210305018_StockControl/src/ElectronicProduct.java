
public class ElectronicProduct extends Product {


    public ElectronicProduct(int id, String name, int quantity, double price, String brand) {   //constructor
        super(id, name, quantity, price, brand);    //inheritance olarak alınanlar

    }

    @Override
    public void displayProductInfo() {        //ürün bilgilerini görüntüle
        System.out.println("Electronic Product: " + getName() + ", Brand: " + getBrand() + ", Quantity: " + getQuantity() + ", Price: " + getPrice());
    }
}
