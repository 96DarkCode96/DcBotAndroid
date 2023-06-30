package me.darkcode.dcbotandroid.object;

import java.util.Iterator;

public interface ClosableIterator<T> extends Iterator<T>, AutoCloseable {

	@Override
	void close();
}
