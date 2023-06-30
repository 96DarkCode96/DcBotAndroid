package me.darkcode.dcbotandroid.object.rest;

public class MValue<T> {

	private final Class<T> parameterType;
	private final T value;

	public MValue(Class<T> parameterType, T value) {
		this.parameterType = parameterType;
		this.value = value;
	}

	public Class<T> getParameterType() {
		return parameterType;
	}

	public T getValue() {
		return value;
	}
}