package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;

public interface MessageChannel extends Channel{

	@NonNull
	default String getLatestMessageId()
	{
		return Long.toUnsignedString(getLatestMessageIdLong());
	}

	long getLatestMessageIdLong();

	boolean canTalk();

}