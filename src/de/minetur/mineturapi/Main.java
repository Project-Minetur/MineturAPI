package de.minetur.mineturapi;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.minetur.mineturapi.api.MineturGameServer;
import de.minetur.mineturapi.api.MineturServer;
import de.minetur.mineturapi.commands.GameStateCommand;
import de.minetur.mineturapi.commands.HealCommand;
import de.minetur.mineturapi.commands.SudoCommand;
import de.minetur.mineturapi.enums.GameState;
import de.minetur.mineturapi.enums.MiniGame;

public class Main extends JavaPlugin {

	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void onEnable() {
		initializeVariables();
		registerCommands();
		registerListeners();
		MineturServer.getConsole().sendMessage("[MineturAPI] Version " + Data.version + "-spigot aktiviert.");
		MineturServer.getConsole().sendMessage("        MineturAPI        ");
		MineturServer.getConsole().sendMessage("    MiniGame: " + MineturGameServer.getMiniGame());
		MineturServer.getConsole().sendMessage("    Port    : " + MineturServer.getPort());
		MineturServer.getConsole().sendMessage("                          ");
		MineturServer.getConsole().sendMessage("    CoinSystem:" + MineturServer.isCoinSystemEnabled());
	}

	private void initializeVariables() {
		instance = this;
		MineturGameServer.setGameState(GameState.LOBBY);
		MineturGameServer.setMiniGame(MiniGame.KnockbackFFA);
		Data.version = getDescription().getVersion();

	}

	private void registerCommands() {
		getCommand("sudo").setExecutor(new SudoCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("gamestate").setExecutor(new GameStateCommand());

	}

	private void registerListeners() {
		// getServer().getPluginManager().registerEvents(new
		// PlayerJoinListener(), this);

	}

	public void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdir();
			}
			File file = new File(getDataFolder().getPath(), "config.yml");
			if (!file.exists()) {
				file.createNewFile();
				YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
				config.save(file);
			}
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
	}

	public void onDisable() {
		Bukkit.getConsoleSender()
				.sendMessage("[MineturAPI] Version " + getDescription().getVersion() + "-spigot deaktiviert.");
	}

}
