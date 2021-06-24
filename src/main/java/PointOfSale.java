import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class PointOfSale {

    public static final String DATE_FORMAT = "MM/dd/yy";
    public static final String TIME_ZONE = "UTC";

    public void checkout(String toolCode, Integer rentalDayCount, Integer discountPercent, String checkoutDate) throws ParseException {
        SimpleDateFormat sdfAmerica = new SimpleDateFormat(DATE_FORMAT);
        sdfAmerica.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        Date parsed = sdfAmerica.parse(checkoutDate);

        System.out.println(new RentalAgreement(Tool.valueOf(toolCode),
                parsed.toInstant(),
                RentalDayCount.valueOf(rentalDayCount),
                Percent.valueOf(discountPercent)));
    }
}
