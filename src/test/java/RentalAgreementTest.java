import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalAgreementTest {
    @Test
    void finalChargeShouldReturnXXGivenTestCase1() {
        RentalAgreement testCase1 = new RentalAgreement("JAKR",
                Instant.parse("2015-09-03T00:00:00Z"),
                5,
                101);

        BigDecimal actual = testCase1.getFinalCharge();

        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    void dueDateShouldReturnCheckoutDatePlusRentalDays() {
        RentalAgreement agreement = new RentalAgreement();
        agreement.setCheckoutDate(Instant.parse("2015-09-03T00:00:00Z"));
        agreement.setRentalDays(1);

        Instant actual = agreement.getDueDate();

        assertEquals(Instant.parse("2015-09-04T00:00:00Z"), actual);
    }
}