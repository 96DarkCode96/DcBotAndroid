package me.darkcode.dcbotandroid.object.channel.guild;

import org.jetbrains.annotations.Nullable;

public interface StandardGuildMessageChannel extends StandardGuildChannel, GuildMessageChannel{
	@Nullable
	String getTopic();
}
