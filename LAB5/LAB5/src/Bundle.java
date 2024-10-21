import java.util.ArrayList;
import java.util.List;

public abstract class Bundle implements Product {
    private String name;
    private String description;
    private double discount;
    protected List<Product> products = new ArrayList<>();

    public Bundle(String name, String description, double discount) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        // Each concrete bundle will define its own products
        addPredefinedProducts();
    }

    // Abstract method that must be implemented by subclasses to add predefined products
    protected abstract void addPredefinedProducts();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.calculateTotalPrice();
        }
        return totalPrice * (1 - discount);  // Apply bundle discount
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Bundle: " + name + " - " + description + " - Discount: " + (discount * 100) + "%");
        for (Product product : products) {
            product.displayProductInfo();
        }
    }
}
