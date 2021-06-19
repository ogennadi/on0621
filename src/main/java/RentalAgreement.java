import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;

public class RentalAgreement {
    private Tool tool;
    private Instant checkoutDate;
    private int rentalDays;
    private int discountPercent;

    public RentalAgreement(Tool tool, Instant checkoutDate, int rentalDays, int discountPercent) {
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
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

    public BigDecimal getPreDiscountCharge() {
        ToolType toolType = tool.getToolType();
        int chargeDays = toolType.getChargeDays(checkoutDate, rentalDays);
        Double dailyCharge = toolType.getDailyCharge();

        return BigDecimal.valueOf(chargeDays).multiply(BigDecimal.valueOf(dailyCharge));
    }

    public BigDecimal getDiscountAmount() {
        return BigDecimal.valueOf(discountPercent)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(getPreDiscountCharge());
    }
}
