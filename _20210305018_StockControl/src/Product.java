abstract class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String brand;

    public Product(int id, String name, int quantity, double price, String brand) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }


    public int getId() {       // id sabit,değişmez
        return id;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public abstract void displayProductInfo();
}
