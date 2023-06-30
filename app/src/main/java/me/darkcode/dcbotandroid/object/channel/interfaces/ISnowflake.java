package me.darkcode.dcbotandroid.object.channel.interfaces;

import androidx.annotation.NonNull;
import me.darkcode.dcbotandroid.object.channel.TimeUtil;

import java.time.OffsetDateTime;

public interface ISnowflake {

	static long parseSnowflake(String input)
	{
		if(input.isEmpty()){
			throw new IllegalArgumentException("Input cannot be empty!");
		}
		try
		{
			return parseLong(input);
		}
		catch (NumberFormatException ex)
		{
			throw new NumberFormatException(String.format("The specified ID is not a valid snowflake (%s). Expecting a valid long value!", input));
		}
	}

	static long parseLong(String input) {
		if (input.startsWith("-"))
			return Long.parseLong(input);
		else
			return Long.parseUnsignedLong(input);
	}

	default @NonNull String getId(){
        return Long.toUnsignedString(getIdLong());
    }

    long getIdLong();

    default @NonNull OffsetDateTime getTimeCreated(){
        return TimeUtil.getTimeCreated(getIdLong());
    }

}