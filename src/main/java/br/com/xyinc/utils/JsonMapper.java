package br.com.xyinc.utils;

import com.google.gson.Gson;

public class JsonMapper {

	private Gson gson;

	public JsonMapper() {
		gson = new Gson();
	}

	public <E> E fromJSON(final String json, final Class<E> clazz) {
		return gson.fromJson(json, clazz);
	}

	public <E> String toJSON(final E e) {
		return gson.toJson(e);
	}
}
