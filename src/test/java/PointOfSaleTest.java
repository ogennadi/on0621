import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PointOfSaleTest {


    @Test
    void checkoutShouldThrowOnTest1() {
        assertThrows(PercentOutOfBoundsException.class, () -> new PointOfSale().checkout("JAKR", 5, 101, "09/03/15"));
    }

    @Test
    void checkoutShouldNotThrowOnTest2() {
        assertDoesNotThrow( () -> new PointOfSale().checkout("LADW", 3, 10, "07/02/20"));
    }
}