package me.darkcode.dcbotandroid.object.rest;

import androidx.annotation.Nullable;
import me.darkcode.dcbotandroid.object.Bot;
import me.darkcode.dcbotandroid.utils.RequestHelper;

import java.util.function.Consumer;
import java.util.function.Function;

public class MapRestAction<I, O> implements RestAction<O> {
    private final Function<? super I, ? extends O> function;
    private final RestAction<I> action;

    public MapRestAction(RestAction<I> action, Function<? super I, ? extends O> function)
    {
        this.action = action;
        this.function = function;
    }

    @Override
    public <S> RestAction<S> map(Function<O, S> mapFunction) {
        return new MapRestAction<>(this, mapFunction);
    }

    @Override
    public Bot getBot() {
        return action.getBot();
    }

    @Override
    public void queue(@Nullable Consumer<? super O> success, @Nullable Consumer<? super Throwable> failure)
    {
        RequestHelper.queue(this::complete, success, failure);
    }

    @Override
    public O complete(boolean shouldQueue) {
        return function.apply(action.complete(shouldQueue));
    }

}
