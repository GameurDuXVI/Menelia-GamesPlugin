package fr.gameurduxvi.gamesplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gameurduxvi.meneliaapi.MeneliaAPI;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("hub")) {
				MeneliaAPI.getInstance().sendToServer(player, "hub");
			}
		}
		return false;
	}
}
