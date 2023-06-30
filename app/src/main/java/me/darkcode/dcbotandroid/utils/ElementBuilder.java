package me.darkcode.dcbotandroid.utils;

import android.speech.tts.Voice;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.darkcode.dcbotandroid.object.Bot;
import me.darkcode.dcbotandroid.object.GuildImpl;
import me.darkcode.dcbotandroid.object.channel.*;
import me.darkcode.dcbotandroid.object.channel.interfaces.IPermissionContainer;
import me.darkcode.dcbotandroid.object.permission.Permission;
import me.darkcode.dcbotandroid.object.permission.PermissionOverride;
import me.darkcode.dcbotandroid.object.permission.PermissionOverrideImpl;
import me.darkcode.dcbotandroid.object.permission.PermissionOverrideType;
import me.darkcode.dcbotandroid.object.users.Member;
import me.darkcode.dcbotandroid.object.users.Role;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ElementBuilder {

	private final Bot bot;

	public ElementBuilder(Bot bot) {
		this.bot = bot;
	}

	public GuildImpl createGuild(long guildId, JsonObject data) {
		GuildImpl guild = new GuildImpl(guildId, bot);
		data.remove("id");
		data.remove("emojis");//TODO REMOVE
		setIfNotnull(guild, "setDescription", data, "description", JsonElement::getAsString, String.class);
		setIfNotnull(guild, "setName", data, "name", JsonElement::getAsString, String.class);
		setIfNotnull(guild, "setIconId", data, "icon", JsonElement::getAsString, String.class);
		setIfNotnull(guild, "setOwnerId", data, "owner_id", JsonElement::getAsLong, long.class);
		setIfNotnull(guild, "setSplashId", data, "splash", JsonElement::getAsString, String.class);
		setIfNotnull(guild, "setBannerId", data, "banner", JsonElement::getAsString, String.class);
		setIfNotnull(guild, "setVanityCode", data, "vanity_url_code", JsonElement::getAsString, String.class);
		setIfNotnull(guild, "setRegion", data, "region", JsonElement::getAsString, String.class);

		try{
			JsonArray array = data.remove("roles").getAsJsonArray();
			for (JsonElement jsonElement : array) {
				createRole(guild, (JsonObject)jsonElement);
			}
		}catch(Throwable e){
			Log.e("DISCORD", "Failed to parse guild's roles!", e);
		}

		try{
			JsonArray array = data.remove("members").getAsJsonArray();
			for (JsonElement jsonElement : array) {
				createMember(guild, (JsonObject)jsonElement);
			}
		}catch(Throwable e){
			Log.e("DISCORD", "Failed to parse guild's members!", e);
		}

		try {
			JsonArray channels = data.remove("channels").getAsJsonArray();
			for (JsonElement channel : channels) {
				JsonObject object = (JsonObject) channel;
				object.remove("guild_id");
				ChannelType type = ChannelType.getChannelType(object.remove("type").getAsInt());
				createGuildChannel(guild, type, object);
			}
		} catch (Throwable e) {
			Log.e("DISCORD", "Error", e);
		}

		Log.d("DISCORD", RequestHelper.GSON.toJson(data));
		return guild;
	}

	private Member createMember(GuildImpl guild, JsonObject jsonElement) {
		Log.d("DISCORD-MEMBER", RequestHelper.GSON.toJson(jsonElement));
		return null;
	}

	private Role createRole(GuildImpl guild, JsonObject object) {
		Role role = new Role(guild, object.remove("id").getAsLong());
		setIfNotnull(role, "setName", object, "name", JsonElement::getAsString, String.class);
		setIfNotnull(role, "setColor", object, "color", JsonElement::getAsInt, int.class);
		setIfNotnull(role, "setHoist", object, "hoist", JsonElement::getAsBoolean, boolean.class);
		setIfNotnull(role, "setManaged", object, "managed", JsonElement::getAsBoolean, boolean.class);
		setIfNotnull(role, "setMentionable", object, "mentionable", JsonElement::getAsBoolean, boolean.class);
		setIfNotnull(role, "setPosition", object, "position", JsonElement::getAsInt, int.class);
		setIfNotnull(role, "setPermissions", object, "permissions", JsonElement::getAsString, String.class);
		setIfNotnull(role, "setTags", object, "tags", JsonElement::getAsJsonObject, JsonObject.class);
		object.remove("flags");

		guild.getRoleManager().updateRole(role);

		return role;

		//TODO POSSIBLE ->
		/*
				icon?       	?string	role icon hash
				unicode_emoji?	?string	role unicode emoji
		 */
	}

	private void createGuildChannel(GuildImpl guild, ChannelType type, JsonObject data) {
		switch (type) {
			case GUILD_TEXT:
				createGuildTextChannel(guild, data);
				break;
			case GUILD_CATEGORY:
				createGuildCategoryChannel(guild, data);
				break;
			case GUILD_VOICE:
				createGuildVoiceChannel(guild, data);
				break;
		}
	}

	public VoiceChannelImpl createGuildVoiceChannel(GuildImpl guild, JsonObject data) {
		long id = data.remove("id").getAsLong();
		data.remove("flags");
		VoiceChannelImpl channel = (VoiceChannelImpl) guild.getVoiceChannelsView().get(id);
		if(channel == null){
			channel = new VoiceChannelImpl(id, data.remove("name").getAsString(), guild);
			guild.getVoiceChannelsView().put(id, channel);
		}
		setIfNotnull(channel, "setLatestMsgId", data, "last_message_id", JsonElement::getAsLong, long.class);
		setIfNotnull(channel, "setBitRate", data, "bitrate", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setUserLimit", data, "user_limit", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setRateLimitPerUser", data, "rate_limit_per_user", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setParentCategoryId", data, "parent_id", JsonElement::getAsLong, long.class);
		setIfNotnull(channel, "setPositionRaw", data, "position", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setNsfw", data, "nsfw", JsonElement::getAsBoolean, boolean.class);
		setIfNotnull(channel, "setIconEmoji", data, "icon_emoji", JsonElement::getAsJsonObject, JsonObject.class);

		try{
			JsonArray array = data.remove("permission_overwrites").getAsJsonArray();
			for (JsonElement jsonElement : array) {
				createPermissionOverride(channel, (JsonObject)jsonElement);
			}
		}catch(Throwable e){
			Log.e("DISCORD", "Failed to parse permission overwrites!", e);
		}

		return channel;
	}

	public TextChannelImpl createGuildTextChannel(GuildImpl guild, JsonObject data) {
		long id = data.remove("id").getAsLong();
		data.remove("flags");
		TextChannelImpl channel = (TextChannelImpl) guild.getTextChannelsView().get(id);
		if(channel == null){
			channel = new TextChannelImpl(id, data.remove("name").getAsString(), guild);
			guild.getTextChannelsView().put(id, channel);
		}
		setIfNotnull(channel, "setLatestMsgId", data, "last_message_id", JsonElement::getAsLong, long.class);
		setIfNotnull(channel, "setParentCategoryId", data, "parent_id", JsonElement::getAsLong, long.class);
		setIfNotnull(channel, "setPositionRaw", data, "position", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setRateLimitPerUser", data, "rate_limit_per_user", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setNsfw", data, "nsfw", JsonElement::getAsBoolean, boolean.class);
		setIfNotnull(channel, "setIconEmoji", data, "icon_emoji", JsonElement::getAsJsonObject, JsonObject.class);
		setIfNotnull(channel, "setDefault_thread_rate_limit_per_user", data, "default_thread_rate_limit_per_user", JsonElement::getAsInt, int.class);

		try{
			JsonArray array = data.remove("permission_overwrites").getAsJsonArray();
			for (JsonElement jsonElement : array) {
				createPermissionOverride(channel, (JsonObject)jsonElement);
			}
		}catch(Throwable e){
			Log.e("DISCORD", "Failed to parse permission overwrites!", e);
		}

		return channel;
	}

	public CategoryImpl createGuildCategoryChannel(GuildImpl guild, JsonObject data) {
		long id = data.remove("id").getAsLong();
		data.remove("flags");
		data.remove("icon_emoji");
		CategoryImpl channel = (CategoryImpl) guild.getCategoryChannelsView().get(id);
		if(channel == null){
			channel = new CategoryImpl(id, data.remove("name").getAsString(), guild);
			guild.getCategoryChannelsView().put(id, channel);
		}
		setIfNotnull(channel, "setPositionRaw", data, "position", JsonElement::getAsInt, int.class);
		setIfNotnull(channel, "setIconEmoji", data, "icon_emoji", JsonElement::getAsJsonObject, JsonObject.class);

		try{
			JsonArray array = data.remove("permission_overwrites").getAsJsonArray();
			for (JsonElement jsonElement : array) {
				createPermissionOverride(channel, (JsonObject)jsonElement);
			}
		}catch(Throwable e){
			Log.e("DISCORD", "Failed to parse permission overwrites!", e);
		}

		return channel;
	}

	private PermissionOverride createPermissionOverride(IPermissionContainer channel, JsonObject jsonElement) {
		final long id = jsonElement.remove("id").getAsLong();
		final PermissionOverrideType type = PermissionOverrideType.get(jsonElement.remove("type").getAsInt());
		switch (type) {
			case ROLE:
				Role role = channel.getGuild().getRoleById(id);
				if(role == null){
					throw new IllegalArgumentException("Role (Permission holder = " + id + ") not found in guild " + channel.getGuild().getId());
				}
				PermissionOverride override = new PermissionOverrideImpl(channel, role,
				                                                         Permission.fromBinary(jsonElement.remove("allow").getAsString()),
				                                                         Permission.fromBinary(jsonElement.remove("deny").getAsString()));
				channel.addPermissionOverride(override);
				return override;
			case MEMBER:
				Log.d("DISCORD-PERMISSION", "Skipping permission override for member! Id='" + id + "'");
				return null;
			default:
				throw new IllegalArgumentException("Unknown type of permission holder!");
		}
	}

	private <T> void setIfNotnull(Object object, String methodName, JsonObject dataInput, String dataKey, Function<JsonElement, T> function, Class<T> clazz) {
		if (!dataInput.has(dataKey) || dataInput.get(dataKey).isJsonNull()) {
			return;
		}
		try {
			Method method = object.getClass()
					.getDeclaredMethod(methodName, clazz);
			method.setAccessible(true);
			method.invoke(object, function.apply(dataInput.remove(dataKey)));
			method.setAccessible(false);
		} catch (NoSuchMethodException | InvocationTargetException |
		         IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}