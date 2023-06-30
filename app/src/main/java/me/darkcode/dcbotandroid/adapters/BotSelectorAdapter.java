package me.darkcode.dcbotandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.darkcode.dcbotandroid.R;
import me.darkcode.dcbotandroid.activities.LoginIntoBotActivity;
import me.darkcode.dcbotandroid.activities.MainActivity;
import me.darkcode.dcbotandroid.utils.RequestHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BotSelectorAdapter extends RecyclerView.Adapter<BotSelectorAdapter.ViewHolder> {

	public List<BotSelectorView> localDataSet = new ArrayList<>();

	@NonNull
	@NotNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_card, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
		holder.setPosition(position);
		holder.getName().setText(localDataSet.get(position).getName());
	}

	@Override
	public int getItemCount() {
		return localDataSet.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private final ImageView profile;
		private final TextView name;
		private int position;

		public ViewHolder(@NonNull @NotNull View itemView) {
			super(itemView);
			profile = itemView.findViewById(R.id.bot_card_bot_image);
			name = itemView.findViewById(R.id.bot_card_bot_name);
			itemView.setOnClickListener((v) -> {
				LoginIntoBotActivity.BOT = BotSelectorAdapter.this.localDataSet.get(position);
				itemView.getContext().startActivity(new Intent(itemView.getContext(), LoginIntoBotActivity.class));
			});
			itemView.setOnLongClickListener((v) -> {
				SharedPreferences prefs = itemView.getContext().getSharedPreferences("me.darkcode.dcbotandroid", Context.MODE_PRIVATE);
				JsonArray array = RequestHelper.GSON.fromJson(prefs.getString("bots", RequestHelper.GSON.toJson(new JsonArray())), JsonArray.class);
				array.remove(position);
				prefs.edit().putString("bots", RequestHelper.GSON.toJson(array)).commit();

				Toast.makeText(itemView.getContext(), "Removed bot!", Toast.LENGTH_LONG).show();
				BotSelectorAdapter.this.localDataSet.remove(position);
				BotSelectorAdapter.this.notifyDataSetChanged();
				return false;
			});
		}

		public ImageView getProfile() {
			return profile;
		}

		public TextView getName() {
			return name;
		}

		public void setPosition(int position) {
			this.position = position;
		}
	}

	public static class BotSelectorView {
		private final String name;
		private final String token;

		public BotSelectorView(JsonObject o) {
			this.name = o.getAsJsonPrimitive("name").getAsString();
			this.token = o.getAsJsonPrimitive("token").getAsString();
		}

		public String getToken() {
			return token;
		}

		public String getName() {
			return name;
		}
	}
}
