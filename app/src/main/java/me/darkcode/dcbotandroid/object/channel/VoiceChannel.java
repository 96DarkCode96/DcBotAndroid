package me.darkcode.dcbotandroid.object.channel;

import me.darkcode.dcbotandroid.object.channel.interfaces.ICategorizableChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPositionableChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IRateLimited;

public interface VoiceChannel extends GuildChannel, IPositionableChannel, IPermissionContainer, ICategorizableChannel, IRateLimited {

	public int getBitRate();

	public int getUserLimit();

	public boolean isNsfw();

	public long getLatestMsgIdLong();

}