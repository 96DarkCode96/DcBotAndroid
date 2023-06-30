package me.darkcode.dcbotandroid.object.channel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import me.darkcode.dcbotandroid.object.permission.PermissionOverride;
import me.darkcode.dcbotandroid.object.rest.RestAction;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryImpl implements Category {

	private String name;
	private final Guild guild;
	private final long id;
	private int positionRaw;
	private final HashMap<IPermissionHolder, PermissionOverride> permissionOverrides = new HashMap<>();
	private String iconEmoji;

	public CategoryImpl(long id, String name, Guild guild) {
		this.guild = guild;
		this.name = name;
		this.id = id;
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
		return ChannelType.GUILD_CATEGORY;
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
	public int getPositionRaw() {
		return positionRaw;
	}

	@Override
	public long getIdLong() {
		return id;
	}

	public void setPositionRaw(int positionRaw) {
		this.positionRaw = positionRaw;
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

	public void setIconEmoji(String iconEmoji) {
		this.iconEmoji = iconEmoji;
	}

	public void setIconEmoji(JsonObject object) {
		this.iconEmoji = object.remove("name").getAsString();
	}
}
