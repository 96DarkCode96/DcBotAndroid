package me.darkcode.dcbotandroid.services;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GetSelfService {
	@Headers({
			"User-Agent: DiscordBot (https://github.com/DV8FromTheWorld/JDA, 14564578)"
	})
	@GET("users/@me")
	Call<JsonObject> verifyToken(@Header("Authorization") String auth);
}