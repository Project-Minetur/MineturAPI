package de.minetur.mineturapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.minetur.mineturapi.api.MineturPlayer;
import de.minetur.mineturapi.api.MineturServer;

public class InfoCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("info")){
			MineturPlayer mp = MineturServer.getPlayer(sender.getName());
			mp.sendMessage("");
		}
		return false;
	}

}
