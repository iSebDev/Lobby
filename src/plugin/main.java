package src.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

    private FileConfiguration messages = null;
    private File messagesFile = null;

    PluginDescriptionFile pdffile = getDescription();
    public String name = pdffile.getName();
    public String version = pdffile.getVersion();
    public String prefix = ChatColor.RED+"[Lobby]";
	
    public void onEnable() {
		registerMessages();
        Bukkit.getConsoleSender().sendMessage(prefix+ChatColor.GREEN+" Plugin running in Version: "+ChatColor.AQUA+version);
    }

    //File Messages.yml
	public FileConfiguration getMessages() {
		if(messages == null) {
			reloadMessages();
		}
		return messages;	
	}
	
	public void reloadMessages() {
		if(messages == null) {
			messagesFile = new File(getDataFolder(),"messages.yml");
		}
		messages = YamlConfiguration.loadConfiguration(messagesFile);
		Reader defConfigStream;
		try {
			
			defConfigStream = new InputStreamReader(this.getResource("messages.yml"),"UTF8");
			if(defConfigStream != null) {
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				messages.setDefaults(defConfig);
			}
		}catch(UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	}
	
	public void saveMessages() {
		try {
			messages.save(messagesFile);
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void registerMessages() {
		messagesFile = new File(this.getDataFolder(),"messages.yml");
		if(!messagesFile.exists()) {
			this.getMessages().options().copyDefaults(true);
			saveMessages();
		}
		
		
	}
    //
}
