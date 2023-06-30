package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.GuildImpl;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import me.darkcode.dcbotandroid.object.permission.PermissionOverride;
import me.darkcode.dcbotandroid.object.rest.RestAction;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class VoiceChannelImpl implements VoiceChannel {

	private final long id;
	private final GuildImpl guild;
	private String name;
	private long parentCategoryId;
	private boolean isSynced;
	private int positionRaw;
	private int userLimit;
	private int bitRate;
	private int rateLimitPerUser;
	private final HashMap<IPermissionHolder, PermissionOverride> permissionOverrides = new HashMap<>();
	private boolean nsfw;
	private String iconEmoji;
	private long latestMsgId;

	public VoiceChannelImpl(long id, String name, GuildImpl guild) {
		this.id = id;
		this.name = name;
		this.guild = guild;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	@NonNull
	@NotNull
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NonNull
	@NotNull
	@Override
	public ChannelType getType() {
		return ChannelType.GUILD_TEXT;
	}

	@NonNull
	@NotNull
	@Override
	public RestAction<Void> delete() {
		return null;
	}

	@NonNull
	@NotNull
	@Override
	public Guild getGuild() {
		return guild;
	}

	@Nullable
	@Override
	public String getIconEmoji() {
		return iconEmoji;
	}

	@Override
	public long getParentCategoryIdLong() {
		return parentCategoryId;
	}

	@Override
	public boolean isSynced() {
		return isSynced;
	}

	public void setSynced(boolean synced) {
		isSynced = synced;
	}

	@Override
	public int getPositionRaw() {
		return positionRaw;
	}

	public void setPositionRaw(int positionRaw) {
		this.positionRaw = positionRaw;
	}

	@Override
	public long getIdLong() {
		return id;
	}

	@Override
	public int getRateLimitPerUser() {
		return rateLimitPerUser;
	}

	public void setRateLimitPerUser(int rateLimitPerUser) {
		this.rateLimitPerUser = rateLimitPerUser;
	}

	@Override
	public int getBitRate() {
		return bitRate;
	}

	public void setBitRate(int bitRate) {
		this.bitRate = bitRate;
	}

	@Override
	public int getUserLimit() {
		return userLimit;
	}

	@Override
	public boolean isNsfw() {
		return nsfw;
	}

	@Override
	public long getLatestMsgIdLong() {
		return latestMsgId;
	}

	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}

	@Nullable
	@Override
	public PermissionOverride getPermissionOverride(@NotNull IPermissionHolder permissionHolder) {
		return permissionOverrides.getOrDefault(permissionHolder, null);
	}

	@NonNull
	@NotNull
	@Override
	public List<PermissionOverride> getPermissionOverrides() {
		return new ArrayList<>(permissionOverrides.values());
	}

	@Override
	public void addPermissionOverride(PermissionOverride override) {
		permissionOverrides.put(override.getHolder(), override);
	}

	public void setNsfw(boolean nsfw) {
		this.nsfw = nsfw;
	}

	public void setIconEmoji(String iconEmoji) {
		this.iconEmoji = iconEmoji;
	}

	public void setIconEmoji(JsonObject object) {
		this.iconEmoji = object.remove("name").getAsString();
	}

	public void setLatestMsgId(long latestMsgId) {
		this.latestMsgId = latestMsgId;
	}
}