package tk.shanebee.skriptpaper;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SkriptPaper extends JavaPlugin {

    SkriptPaper instance;
    SkriptAddon addon;
    private String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + this.getDescription().getName() + ChatColor.GRAY +  "] ";

    @Override
    public void onEnable() {
        if ((Bukkit.getPluginManager().getPlugin("Skript") != null) && (Skript.isAcceptRegistrations())) {
            if (isRunningPaper()) {
                instance = this;
                addon = Skript.registerAddon(this);
                try {
                    addon.loadClasses("tk.shanebee.skriptpaper", "elements");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendConsole(ChatColor.GREEN + "Successfully enabled");
                if (getDescription().getVersion().contains("Beta")) {
                    sendConsole(ChatColor.YELLOW + "This is a Beta build and may be unstable");
                }
            } else {
                sendError("****************************************");
                sendError("*                                      *");
                sendError("*" + ChatColor.GOLD + "    You must run Paper server for     " + ChatColor.RED + "*");
                sendError("*" + ChatColor.GOLD + "        Skript-Paper to work          " + ChatColor.RED + "*");
                sendError("*                                      *");
                sendError("*" + ChatColor.GOLD + "    More info can be found here:      " + ChatColor.RED + "*");
                sendError("*" + ChatColor.GOLD + "        https://papermc.io            " + ChatColor.RED + "*");
                sendError("*                                      *");
                sendError("****************************************");
                Bukkit.getPluginManager().disablePlugin(this);
                sendError("Disabled");
            }
        } else {
            sendError("Dependency Skript was not found");
            Bukkit.getPluginManager().disablePlugin(this);
            sendError("Disabled");
        }
    }

    private boolean isRunningPaper() {
        try {
            Class.forName("co.aikar.timings.Timing");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    private void sendConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage(prefix + msg);
    }

    private void sendError(String msg) {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + msg);
    }

}
