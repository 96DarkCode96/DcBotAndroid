package me.darkcode.dcbotandroid.utils;

import me.darkcode.dcbotandroid.object.channel.interfaces.ISnowflake;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class SnowflakeCacheViewImpl<T extends ISnowflake> {

	private final HashMap<Long, T> map = new HashMap<>();

	public @Nullable T put(long id, T value) {
		return map.put(id, value);
	}

	public T invalidate(long id){
		return map.remove(id);
	}

	public @Nullable T get(long id) {
		return map.get(id);
	}

	public List<T> sorted() {
		return Collections.unmodifiableList(map.values().stream().sorted(Comparator.comparingLong(ISnowflake::getIdLong)).collect(Collectors.toList()));
	}

}