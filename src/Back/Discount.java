package Back;

public enum Discount {
    VETERAN(0.10),
    SOLDIER(0.05),
    STUDENT(0.03),
    GENERAL(0.0);

    private final double rate;

    Discount(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}