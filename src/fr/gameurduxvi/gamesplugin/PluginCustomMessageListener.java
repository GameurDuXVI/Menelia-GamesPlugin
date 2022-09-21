package fr.gameurduxvi.gamesplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class PluginCustomMessageListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
		if(channel.equals("MeneliaChannel")) {
			ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
			
			String server = in.readUTF();
			String sub = in.readUTF();
			
			//Bukkit.broadcastMessage(server + "/" + Bukkit.getServerName());
			if(server.equals(Bukkit.getServerName())) {
				if(sub.equals("SendToGame")) {
					String receivedPlayer = in.readUTF();
					String game = in.readUTF();
					String gameNumber = in.readUTF();
					for(Player loopPlayer: Bukkit.getOnlinePlayers()) {
						if(loopPlayer.getName().equals(receivedPlayer)) {
							loopPlayer.performCommand(game + " join " + gameNumber);
						}
					}
					//Bukkit.broadcastMessage("GameManager: " + player.getName() + " " + game + " " + gameNumber);
				}
				
				
				
				
			}
		}
	}
}
