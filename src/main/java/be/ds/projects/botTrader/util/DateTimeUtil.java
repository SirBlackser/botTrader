package be.ds.projects.botTrader.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
public class DateTimeUtil {

    public static LocalDateTime unitTimestampToLocalDateTime(final Long unixTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp), TimeZone.getDefault().toZoneId());
    }

}
