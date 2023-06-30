package me.darkcode.dcbotandroid.object.channel;

import me.darkcode.dcbotandroid.object.channel.interfaces.ICopyableChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPositionableChannel;

public interface Category extends GuildChannel, ICopyableChannel, IPositionableChannel,
		IPermissionContainer/*, IMemberContainer*/{
}
