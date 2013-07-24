package com.podts.races;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Race {
	
	private static Race defaultrace;
	
	private static List<Race> races = new LinkedList<Race>();
	
	public static Collection<Race> getRaces() {
		return races;
	}
	
	public static Collection<String> getRacesByName() {
		List<String> l = new LinkedList<String>();
		for (Race r : races)
			l.add(r.getName());
		return l;
	}
	
	public static Race getDefault() {
		return defaultrace;
	}
	
	private final String name;
	
	public String getName() {
		return name;
	}
	
	public Race(String name) throws Exception {
		this.name = name;
		for (Race r : getRaces()) {
			if (r.getName().equalsIgnoreCase(name)) {
				throw new Exception("There is already a race named " + name);
			}
		}
		races.add(this);
		Races.getIntstance().getRacesFile().getStringList("races").add(name);
	}
	
}
