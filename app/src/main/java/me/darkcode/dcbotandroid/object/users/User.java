package me.darkcode.dcbotandroid.object.users;

import me.darkcode.dcbotandroid.object.channel.interfaces.ISnowflake;
import org.jetbrains.annotations.NotNull;

public interface User extends ISnowflake {

	@NotNull
	String getEffectiveAvatarUrl();

	@NotNull
	String getUserName();

}