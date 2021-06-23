import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    private Tool tool;
    private Instant checkoutDate;
    private int rentalDays;
    private int discountPercent;

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
                .concat("Discount percent: "+ formatPercent(discountPercent) +"\n")
                .concat("Discount amount: "+ formatCurrency(getDiscountAmount()) +"\n")
                .concat("Final charge: "+ formatCurrency(getFinalCharge()) +"\n");
    }

    private String formatPercent(int p) {
        return p+"%";
    }

    public RentalAgreement(Tool tool, Instant checkoutDate, int rentalDays, int discountPercent) {
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
        return BigDecimal.valueOf(getChargeDays()).multiply(getDailyCharge());
    }

    public BigDecimal getDiscountAmount() {
        return BigDecimal.valueOf(discountPercent)
                .divide(BigDecimal.valueOf(100))
                .multiply(getPreDiscountCharge());
    }

    public BigDecimal getDailyCharge() {
        return BigDecimal.valueOf(tool.getToolType().getDailyCharge());
    }

    public int getChargeDays() {
        return tool.getToolType().getChargeDays(checkoutDate, rentalDays);
    }

    private String formatDate(Instant i) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy")
                .withZone(ZoneId.of("UTC"));

        return formatter.format(i);
    }

    private String formatCurrency(BigDecimal bd) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(bd);

    }
}
