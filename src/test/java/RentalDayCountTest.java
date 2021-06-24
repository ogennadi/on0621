import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalDayCountTest {
    @Test
    void shouldThrowIfValueLessThan1() {
        assertThrows(RentalDayCountOutOfBoundsException.class, () -> new RentalDayCount(0));
    }

    @Test
    void shouldNotThrowIfValueGreaterOrEqualTo1() {
        assertDoesNotThrow(() -> new RentalDayCount(1));
    }
}