package me.darkcode.dcbotandroid.object.channel.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.channel.GuildChannel;
import me.darkcode.dcbotandroid.object.permission.PermissionOverride;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IPermissionContainer extends GuildChannel {

	@Nullable
	PermissionOverride getPermissionOverride(@NotNull IPermissionHolder permissionHolder);

	@NonNull
	List<PermissionOverride> getPermissionOverrides();

	public void addPermissionOverride(PermissionOverride override);

}