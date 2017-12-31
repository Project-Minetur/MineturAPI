package de.minetur.mineturapi.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import de.mcmalte.coinsystem.api.CoinsAPI;
import de.mcmalte.coinsystem.profile.UUIDFetcher;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MineturPlayer extends CraftPlayer {
	private static final HashMap<String, MineturPlayer> PLAYERS = new HashMap<>();

	private Player PLAYER;

	private MineturPlayer(Player player) {
		super((CraftServer) player.getServer(), ((CraftPlayer) player).getHandle());
		PLAYERS.put(player.getName().toLowerCase(), this);
		PLAYER = player;
	}

	@Override
	public boolean hasPermission(String perm) {
		return PLAYER.hasPermission(perm);
	}

	@Override
	public boolean hasPermission(Permission perm) {
		return PLAYER.hasPermission(perm);
	}

	/**
	 * Do not call this!
	 */
	public void onLeave() {
		if (PLAYERS.containsKey(getName().toLowerCase())) {
			PLAYERS.remove(getName().toLowerCase());
		}
	}

	/**
	 * Use MineturServer.getPlayer() instead.
	 */
	public static MineturPlayer getPlayer(String name) {
		if (PLAYERS.containsKey(name.toLowerCase())) {
			return PLAYERS.get(name.toLowerCase());
		} else {
			Player p = Bukkit.getPlayer(name);
			return p == null ? null : new MineturPlayer(p);
		}
	}

	public UUID getUUID() {
		return UUIDFetcher.getUUID(PLAYER.getName());
	}
	
	/**
	 * @deprecated use getUUID() instead.  
	 */
	@Deprecated
	public UUID getUniqueId(){
		return getUUID();
		
	}

	public Long getCoins() {
		return CoinsAPI.getCoins(getUUID().toString());
	}

	public boolean hasCoins(Long coins) {
		return getCoins() >= coins;
	}

	public void resetCoins(){
		  CoinsAPI.resetCoins(getUUID().toString());
	  }

	public void setCoins(Long coins) {
		CoinsAPI.setCoins(getUUID().toString(), coins);
	}

	public void addCoins(Long coins) {
		CoinsAPI.addCoins(getUUID().toString(), coins);
	}

	public void removeCoins(Long coins) {
		CoinsAPI.removeCoins(getUUID().toString(), coins);
	}

	public void tpToPlayer(MineturPlayer mp) {
		PLAYER.teleport(mp);
	}

	public void tpPlayer(MineturPlayer mp) {
		mp.teleport(PLAYER);
	}

	public void sendPercentageBar(Integer percent) {
		if ((Integer.valueOf(percent) > 100) || (Integer.valueOf(percent) < 0)) {
			throw new ArrayIndexOutOfBoundsException("The percentage-value must be between 0 and 100.");
		}
		String strich = "|";
		Integer rest = 100 - percent;
		String msg = "§a§l" + StringUtils.repeat(strich, percent / 2) + "§c§l" + StringUtils.repeat(strich, rest / 2);
		sendActionBar(msg);
	}

	public void sendPercentageLevelBar(Integer percent) {
		if ((Integer.valueOf(percent) > 100) || (Integer.valueOf(percent) < 0)) {
			throw new ArrayIndexOutOfBoundsException("The percentage-value must be between 0 and 100.");
		}
		Integer rest = 100 - percent;
		String msg = "§a§l" + percent + "%                 §c§l" + rest + "%";
		sendActionBar(msg);
	}

	public void sendActionBar(String msg) {
		IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) PLAYER).getHandle().playerConnection.sendPacket(ppoc);
	}

	public void sendTitle(Integer fadeIn, Integer stay, Integer fadeOut, String t, String st) {
		PlayerConnection connection = ((CraftPlayer) PLAYER).getHandle().playerConnection;
		PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null,
				fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
		connection.sendPacket(packetPlayOutTimes);
		if (st != null) {
			st = st.replaceAll("%player%", PLAYER.getName());
			st = ChatColor.translateAlternateColorCodes('&', st);
			IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + st + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(
					PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		}
		if (t != null) {
			t = t.replaceAll("%player%", PLAYER.getName());
			t = ChatColor.translateAlternateColorCodes('&', t);
			IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + t + "\"}");
			PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
					titleMain);
			connection.sendPacket(packetPlayOutTitle);
		}
	}
	
	public void setRank(MineturRank rank){
        PermissionsEx.getUser(PLAYER).addGroup(rank.toPermissionGroup());
	}
	
	public boolean hasRank(MineturRank rank){	
		return PermissionsEx.getPermissionManager().getUser(PLAYER).inGroup(rank.toPermissionGroup());		
	}
	
	@SuppressWarnings("deprecation")
	public List<MineturRank> getGroups(){
		List<MineturRank> groups = new ArrayList<MineturRank>();
		for(PermissionGroup group: PermissionsEx.getPermissionManager().getUser(PLAYER).getGroups()){
			groups.add(MineturServer.getRank(group));			
		}
		return groups;
	}
	
	public boolean isTeamMember(){
		return (hasRank(MineturRank.Owner) || (hasRank(MineturRank.Administrator) || (hasRank(MineturRank.Developer) || (hasRank(MineturRank.Builder) || (hasRank(MineturRank.Moderator) || (hasRank(MineturRank.Azubi) || hasRank(MineturRank.Supporter)))))));
	}
	
	public boolean isPremium(){
		return hasRank(MineturRank.Premium);
	}
	
	public boolean isUltra(){
		return hasRank(MineturRank.Ultra);
	}
	
	public boolean isVIP(){
		return hasRank(MineturRank.VIP);
	}
	
	public boolean isYouTuber(){
		return hasRank(MineturRank.YouTuber);
	}
	
	/**
	 * returns the highest Rank of the player  
	 */
	public MineturRank getRank() {
		for (MineturRank rank : getGroups()) {
			if (rank == MineturRank.Owner) {
				return MineturRank.Owner;
			} else if (rank == MineturRank.Administrator) {
				return MineturRank.Administrator;
			} else if (rank == MineturRank.Developer) {
				return MineturRank.Developer;
			} else if (rank == MineturRank.Builder) {
				return MineturRank.Builder;
			}else if(rank == MineturRank.Moderator){
				return MineturRank.Moderator;
			}else if(rank == MineturRank.Azubi){
				return MineturRank.Azubi;
			}else if(rank == MineturRank.Supporter){
				return MineturRank.Supporter;
			}else if(rank == MineturRank.VIP){
				return MineturRank.VIP;
			}else if(rank == MineturRank.YouTuber){
				return MineturRank.YouTuber;
			}else if(rank == MineturRank.Ultra){
				return MineturRank.Ultra;
			}else if(rank == MineturRank.Premium){
				return MineturRank.Premium;
			}else if(rank == MineturRank.Spieler){
				return MineturRank.Spieler;
			}
		}
		return MineturRank.Spieler;
	}

}