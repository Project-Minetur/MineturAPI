package de.minetur.mineturapi.api;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import de.minetur.mineturapi.profile.UUIDFetcher;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MineturServer {

	public static void setMotd(String motd) {
		MinecraftServer.getServer().setMotd(motd);
		MinecraftServer.getServer().getPropertyManager().setProperty("motd", motd);
		MinecraftServer.getServer().getPropertyManager().savePropertiesFile();
	}

	public static int getPort() {
		return Bukkit.getServer().getPort();
	}
   /*
    *@Deprecated Use MineturServer.getConsole() instead.
    */
	@Deprecated
	public static ConsoleCommandSender getConsoleSender() {
		return Bukkit.getConsoleSender();
	}
	
	public static ConsoleCommandSender getConsole() {
		return Bukkit.getConsoleSender();
	}


	public static MineturPlayer getPlayer(UUID uuid) {
		return MineturPlayer.getPlayer(UUIDFetcher.getName(uuid));
	}

	public static MineturPlayer getPlayer(String name) {
		return MineturPlayer.getPlayer(name);
	}
	
	public static MineturPlayer getPlayer(Player p) {
		return new MineturPlayer(p);
	}
	
	public static MineturPlayer getPlayer(CommandSender sender) {
		return getPlayer(sender.getName());
	}
	
	
	/*
	 * @deprecated Use MineturServer.getPlayer() instead.
	 */
	@Deprecated
	public static MineturPlayer fromPlayer(Player p) {
		return new MineturPlayer(p);
	}


	public static Integer getRandomBetween(Integer low, Integer high) {
		Random r = new Random();
		return r.nextInt(high - low) + low;
	}

	public static PluginManager getPluginManager() {
		return Bukkit.getServer().getPluginManager();
	}

	/*
	 * @Deprecated This method is unreliable. Sometimes it reurns a wrong value becuase this plugin is enabeled before the CoinSystem is beeing enabeled.
	 */
	@Deprecated
	public static boolean isCoinSystemEnabled() {
		return getPluginManager().isPluginEnabled("CoinSystem");
	}

	public static MineturRank getRank(PermissionGroup group) {
		for (MineturRank rank : MineturRank.values()) {
			if (group == rank.toPermissionGroup()) {
				return rank;
			}
		}
		return MineturRank.Spieler;
	}

	public static PermissionGroup getGroup(String group) {
		return PermissionsEx.getPermissionManager().getGroup(group);
	}

}
