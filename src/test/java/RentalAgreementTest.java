import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class RentalAgreementTest {

    public static final Instant NON_WEEKEND_AND_NON_HOLIDAY = Instant.parse("2021-06-14T00:00:00Z");

    @Test
    void finalChargeIsPreDiscountChargeLessDiscountAmount() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                NON_WEEKEND_AND_NON_HOLIDAY,
                RentalDayCount.valueOf(1),
                Percent.valueOf(100));

        BigDecimal actual = agreement.getFinalCharge();

        assertEquals(BigDecimal.ZERO.setScale(2), actual);
    }

    @Test
    void finalChargeIsCorrectForTest3() {
        RentalAgreement agreement = new RentalAgreement(Tool.CHNS,
                Instant.parse("2015-07-02T00:00:00Z"),
                RentalDayCount.valueOf(5),
                Percent.valueOf(25));

        BigDecimal actual = agreement.getFinalCharge();

        assertEquals(BigDecimal.valueOf(3.35), actual);
    }

    @Test
    void finalChargeIsCorrectForTest4() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKD,
                Instant.parse("2015-09-03T00:00:00Z"),
                RentalDayCount.valueOf(6),
                Percent.valueOf(0));

        BigDecimal actual = agreement.getFinalCharge();

        assertEquals(BigDecimal.valueOf(8.97), actual);
    }

    @Test
    void finalChargeIsCorrectForTest5() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                Instant.parse("2015-07-02T00:00:00Z"),
                RentalDayCount.valueOf(9),
                Percent.valueOf(0));

        BigDecimal actual = agreement.getFinalCharge();

        assertEquals(BigDecimal.valueOf(14.95), actual);
    }

    @Test
    void finalChargeIsCorrectForTest6() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                Instant.parse("2020-07-02T00:00:00Z"),
                RentalDayCount.valueOf(4),
                Percent.valueOf(50));

        BigDecimal actual = agreement.getFinalCharge();

        assertEquals(BigDecimal.valueOf(1.49), actual);
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
                RentalDayCount.valueOf(1),
                Percent.valueOf(0));

        BigDecimal actual = agreement.getPreDiscountCharge();

        assertEquals(BigDecimal.valueOf(1*ToolType.JACKHAMMER.getDailyCharge()), actual);
    }




    @Test
    void discountAmountIsDiscountPercentTimesPreDiscountCharge() {
        RentalAgreement agreement = new RentalAgreement(Tool.JAKR,
                NON_WEEKEND_AND_NON_HOLIDAY,
                RentalDayCount.valueOf(1),
                Percent.valueOf(100));

        BigDecimal actual = agreement.getDiscountAmount();

        assertEquals(BigDecimal.valueOf(2.99), actual);
    }

    @Test
    void toStringCorrectlyFormatsTest2() {
        RentalAgreement agreement = new RentalAgreement(Tool.LADW,
                Instant.parse("2020-07-02T00:00:00Z"),
                RentalDayCount.valueOf(3),
                Percent.valueOf(10));

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