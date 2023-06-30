package me.darkcode.dcbotandroid.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.R;
import me.darkcode.dcbotandroid.utils.RequestHelper;

public class AddBotToAppActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bot_to_app);
		EditText name = findViewById(R.id.name);
		TextView nameVisual = findViewById(R.id.adding_bot_include).findViewById(R.id.bot_card_bot_name);
		nameVisual.setText(name.getHint());
		EditText token = findViewById(R.id.token);
		name.setOnKeyListener((v, keyCode, event) -> {
			if(name.getText().toString().isEmpty()){
				nameVisual.setText(name.getHint());
			}else{
				nameVisual.setText(name.getText());
			}
			return false;
		});
		findViewById(R.id.activity_main_fab_create_bot).setOnClickListener(v -> {
			if(name.getText().toString().isEmpty()){
				Toast.makeText(AddBotToAppActivity.this, "Name cannot be empty!", Toast.LENGTH_LONG).show();
				return;
			}

			if(token.getText().toString().isEmpty()){
				Toast.makeText(AddBotToAppActivity.this, "Token cannot be empty!", Toast.LENGTH_LONG).show();
				return;
			}

			SharedPreferences prefs = AddBotToAppActivity.this.getSharedPreferences("me.darkcode.dcbotandroid", Context.MODE_PRIVATE);
			JsonArray array = RequestHelper.GSON.fromJson(prefs.getString("bots", RequestHelper.GSON.toJson(new JsonArray())), JsonArray.class);
			JsonObject o = new JsonObject();
			o.addProperty("name", name.getText().toString());
			o.addProperty("token", token.getText().toString());
			array.add(o);

			prefs.edit().putString("bots", RequestHelper.GSON.toJson(array)).commit();
			finish();
		});
	}

}