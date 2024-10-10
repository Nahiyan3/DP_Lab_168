import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;


public class Main {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Beverage beverage = null;

            // Step 1: Choose a beverage
            System.out.println("Select a beverage: ");
            System.out.println("1. Espresso\n2. Latte\n3. Cappuccino");
            int beverageChoice = scanner.nextInt();

            HashMap<Integer, BeverageFactory> beverageMap = new HashMap<>();
            beverageMap.put(1, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new Espresso();
                }
            });
            beverageMap.put(2, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new Latte();
                }
            });
            beverageMap.put(3, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new Cappuccino();
                }
            });

            beverage = beverageMap.getOrDefault(beverageChoice, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new Espresso();
                }
            }).create(null);

            HashMap<Integer, BeverageFactory> condimentMap = new HashMap<>();

            condimentMap.put(1, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new Milk(beverage);
                }
            });
            condimentMap.put(2, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new Sugar(beverage);
                }
            });
            condimentMap.put(3, new BeverageFactory() {
                @Override
                public Beverage create(Beverage beverage) {
                    return new WhippedCream(beverage);
                }
            });

            boolean addingCondiments = true;

            while (addingCondiments) {
                System.out.println("Select a condiment: ");
                System.out.println("1. Milk\n2. Sugar\n3. Whipped Cream\n4. Done");

                int condimentChoice = scanner.nextInt();

                if (condimentChoice == 4) {
                    addingCondiments = false;
                } else if (condimentMap.containsKey(condimentChoice)) {
                    beverage = condimentMap.get(condimentChoice).create(beverage);
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            }

            System.out.println("Final Order: " + beverage.getDescription());
            System.out.println("Total Cost: $" + beverage.cost());
        }
}

