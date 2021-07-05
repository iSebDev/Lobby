package src.plugin;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
 
public class main extends JavaPlugin{
 
    private FileConfiguration messages = null;
    private File messagesFile = null;
    FileConfiguration messgaesFileReal = getMessages();
 
    PluginDescriptionFile pdffile = getDescription();
    public String name = pdffile.getName();
    public String version = pdffile.getVersion();
        String prefixPath = messgaesFileReal.getString("messages.prefix");
        public String prefix = ChatColor.translateAlternateColorCodes('&', prefixPath);
 
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(prefix+ChatColor.GREEN+" Plugin running in Version: "+ChatColor.AQUA+version);
        yamlRegister();
    }
 
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
 
    public void yamlRegister() {
        messagesFile = new File(this.getDataFolder(),"messages.yml");
        if(!messagesFile.exists()) {
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
 
 
    }
    //
}
