public class RentalDayCount {
    private final Integer value;

    public RentalDayCount(Integer value) {
        if (value < 1){
            throw new RentalDayCountOutOfBoundsException();
        }
        this.value = value;
    }

    public static RentalDayCount valueOf(Integer rentalDayCount) {
        return new RentalDayCount(rentalDayCount);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
