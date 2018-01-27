package be.ds.projects.botTrader.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class DateTimeUtil {

    public static LocalDateTime unixTimestampToLocalDateTime(final Long unixTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp), TimeZone.getDefault().toZoneId());
    }

    public static Long localDateTimeToUnixTimestamp(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

}
