package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;
import me.darkcode.dcbotandroid.object.channel.interfaces.ISnowflake;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil
{
    public static final long DISCORD_EPOCH = 1420070400000L;
    public static final long TIMESTAMP_OFFSET = 22;
    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.RFC_1123_DATE_TIME;

    public static long getDiscordTimestamp(long millisTimestamp)
    {
        return (millisTimestamp - DISCORD_EPOCH) << TIMESTAMP_OFFSET;
    }

    @NonNull
    public static OffsetDateTime getTimeCreated(long entityId)
    {
        long timestamp = (entityId >>> TIMESTAMP_OFFSET) + DISCORD_EPOCH;
        Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmt.setTimeInMillis(timestamp);
        return OffsetDateTime.ofInstant(gmt.toInstant(), gmt.getTimeZone().toZoneId());
    }

    @NonNull
    public static OffsetDateTime getTimeCreated(@NonNull ISnowflake entity)
    {
        return getTimeCreated(entity.getIdLong());
    }

    @NonNull
    public static String getDateTimeString(@NonNull OffsetDateTime time)
    {
        return time.format(dtFormatter);
    }
}
