package me.darkcode.dcbotandroid.object;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.object.rest.MValue;
import me.darkcode.dcbotandroid.object.rest.RestAction;
import me.darkcode.dcbotandroid.object.rest.RestActionImpl;
import me.darkcode.dcbotandroid.utils.ElementBuilder;
import me.darkcode.dcbotandroid.utils.RequestHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Bot implements Serializable {

	private final String token;
	private String username;
	private String discriminator;
	private String id;
	private long verified = -1;
	private ElementBuilder elementBuilder;

	public Bot(String token) {
		this(token, null, null, null);
	}

	public Bot(String token,
			   @Nullable String lastUsername,
			   @Nullable String lastDiscriminator,
			   @Nullable String lastId) {
		this.token = token;
		this.username = lastUsername;
		this.discriminator = lastDiscriminator;
		this.id = lastId;
		this.elementBuilder = new ElementBuilder(this);
	}

	public RestAction<Void> verifyToken(){
		return new RestActionImpl<>(this, RequestHelper.GET_SELF, "verifyToken")
				.map(o -> {
					JsonObject object = (JsonObject) o;
					username = object.get("username").getAsString();
					discriminator = object.get("discriminator").getAsString();
					id = object.get("id").getAsString();
					verified = System.currentTimeMillis();
					return null;
				});
	}

	public RestAction<List<Guild>> getGuilds(){
		return new RestActionImpl<JsonArray>(this, RequestHelper.GET_GUILDS, "getGuilds")
				.map((array) -> {
					List<Guild> guilds = new ArrayList<>();
					for (JsonElement jsonElement : array) {
						try {
							JsonObject o = jsonElement.getAsJsonObject();
							JsonObject data = new RestActionImpl<JsonObject>(this, RequestHelper.GET_GUILD, "getGuild",
							                                                 new MValue<>(String.class, o.get("id").getAsString())).complete();
							data.add("channels", new RestActionImpl<JsonArray>(this, RequestHelper.GET_GUILD, "getChannels",
							                                                   new MValue<>(String.class, o.get("id").getAsString())).complete());
							data.add("members", new RestActionImpl<JsonArray>(this, RequestHelper.GET_GUILD, "getMembers",
							                                                   new MValue<>(String.class, o.get("id").getAsString())).complete());
							guilds.add(elementBuilder.createGuild(o.get("id").getAsLong(), data));
						} catch (Exception e) {
							Log.e("DISCORD", "Failed to parse guild!", e);
						}
					}
					return guilds;
				});
	}

	public String getToken() {
		return token;
	}

	public String getTag() {
		verifiedCheck();
		return username + "#" + discriminator;
	}

	public void verifiedCheck() {
		if(System.currentTimeMillis() - verified > 3_600_000L){
			AtomicInteger a = new AtomicInteger(0);
			verifyToken().queue((b) -> a.set(1), (b) -> a.set(-1));
			while(a.get()==0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			if(a.get()==-1){
				throw new RuntimeException("Cannot run tasks when token is invalid!");
			}
		}
	}
}