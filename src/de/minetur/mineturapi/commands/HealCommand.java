package de.minetur.mineturapi.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minetur.mineturapi.Data;
import de.minetur.mineturapi.api.MineturCommand;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("heal")) {
			MineturCommand.playerCheck(sender);
			Player p = (Player) sender;
			if(args.length == 1){
				MineturCommand.checkPerm(p, "minetur.command.heal.self", "minetur.command.heal.*");
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null){
					p.sendMessage(Data.notOnline);
				}else
					target.setHealth(20);
				p.sendMessage(Data.Prefix + "§7Du hast §a" + target.getName() + " §7geheilt.");
			}else if(args.length == 0){
				MineturCommand.checkPerm(p, "minetur.command.heal.others", "minetur.command.heal.*");
				p.setHealth(20);
				p.sendMessage(Data.Prefix + "§7Du hast §adich §7geheilt.");
			}else{
				p.sendMessage("§cSyntax: /heal <Spieler>");
			}

		}
		return false;
	}

}
