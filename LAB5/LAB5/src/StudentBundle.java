public class StudentBundle extends Bundle {

    public StudentBundle() {
        super("Student Bundle", "A bundle for students with necessary gadgets", 0.20);
    }

    @Override
    protected void addPredefinedProducts() {
        products.add(new SimpleProduct("Laptop", "Affordable student laptop", 600.0));
        products.add(new SimpleProduct("Headphones", "Noise-cancelling headphones", 150.0));
        products.add(new SimpleProduct("Notebook", "Set of notebooks", 20.0));
    }
}
