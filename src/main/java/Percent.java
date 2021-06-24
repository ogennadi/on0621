public class Percent {
    private final Integer value;

    public Percent(Integer value) {
        if (value < 0 || value > 100) {
            throw new PercentOutOfBoundsException();
        }

        this.value = value;
    }

    public static Percent valueOf(Integer discountPercent) {
        return new Percent(discountPercent);
    }

    public int getValue() {
        return value;
    }
}
