package fr.gameurduxvi.gamesplugin.database;

import org.bukkit.entity.Player;

public class InGame {
	private Player player;
	private String game;
	
	public InGame(Player player, String game) {
		this.player = player;
		this.game = game;
	}
	
	public String getGame() {
		return game;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setGame(String game) {
		this.game = game;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}
