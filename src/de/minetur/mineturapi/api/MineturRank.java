package de.minetur.mineturapi.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public enum MineturRank {
	Operator(1, MineturServer.getGroup("Operator"), "§4§lOperator", ChatColor.DARK_RED, "OP"), 
	Owner(getRanking("Owner"), MineturServer.getGroup("Owner"), "§4§lOwner", ChatColor.DARK_RED, "Owner"), 
	Administrator(getRanking("Administrator"), MineturServer.getGroup("Administrator"), "§4§lAdministrator", ChatColor.DARK_RED, "Admin"),
	Developer(getRanking("Developer"),MineturServer.getGroup("Developer"), "§8§lDeveloper", ChatColor.DARK_GRAY, "Dev"),
	Builder(getRanking("Builder"), MineturServer.getGroup("Builder"), "§9§lBuilder", ChatColor.BLUE, "Builder"),
	Moderator(getRanking("Moderator"), MineturServer.getGroup("Moderator"), "§e§lModerator", ChatColor.YELLOW, "Mod"), 
	Azubi(getRanking("Azubi"), MineturServer.getGroup("Azubi"), "§e§lAzubi-Moderator", ChatColor.YELLOW, "Azubi"),
	Supporter(getRanking("Supporter"), MineturServer.getGroup("Supporter"), "§b§lSupporter", ChatColor.AQUA, "Sup"),
	VIP(getRanking("VIP"), MineturServer.getGroup("VIP"), "§2§lVIP", ChatColor.DARK_GREEN, "VIP"), 
	YouTuber(getRanking("YouTuber"), MineturServer.getGroup("YouTuber"), "§5§lYouTuber", ChatColor.DARK_PURPLE, "YT"),
	Ultra(getRanking("Ultra"), MineturServer.getGroup("Ultra"), "§1§lUltra", ChatColor.DARK_BLUE, "Ultra"),
	Premium(getRanking("Premium"), MineturServer.getGroup("Premium"), "§6§lPermium", ChatColor.GOLD, "Premium"),
	Spieler(getRanking("Spieler"), MineturServer.getGroup("Spieler"), "§7§lSpieler", ChatColor.GRAY, "Spieler");
	
	public static String getSuffix(MineturRank rank){
		return " §8| " + rank.getColor();
	}
	
	private int ranking;
	private PermissionGroup group;
	private String prefix;
	private ChatColor color;
	private String abbreviation;

	MineturRank(int _ranking, PermissionGroup _group, String _prefix, ChatColor _color, String _abbreviation) {
		this.ranking = _ranking;
		this.group = _group;
	}

	public int getRanking() {
		return ranking;
	}

	public PermissionGroup toPermissionGroup() {
		return group;
	}

	public String getName() {
		return toString();
	}
	
	public String getPrefix(){
		return prefix;
	}
	
	public ChatColor getColor(){
		return color;
	}
	
	public String getAbbreviation(){
		return abbreviation;
	}

	@SuppressWarnings("deprecation")
	public static MineturRank getGroupName(Player player) {
		List<String> groups = new ArrayList<String>();
		for (String group : PermissionsEx.getUser(player).getGroupsNames()) {
			groups.add(group);
			MineturServer.getConsoleSender().sendMessage(group);
		}
		if (groups.contains("Owner")) {
			return MineturRank.Owner;
		} else if (groups.contains("Adminisrator")) {
			return MineturRank.Administrator;
		} else if (groups.contains("Developer")) {
			return MineturRank.Developer;
		} else if (groups.contains("Builder")) {
			return MineturRank.Builder;
		} else if (groups.contains("Moderator")) {
			return MineturRank.Moderator;
		} else if (groups.contains("Azubi")) {
			return MineturRank.Azubi;
		} else if (groups.contains("Supporter")) {
			return MineturRank.Supporter;
		} else if (groups.contains("VIP")) {
			return MineturRank.VIP;
		} else if (groups.contains("YouTuber")) {
			return MineturRank.YouTuber;
		} else if (groups.contains("Ultra")) {
			return MineturRank.Ultra;
		} else if (groups.contains("Premium")) {
			return MineturRank.Premium;
		} else if (groups.contains("Spieler")) {
			return MineturRank.Spieler;
		} else {
			return MineturRank.Spieler;
		}
	}
	
	private static int getRanking(String rank) {
		return MineturServer.getGroup(rank).getRank();
	}
}
