package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.GuildImpl;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import me.darkcode.dcbotandroid.object.permission.PermissionOverride;
import me.darkcode.dcbotandroid.object.rest.RestAction;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextChannelImpl implements TextChannel {

	private final long id;
	private final GuildImpl guild;
	private String name;
	private long parentCategoryId;
	private boolean isSynced;
	private int positionRaw;
	private long latestMsgId;
	private String topic;
	private int rateLimitPerUser;
	private final HashMap<IPermissionHolder, PermissionOverride> permissionOverrides = new HashMap<>();
	private boolean nsfw;
	private String iconEmoji;
	private int default_thread_rate_limit_per_user;

	public TextChannelImpl(long id, String name, GuildImpl guild){
		this.id = id;
		this.name = name;
		this.guild = guild;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public void setSynced(boolean synced) {
		isSynced = synced;
	}

	public void setPositionRaw(int positionRaw) {
		this.positionRaw = positionRaw;
	}

	public void setLatestMsgId(long latestMsgId) {
		this.latestMsgId = latestMsgId;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@NonNull
	@NotNull
	@Override
	public String getName() {
		return name;
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

	@Override
	public int getPositionRaw() {
		return positionRaw;
	}

	@Override
	public long getIdLong() {
		return id;
	}

	@Override
	public long getLatestMessageIdLong() {
		return latestMsgId;
	}

	@Override
	public boolean canTalk() {
		return false;//TODO
	}

	@Nullable
	@Override
	public String getTopic() {
		return topic;
	}

	@Override
	public int getRateLimitPerUser() {
		return rateLimitPerUser;
	}

	public void setRateLimitPerUser(int rateLimitPerUser) {
		this.rateLimitPerUser = rateLimitPerUser;
	}

	@androidx.annotation.Nullable
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

	@Override
	public boolean isNsfw() {
		return nsfw;
	}

	@Override
	public int getDefaultThreadRateLimitPerUser() {
		return default_thread_rate_limit_per_user;
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

	public void setDefault_thread_rate_limit_per_user(int default_thread_rate_limit_per_user) {
		this.default_thread_rate_limit_per_user = default_thread_rate_limit_per_user;
	}
}