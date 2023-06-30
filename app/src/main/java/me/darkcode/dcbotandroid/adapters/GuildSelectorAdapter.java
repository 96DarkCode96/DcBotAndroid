package me.darkcode.dcbotandroid.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.darkcode.dcbotandroid.R;
import me.darkcode.dcbotandroid.object.Guild;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GuildSelectorAdapter extends RecyclerView.Adapter<GuildSelectorAdapter.ViewHolder> {

	public List<Guild> localDataSet = new ArrayList<>();

	@NonNull
	@NotNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guild_card, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
		holder.setPosition(position);
		holder.getName().setText(localDataSet.get(position).getName());
		String url = localDataSet.get(position).getIconUrl();
		if(url != null){
			new Thread(() ->{
				File file = new File(holder.itemView.getContext().getCacheDir(), "DiscordBOT/gIcons/");
				file.mkdirs();
				Bitmap bitmap = getBitmapCache(file, url);
				if(bitmap == null){
					Log.d("DISCORD", "Loading bitmap from WEB");
					bitmap = downloadBitmapAndCache(file, url);
				}else{
					Log.d("DISCORD", "Loaded bitmap from cache!");
				}
				if(bitmap != null){
					Bitmap finalBitmap = bitmap;
					new Handler(Looper.getMainLooper()).post(() ->
							holder.getProfile().setImageBitmap(finalBitmap));
				}
			}).start();
		}
	}

	private Bitmap getBitmapCache(File dir, String src) {
		File f = new File(dir, src.replaceFirst("https://cdn.discordapp.com/", "").replaceAll("/", "-"));
		if(!f.exists()){
			return null;
		}
		if(System.currentTimeMillis() - f.lastModified() > 86_400_000L){
			return null;
		}
		try {
			return BitmapFactory.decodeStream(Files.newInputStream(f.toPath()));
		} catch (IOException e) {
			Log.e("DISCORD-GUILD-ICON", "Failed to load icon from cache! (" + src + ")", e);
			return null;
		}
	}

	private Bitmap downloadBitmapAndCache(File dir, String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			cacheImage(dir, src, bitmap);
			return bitmap;
		} catch (Throwable e) {
			return null;
		}
	}

	private void cacheImage(File dir, String src, Bitmap bitmap) {
		File f = new File(dir, src.replaceFirst("https://cdn.discordapp.com/", "").replaceAll("/", "-"));
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		try {
			Files.write(f.toPath(), byteArray);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
				Guild guild = GuildSelectorAdapter.this.localDataSet.get(position);
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
}
