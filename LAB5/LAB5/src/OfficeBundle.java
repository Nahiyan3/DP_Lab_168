public class OfficeBundle extends Bundle {

    public OfficeBundle() {
        super("Office Bundle", "A bundle of office essentials", 0.15);
    }

    @Override
    protected void addPredefinedProducts() {
        products.add(new SimpleProduct("Laptop", "High performance laptop", 1000.0));
        products.add(new SimpleProduct("Monitor", "24-inch LED Monitor", 200.0));
        products.add(new SimpleProduct("Keyboard", "Mechanical keyboard", 100.0));
    }
}
