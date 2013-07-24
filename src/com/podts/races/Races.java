package com.podts.races;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Races extends JavaPlugin {
	
	private static Races instance;
	
	public static Races getIntstance() {
		return instance;
	}
	
	private File racesfile;
	private YamlConfiguration races;
	
	private File playerfile;
	private YamlConfiguration players;
	
	public YamlConfiguration getRacesFile() {
		return races;
	}
	
	public YamlConfiguration getPlayersFile() {
		return players;
	}
	
	private boolean loadDB() {
		try {
			racesfile = new File(getDataFolder(),"races.yml");
			if (!racesfile.exists())
				racesfile.createNewFile();
			races = YamlConfiguration.loadConfiguration(racesfile);
			if (races.getStringList("races") == null)
				races.set("races",new LinkedList<String>());
			else {
				for (String s : races.getStringList("races")) {
					new Race(s);
				}
			}
			playerfile = new File(getDataFolder(),"players.yml");
			players = YamlConfiguration.loadConfiguration(playerfile);
			if (players.getStringList("players") == null)
				players.set("players",new LinkedList<String>());
		}
		catch (Exception e) {
			getLogger().warning("Error while loading files.");
			return false;
		}
		return true;
	}
	
	private boolean saveDB() {
		try {
			races.save(racesfile);
			players.save(playerfile);
		}
		catch (IOException e) {
			getLogger().warning("Erroir while saving files.");
			return false;
		}
		return true;
	}
	
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadDB();
	}
 
	public void onDisable() {
		saveDB();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			if (((Player)sender).hasPermission("races.use"))
				return false;
		}
		
		if (cmd.getName().equals("races")) {
			
			String command;
			if (args.length > 0) {
				command = args[0];
				
				if (command.equalsIgnoreCase("new")) {
					if (args.length == 2) {
						try {
							new Race(args[1]);
						} catch (Exception e) {
							sender.sendMessage(ChatColor.RED + e.getMessage());
						}
					}
					return true;
				}
				
				if (command.equalsIgnoreCase("list")) {
					String message = "Races: ";
					for (Race r : Race.getRaces()) {
						message += r.getName() + " ";
					}
					sender.sendMessage(message);
				}
				
			}
			else
				return true;
			
		}
    	return true;
    	
    }
	
}
