package de.minetur.mineturapi.api;

import de.minetur.mineturapi.enums.GameState;
import de.minetur.mineturapi.enums.MiniGame;

public class MineturGameServer {
	
    private static GameState gamestate = GameState.LOBBY;
    private static MiniGame minigame;
    
	public static boolean isGameServer(){
		return true;		
	}
	
	public static void setGameState(GameState state) {
		if (state == GameState.FULL) {
			gamestate = state;
			MineturServer.setMotd("§eFULL");
		} else if (state == GameState.LOBBY) {
			MineturServer.setMotd("§aLOBBY");
			gamestate = state;
		} else if (state == GameState.INGAME) {
			MineturServer.setMotd("§eINGAME");
			gamestate = state;
		} else if (state == GameState.RESTARTING) {
			MineturServer.setMotd("§cRESTARTING");
			gamestate = state;
		}
	}	

	public static void setMiniGame(MiniGame game) {
		minigame = game;		
	}
	
	public static MiniGame getMiniGame(){
		return minigame;
	}

}
