import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToolTypeTest {
    @Test
    void chargeDaysIsRentalDaysIfItContainsNoHolidaysOrWeekends() {
        Instant aSunday = Instant.parse("2021-06-20T00:00:00Z");
        int actual = ToolType.CHAINSAW.getChargeDays(aSunday, 5);

        assertEquals(5, actual);
    }

    @Test
    void chargeDaysIsRentalDaysLessWeekend() {
        Instant aFriday = Instant.parse("2021-06-18T00:00:00Z");

        int actual = ToolType.CHAINSAW.getChargeDays(aFriday, 1);

        assertEquals(0, actual);
    }

    @Test
    void chargeDaysIsRentalDaysLessLaborDay() {
        Instant dayBeforeLaborDay = Instant.parse("2021-09-05T00:00:00Z");

        int actual = ToolType.LADDER.getChargeDays(dayBeforeLaborDay, 1);

        assertEquals(0, actual);
    }

    @Test
    void chargeDaysIsRentalDaysLessIndependenceSunday() {
        Instant independenceSunday = Instant.parse("2021-07-04T00:00:00Z");

        int actual = ToolType.JACKHAMMER.getChargeDays(independenceSunday, 1);

        assertEquals(0, actual);
    }

    @Test
    void chargeDaysIsRentalDaysLessIndependenceSaturday() {
        Instant thursdayBeforeIndependenceSaturday = Instant.parse("2020-07-02T00:00:00Z");

        int actual = ToolType.JACKHAMMER.getChargeDays(thursdayBeforeIndependenceSaturday, 1);

        assertEquals(0, actual);
    }

    @Test
    void chargeDaysIsRentalDaysIfRentalDayIsOnWeekendAndThereIsWeekendCharge() {
        Instant aFriday = Instant.parse("2021-06-18T00:00:00Z");

        int actual = ToolType.LADDER.getChargeDays(aFriday, 1);

        assertEquals(1, actual);
    }


    @Test
    void chargeDaysIsRentalDaysIfRentalDayIsOnHolidayAndThereIsHolidayCharge() {
        Instant dayBeforeLaborDay = Instant.parse("2021-09-05T00:00:00Z");

        int actual = ToolType.CHAINSAW.getChargeDays(dayBeforeLaborDay, 1);

        assertEquals(1, actual);
    }
}