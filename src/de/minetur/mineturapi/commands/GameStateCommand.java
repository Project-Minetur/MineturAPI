package de.minetur.mineturapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minetur.mineturapi.Data;
import de.minetur.mineturapi.api.MineturCommand;
import de.minetur.mineturapi.api.MineturGameServer;
import de.minetur.mineturapi.api.MineturPlayer;
import de.minetur.mineturapi.api.MineturServer;
import de.minetur.mineturapi.enums.GameState;

public class GameStateCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gamestate")) {
			if (MineturCommand.isConsole(sender)) {
				sender.sendMessage(Data.mustBePlayer);
				return true;
			}
			MineturPlayer mp = MineturServer.getPlayer(sender.getName());
			if (args.length == 1) {
				if (mp.hasPermission("minetur.command.gamestate")) {
					try {
						MineturGameServer.setGameState(GameState.valueOf(args[0]));
					} catch (NullPointerException e) {
						mp.sendMessage(Data.Prefix + "§cDer GameState " + args[0] + " existiert nicht.");
						return true;
					}
				} else {
					mp.sendMessage(Data.noPerm);
				}
			} else {
				mp.sendMessage("§cSyntax: /gamestate <state>");
			}
		}
		return false;

	}

}
