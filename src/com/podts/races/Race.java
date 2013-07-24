package com.podts.races;

public class Race {
	
	private static Race defaultrace;
	
	public static Race getDefault() {
		return defaultrace;
	}
	
	private final String name;
	
	public String getName() {
		return name;
	}
	
	public Race(String name) {
		this.name = name;
	}
	
}
