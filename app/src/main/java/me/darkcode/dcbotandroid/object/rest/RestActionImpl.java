package me.darkcode.dcbotandroid.object.rest;

import android.util.Log;
import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.Bot;
import me.darkcode.dcbotandroid.utils.RequestHelper;
import retrofit2.Call;
import retrofit2.Response;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class RestActionImpl<T> implements RestAction<T> {

	private final Bot bot;
	private final Object suppiler;
	private final String method;
	private final MValue<?>[] values;

	public RestActionImpl(Bot bot, Object suppiler, String method, MValue<?>...values) {
		this.bot = bot;
		this.suppiler = suppiler;
		this.method = method;
		this.values = new MValue<?>[values.length+1];
		this.values[0] = new MValue<>(String.class, "Bot " + bot.getToken());
		System.arraycopy(values, 0, this.values, 1, values.length);
	}

	@Override
	public <S> RestAction<S> map(Function<T, S> mapFunction) {
		return new MapRestAction<>(this, mapFunction);
	}

	@Override
	public Bot getBot() {
		return bot;
	}

	@Override
	public void queue(@Nullable @org.jetbrains.annotations.Nullable Consumer<? super T> success, @Nullable @org.jetbrains.annotations.Nullable Consumer<? super Throwable> failure) {
		RequestHelper.queue(this::complete, success, failure);
	}

	@Override
	public T complete(boolean shouldQueue) {
		try{
			Method mClass = suppiler.getClass().getDeclaredMethod(method, Arrays.stream(values).map(MValue::getParameterType).toArray(Class[]::new));
			Object[] o = Arrays.stream(values).map(MValue::getValue).toArray();
			Response<T> res = ((Call<T>) mClass.invoke(suppiler, o)).execute();
			if(res.code() != 200){
				Log.d("DISCORD-Res", "Response code: " + res.code(), new Throwable());
			}
			return res.body();
		}catch(Throwable e){
			Log.e("DISCORD", e.getMessage(), e);
		}
		return null;
	}
}