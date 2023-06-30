package me.darkcode.dcbotandroid.object.channel.guild;

import me.darkcode.dcbotandroid.object.channel.GuildChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.ICategorizableChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.ICopyableChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPositionableChannel;

public interface StandardGuildChannel extends GuildChannel, IPositionableChannel, IPermissionContainer, ICopyableChannel, ICategorizableChannel {
}
