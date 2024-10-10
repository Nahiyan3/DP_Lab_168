class WhippedCream implements Beverage {
    private Beverage beverage;

    public WhippedCream(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whipped Cream";
    }

    @Override
    public double cost() {
        return 0.50 + beverage.cost();  // Adds whipped cream cost to the beverage
    }
}
