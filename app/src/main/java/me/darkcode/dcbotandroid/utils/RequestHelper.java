package me.darkcode.dcbotandroid.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.darkcode.dcbotandroid.services.GetGuildsService;
import me.darkcode.dcbotandroid.services.GetSelfService;
import me.darkcode.dcbotandroid.services.guilds.GuildService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RequestHelper {

	public static final Gson GSON = new GsonBuilder().create();

	private static final ExecutorService executor = Executors.newSingleThreadExecutor();
	public static final GetSelfService GET_SELF;
	public static final GetGuildsService GET_GUILDS;
	public static final GuildService GET_GUILD;

	static{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://discord.com/api/v10/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		GET_SELF = retrofit.create(GetSelfService.class);
		GET_GUILDS = retrofit.create(GetGuildsService.class);
		GET_GUILD = retrofit.create(GuildService.class);
	}

	public static <T> void queue(Supplier<T> supplier, Consumer<? super T> success, Consumer<? super Throwable> failure) {
		executor.submit(() -> {
			try {
				T t = supplier.get();
				if(success != null){
					success.accept(t);
				}
			} catch (Throwable t) {
				if(failure != null){
					failure.accept(t);
				}
			}
		});
	}
}