package me.darkcode.dcbotandroid.object.rest;

import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.Bot;

import java.util.function.Consumer;
import java.util.function.Function;

public interface RestAction<T>{

	public <S> RestAction<S> map(Function<T, S> mapFunction);

	public Bot getBot();

	default void queue() {
		queue(null);
	}

	default void queue(@Nullable Consumer<? super T> success){
		queue(success, null);
	}

	void queue(@Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure);

	default T complete()
	{
		return complete(true);
	}

	T complete(boolean shouldQueue);

}