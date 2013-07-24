package com.podts.races;

import org.bukkit.entity.Player;

public class RPlayer {
	
	private Player player;
	private Race race;
	
	public String getName() {
		return player.getName();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Race getRace() {
		return race;
	}
	
	public RPlayer(Player player) {
		this.player = player;
	}
	
}
