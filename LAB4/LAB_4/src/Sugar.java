class Sugar implements Beverage {
    private Beverage beverage;

    public Sugar(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return 0.10 + beverage.cost();  // Adds sugar cost to the beverage
    }
}
