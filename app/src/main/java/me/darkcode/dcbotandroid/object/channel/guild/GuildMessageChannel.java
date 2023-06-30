package me.darkcode.dcbotandroid.object.channel.guild;

import me.darkcode.dcbotandroid.object.channel.GuildChannel;
import me.darkcode.dcbotandroid.object.channel.MessageChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IRateLimited;

public interface GuildMessageChannel extends GuildChannel, MessageChannel, IRateLimited {
}
