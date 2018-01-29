package be.ds.projects.botTrader.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class DateTimeUtil {

    public static Date unixTimestampToDate(final Long unixTimestamp) {
        return new Date(unixTimestamp * 1000);
    }

    public static LocalDateTime unixTimestampToLocalDateTime(final Long unixTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp * 1000), TimeZone.getDefault().toZoneId());
    }

    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Long localDateTimeToUnixTimestamp(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

}
