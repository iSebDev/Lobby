package src.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	PluginDescriptionFile pdffile = getDescription();
	public String name = pdffile.getName();
	public String version = pdffile.getVersion();
	public String prefix = ChatColor.RED+"[Lobby]";
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(prefix+ChatColor.GREEN+" Plugin running in Version: "+ChatColor.AQUA+version);
	}
}
