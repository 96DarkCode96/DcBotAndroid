package me.darkcode.dcbotandroid.object.permission;

import me.darkcode.dcbotandroid.object.channel.GuildChannel;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import me.darkcode.dcbotandroid.object.users.Role;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PermissionOverrideImpl implements PermissionOverride {
	private final GuildChannel channel;
	private final IPermissionHolder holder;
	private final List<Permission> allowed,denied;

	public PermissionOverrideImpl(GuildChannel channel, IPermissionHolder holder, List<Permission> allowed, List<Permission> denied) {
		this.channel = channel;
		this.holder = holder;
		this.allowed = allowed;
		this.denied = denied;
	}

	public PermissionOverrideImpl(GuildChannel channel, IPermissionHolder holder) {
		this.channel = channel;
		this.holder = holder;
		this.allowed = new ArrayList<>();
		this.denied = new ArrayList<>();
	}

	@NotNull
	@Override
	public GuildChannel getChannel() {
		return channel;
	}

	@NotNull
	@Override
	public IPermissionHolder getHolder() {
		return holder;
	}

	@NotNull
	@Override
	public PermissionOverrideType getType() {
		return holder instanceof Role ? PermissionOverrideType.ROLE : PermissionOverrideType.MEMBER;
	}

	@NotNull
	@Override
	public List<Permission> getAllowed() {
		return allowed;
	}

	@NotNull
	@Override
	public List<Permission> getDenied() {
		return denied;
	}
}
