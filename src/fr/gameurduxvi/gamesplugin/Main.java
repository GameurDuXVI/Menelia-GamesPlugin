package fr.gameurduxvi.gamesplugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gameurduxvi.gamesplugin.database.InGame;
import fr.gameurduxvi.meneliaapi.MeneliaAPI;

public class Main extends JavaPlugin {
	
	public static Main INSTANCE;
	public String pluginPrefix = "§6[GamesPlugin]";
	
	public ArrayList<InGame> inGame = new ArrayList<>();
	
	@Override
	public void onEnable() {
		// Plugin loading
		Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize(pluginPrefix + "========================================================="));
		Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize(pluginPrefix + " Enabling GamesPlugin"));
		Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize(pluginPrefix + "========================================================="));
		
		INSTANCE = this;
		
		// Event listener
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		
		getCommand("hub").setExecutor(new CommandListener());
		
		// Channel loader
		getServer().getMessenger().registerOutgoingPluginChannel(this, "MeneliaChannel");
		getServer().getMessenger().registerIncomingPluginChannel(this, "MeneliaChannel", new PluginCustomMessageListener());	
		
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player loopPlayer: Bukkit.getOnlinePlayers()) {
					MeneliaAPI.getInstance().SendPluginMessage(loopPlayer, Bukkit.getServerName(), "MeneliaChannel", "GetProfile", false, null);
				}
			}
		}, 100);
	}		
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(MeneliaAPI.getInstance().colorize(pluginPrefix + " Disabling GamesPlugin"));
	}
	

	
	public void addInGame(Player player, String game) {
		inGame.add(new InGame(player, game));
	}
	
	public void removeInGame(Player player) {
		int i = 0;
		for(InGame data: inGame) {
			if(player.equals(data.getPlayer())) {
				inGame.remove(inGame.get(i));
				break;
			}
			i++;
		}
	}
}
