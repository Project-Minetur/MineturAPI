package de.minetur.mineturapi.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minetur.mineturapi.Data;

public class MineturCommand {

	public static void playerCheck(CommandSender sender) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Data.mustBePlayer);
		}
	}

	public static void checkPerm(Player p, String perm) {
		if (!p.hasPermission(perm)) {
			p.sendMessage(Data.noPerm);
		}
	}
	
	public static void checkPerm(Player p, String perm, String perm2) {
		if (!p.hasPermission(perm) || (!p.hasPermission(perm2))) {
			p.sendMessage(Data.noPerm);
		}
	}

}
