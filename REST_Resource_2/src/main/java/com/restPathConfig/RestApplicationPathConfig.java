package com.restPathConfig;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.resource.MessageResource;

@ApplicationPath("rest")
public class RestApplicationPathConfig extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(MessageResource.class);
		return classes;
	}
}
