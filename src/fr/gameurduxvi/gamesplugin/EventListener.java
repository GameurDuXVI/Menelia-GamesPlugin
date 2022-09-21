package fr.gameurduxvi.gamesplugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.gameurduxvi.gamesplugin.database.InGame;
import fr.gameurduxvi.meneliaapi.MeneliaAPI;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EventListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		Bukkit.getScheduler().runTaskLater(Main.INSTANCE, new Runnable() {
			
			@Override
			public void run() {
				MeneliaAPI.getInstance().SendPluginMessage(event.getPlayer(), Bukkit.getServerName(), "MeneliaChannel", "RequestJoinAction", false, null);			
			}
		}, 10);
		
	}
	
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent event) {
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		boolean isInAGame = false;
		
		for(InGame data: Main.INSTANCE.inGame) {
			if(data.getPlayer().equals(event.getPlayer())) {
				isInAGame = true;
			}
		}
		if(!isInAGame) {
			ArrayList<Player> otherPlayers = new ArrayList<>();
			for(Player loopPlayer: Bukkit.getOnlinePlayers()) {
				otherPlayers.add(loopPlayer);
			}
			for(InGame data: Main.INSTANCE.inGame) {
				otherPlayers.remove(data.getPlayer());
			}
			
			Player player = event.getPlayer();
			
			String group = MeneliaAPI.getInstance().getGroup(player);
			String prefix = PermissionsEx.getPermissionManager().getGroup(group).getPrefix();
			String colorname = PermissionsEx.getPermissionManager().getGroup(group).getOption("colorname");
			
			//event.setFormat(MeneliaAPI.getInstance().colorize(prefix + " &" + colorname + player.getDisplayName() + " &7>> " + event.getMessage()));
			for(Player loopPlayer: otherPlayers) {
				loopPlayer.sendMessage(MeneliaAPI.getInstance().colorize(prefix + " &" + colorname + player.getDisplayName() + " &7>> " + event.getMessage()));
			}
		}
		event.setCancelled(true);
	}
}
