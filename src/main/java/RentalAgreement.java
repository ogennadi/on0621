import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    private Tool tool;
    private Instant checkoutDate;
    private RentalDayCount rentalDays;
    private Percent discountPercent;

    @Override
    public String toString() {
        return "Tool code: "+ tool +"\n"
                .concat("Tool type: "+ tool.getToolType() +"\n")
                .concat("Tool brand: "+ tool.getBrand() +"\n")
                .concat("Rental days: "+ rentalDays +"\n")
                .concat("Check out date: "+ formatDate(checkoutDate) +"\n")
                .concat("Due date: "+ formatDate(getDueDate()) +"\n")
                .concat("Daily rental charge: "+ formatCurrency(getDailyCharge()) + "\n")
                .concat("Charge days: "+ getChargeDays() +"\n")
                .concat("Pre-discount charge: "+ formatCurrency(getPreDiscountCharge()) +"\n")
                .concat("Discount percent: "+ formatPercent(discountPercent.getValue()) +"\n")
                .concat("Discount amount: "+ formatCurrency(getDiscountAmount()) +"\n")
                .concat("Final charge: "+ formatCurrency(getFinalCharge()) +"\n");
    }

    private String formatPercent(int p) {
        return p+"%";
    }

    public RentalAgreement(Tool tool, Instant checkoutDate, RentalDayCount rentalDays, Percent discountPercent) {
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
    }

    public RentalAgreement() {

    }

    public BigDecimal getFinalCharge() {
        return getPreDiscountCharge().subtract(getDiscountAmount());
    }

    public Instant getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = new RentalDayCount(rentalDays);
    }

    public Instant getDueDate() {
        return checkoutDate.plus(Duration.ofDays(rentalDays.getValue()));
    }

    public BigDecimal getPreDiscountCharge() {
        return BigDecimal.valueOf(getChargeDays())
                .multiply(getDailyCharge())
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountAmount() {
        return BigDecimal.valueOf(discountPercent.getValue())
                .divide(BigDecimal.valueOf(100))
                .multiply(getPreDiscountCharge())
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDailyCharge() {
        return BigDecimal.valueOf(tool.getToolType().getDailyCharge());
    }

    public int getChargeDays() {
        return tool.getToolType().getChargeDays(checkoutDate, rentalDays.getValue());
    }

    private String formatDate(Instant i) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PointOfSale.DATE_FORMAT)
                .withZone(ZoneId.of(PointOfSale.TIME_ZONE));

        return formatter.format(i);
    }

    private String formatCurrency(BigDecimal bd) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(bd);
    }
}
