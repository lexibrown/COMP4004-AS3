package com.lexi.comp4004.server.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class StartApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		// register root resource
		
		classes.add(Hello.class);
		
		classes.add(ConnectApplication.class);
		classes.add(DisconnectApplication.class);
		classes.add(LobbyApplication.class);
		classes.add(GameApplication.class);
		
		classes.add(DevApplication.class);
		
		return classes;
	}
}