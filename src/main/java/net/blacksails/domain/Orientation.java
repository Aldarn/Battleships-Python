package net.blacksails.domain;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
	NORTH("N"),
	SOUTH("S"),
	EAST("E"),
	WEST("W");
	
	private String token;
	
	public static Map<String, Orientation> LOOKUP = new HashMap<>();
	static {
		LOOKUP.put("N", NORTH);
		LOOKUP.put("S", SOUTH);
		LOOKUP.put("E", EAST);
		LOOKUP.put("W", WEST);
	}
	
	Orientation(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
