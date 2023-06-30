package me.darkcode.dcbotandroid.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.R;
import me.darkcode.dcbotandroid.adapters.BotSelectorAdapter;
import me.darkcode.dcbotandroid.utils.RequestHelper;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

	private final BotSelectorAdapter adapter = new BotSelectorAdapter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RecyclerView view = findViewById(R.id.bot_selector_recycler_view);
		view.setLayoutManager(new LinearLayoutManager(this));
		view.setAdapter(adapter);
		FloatingActionButton fab = findViewById(R.id.activity_main_fab_create_bot);
		fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddBotToAppActivity.class)));
		load();
	}

	@Override
	protected void onResume() {
		super.onResume();
		load();
	}

	private void load() {
		adapter.localDataSet.clear();
		SharedPreferences prefs = MainActivity.this.getSharedPreferences("me.darkcode.dcbotandroid", Context.MODE_PRIVATE);
		JsonArray array = RequestHelper.GSON.fromJson(prefs.getString("bots", RequestHelper.GSON.toJson(new JsonArray())), JsonArray.class);
		for (JsonElement jsonElement : array) {
			JsonObject o = (JsonObject) jsonElement;
			adapter.localDataSet.add(new BotSelectorAdapter.BotSelectorView(o));
		}
		adapter.notifyDataSetChanged();
	}
}