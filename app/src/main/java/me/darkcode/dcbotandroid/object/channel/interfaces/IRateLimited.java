package me.darkcode.dcbotandroid.object.channel.interfaces;

import me.darkcode.dcbotandroid.object.channel.GuildChannel;

public interface IRateLimited extends GuildChannel {

	public int getRateLimitPerUser();

}