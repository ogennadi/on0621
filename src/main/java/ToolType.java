import java.time.*;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;

public enum ToolType {
    LADDER("Ladder", 1.99, true, false),
    CHAINSAW("Chainsaw", 1.49, false, true),
    JACKHAMMER("Jackhammer", 2.99, false, false);

    private final String label;
    private final Double dailyCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    ToolType(String label, Double dailyCharge, boolean weekendCharge, boolean holidayCharge) {
        this.label = label;
        this.dailyCharge = dailyCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public int getChargeDays(Instant checkoutDate, int rentalDays) {
        int chargeDays = rentalDays;

        Instant currentDay;

        for (int i = 1; i <= rentalDays; i++) {
            currentDay = checkoutDate.plus(Duration.ofDays(i));

            if (shouldNotChargeForWeekend(currentDay) || shouldNotChargeForHoliday(currentDay)) {
                chargeDays = chargeDays - 1;
            }
        }

        return chargeDays;
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }

    private boolean shouldNotChargeForHoliday(Instant currentDay) {
        return (isLaborDay(currentDay) || isIndependenceDay(currentDay)) && !holidayCharge;
    }

    private boolean shouldNotChargeForWeekend(Instant currentDay) {
        return isAmericanWeekend(currentDay) && !weekendCharge;
    }

    private boolean isIndependenceDay(Instant i) {
        ZonedDateTime date = i.atZone(ZoneId.of(PointOfSale.TIME_ZONE));
        int dayOfMonth = date.getDayOfMonth();
        Month month = date.getMonth();
        List<DayOfWeek> weekdays = Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

        return month.equals(Month.JULY) && (
                dayOfMonth == 4 && weekdays.contains(date.getDayOfWeek()) ||
                dayOfMonth == 5 && date.getDayOfWeek().equals(MONDAY) ||
                dayOfMonth == 3 && date.getDayOfWeek().equals(FRIDAY)
        );
    }

    private boolean isLaborDay(Instant i) {
        ZonedDateTime dateAtZone = i.atZone(ZoneId.of(PointOfSale.TIME_ZONE));
        DayOfWeek currentDayOfWeek = dateAtZone.getDayOfWeek();
        int dayOfMonth = dateAtZone.getDayOfMonth();
        Month month = dateAtZone.getMonth();

        return currentDayOfWeek.equals(MONDAY) &&
                dayOfMonth <= 7 &&
                month.equals(Month.SEPTEMBER);
    }

    private boolean isAmericanWeekend(Instant i) {
        List<DayOfWeek> weekend = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        DayOfWeek currentDayOfWeek = i.atZone(ZoneId.of(PointOfSale.TIME_ZONE)).getDayOfWeek();

        return weekend.contains(currentDayOfWeek);
    }

    @Override
    public String toString() {
        return label;
    }
}
