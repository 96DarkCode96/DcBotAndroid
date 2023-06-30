package me.darkcode.dcbotandroid.activities;

import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import me.darkcode.dcbotandroid.R;
import me.darkcode.dcbotandroid.adapters.BotSelectorAdapter;
import me.darkcode.dcbotandroid.object.Bot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginIntoBotActivity extends AppCompatActivity {

	public static BotSelectorAdapter.BotSelectorView BOT;
	private boolean started = false;
	private final ExecutorService service = Executors.newSingleThreadExecutor();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_into_bot);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if(started){
			return;
		}
		started = true;
		service.execute(() -> {
			Bot bot = new Bot(BOT.getToken());
			try {
				bot.verifyToken().complete();
				Looper.prepare();
				Intent intent = new Intent(LoginIntoBotActivity.this, BotActivity.class);
				BotActivity.bot = bot;
				BotActivity.lastUpdate = -1;
				finishAfterTransition();
				startActivity(intent);
			} catch (Exception e) {
				Looper.prepare();
				Toast.makeText(LoginIntoBotActivity.this, "Failed to verify bot!", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		service.shutdownNow();
	}

}