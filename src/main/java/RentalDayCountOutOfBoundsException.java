public class RentalDayCountOutOfBoundsException extends RuntimeException{
    @Override
    public String toString() {
        return "RentalDayCountOutOfBoundsException: The rental day count is 0 or less.";
    }
}
