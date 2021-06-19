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
    void chargeDaysIsRentalDaysLessFourthOfJuly() {
        Instant dayBeforeFourthOfJuly = Instant.parse("2022-07-03T00:00:00Z");

        int actual = ToolType.JACKHAMMER.getChargeDays(dayBeforeFourthOfJuly, 1);

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