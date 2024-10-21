public class Main {
    public static void main(String[] args) {
        // Creating predefined bundles
        Product officeBundle = new OfficeBundle();
        Product studentBundle = new StudentBundle();

        // Display product information and calculate total price for Office Bundle
        officeBundle.displayProductInfo();
        System.out.println("Total Price for Office Bundle: $" + officeBundle.calculateTotalPrice());

        System.out.println("\n");

        // Display product information and calculate total price for Student Bundle
        studentBundle.displayProductInfo();
        System.out.println("Total Price for Student Bundle: $" + studentBundle.calculateTotalPrice());

    }
}