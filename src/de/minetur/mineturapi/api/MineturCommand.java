package de.minetur.mineturapi.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineturCommand {

	public static boolean isConsole(CommandSender sender) {
		if (!(sender instanceof Player)) {
			return true;
		}
		return false;
	}

	public static boolean hasPerm(MineturPlayer mp, String perm) {
		if (!mp.hasPermission(perm)) {
			return true;
		}
		return false;
	}
	
	public static boolean hasPerm(Player p, String perm, String perm2) {
		if (!p.hasPermission(perm) || (!p.hasPermission(perm2))) {
			return true;
		}
		return false;
	}

}
