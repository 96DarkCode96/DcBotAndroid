package me.darkcode.dcbotandroid.object;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.channel.*;
import me.darkcode.dcbotandroid.object.users.Role;
import me.darkcode.dcbotandroid.object.users.RoleManager;
import me.darkcode.dcbotandroid.utils.SnowflakeCacheViewImpl;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class GuildImpl implements Guild {

	private final long id;
	private final Bot bot;
	private final SnowflakeCacheViewImpl<TextChannel> textChannelsView = new SnowflakeCacheViewImpl<>();
	private final SnowflakeCacheViewImpl<Category> categoryChannelsView = new SnowflakeCacheViewImpl<>();
	private final SnowflakeCacheViewImpl<VoiceChannel> voiceChannelsView = new SnowflakeCacheViewImpl<>();
	private final RoleManager roleManager = new RoleManager(this);
	private String name;
	private String iconId, splashId;
	private String vanityCode;
	private String description, banner;
	private long ownerId;
	private int memberCount;
	private Member owner;
	private String region;

	public GuildImpl(long id, Bot bot) {
		this.id = id;
		this.bot = bot;
	}

	@Override
	public boolean isLoaded() {
		return false;
	}

	@Override
	public boolean unloadMember(long userId) {
		return false;
	}

	@Override
	public int getMemberCount() {
		return memberCount;
	}

	@NonNull
	@NotNull
	@Override
	public String getName() {
		return name;
	}

	@Nullable
	@Override
	public String getIconId() {
		return iconId;
	}

	@Nullable
	@Override
	public String getDescription() {
		return description;
	}

	@Nullable
	@Override
	public String getSplashId() {
		return splashId;
	}

	@Nullable
	@Override
	public String getVanityCode() {
		return vanityCode;
	}

	@Nullable
	@Override
	public String getBannerId() {
		return banner;
	}

	@Nullable
	@Override
	public Member getOwner() {
		return owner;
	}

	@Override
	public long getOwnerIdLong() {
		return ownerId;
	}

	@NonNull
	@NotNull
	@Override
	public List<GuildChannel> getChannels() {
		ArrayList<GuildChannel> channels = new ArrayList<>();
		channels.addAll(getTextChannelsView().sorted());
		channels.addAll(getCategoryChannelsView().sorted());
		channels.addAll(getVoiceChannelsView().sorted());
		return channels;
	}

	@Override
	public SnowflakeCacheViewImpl<TextChannel> getTextChannelsView() {
		return textChannelsView;
	}

	@Override
	public SnowflakeCacheViewImpl<Category> getCategoryChannelsView() {
		return categoryChannelsView;
	}

	@Override
	public SnowflakeCacheViewImpl<VoiceChannel> getVoiceChannelsView() {
		return voiceChannelsView;
	}

	@Override
	public RoleManager getRoleManager() {
		return roleManager;
	}

	@Override
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public GuildImpl setBannerId(String bannerId) {
		this.banner = bannerId;
		return this;
	}

	public GuildImpl setVanityCode(String code) {
		this.vanityCode = code;
		return this;
	}

	public GuildImpl setSplashId(String splashId) {
		this.splashId = splashId;
		return this;
	}

	public GuildImpl setDescription(String description) {
		this.description = description;
		return this;
	}

	public GuildImpl setIconId(String iconId) {
		this.iconId = iconId;
		return this;
	}

	public GuildImpl setName(String name) {
		this.name = name;
		return this;
	}

	public GuildImpl setMemberCount(int count) {
		this.memberCount = count;
		return this;
	}

	@Override
	public long getIdLong() {
		return id;
	}

	public GuildImpl setOwnerId(long ownerId) {
		this.ownerId = ownerId;
		return this;
	}

	public void onMemberAdd() {
		memberCount++;
	}

	public void onMemberRemove() {
		memberCount--;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(id);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof GuildImpl))
			return false;
		GuildImpl oGuild = (GuildImpl) o;
		return this.id == oGuild.id;
	}

	public Bot getBot() {
		return bot;
	}
}
