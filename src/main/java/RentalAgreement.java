import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class RentalAgreement {

    private Instant checkoutDate;
    private int rentalDays;

    public RentalAgreement(String toolCode, Instant checkoutDate, int rentalDays, int discountPercent) {

        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
    }

    public RentalAgreement() {

    }

    public BigDecimal getFinalCharge() {
        return BigDecimal.ZERO;
    }

    public Instant getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Instant getDueDate() {
        return checkoutDate.plus(Duration.ofDays(rentalDays));
    }
}
