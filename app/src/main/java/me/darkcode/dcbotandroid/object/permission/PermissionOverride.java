package me.darkcode.dcbotandroid.object.permission;

import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.channel.GuildChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PermissionOverride {

	@NotNull
	default Guild getGuild(){
		return getHolder().getGuild();
	}

	@NotNull
	public GuildChannel getChannel();

	@NotNull
	public IPermissionHolder getHolder();

	@NotNull
	public PermissionOverrideType getType();

	@NotNull
	public List<Permission> getAllowed();

	@NotNull
	public List<Permission> getDenied();

}