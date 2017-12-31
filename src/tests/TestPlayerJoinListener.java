package tests;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.minetur.mineturapi.api.MineturPlayer;
import de.minetur.mineturapi.api.MineturRank;
import de.minetur.mineturapi.api.MineturServer;

public class TestPlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		MineturPlayer mp = MineturServer.getPlayer(e.getPlayer().getName());
		mp.sendMessage("§7Deine Coins: §a" + mp.getCoins());
		mp.sendMessage("§7Dqein Rang: §a" + mp.getRank());
		mp.sendMessage("§7Deine UUID: §a" + mp.getUUID());
		mp.setRank(MineturRank.Ultra);

	}

}
