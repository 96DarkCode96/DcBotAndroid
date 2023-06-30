package me.darkcode.dcbotandroid.object.users;

import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.utils.SnowflakeCacheViewImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RoleManager {

	private final Guild guild;
	private final SnowflakeCacheViewImpl<Role> roles = new SnowflakeCacheViewImpl<>();

	public RoleManager(@NotNull Guild guild) {
		this.guild = guild;
	}

	public void updateRole(@NotNull Role role){
		roles.put(role.getIdLong(), role);
	}

	public @Nullable Role removeRole(long id){
		return roles.invalidate(id);
	}

	public @Nullable Role getRoleById(long id){
		return roles.get(id);
	}

	public @NotNull SnowflakeCacheViewImpl<Role> getRolesView() {
		return roles;
	}

	public @NotNull List<Role> getRoles(){
		return roles.sorted();
	}

	public @NotNull Guild getGuild() {
		return guild;
	}
}