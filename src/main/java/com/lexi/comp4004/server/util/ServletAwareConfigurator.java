package com.lexi.comp4004.server.util;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import com.lexi.comp4004.common.game.util.Config.Key;

public class ServletAwareConfigurator extends ServerEndpointConfig.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		HttpServletRequest httpservletRequest = getField(request, HttpServletRequest.class);
		String token = httpservletRequest.getHeader(Key.TOKEN);
		config.getUserProperties().put(Key.TOKEN, token);
	}

	// hacking reflector to expose fields...
	@SuppressWarnings("unchecked")
	private <I, F> F getField(I instance, Class<F> fieldType) {
		try {
			for (Class<?> type = instance.getClass(); type != Object.class; type = type.getSuperclass()) {
				for (Field field : type.getDeclaredFields()) {
					if (fieldType.isAssignableFrom(field.getType())) {
						field.setAccessible(true);
						return (F) field.get(instance);
					}
				}
			}
		} catch (Exception e) {
			// Handle?
		}
		return null;
	}
}