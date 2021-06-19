import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

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
}