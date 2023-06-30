package me.darkcode.dcbotandroid.object.channel.interfaces;

import me.darkcode.dcbotandroid.object.Guild;
import org.jetbrains.annotations.NotNull;

public interface IPermissionHolder extends ISnowflake{

	@NotNull
	public Guild getGuild();
}