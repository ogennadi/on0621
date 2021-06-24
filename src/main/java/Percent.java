public class Percent {
    private final int value;

    public Percent(int value) {
        if (value < 0 || value > 100) {
            throw new RuntimeException(value + " is outside percent bounds");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
