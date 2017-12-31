package de.minetur.mineturapi.enums;

import org.bukkit.ChatColor;

public enum MiniGame {

	BedWars("BW", ChatColor.AQUA, "§b§lBedWars§8» "), KnockbackFFA("KBFFA", ChatColor.GREEN, "§a§lKnockbackFFA§8» "), SkyWars("SW", ChatColor.BLUE, "§9§lSkyWars§8» ");
	
	String abbreviation = "";
	ChatColor color;
	String prefix;

	MiniGame(String abbreviation, ChatColor color, String prefix) {
		this.prefix = prefix;
		this.abbreviation = abbreviation;
		this.color = color;
	}
}
