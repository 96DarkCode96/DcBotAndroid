package me.darkcode.dcbotandroid.object.users;

import org.jetbrains.annotations.NotNull;

public class UserImpl implements User {

	private final long id;
	private String username;
	private String globalName;
	private String displayName;

	public UserImpl(long id) {
		this.id = id;
	}

	@Override
	public long getIdLong() {
		return id;
	}

	@NotNull
	@Override
	public String getEffectiveAvatarUrl() {
		return null;
	}

	@NotNull
	@Override
	public String getUserName() {
		return null;
	}
}
