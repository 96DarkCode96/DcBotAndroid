package me.darkcode.dcbotandroid.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.darkcode.dcbotandroid.R;
import me.darkcode.dcbotandroid.adapters.GuildSelectorAdapter;
import me.darkcode.dcbotandroid.object.Bot;
import me.darkcode.dcbotandroid.object.Guild;

import java.util.List;
import java.util.function.Consumer;

public class BotActivity extends AppCompatActivity {

	public static Bot bot;
	private static GuildSelectorAdapter guildAdapter = new GuildSelectorAdapter();
	public static long lastUpdate = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bot);
		RecyclerView guildRecycler = findViewById(R.id.activity_bot_guilds);
		guildRecycler.setLayoutManager(new LinearLayoutManager(this));
		guildRecycler.setAdapter(guildAdapter);
		updateGuilds();
	}

	public void updateGuilds(){
		if(System.currentTimeMillis() - lastUpdate <= 300_000){
			return;
		}
		lastUpdate = System.currentTimeMillis();
		guildAdapter.localDataSet.clear();
		new Handler(Looper.getMainLooper()).post(() -> guildAdapter.notifyDataSetChanged());
		bot.getGuilds().queue(guilds -> {
			guildAdapter.localDataSet = guilds;
			new Handler(Looper.getMainLooper()).post(() -> guildAdapter.notifyDataSetChanged());
		}, (f) -> {
			guildAdapter.localDataSet.clear();
			new Handler(Looper.getMainLooper()).post(() -> guildAdapter.notifyDataSetChanged());
			Log.e("DISCORD", "Error", f);
		});
	}

}