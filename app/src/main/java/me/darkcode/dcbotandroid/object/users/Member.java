package me.darkcode.dcbotandroid.object.users;

import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.channel.interfaces.IMentionable;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.List;

public interface Member extends IMentionable, IPermissionHolder{

	String AVATAR_URL = "https://cdn.discordapp.com/guilds/%s/users/%s/avatars/%s.%s";

	@NotNull
	public User getUser();

	@NotNull
	public Guild getGuild();

	@Nullable
	OffsetDateTime getTimeJoined();

	@Nullable
	OffsetDateTime getTimeBoosted();

	boolean isBoosting();

	@Nullable
	OffsetDateTime getTimeOutEnd();

	default boolean isTimedOut()
	{
		return getTimeOutEnd() != null && getTimeOutEnd().isAfter(OffsetDateTime.now());
	}

	@Nullable
	String getNickname();

	@NotNull
	String getEffectiveName();

	@Nullable
	String getAvatarId();

	@Nullable
	default String getAvatarUrl()
	{
		String avatarId = getAvatarId();
		return avatarId == null ? null : String.format(AVATAR_URL, getGuild().getId(), getId(), avatarId, avatarId.startsWith("a_") ? "gif" : "png");
	}

	@NotNull
	default String getEffectiveAvatarUrl()
	{
		String avatarUrl = getAvatarUrl();
		return avatarUrl == null ? getUser().getEffectiveAvatarUrl() : avatarUrl;
	}

	@NotNull
	List<Role> getRoles();

	boolean isOwner();

	boolean isPending();

}
