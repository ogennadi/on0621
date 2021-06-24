import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentTest {
    @Test
    void throwsIfDiscountGreaterThan100Percent() {
        assertThrows(PercentOutOfBoundsException.class, () -> new Percent(101));
    }

    @Test
    void throwsIfDiscountLessThan0Percent() {
        assertThrows(PercentOutOfBoundsException.class, () -> new Percent(-1));
    }

    @Test
    void shouldNotThrowIfDiscountWithin0To100() {
        assertDoesNotThrow(() -> new Percent(0));
    }
}