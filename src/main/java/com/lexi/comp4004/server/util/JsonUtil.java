package com.lexi.comp4004.server.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lexi.comp4004.common.game.util.Config.Key;

public class JsonUtil {

	static final ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
	static final JsonFactory factory = objectMapper.getFactory();

	public static String stringify(Object object) throws Exception {
		if (object == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		JsonGenerator jgen = factory.createGenerator(sw);
		jgen.writeObject(object);
		jgen.close();
		return sw.toString();
	}

	public static <O> O parse(String json, Class<O> objectClass) throws JsonParseException, IOException {
		if (json == null) {
			return null;
		}
		JsonParser jp = factory.createParser(json);
		return jp.readValueAs(objectClass);
	}
	
	public static <O> O parseList(String json, Class<?> objectClass) throws JsonParseException, IOException {
		if (json == null) {
			return null;
		}
		return objectMapper.readValue(json,
				objectMapper.getTypeFactory().constructCollectionType(List.class, objectClass));
	}

	public static String makeMessage(String message) throws Exception {
		return makeJson(Key.MESSAGE, message);
	}

	public static String makeJson(String key, Object value) throws Exception {
		HashMap<String, Object> message = new HashMap<String, Object>();
		message.put(key, value);
		return stringify(message);
	}

	public static String makeComplexJson(String key, Object value) throws Exception {
		HashMap<String, Object> message = new HashMap<String, Object>();
		message.put(key, stringify(value));
		return stringify(message);
	}

	public static String errorJson(String message) throws Exception {
		return errorJson(null, message);
	}

	public static String errorJson(String type, String message) throws Exception {
		HashMap<String, String> error = new HashMap<String, String>();
		error.put(Key.ERROR, type);
		error.put(Key.MESSAGE, message);
		return stringify(error);
	}

	public static String fail(Exception e) {
		return "{ \"" + e.getMessage() + "\" : \"" + e.getStackTrace().toString() + "\" }";
	}

}