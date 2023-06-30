package me.darkcode.dcbotandroid.services.guilds;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GuildService {
	@Headers({
			"User-Agent: DiscordBot (https://github.com/DV8FromTheWorld/JDA, 14564578)"
	})
	@GET("guilds/{guild_id}")
	Call<JsonObject> getGuild(@Header("Authorization") String auth, @Path("guild_id") String guildId);

	@Headers({
			"User-Agent: DiscordBot (https://github.com/DV8FromTheWorld/JDA, 14564578)"
	})
	@GET("guilds/{guild_id}/channels")
	Call<JsonArray> getChannels(@Header("Authorization") String auth, @Path("guild_id") String guildId);

	@Headers({
			"User-Agent: DiscordBot (https://github.com/DV8FromTheWorld/JDA, 14564578)"
	})
	@GET("guilds/{guild_id}/members")
	Call<JsonArray> getMembers(@Header("Authorization") String auth, @Path("guild_id") String guildId);

	@Headers({
			"User-Agent: DiscordBot (https://github.com/DV8FromTheWorld/JDA, 14564578)"
	})
	@GET("guilds/{guild_id}/members/{user_id}")
	Call<JsonObject> getMember(@Header("Authorization") String auth, @Path("guild_id") String guildId, @Path("user_id") String userId);
}