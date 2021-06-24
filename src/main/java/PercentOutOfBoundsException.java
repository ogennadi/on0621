public class PercentOutOfBoundsException extends RuntimeException{
    @Override
    public String toString() {
        return "PercentOutOfBoundsException: The percentage entered is less than 0 or greater than 100";
    }
}
