package me.darkcode.dcbotandroid.object.users;

import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.object.Guild;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionHolder;
import me.darkcode.dcbotandroid.object.permission.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Role implements IPermissionHolder {

	private final Guild guild;
	private final long id;
	private String name;
	private int color;
	private boolean hoist;              //pinned in user listing
	private int position;
	private boolean mentionable;
	private boolean managed;            //by integration
	private List<Permission> permissions = new ArrayList<>();
	private Long botId;
	private Long integrationId;
	private Long subscription_listing_id;
	private boolean premiumSubscriber;
	private boolean available_for_purchase;
	private boolean guild_connections;

	public Role(Guild guild, long id) {
		this.guild = guild;
		this.id = id;
	}

	@NotNull
	@Override
	public Guild getGuild() {
		return guild;
	}

	@Override
	public long getIdLong() {
		return id;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean isHoist() {
		return hoist;
	}

	public void setHoist(boolean hoist) {
		this.hoist = hoist;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isMentionable() {
		return mentionable;
	}

	public void setMentionable(boolean mentionable) {
		this.mentionable = mentionable;
	}

	public boolean isManaged() {
		return managed;
	}

	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	@NotNull
	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(@NotNull List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setPermissions(@NotNull String binary) {
		this.permissions = Permission.fromBinary(binary);
	}

	@NotNull
	public String getPermissionsBinary() {
		return Permission.toBinary(this.permissions);
	}

	public void setTags(JsonObject object) {
		if (object.has("bot_id")) {
			botId = object.getAsJsonPrimitive("bot_id").getAsLong();
		}
		if (object.has("integration_id")) {
			integrationId = object.getAsJsonPrimitive("integration_id")
					.getAsLong();
		}
		if (object.has("subscription_listing_id")) {
			subscription_listing_id = object.getAsJsonPrimitive("subscription_listing_id")
					.getAsLong();
		}
		premiumSubscriber = object.has("premium_subscriber");
		available_for_purchase = object.has("available_for_purchase");
		guild_connections = object.has("guild_connections");
	}

	public @Nullable Long getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(@Nullable Long integrationId) {
		this.integrationId = integrationId;
	}

	public @Nullable Long getBotId() {
		return botId;
	}

	public void setBotId(@Nullable Long botId) {
		this.botId = botId;
	}

	public boolean isPremiumSubscriber() {
		return premiumSubscriber;
	}

	public void setPremiumSubscriber(boolean premiumSubscriber) {
		this.premiumSubscriber = premiumSubscriber;
	}

	public @Nullable Long getSubscription_listing_id() {
		return subscription_listing_id;
	}

	public void setSubscription_listing_id(@Nullable Long subscription_listing_id) {
		this.subscription_listing_id = subscription_listing_id;
	}

	public boolean isAvailable_for_purchase() {
		return available_for_purchase;
	}

	public void setAvailable_for_purchase(boolean available_for_purchase) {
		this.available_for_purchase = available_for_purchase;
	}

	public boolean isGuild_connections() {
		return guild_connections;
	}

	public void setGuild_connections(boolean guild_connections) {
		this.guild_connections = guild_connections;
	}
}
