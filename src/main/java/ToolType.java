import java.time.*;
import java.util.Arrays;
import java.util.List;

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

    private boolean shouldNotChargeForHoliday(Instant currentDay) {
        return (isLaborDay(currentDay) || isFourthOfJuly(currentDay)) && !holidayCharge;
    }

    private boolean shouldNotChargeForWeekend(Instant currentDay) {
        return isAmericanWeekend(currentDay) && !weekendCharge;
    }

    private boolean isFourthOfJuly(Instant i) {
        ZonedDateTime dateAtZone = i.atZone(ZoneId.of("UTC"));
        int dayOfMonth = dateAtZone.getDayOfMonth();
        Month month = dateAtZone.getMonth();

        return (dayOfMonth == 4 && month.equals(Month.JULY));
    }

    private boolean isLaborDay(Instant i) {
        ZonedDateTime dateAtZone = i.atZone(ZoneId.of("UTC"));
        DayOfWeek currentDayOfWeek = dateAtZone.getDayOfWeek();
        int dayOfMonth = dateAtZone.getDayOfMonth();
        Month month = dateAtZone.getMonth();

        return currentDayOfWeek.equals(DayOfWeek.MONDAY) && dayOfMonth <= 7 && month.equals(Month.SEPTEMBER);
    }

    private boolean isAmericanWeekend(Instant i) {
        List<DayOfWeek> weekend = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        DayOfWeek currentDayOfWeek = i.atZone(ZoneId.of("UTC")).getDayOfWeek();

        return weekend.contains(currentDayOfWeek);
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }
}
