package me.darkcode.dcbotandroid.object.channel;

import me.darkcode.dcbotandroid.object.channel.guild.StandardGuildMessageChannel;

public interface TextChannel extends StandardGuildMessageChannel {

	public boolean isNsfw();

	public int getDefaultThreadRateLimitPerUser();

}