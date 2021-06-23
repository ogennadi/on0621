import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RentalAgreementTest {

    public static final Instant NON_WEEKEND_AND_NON_HOLIDAY = Instant.parse("2021-06-14T00:00:00Z");

    @Test
    void finalChargeIsPreDiscountChargeLessDiscountAmount() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                NON_WEEKEND_AND_NON_HOLIDAY,
                1,
                100);

        BigDecimal actual = agreement.getFinalCharge();

        assertTrue(BigDecimal.ZERO.subtract(actual).compareTo(BigDecimal.valueOf(0.01)) < 0,
                "actual is not within $0.01 of expected");
    }


    @Test
    void dueDateShouldReturnCheckoutDatePlusRentalDays() {
        RentalAgreement agreement = new RentalAgreement();
        agreement.setCheckoutDate(Instant.parse("2015-09-03T00:00:00Z"));
        agreement.setRentalDays(1);

        Instant actual = agreement.getDueDate();

        assertEquals(Instant.parse("2015-09-04T00:00:00Z"), actual);
    }




    @Test
    void preDiscountChargeIsChargeDaysTimesDailyCharge() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                NON_WEEKEND_AND_NON_HOLIDAY,
                1,
                0);

        BigDecimal actual = agreement.getPreDiscountCharge();

        assertEquals(BigDecimal.valueOf(1*ToolType.JACKHAMMER.getDailyCharge()), actual);
    }




    @Test
    void discountAmountIsDiscountPercentTimesPreDiscountCharge() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                NON_WEEKEND_AND_NON_HOLIDAY,
                1,
                100);

        BigDecimal actual = agreement.getDiscountAmount();

        assertEquals(BigDecimal.valueOf(2.99), actual);
    }

    @Test
    void toStringCorrectlyFormatsTest2() {
        RentalAgreement agreement = new RentalAgreement(Tool.LADW,
                Instant.parse("2020-07-02T00:00:00Z"),
                3,
                10);

        String expected =
                "Tool code: LADW\n"
                .concat("Tool type: Ladder\n")
                .concat("Tool brand: Werner\n")
                .concat("Rental days: 3\n")
                .concat("Check out date: 07/02/20\n")
                .concat("Due date: 07/05/20\n")
                .concat("Daily rental charge: $1.99\n")
                .concat("Charge days: 2\n")
                .concat("Pre-discount charge: $3.98\n")
                .concat("Discount percent: 10%\n")
                .concat("Discount amount: $0.40\n")
                .concat("Final charge: $3.58\n");

        String actual = agreement.toString();

        assertEquals(expected, actual);
    }
}