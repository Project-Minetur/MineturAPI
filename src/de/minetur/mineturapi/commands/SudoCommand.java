package de.minetur.mineturapi.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import de.minetur.mineturapi.Data;
import de.minetur.mineturapi.api.MineturCommand;

public class SudoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sudo")) {
			if(MineturCommand.isConsole(sender)){
				sender.sendMessage(Data.mustBePlayer);
				return true;
			}
			Player p = (Player) sender;
			if (args.length >= 2) {
				if(p.hasPermission("minetur.command.sudo")) {
				if (!(Bukkit.getPlayer(args[0]) == null)) {
					String command = "";
					for (int i = 1; i < args.length; i++) {
						if (!(i == 1)) {
							command = command + " " + args[i];
						} else {
							command = command + args[i];
						}
					}
					Bukkit.getConsoleSender().sendMessage(
							"[Sudo] " + Bukkit.getPlayer(args[0]).getName() + " issued server command: /" + command);
					Bukkit.getPlayer(args[0]).performCommand(command);
					p.sendMessage(Data.Prefix + "§7Du hast den Command \"§a/" + command + " §7\"erfolgreich für §a"
							+ p.getName() + " §7ausgeführt.");
				} else {
					p.sendMessage(Data.notOnline);
				}
				}else{
					p.sendMessage(Data.noPerm);
				}
			} else {
				p.sendMessage("§cSyntax: /sudo <Spieler> <Command>");
			}
		}
		return false;

	}
}
